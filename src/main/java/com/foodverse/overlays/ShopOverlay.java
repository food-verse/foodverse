package com.foodverse.overlays;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.Overlay;
import com.foodverse.utility.Router;
import com.foodverse.utility.core.Button.ButtonSize;
import com.foodverse.utility.core.Button.ButtonType;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.Graphics;
import javax.swing.*;

public class ShopOverlay extends Overlay {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        var text = new Heading("ShopOverlay", HeadingSize.L);
        var button = new PillButton("Close ShopOverlay ->", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            Router.closeOverlay();
        });
        panel.add(text.getRef());
        panel.add(button.getRef());
        panel.setOpaque(false);

        // panel creation
        var panel1 = new JPanel();
        var panel2 = new JPanel();
        var panel3 = new JPanel();

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

        // creation, size, colour of offers and menu
        var offers = new JLabel("Offers");
        var menu = new JLabel("Menu  ");

        offers.setFont(new Font("Courier New", Font.BOLD, 25));
        menu.setFont(new Font("Courier New", Font.BOLD, 25));
        offers.setForeground(Color.white);
        menu.setForeground(Color.white);

        // adding elements to the panels
        panel1.setPreferredSize(new Dimension(1300, 300));
        panel1.setBackground(Color.lightGray);
        panel1.add(offers);

        panel2.setPreferredSize(new Dimension(1300, 300));
        panel2.setBackground(Color.lightGray);
        panel2.add(menu);

        panel3.add(rateNumberLabel);
        panel3.add(wordRate);
        panel3.add(rate1);
        panel3.add(rate2);
        panel3.add(rate3);
        panel3.add(rate4);
        panel3.add(rate5);

        this.showItems(panel1);
        this.showItems(panel2);

        // adding scrolling bars at panel1 and panel2
        JScrollPane scroll1 = new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll1);

        JScrollPane scroll2 = new JScrollPane(panel2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll2);

        // RectButton "Bug" to appear the OrderOverlay
        var bugButton = new RectButton("Bag", ButtonSize.S, ButtonType.PRIMARY, e -> {
            showSuccessfulOrderMessage(e);
        });

        panel.add(bugButton.getRef());

        // panel.setOpaque(false);

        panel.add(panel3);

        RadioButtonListener listener = new RadioButtonListener(rate1, rate2, rate3,
                rate4, rate5);
        rate1.addActionListener(listener);
        rate2.addActionListener(listener);
        rate3.addActionListener(listener);
        rate4.addActionListener(listener);
        rate5.addActionListener(listener);

        return panel;
    }

    public void showItems(JPanel panel1) {

        for (var i = 0; i < 3; i++) {
            var productAddButton = new PillButton("Add", ButtonSize.XS, ButtonType.SECONDARY, e -> {
                // addproducts();

            });
            var panel4 = new JPanel();
            var menuItem = new JLabel("Item");

            panel4.setPreferredSize(new Dimension(1200, 60));
            panel4.add(menuItem);
            panel4.add(productAddButton.getRef());
            panel1.add(panel4);
        }

    }

    private void showSuccessfulOrderMessage(ActionEvent e) {
        JOptionPane.showMessageDialog(getFrame(), "We will deal with this later");
    }

    // public void paint(Graphics g)
    // {
    // super.paint(g);
    // g.setColor(Color.white);
    // g.drawLine(2000, 222, 0, 222);
    // g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
    // }

    class RadioButtonListener implements ActionListener {

        private JRadioButton rate1;
        private JRadioButton rate2;
        private JRadioButton rate3;
        private JRadioButton rate4;
        private JRadioButton rate5;

        public RadioButtonListener(JRadioButton rate1, JRadioButton rate2,
                JRadioButton rate3,
                JRadioButton rate4, JRadioButton rate5) {
            this.rate1 = rate1;
            this.rate2 = rate2;
            this.rate3 = rate3;
            this.rate4 = rate4;
            this.rate5 = rate5;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            double newrate = 0, oldrate = 0;
            Integer numberofrates = 0;

            if (e.getSource() == rate1) {
                numberofrates++;
                System.out.println(rate1.getText());
                calculateNewRate(1, newrate, oldrate, numberofrates);
            } else if (e.getSource() == rate2) {
                System.out.println(rate2.getText());
            } else if (e.getSource() == rate3) {
                System.out.println(rate3.getText());
            } else if (e.getSource() == rate4) {
                System.out.println(rate4.getText());
            } else if (e.getSource() == rate5) {
                System.out.println(rate5.getText());
            }

        }

        private double calculateNewRate(Integer rateValue, double newrate, double oldrate, Integer numberofrates) {

            newrate = oldrate + (newrate / numberofrates);

            return newrate;

        }

    }

    // Choose one button for rate

    // public void actionPerformed(ActionEvent e, Integer rateValue, double newrate,
    // double oldrate,
    // Integer numberofrates) {

    // numberofrates++;

    // if (e.getSource().equals("rate1")) {

    // calculateNewRate(1, newrate, oldrate, numberofrates);
    // } else if (e.getSource().equals("rate2")) {
    // calculateNewRate(1, newrate, oldrate, numberofrates);
    // } else if (e.getSource().equals("rate3")) {
    // calculateNewRate(1, newrate, oldrate, numberofrates);
    // } else if (e.getSource().equals("rate4")) {
    // calculateNewRate(1, newrate, oldrate, numberofrates);
    // } else if (e.getSource().equals("rate5")) {

    // }

    // }

    // // Culculate New Rate

    // private double calculateNewRate(Integer rateValue, double newrate, double
    // oldrate, Integer numberofrates) {

    // newrate = oldrate + (newrate / numberofrates);

    // return newrate;

    // }

    // // Add products in the cart

    // private void addproducts() {

    // }
}
