package com.foodverse.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.foodverse.models.Shop;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.PillButton;

public final class Offers extends Widget {
    private final Component component;

    public Offers(Optional<Shop> shop) {

        var panel = new JPanel();

        var offers = new JLabel(
                "Offers");
        offers.setFont(new Font("Courier New", Font.BOLD, 25));
        offers.setForeground(Color.black);

        panel.setPreferredSize(new Dimension(1400, 300));
        panel.setBackground(Color.white);
        panel.add(offers);

        for (int i = 0; i < shop.get().offers().size(); i++) {

            var itemPanel = new JPanel();

            Map<String, Integer> items = shop.get().offers().get(i).items();
            JLabel offerTotal = new JLabel();
            // print the contents of the items map
            for (String item : items.keySet()) {
                var offerName = new JLabel(item);
                var price = shop.get().menu().get(i).price();
                var priceAsAString = Float.toString(price);
                offerTotal.setText(priceAsAString);
                itemPanel.add(offerName);
                panel.add(itemPanel);

            }
            itemPanel.add(offerTotal);

            itemPanel.setPreferredSize(new Dimension(1500, 60));

        }

        var productAddButton = new PillButton("Add", ButtonSize.XS, ButtonType.SECONDARY, e -> {

        });

        // JScrollPane scroll1 = new JScrollPane(panel,
        // JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        // JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // panel.add(scroll1);

        component = panel;

    }

    @Override
    public Component getRef() {
        return component;
    }

}
