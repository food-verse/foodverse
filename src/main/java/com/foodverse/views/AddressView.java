package com.foodverse.views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.foodverse.models.User;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Colors;
// import com.foodverse.widgets.card.OrderCard;

public final class AddressView extends Widget {
    private final Component component;

    public AddressView(Optional<User> user) {
        var panel = new JPanel();

        panel.setPreferredSize(new Dimension(1250, 50));
        panel.setBackground(Colors.white);

        // OrderCard acard = new OrderCard(null);
        // panel.add(acard.getRef());
        var address = new JLabel("Address: " + user.get().addresses().get(0));
        setFontSize(address, 24);
        panel.add(address);

        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

    public static void setFontSize(JLabel label, int size) {
        Font font = label.getFont();
        Font modifiedFont = font.deriveFont((float) size); // Create a font with the specified size
        label.setFont(modifiedFont); // Set the modified font size
    }

}
