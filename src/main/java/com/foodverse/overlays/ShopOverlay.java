package com.foodverse.overlays;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.foodverse.models.Shop;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.ImageStyle;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.views.EmptyView;
import com.foodverse.views.Offers;
import com.foodverse.views.RateView;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.ListTile;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.media.VectorImage;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class ShopOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();
    // private final Component component;
    private final String merchant;

    public ShopOverlay(String merchant) {
        super(800, 680);
        this.merchant = merchant;
    }

    @Override
    public Component getRef() {

        // Searching for the shop in the database...
        Optional<Shop> shop = db.findShopByName(merchant);
        if (!shop.isPresent()) {
            return new EmptyView().getRef();
        }

        // pass the name of the shop in a variable
        // var name = " ";
        var name = shop.get().name();

        var column = new Column();

        // Remove the default padding of the shop with a temporary panel
        var panelWithoutPad = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelWithoutPad.setOpaque(false);

        // Creating main panel...
        var panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // create heading with the name of the shop and put it in the panel
        var nameHeading = new Heading(name, HeadingSize.L);
        panel.add(nameHeading.getRef());

        //
        var props = ShopProps.from(shop.get());

        // Creating image widgets...
        var thumbnailImage = new Image(props.thumbnail(),
                new ImageStyle.Builder().width(800).height(200).build());
        var heartImage = new VectorImage(IconAsset.HEART);
        var heartFillImage = new VectorImage(IconAsset.HEART_FILL);

        // Create a map for the choosen items
        Map<String, Integer> addproducts = new HashMap<>();

        // Add padding to heart
        var paddedImage = new Column();
        paddedImage.addWidget(thumbnailImage, new EdgeInsets.Builder().all(0).build(),
                Align.FIRST_LINE_START);

        var paddedHeartImage = new Column();
        paddedHeartImage.addWidget(heartImage, new EdgeInsets.Builder().all(4).build(),
                Align.FIRST_LINE_START);

        var paddedHeartFillImage = new Column();
        paddedHeartFillImage.addWidget(heartFillImage, new EdgeInsets.Builder().all(4).build(),
                Align.FIRST_LINE_START);

        heartImage.onPressed(e -> {
            panel.remove(heartImage.getRef());
            panel.add(heartFillImage.getRef());
            panel.revalidate();
            panel.repaint();
        }, "Add to favorites");

        heartFillImage.onPressed(e -> {
            panel.remove(heartFillImage.getRef());
            panel.add(heartImage.getRef());
            panel.revalidate();
            panel.repaint();
        }, "Remove from favorites");

        panel.add(heartImage.getRef());

        // offers
        var offersTile = new ListTile("Offers");

        panel.add(offersTile.getRef());
        // offersTile.setFont(new Font("Courier New", Font.BOLD, 25));
        // offersTile.setForeground(Color.black);

        if (shop.get().offers().isEmpty()) {
            panel.add(new EmptyView().getRef());
        } else {
            panel.add(new Offers(shop).getRef());
        }

        // props.offers();

        // Menu
        var menuTile = new ListTile("Menu");
        // var menu = new JLabel("Menu ");
        // menuTitle.setFont(new Font("Courier New", Font.BOLD, 25));
        // menutitle.setForeground(Color.black);

        panel.add(menuTile.getRef());

        for (int i = 0; i < shop.get().menu().size(); i++) {

            var itemPanel = new JPanel();

            itemPanel.setPreferredSize(new Dimension(200, 40));

            var x = shop.get().menu().get(i).name();
            var price = shop.get().menu().get(i).price();
            var priceAsAString = Float.toString(price);
            var menuName = new JLabel(x);
            var menuPrice = new JLabel(priceAsAString + " \u20AC");

            var productAddButton = new PillButton("Add", ButtonSize.XS, ButtonType.SECONDARY, e -> {
                addproducts.put(x, 1);
            });

            itemPanel.add(menuName);
            itemPanel.add(menuPrice);
            itemPanel.add(productAddButton.getRef());

            itemPanel.setBackground(Color.white);

            panel.add(itemPanel);

        }

        panel.setBackground(Color.white);

        // Rate
        RateView rate = new RateView(shop);
        panel.add(rate.getRef());

        // RectButton "Cart" to appear the OrderOverlay
        var OrderButton = new RectButton("Cart", ButtonSize.S, ButtonType.PRIMARY, e -> {
            Router.closeOverlay();
            Router.openOverlay(new OrderOverlay(merchant, addproducts));

        });

        panel.add(OrderButton.getRef());

        // component = new ScrollView(panel).getRef();

        column.addWidget(thumbnailImage, new EdgeInsets.Builder().all(0).build(),
                Align.FIRST_LINE_START);
        column.addComponent(panel, new EdgeInsets.Builder().all(0).build(), Align.LAST_LINE_START);
        // return column.getRef();
        return new ScrollView(column.getRef()).getRef();
    }

}
