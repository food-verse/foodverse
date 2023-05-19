package com.foodverse.views;

import java.awt.Component;
import java.util.Optional;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.foodverse.models.User;
import com.foodverse.utility.core.Widget;

public final class AddressView extends Widget {
    private final Component component;

    public AddressView(Optional<User> user) {
        var panel = new JPanel();

        // panel.setPreferredSize(new Dimension(200, 50));

        // OrderCard acard = new OrderCard(null);
        // panel.add(acard.getRef());
        var address = new JLabel("Address: " + user.get().addresses().get(0).street());
        panel.add(address);

        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
