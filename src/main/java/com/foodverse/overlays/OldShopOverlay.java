package com.foodverse.overlays;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import com.foodverse.models.Shop;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.views.EmptyView;
import com.foodverse.views.Offers;
import com.foodverse.views.RateView;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.layout.ScrollView;

public class OldShopOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();
    private final Component component;

    public OldShopOverlay(String merchant) {

        // db.findShopByName(merchant).ifPresentOrElse(shop -> {
        // System.out.println(shop);
        // }, () -> {
        // // System.out.println("Merchant not found");
        // });
        // // Alternative
        var name = " ";

        Optional<Shop> shop = db.findShopByName(merchant);
        if (shop.isPresent()) {
            name = shop.get().name();
        } else {
            System.out.println("Merchant not found");
        }

        var panel = new JPanel();
        var text = new Heading(name, HeadingSize.L);
        var button = new PillButton(
                "Close ShopOverlay ->",
                ButtonSize.XS,
                ButtonType.SECONDARY,
                e -> {
                    Router.closeOverlay();
                });
        var offers = new JLabel("Offers");

        panel.add(text.getRef());
        panel.add(button.getRef());
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // offers
        panel.add(offers);
        offers.setFont(new Font("Courier New", Font.BOLD, 25));
        offers.setForeground(Color.black);

        if (shop.get().offers().isEmpty()) {
            panel.add(new EmptyView().getRef());
        } else {
            panel.add(new Offers(shop).getRef());
        }

        // Create a map for the choosen items
        Map<String, Integer> addproducts = new HashMap<>();

        // Menu
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
                addproducts.put(x, 1);
            });

            itemPanel.add(menuName);
            itemPanel.add(menuPrice);
            itemPanel.add(productAddButton.getRef());

            panel.add(itemPanel);

        }

        panel.setPreferredSize(new Dimension(1400, 300));
        panel.setBackground(Color.white);

        // Rate
        RateView rate = new RateView(shop);
        panel.add(rate.getRef());

        // RectButton "Bug" to appear the OrderOverlay
        var OrderButton = new RectButton(
                "Bag",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.closeOverlay();
                    Router.openOverlay(new OrderOverlay(merchant, addproducts));

                });

        panel.add(OrderButton.getRef());

        component = new ScrollView(panel).getRef();
    }

    @Override
    public Component getRef() {
        return component;
    }

}
