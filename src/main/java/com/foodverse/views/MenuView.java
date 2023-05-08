package com.foodverse.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.foodverse.models.Shop;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.PillButton;
import java.awt.Font;
import java.util.List;

public final class MenuView extends Widget {

    private final Database db = Database.getInstance();

    @Override
    public Component getRef() {

        var panel = new JPanel();

        List<Shop> shops = db.getShops();


        var menu = new JLabel("Menu  ");
        menu.setFont(new Font("Courier New", Font.BOLD, 25));
        menu.setForeground(Color.black);


        panel.add(menu);


        for (int i = 0; i < shops.get(0).menu().size(); i++) {

            var itemPanel = new JPanel();

            itemPanel.setPreferredSize(new Dimension(1500, 60));


            var x = shops.get(0).menu().get(i).name();
            var price = shops.get(0).menu().get(i).price();
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


        // JScrollPane scroll2 = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        // JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // panel.add(scroll2);


        panel.setPreferredSize(new Dimension(1400, 300));
        panel.setBackground(Color.white);


        return panel;
    }
}
