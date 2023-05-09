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
        var oldRate = shop.get().rating();
        var oldRateAsAString = Float.toString(oldRate);
        var rateNumberLabel = new JLabel(oldRateAsAString);

        rateNumberLabel.setText(oldRate + " ");
        rateNumberLabel.setFont(new Font("Courier New", Font.BOLD, 24));

        var numberOfRates = shop.get().reviews();

        // create PillButtons rate1 - rate5
        rate1 = new PillButton("1", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            example(numberOfRates, e, oldRate, rateNumberLabel);
        });
        rate2 = new PillButton("2", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            example(numberOfRates, e, oldRate, rateNumberLabel);
        });
        rate3 = new PillButton("3", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            example(numberOfRates, e, oldRate, rateNumberLabel);
        });
        rate4 = new PillButton("4", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            example(numberOfRates, e, oldRate, rateNumberLabel);
        });
        rate5 = new PillButton("5", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            example(numberOfRates, e, oldRate, rateNumberLabel);
        });

        // set size and color for the panel
        panel.setPreferredSize(new Dimension(500, 100));
        panel.setBackground(Color.white);

        // adding rate elements to panel
        panel.add(wordRate);
        panel.add(rateNumberLabel);
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

    private void example(Integer numberOfRates, ActionEvent e, float oldRate, JLabel rateNumberLabel) {

        numberOfRates++;
        double newRate = actionPerformed(e, oldRate, numberOfRates);

        DecimalFormat df = new DecimalFormat("#0.00");
        String oneDigitRate = df.format(newRate);
        rateNumberLabel.setText(oneDigitRate + " ");
        rateNumberLabel.setFont(new Font("Courier New", Font.BOLD, 24));
    }

    // call calculateNewRate for each PillButton (rate1 - rate5)
    public double actionPerformed(ActionEvent e, double oldRate, Integer numberOfRates) {

        double newRate = 0;

        if (e.getSource() == rate1.getRef()) {
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

    // calculate new rate
    private double calculateNewRate(Integer rateValue, double oldRate, Integer numberOfRates) {

        double newRate = 0;

        newRate = (oldRate * (numberOfRates - 1) + rateValue) / numberOfRates;

        return newRate;
    }
}
