package com.foodverse.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.foodverse.models.Shop;
import com.foodverse.utility.core.Widget;
// import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.PillButton;
import java.awt.Font;
import java.util.Optional;

public final class MenuView extends Widget {
    private final Component component;

    // private final Database db = Database.getInstance();

    public MenuView(Optional<Shop> shop) {

        var panel = new JPanel();

        var menu = new JLabel("Menu  ");
        menu.setFont(new Font("Courier New", Font.BOLD, 25));
        menu.setForeground(Color.black);

        panel.add(menu);

        for (int i = 0; i < shop.get().menu().size(); i++) {

            var itemPanel = new JPanel();

            itemPanel.setPreferredSize(new Dimension(1500, 60));

            var x = shop.get().menu().get(i).name();
            var price = shop.get().menu().get(i).price();
            var priceAsAString = Float.toString(price);
            var menuName = new JLabel(x);
            var menuPrice = new JLabel(priceAsAString);

            var productAddButton = new PillButton("Add", ButtonSize.XS, ButtonType.SECONDARY, e -> {

            });

            itemPanel.add(menuName);
            itemPanel.add(menuPrice);
            itemPanel.add(productAddButton.getRef());

            panel.add(itemPanel);

        }

        // JScrollPane scroll2 = new JScrollPane(panel,
        // JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        // JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // panel.add(scroll2);

        panel.setPreferredSize(new Dimension(1400, 300));
        panel.setBackground(Color.white);

        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }
}
