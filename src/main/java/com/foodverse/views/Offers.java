package com.foodverse.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Colors;

public final class Offers extends Widget {
    @Override
    public Component getRef() {

        var panel = new JPanel();
        var panel1 = new JPanel();


        var offers = new JLabel("Offers");
        offers.setFont(new Font("Courier New", Font.BOLD, 25));
        offers.setForeground(Color.white);


        panel1.setBorder(BorderFactory.createLineBorder(Colors.black));
        panel1.setPreferredSize(new Dimension(1250, 100));
        panel1.setBackground(Colors.white);


        panel.setPreferredSize(new Dimension(1300, 300));
        panel.setBackground(Color.lightGray);
        panel.add(offers);


        for (var i = 0; i < 3; i++) {
            showItemsView showItems = new showItemsView();
            panel.add(showItems.getRef());
        }

        // JScrollPane scroll1 = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        // JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // panel.add(scroll1);

        panel1.add(panel);

        return panel1;

    }

}
