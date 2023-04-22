package com.foodverse.views;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Colors;
import javax.swing.JLabel;

public final class AddressView extends Widget {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Colors.black));
        panel.setPreferredSize(new Dimension(1250, 100));
        panel.setBackground(Colors.white);

        JLabel address = new JLabel("Street: " + "\n");
        JLabel address2 = new JLabel("Number: " + "\n");
        JLabel address3 = new JLabel("floor: " + "\n");
        panel.add(address);
        panel.add(address2);
        panel.add(address3);

        return panel;
    }

}
