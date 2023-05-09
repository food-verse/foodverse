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

    private PillButton rate1, rate2, rate3, rate4, rate5;

    public RateView(Optional<Shop> shop) {

        var panel = new JPanel();

        // label with the word Rate
        var wordRate = new JLabel("Rate ");
        wordRate.setFont(new Font("Courier New", Font.BOLD, 25));

        // radioButtons with the ratings 1-5
        // var numberOfRates = shop.get().reviews();
        var oldRate = shop.get().rating();

        rate1 = new PillButton("1", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            var numberOfRates = shop.get().reviews();
            numberOfRates++;
            double newRate = actionPerformed(e, oldRate, numberOfRates);
            // System.out.println("kkkkk " + newRate);

            DecimalFormat df = new DecimalFormat("#0.00");
            String oneDigitRate = df.format(newRate);
            // System.out.println("hiiii" + oneDigitRate);
            var rateNumberLabel = new JLabel((oneDigitRate) + " ");
            rateNumberLabel.setFont(new Font("Courier New", Font.BOLD, 24));
        });
        rate2 = new PillButton("2", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            var numberOfRates = shop.get().reviews();
            numberOfRates++;
            double newRate = actionPerformed(e, oldRate, numberOfRates);
        });
        rate3 = new PillButton("3", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            var numberOfRates = shop.get().reviews();
            numberOfRates++;
            double newRate = actionPerformed(e, oldRate, numberOfRates);
        });
        rate4 = new PillButton("4", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            var numberOfRates = shop.get().reviews();
            numberOfRates++;
            double newRate = actionPerformed(e, oldRate, numberOfRates);
        });
        rate5 = new PillButton("5", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            var numberOfRates = shop.get().reviews();
            numberOfRates++;
            double newRate = actionPerformed(e, oldRate, numberOfRates);
        });

        // store's rate
        // DecimalFormat df = new DecimalFormat("#.#");
        // String oneDigitRate = df.format(oldRate);
        // var rateNumberLabel = new JLabel((oneDigitRate) + " ");
        // rateNumberLabel.setFont(new Font("Courier New", Font.BOLD, 24));

        // adding rate elements panel
        panel.setPreferredSize(new Dimension(500, 100));
        panel.setBackground(Color.white);

        // panel.add(rateNumberLabel);
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

    public double actionPerformed(ActionEvent e, double oldRate, Integer numberOfRates) {

        double newRate = 0;

        if (e.getSource() == rate1.getRef()) {

            // System.out.println("gggggggggggggggggg");

            newRate = calculateNewRate(1, oldRate, numberOfRates);
        } else if (e.getSource() == rate2.getRef()) {
            newRate = calculateNewRate(2, oldRate, numberOfRates);
        } else if (e.getSource() == rate3.getRef()) {
            newRate = calculateNewRate(3, oldRate, numberOfRates);
        } else if (e.getSource() == rate4.getRef()) {
            newRate = calculateNewRate(4, oldRate, numberOfRates);
        } else if (e.getSource() == rate5.getRef()) {
            newRate = calculateNewRate(5, oldRate, numberOfRates);
        }
        return newRate;
    }

    // Calculate New Rate

    private double calculateNewRate(Integer rateValue, double oldRate,
            Integer numberOfRates) {

        double newRate = 0;
        if (numberOfRates != 0) {
            newRate = (oldRate * (numberOfRates - 1) + rateValue) / numberOfRates;

        } else {
            newRate = oldRate;
        }
        System.out.println(newRate);

        return newRate;

    }

}
