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
import com.foodverse.models.User;
import com.foodverse.props.ShopProps;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.utility.ui.Colors;
import com.foodverse.utility.ui.Divider;
import com.foodverse.utility.ui.ImageStyle;
import com.foodverse.views.EmptyView;
import com.foodverse.views.Offers;
import com.foodverse.widgets.button.IconButton;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.ListTile;
import com.foodverse.widgets.layout.ListTile.ListTileSize;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.pickers.StarRating;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class ShopOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();
    // The name of the shop
    private final String merchant;

    public ShopOverlay(String merchant) {
        super(800, 680);
        this.merchant = merchant;
    }

    @Override
    public Component getRef() {

        // Searching for the shop in the database...
        Optional<Shop> shop = db.findShopByName(merchant);

        // Getting the authenticated user...
        Optional<User> user = db.getAuthenticatedUser();

        if (shop.isEmpty() || user.isEmpty()) {
            return new EmptyView().getRef();
        }

        //
        var props = ShopProps.from(shop.get());
        var signedUser = user.get();

        var orderInfo = String.format(
            "%s | %d’ | Minimum %.2f€",
            props.type(),
            props.prepTime(),
            props.minOrder());

        // Creating text widgets...
        var shopNameText = new Heading(props.name(), HeadingSize.L);
        var orderInfoText = new Label(orderInfo, LabelSize.M, Colors.gray600);

        //
        var favoritesButton = new IconButton(
            IconAsset.HEART_FILL,
            "Remove from favorites",
            IconAsset.HEART,
            "Add to favorites",
            isEnabled -> {
                if (isEnabled) {
                    signedUser.favorites().add(merchant);
                    db.updateUser(signedUser);
                } else {
                    signedUser.favorites().remove(merchant);
                    db.updateUser(signedUser);
                }
            },
            signedUser.favorites().contains(merchant));

        //
        var paddedFavoritesButton = new Column();
        paddedFavoritesButton.addWidget(favoritesButton, new EdgeInsets.Builder()
                .all(4)
                .build(),
            Align.FIRST_LINE_START);

        //
        var starRating = new StarRating(5, rating -> {
            if (rating == 0) {
                signedUser.ratings().remove(merchant);
                db.updateUser(signedUser);
            } else {
                signedUser.ratings().put(merchant, rating);
                db.updateUser(signedUser);
            }
        }, signedUser.ratings().getOrDefault(merchant, 0));

        // Creating card's heading widget...
        var headingWidget = new Row();
        headingWidget.addWidget(shopNameText, new EdgeInsets.Builder()
                .right(16)
                .build(),
            Align.CENTER);
        headingWidget.addWidget(paddedFavoritesButton, Align.CENTER);

        // Creating card's heading widget...
        var headingWidgets = new Column();
        headingWidgets.addWidget(headingWidget, new EdgeInsets.Builder()
                .bottom(8)
                .build(),
            Align.FIRST_LINE_START);
        headingWidgets.addWidget(orderInfoText, new EdgeInsets.Builder()
                .bottom(8)
                .build(),
            Align.FIRST_LINE_START);
        headingWidgets.addWidget(starRating, Align.FIRST_LINE_START);

        //
        var paddedHeading = new Column();
        paddedHeading.addWidget(headingWidgets, new EdgeInsets.Builder()
                .symmetric(24, 40)
                .build(),
            Align.FIRST_LINE_START);

        // Remove the default padding of the shop with a temporary panel
        var panelWithoutPad = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelWithoutPad.setOpaque(false);

        // Creating main panel...
        var panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Creating image widgets...
        var thumbnailImage = new Image(props.thumbnail(), new ImageStyle.Builder()
            .width(800)
            .height(200)
            .build());

        // Create a map for the chosen items
        Map<String, Integer> addProducts = new HashMap<>();

        var paddedImage = new Column();
        paddedImage.addWidget(thumbnailImage, new EdgeInsets.Builder()
                .all(0)
                .build(),
            Align.FIRST_LINE_START);

        // Shop's address
        panel.add(new Divider());
        var shopAddress = new Paragraph(props.address(), ParagraphSize.L);
        var paddedAddress = new Row();
        paddedAddress.addWidget(shopAddress, new EdgeInsets.Builder()
                .top(0)
                .build(),
            Align.CENTER);
        panel.add(paddedAddress.getRef());
        panel.add(new Divider());

        // offers
        var offersTile = new ListTile("Offers", ListTileSize.S);

        panel.add(offersTile.getRef());

        if (props.offers().isEmpty()) {
            panel.add(new EmptyView().getRef());
        } else {
            panel.add(new Offers(shop).getRef());
        }

        // Menu
        var menuTile = new ListTile("Menu", ListTileSize.S);

        panel.add(menuTile.getRef());

        for (int i = 0; i < props.menu().size(); i++) {

            var itemPanel = new JPanel();

            itemPanel.setPreferredSize(new Dimension(200, 40));

            var x = props.menu().get(i).name();
            var price = props.menu().get(i).price();
            var priceAsAString = Float.toString(price);
            var menuName = new JLabel(x);
            var menuPrice = new JLabel(priceAsAString + " €");

            var productAddButton = new PillButton("Add", ButtonSize.XS, ButtonType.SECONDARY, e -> {
                addProducts.put(x, 1);
            });

            itemPanel.add(menuName);
            itemPanel.add(menuPrice);
            itemPanel.add(productAddButton.getRef());

            itemPanel.setBackground(Color.white);
            var paddedMenu = new Row();
            paddedMenu.addComponent(itemPanel, new EdgeInsets.Builder()
                    .symmetric(40, 48)
                    .build(),
                Align.CENTER);
            panel.add(paddedMenu.getRef());

            var paddedMenu2 = new Column();
            paddedMenu2.addComponent(itemPanel, new EdgeInsets.Builder()
                    .symmetric(40, 48)
                    .build(),
                Align.CENTER);
            panel.add(paddedMenu.getRef());
            panel.add(itemPanel);

        }

        // RectButton "Cart" to appear the OrderOverlay
        var OrderButton = new RectButton("Cart", ButtonSize.L, ButtonType.PRIMARY, e -> {
            Router.closeOverlay();
            Router.openOverlay(new OrderOverlay(merchant, addProducts));

        });

        panel.add(OrderButton.getRef());
        var column = new Column();
        column.addWidget(thumbnailImage, new EdgeInsets.Builder().all(0).build(),
            Align.FIRST_LINE_START);
        column.addWidget(paddedHeading, Align.FIRST_LINE_START);
        column.addComponent(panel, new EdgeInsets.Builder().left(150).build(),
            Align.LAST_LINE_START);

        return new ScrollView(column.getRef()).getRef();
    }

}
