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
        var productAddButton = new PillButton("Add", ButtonSize.XS, ButtonType.SECONDARY, e -> {
        });


        var offers = new JLabel("Offers");
        var menu = new JLabel("Menu");
        var menuItem = new JLabel("Item");


        offers.setFont(new Font("Courier New", Font.BOLD, 25));
        menu.setFont(new Font("Courier New", Font.BOLD, 25));
        offers.setForeground(Color.white);
        menu.setForeground(Color.white);


        panel1.setPreferredSize(new Dimension(1200, 300));
        panel1.setBackground(Color.lightGray);
        panel1.add(offers);
        panel1.add(menuItem);
        panel1.add(productAddButton.getRef());


        panel2.setPreferredSize(new Dimension(1200, 300));
        panel2.setBackground(Color.lightGray);
        panel2.add(menu);


        panel.add(panel1);
        panel.add(panel2);


        JScrollPane scroll1 = new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll1);

        JScrollPane scroll2 = new JScrollPane(panel2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll2);


        return panel;
    }

    // public void paint(Graphics g)
    // {
    // super.paint(g);
    // g.setColor(Color.white);
    // // g.drawLine(2000, 222, 0, 222);
    // g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
    // }

}
