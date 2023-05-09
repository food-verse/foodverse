package com.foodverse.overlays;

import java.awt.Component;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.foodverse.models.Shop;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.views.MenuView;
import com.foodverse.views.Offers;
import com.foodverse.views.RateView;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.layout.ScrollView;

public class ShopOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();
    private final Component component;

    public ShopOverlay(String merchant) {

        // db.findShopByName(merchant).ifPresentOrElse(shop -> {
        // System.out.println(shop);
        // }, () -> {
        // // System.out.println("Merchant not found");
        // });
        // // Alternative
        var name = " ";

        Optional<Shop> shop = db.findShopByName(merchant);
        if (shop.isPresent()) {
            System.out.println(shop);
            name = shop.get().name();

        } else {
            System.out.println("Merchant not found");
        }

        var panel = new JPanel();
        var text = new Heading(name, HeadingSize.L);
        var button = new PillButton("Close ShopOverlay ->", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            Router.closeOverlay();
        });
        panel.add(text.getRef());
        panel.add(button.getRef());
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // offers
        if (!shop.get().offers().isEmpty()) {
            Offers offer = new Offers(shop);
            panel.add(offer.getRef());
        }

        // Menu
        MenuView menu = new MenuView(shop);
        panel.add(menu.getRef());

        // Rate
        RateView rate = new RateView(shop);
        panel.add(rate.getRef());

        // RectButton "Bug" to appear the OrderOverlay
        var OrderButton = new RectButton("Bag", ButtonSize.S, ButtonType.PRIMARY, e -> {
            Router.closeOverlay();
            Router.openOverlay(new OrderOverlay(merchant, null));

        });

        panel.add(OrderButton.getRef());

        component = new ScrollView(panel).getRef();
    }

    @Override
    public Component getRef() {
        return component;
    }

    // public void paint(Graphics g)
    // {
    // super.paint(g);
    // g.setColor(Color.white);
    // g.drawLine(2000, 222, 0, 222);
    // g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
    // }

}
