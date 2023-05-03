package com.foodverse.overlays;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import com.foodverse.models.Offer;
import com.foodverse.models.Shop;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.views.MenuView;
import com.foodverse.views.Offers;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.layout.ScrollView;

public class ShopOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    public ShopOverlay(String merchant) {
        db.findShopByName(merchant).ifPresentOrElse(shop -> {
            // System.out.println(shop);
        }, () -> {
            // System.out.println("Merchant not found");
        });
        // Alternative
        Optional<Shop> shop = db.findShopByName(merchant);
        if (shop.isPresent()) {
            // System.out.println(shop.get());
        } else {
            // System.out.println("Merchant not found");
        }
    }

    @Override
    public Component getRef() {
        var panel = new JPanel();
        var text = new Heading("ShopOverlay", HeadingSize.L);
        var button =
                new PillButton("Close ShopOverlay ->", ButtonSize.XS, ButtonType.SECONDARY, e -> {
                    Router.closeOverlay();
                });
        panel.add(text.getRef());
        panel.add(button.getRef());
        panel.setOpaque(false);

        // offers
        Offers offer = new Offers();
        panel.add(offer.getRef());

        // Menu
        MenuView menu = new MenuView();
        panel.add(menu.getRef());

        // this.showItems(panel1);
        // this.showItems(panel2);

        // RectButton "Bug" to appear the OrderOverlay
        var bugButton = new RectButton("Bag", ButtonSize.S, ButtonType.PRIMARY, e -> {
            showSuccessfulOrderMessage(e);
        });

        panel.add(bugButton.getRef());

        // panel.setOpaque(false);

        // panel.add(panel3);

        return new ScrollView(panel).getRef();
    }

    public void showItems(JPanel panel1) {

        for (var i = 0; i < 3; i++) {
            var productAddButton = new PillButton("Add", ButtonSize.XS, ButtonType.SECONDARY, e -> {
                // addproducts();

            });
            var panel4 = new JPanel();
            var menuItem = new JLabel("Item");

            panel4.setPreferredSize(new Dimension(1200, 60));
            panel4.add(menuItem);
            panel4.add(productAddButton.getRef());
            panel1.add(panel4);
        }

    }

    private void showSuccessfulOrderMessage(ActionEvent e) {
        JOptionPane.showMessageDialog(getFrame(), "We will deal with this later");
    }

    // public void paint(Graphics g)
    // {
    // super.paint(g);
    // g.setColor(Color.white);
    // g.drawLine(2000, 222, 0, 222);
    // g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
    // }


}
