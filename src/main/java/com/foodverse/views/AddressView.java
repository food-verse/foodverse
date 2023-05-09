package com.foodverse.views;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.foodverse.models.User;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Colors;
// import com.foodverse.widgets.card.OrderCard;

import javax.swing.JLabel;

public final class AddressView extends Widget {
    private final Component component;

    public AddressView(Optional<User> user) {
        var panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Colors.black));
        panel.setPreferredSize(new Dimension(1250, 100));
        panel.setBackground(Colors.white);

        // OrderCard acard = new OrderCard(null);
        // panel.add(acard.getRef());
        JLabel address = new JLabel("Street: " + user.get().addresses() + "\n");
        JLabel address2 = new JLabel("Number: " + user.get().phone() + "\n");
        // JLabel address3 = new JLabel("floor: "+ user.get() + "\n");
        panel.add(address);
        panel.add(address2);
        // panel.add(address3);

        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
