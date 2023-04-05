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

        var panel0 = new JPanel();
        var panel1 = new JPanel();
        var panel2 = new JPanel();

        var offers = new JLabel("Offers");
        var menu = new JLabel("Menu");
        var offerProducts = new JTextArea("12345");

        offers.setFont(new Font("Courier New", Font.BOLD, 25));
        menu.setFont(new Font("Courier New", Font.BOLD, 25));
        offers.setForeground(Color.white);
        menu.setForeground(Color.white);

        panel1.setPreferredSize(new Dimension(1000, 190));
        panel1.setBackground(Color.gray);
        panel1.add(offers);
        // panel1.add(offerProducts);
        // panel1.add (new LinePanel());

        panel2.setPreferredSize(new Dimension(1000, 190));
        panel2.setBackground(Color.gray);

        panel2.add(menu);
        // panel2.add(menuProducts);


        // LinePanel linePanel = new LinePanel();
        // linePanel.setPreferredSize(new Dimension(panel1.getWidth(), 1));
        // panel1.add(linePanel);


        panel0.setBackground(Color.lightGray);
        panel0.add(panel1);
        panel0.add(panel2);

        panel.add(panel0);


        return panel;
    }

}
