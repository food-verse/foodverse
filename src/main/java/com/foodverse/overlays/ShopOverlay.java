package com.foodverse.overlays;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.Overlay;
import com.foodverse.utility.Router;
import com.foodverse.utility.core.Button.ButtonSize;
import com.foodverse.utility.core.Button.ButtonType;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
// import java.awt.Graphics;
import javax.swing.*;


public class ShopOverlay extends Overlay {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        var text = new Heading("ShopOverlay", HeadingSize.L);
        var button =
                new PillButton("Close ShopOverlay ->", ButtonSize.XS, ButtonType.SECONDARY, e -> {
                    Router.closeOverlay();
                });
        panel.add(text.getRef());
        panel.add(button.getRef());
        panel.setOpaque(false);


        var panel1 = new JPanel();
        var panel2 = new JPanel();
        var panel3 = new JPanel();



        var rateLabel = new JLabel("Rate ");
        rateLabel.setFont(new Font("Courier New", Font.BOLD, 25));


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


        var offers = new JLabel("Offers");
        var menu = new JLabel("Menu");



        offers.setFont(new Font("Courier New", Font.BOLD, 25));
        menu.setFont(new Font("Courier New", Font.BOLD, 25));
        offers.setForeground(Color.white);
        menu.setForeground(Color.white);


        panel1.setPreferredSize(new Dimension(1300, 300));
        panel1.setBackground(Color.lightGray);
        panel1.add(offers);


        panel2.setPreferredSize(new Dimension(1300, 300));
        panel2.setBackground(Color.lightGray);
        panel2.add(menu);


        panel3.add(rateLabel);
        panel3.add(rate1);
        panel3.add(rate2);
        panel3.add(rate3);
        panel3.add(rate4);
        panel3.add(rate5);


        for (var i = 0; i < 3; i++) {
            var productAddButton = new PillButton("Add", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            });
            var panel4 = new JPanel();
            var menuItem = new JLabel("Item");

            panel4.setPreferredSize(new Dimension(1200, 60));
            panel4.add(menuItem);
            panel4.add(productAddButton.getRef());
            panel1.add(panel4);

        }



        JScrollPane scroll1 = new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll1);

        JScrollPane scroll2 = new JScrollPane(panel2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll2);


        panel.add(panel3);


        return panel;
    }

    public void showItems() {

    }

    // public void paint(Graphics g)
    // {
    // super.paint(g);
    // g.setColor(Color.white);
    // // g.drawLine(2000, 222, 0, 222);
    // g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
    // }

}
