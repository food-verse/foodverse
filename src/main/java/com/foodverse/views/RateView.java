package com.foodverse.views;

import java.awt.Component;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import com.foodverse.utility.core.Widget;
import java.awt.Font;


public class RateView {
    public final class AddressView extends Widget {

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
            var rate1 = new JRadioButton("1 ");
            var rate2 = new JRadioButton("2 ");
            var rate3 = new JRadioButton("3 ");
            var rate4 = new JRadioButton("4 ");
            var rate5 = new JRadioButton("5 ");

            var group = new ButtonGroup();
            group.add(rate1);
            group.add(rate2);
            group.add(rate3);
            group.add(rate4);
            group.add(rate5);

            // adding rate elements panel3
            var panel = new JPanel();

            panel.add(rateNumberLabel);
            panel.add(wordRate);
            panel.add(rate1);
            panel.add(rate2);
            panel.add(rate3);
            panel.add(rate4);
            panel.add(rate5);

            RadioButtonListener listener =
                    new RadioButtonListener(rate1, rate2, rate3, rate4, rate5);
            rate1.addActionListener(listener);
            rate2.addActionListener(listener);
            rate3.addActionListener(listener);
            rate4.addActionListener(listener);
            rate5.addActionListener(listener);

            return panel;
        }
    }
}
