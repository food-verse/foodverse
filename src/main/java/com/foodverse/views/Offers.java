package com.foodverse.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

        panel.setPreferredSize(new Dimension(100, 200));
        panel.setBackground(Color.white);

        for (int i = 0; i < shop.get().offers().size(); i++) {

            var itemPanel = new JPanel();

            Map<String, Integer> items = shop.get().offers().get(i).items();
            JLabel offerTotal = new JLabel();

            // print the contents of the items map
            var k = 0;
            for (String item : items.keySet()) {

                k++;
                var offerName = new JLabel();

                if (k == shop.get().offers().get(i).items().size()) {
                    // offerName.setText("<html>" + item + "<br>" + "</html>");
                    offerName.setText(item + " ");
                } else {
                    offerName.setText(item + "  & ");
                }

                var offerQuantity = new JLabel();

                var quantity = items.get(item);
                var quantityAsAString = Integer.toString(quantity);

                var price = shop.get().menu().get(i).price();
                var priceAsAString = Float.toString(price);

                offerTotal.setText(priceAsAString + " \u20AC");
                offerQuantity.setText(quantityAsAString);

                itemPanel.add(offerQuantity);
                itemPanel.add(offerName);

                panel.add(itemPanel);

            }

            itemPanel.add(offerTotal);

            var productAddButton = new PillButton("Add", ButtonSize.XS, ButtonType.SECONDARY, e -> {

            });

            itemPanel.add(productAddButton.getRef());

            itemPanel.setPreferredSize(new Dimension(400, 50));

            itemPanel.setBackground(Color.white);
        }

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
