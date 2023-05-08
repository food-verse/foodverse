package com.foodverse.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;

public final class Offers extends Widget {
    @Override
    public Component getRef() {

        var panel = new JPanel();


        var offers = new JLabel("Offers");
        offers.setFont(new Font("Courier New", Font.BOLD, 25));
        offers.setForeground(Color.black);


        panel.setPreferredSize(new Dimension(1400, 300));
        panel.setBackground(Color.white);
        panel.add(offers);



        // JScrollPane scroll1 = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        // JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // panel.add(scroll1);


        return panel;

    }

}
