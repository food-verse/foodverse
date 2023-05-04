package com.foodverse.views;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;
import com.foodverse.widgets.button.PillButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;


public final class RateView extends Widget {

    @Override
    public Component getRef() {

        // store's rate
        double rateNumber = 5.5;
        var rateNumberLabel = new JLabel(Double.toString(rateNumber) + " ");
        rateNumberLabel.setFont(new Font("Courier New", Font.BOLD, 24));

        // label with the word Rate
        var wordRate = new JLabel("Rate ");
        wordRate.setFont(new Font("Courier New", Font.BOLD, 25));

        // radioButtons with the ratings 1-5
        var numberOfRates = 0;
        var oldRate = 0;
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



        // adding rate elements panel3
        var panel = new JPanel();

        panel.add(rateNumberLabel);
        panel.add(wordRate);
        panel.add(rate1.getRef());
        panel.add(rate2.getRef());
        panel.add(rate3.getRef());
        panel.add(rate4.getRef());
        panel.add(rate5.getRef());


        return panel;

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
        if (numberOfRates != 0) {
            newRate = oldRate + (rateValue / numberOfRates);
        } else {
            newRate = oldRate;
        }

        return newRate;

    }


}

