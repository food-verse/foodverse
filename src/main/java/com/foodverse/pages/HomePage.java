package com.foodverse.pages;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import com.foodverse.models.Item;
import com.foodverse.utility.EdgeInsets;
import com.foodverse.utility.Page;
import com.foodverse.utility.Pages;
import com.foodverse.utility.Router;
import com.foodverse.utility.core.Align;
import com.foodverse.utility.core.Column;
import com.foodverse.utility.core.ImageAsset;
import com.foodverse.utility.core.ImageStyle;
import com.foodverse.utility.core.Row;
import com.foodverse.utility.core.ShopType;
import com.foodverse.utility.core.Button.ButtonSize;
import com.foodverse.utility.core.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.card.ShopProps;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.card.OfferCard;
import com.foodverse.widgets.card.OfferProps;
import com.foodverse.widgets.card.OrderCard;
import com.foodverse.widgets.card.OrderProps;
import com.foodverse.widgets.card.ShopCard;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class HomePage extends Page {

    @Override
    public Component getRef() {
        // Creating main panel...
        var panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // TODO: Remove when all the screens have been implemented
        var openOverviewPage = new RectButton(
                "Open OverviewPage ->",
                ButtonSize.S,
                ButtonType.SECONDARY,
                e -> {
                    Router.pushPage(Pages.OVERVIEW);
                });
        panel.add(openOverviewPage.getRef());
        // Row with the brand's logo and the user's avatar
        // Creating parent panel for the images...
        JPanel headingRow = new JPanel();
        headingRow.setOpaque(false);
        headingRow.setLayout(new BoxLayout(headingRow, BoxLayout.X_AXIS));
        // Creating image widgets...
        var brandImage = new Image(ImageAsset.BRAND, new ImageStyle.Builder()
                .width(328)
                .height(72)
                .build());
        var avatarImage = new Image(ImageAsset.AVATAR, new ImageStyle.Builder()
                .width(48)
                .height(48)
                .build());
        // Add padding to avatar
        var paddedAvatar = new Column();
        paddedAvatar.addWidget(avatarImage, new EdgeInsets.Builder()
                .top(8)
                .build(),
                Align.FIRST_LINE_END);
        // Add images to the parent panel
        headingRow.add(brandImage.getRef());
        headingRow.add(Box.createHorizontalGlue());
        headingRow.add(paddedAvatar.getRef());
        // Add the padded heading row to the main panel
        var paddedHeading = new Row();
        paddedHeading.addComponent(headingRow, new EdgeInsets.Builder()
                .all(48)
                .top(40)
                .build(),
                Align.CENTER);
        panel.add(paddedHeading.getRef());
        // Heading for the carousel of nearby shops
        var nearbyText = new Heading("Nearby", HeadingSize.L);
        var paddedNearby = new Row();
        paddedNearby.addWidget(nearbyText, new EdgeInsets.Builder()
                .left(48)
                .build(),
                Align.CENTER);
        // Add the heading for the carousel to the main panel
        panel.add(paddedNearby.getRef());
        // Remove default padding of carousel with a temporary panel
        var panelWithoutPad = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelWithoutPad.setOpaque(false);
        // Creating carousel of nearby shops...
        var shopCarousel = new Row();
        for (int i = 0; i < 5; i++) {
            // Add samples of the ShopCard to the carousel
            // TODO: Remove mock data
            shopCarousel.addWidget((new ShopCard(new ShopProps.Builder()
                    .thumbnail(ImageAsset.BURGER)
                    .shopName("Burgerlicious")
                    .rating(5.0f)
                    .type(ShopType.BURGER)
                    .minimumOrder(7.0f)
                    .time(30)
                    .build())),
                    new EdgeInsets.Builder()
                            .right(8)
                            .build(),
                    Align.FIRST_LINE_END);
        }
        panelWithoutPad.add(shopCarousel.getRef());
        // Add padding to shop carousel
        var paddedCarousel = new Row();
        paddedCarousel.addComponent(panelWithoutPad, new EdgeInsets.Builder()
                .symmetric(16, 48)
                .bottom(24)
                .build(),
                Align.FIRST_LINE_START);
        panel.add(paddedCarousel.getRef());
        // Heading for the carousel of offers
        var paddedOffers = new Row();
        var offersText = new Heading("Offers", HeadingSize.L);
        paddedOffers.addWidget(offersText, new EdgeInsets.Builder()
                .left(48)
                .build(),
                Align.CENTER);
        panel.add(paddedOffers.getRef());
        // TODO: Remove map of offers when the process of loading the offers is ready
        Map<Item, Integer> offerItems = Map.of(
                new Item("Pizza Margarita + 2 Coca Cola -> 5.20€"), 1,
                new Item("Pizza Picolla + 1 Coca Cola -> 3.40€"), 1,
                new Item("Pizza Al Forno + 2 Fanta -> 8.60€"), 1);
        // Add sample of the OfferCard the the main panel
        // TODO: Remove mock data
        panel.add(new OfferCard(new OfferProps.Builder()
                .thumbnail(ImageAsset.SMALL_BURGER)
                .shopName("Burgerlicious")
                .rating(5.0f)
                .items(offerItems)
                .build()).getRef());
        // Heading for the carousel of recent orders
        var paddedRecent = new Row();
        var recentText = new Heading("Recent", HeadingSize.L);
        paddedRecent.addWidget(recentText, new EdgeInsets.Builder()
                .left(48)
                .build(),
                Align.CENTER);
        panel.add(paddedRecent.getRef());
        // TODO: Remove map of items when the process of loading recent orders is ready
        Map<Item, Integer> orderItems = Map.of(
                new Item("BBQ Burger XL with fries"), 1,
                new Item("Pepsi Cola"), 1,
                new Item("Onion Rings"), 1);
        // Add sample of the OrderCard to the main panel
        // TODO: Remove mock data
        panel.add(new OrderCard(new OrderProps.Builder()
                .thumbnail(ImageAsset.BURGER)
                .shopName("Burgerlicious")
                .rating(5.0f)
                .items(orderItems)
                .price(24.78f)
                .build()).getRef());
        // Add scrolling to the main panel
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        return scrollPane;
    }

}
