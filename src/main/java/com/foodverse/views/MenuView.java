package com.foodverse.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.foodverse.utility.core.Widget;
import java.awt.Font;

public final class MenuView extends Widget {

    @Override
    public Component getRef() {
        var panel = new JPanel();

        var menu = new JLabel("Menu  ");
        menu.setFont(new Font("Courier New", Font.BOLD, 25));
        menu.setForeground(Color.white);

        panel.setPreferredSize(new Dimension(1300, 300));
        panel.setBackground(Color.lightGray);
        panel.add(menu);

        JScrollPane scroll2 = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll2);

        return panel;
    }
}
