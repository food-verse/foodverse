package com.foodverse.overlays;

import java.awt.Component;
import java.util.List;
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

    public ShopOverlay(String merchant) {


        // db.findShopByName(merchant).ifPresentOrElse(shop -> {
        // System.out.println(shop);
        // }, () -> {
        // // System.out.println("Merchant not found");
        // });
        // // Alternative

        // Optional<Shop> shop = db.findShopByName(merchant);
        // if (shop.isPresent()) {
        // System.out.println(shop);
        // } else {
        // System.out.println("Merchant not found");
        // }
    }

    @Override
    public Component getRef() {

        List<Shop> shops = db.getShops();

        var name = shops.get(0).name();

        var panel = new JPanel();
        var text = new Heading(name, HeadingSize.L);
        var button =
                new PillButton("Close ShopOverlay ->", ButtonSize.XS, ButtonType.SECONDARY, e -> {
                    Router.closeOverlay();
                });
        panel.add(text.getRef());
        panel.add(button.getRef());
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // offers
        Offers offer = new Offers();
        panel.add(offer.getRef());

        // Menu
        MenuView menu = new MenuView();
        panel.add(menu.getRef());

        // Rate
        RateView rate = new RateView();
        panel.add(rate.getRef());


        // RectButton "Bug" to appear the OrderOverlay
        var OrderButton = new RectButton("Bag", ButtonSize.S, ButtonType.PRIMARY, e -> {
            Router.openOverlay(new OrderOverlay(null, null));

        });

        panel.add(OrderButton.getRef());


        return new ScrollView(panel).getRef();
    }

    // public void paint(Graphics g)
    // {
    // super.paint(g);
    // g.setColor(Color.white);
    // g.drawLine(2000, 222, 0, 222);
    // g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
    // }

}
