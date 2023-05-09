package com.foodverse.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.foodverse.models.Shop;
import com.foodverse.utility.core.Widget;
import com.foodverse.widgets.button.PillButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.Optional;

import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;

public final class RateView extends Widget {

    private final Component component;

    public RateView(Optional<Shop> shop) {

        // label with the word Rate
        var wordRate = new JLabel("Rate ");
        wordRate.setFont(new Font("Courier New", Font.BOLD, 25));

        // radioButtons with the ratings 1-5
        var numberOfRates = shop.get().reviews();
        var oldRate = shop.get().rating();
        var newRate = 0;

        var rate1 = new PillButton("1", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            actionPerformed(e, newRate, oldRate, numberOfRates);
        });
        var rate2 = new PillButton("2", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            actionPerformed(e, newRate, oldRate, numberOfRates);
        });
        var rate3 = new PillButton("3", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            actionPerformed(e, newRate, oldRate, numberOfRates);
        });
        var rate4 = new PillButton("4", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            actionPerformed(e, newRate, oldRate, numberOfRates);
        });
        var rate5 = new PillButton("5", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            actionPerformed(e, newRate, oldRate, numberOfRates);
        });

        // store's rate
        DecimalFormat df = new DecimalFormat("#.#");
        String oneDigitRate = df.format(oldRate);
        var rateNumberLabel = new JLabel((oneDigitRate) + " ");
        rateNumberLabel.setFont(new Font("Courier New", Font.BOLD, 24));

        // adding rate elements panel3
        var panel = new JPanel();

        panel.setPreferredSize(new Dimension(500, 100));
        panel.setBackground(Color.white);

        panel.add(rateNumberLabel);
        panel.add(wordRate);
        panel.add(rate1.getRef());
        panel.add(rate2.getRef());
        panel.add(rate3.getRef());
        panel.add(rate4.getRef());
        panel.add(rate5.getRef());

        component = panel;
    }

    @Override
    public Component getRef() {
        return component;

    }

    // Choose one button for rate

    public void actionPerformed(ActionEvent e, double newRate, double oldRate,
            Integer numberOfRates) {

        numberOfRates++;

        if (e.getSource().equals("rate1")) {
            calculateNewRate(1, newRate, oldRate, numberOfRates);
        } else if (e.getSource().equals("rate2")) {
            calculateNewRate(2, newRate, oldRate, numberOfRates);
        } else if (e.getSource().equals("rate3")) {
            calculateNewRate(3, newRate, oldRate, numberOfRates);
        } else if (e.getSource().equals("rate4")) {
            calculateNewRate(4, newRate, oldRate, numberOfRates);
        } else if (e.getSource().equals("rate5")) {
            calculateNewRate(5, newRate, oldRate, numberOfRates);
        }

    }

    // Calculate New Rate

    private double calculateNewRate(Integer rateValue, double newRate, double oldRate,
            Integer numberOfRates) {
        System.out.println("hiiiiiiiiiii");
        if (numberOfRates != 0) {
            newRate = oldRate + (rateValue / numberOfRates);

        } else {
            newRate = oldRate;
        }
        System.out.println(newRate);

        return newRate;

    }

}
