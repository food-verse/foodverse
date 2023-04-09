package com.foodverse.pages;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import com.foodverse.models.Item;
import com.foodverse.utility.core.layout.Align;
import com.foodverse.utility.core.layout.EdgeInsets;
import com.foodverse.utility.core.ui.ImageStyle;
import com.foodverse.utility.core.ui.Button.ButtonSize;
import com.foodverse.utility.core.ui.Button.ButtonType;
import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.ImageAsset;
import com.foodverse.views.ListTile;
import com.foodverse.views.ScrollView;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.card.ShopProps;
import com.foodverse.widgets.layout.Carousel;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.card.OfferProps;
import com.foodverse.widgets.card.OrderProps;

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
                .symmetric(40, 48)
                .build(),
                Align.CENTER);
        panel.add(paddedHeading.getRef());
        // Heading for the carousel of nearby shops
        var nearbyTile = new ListTile("Nearby");
        // Add the heading for the shops' carousel to the main panel
        panel.add(nearbyTile.getRef());
        // TODO: Remove list of ShopCard props
        List<ShopProps> shopProps = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            shopProps.add(new ShopProps.Builder()
                    .thumbnail(ImageAsset.BURGER)
                    .shopName("Burgerlicious")
                    .rating(5f)
                    .minimumOrder(7f)
                    .time(30)
                    .build());
        }
        // Create a carousel for the nearby shops
        var shopCarousel = new Carousel(shopProps);
        // Add the carousel for the nearby shops to the main panel
        panel.add(shopCarousel.getRef());
        // Heading for the carousel of offers
        var offersTile = new ListTile("Offers");
        // Add the heading for the offers' carousel to the main panel
        panel.add(offersTile.getRef());
        // TODO: Remove map of offers when the process of loading the offers is ready
        Map<Item, Integer> offerItems = Map.of(
                new Item("Pizza Margarita + 2 Coca Cola -> 5.20€"), 1,
                new Item("Pizza Picolla + 1 Coca Cola -> 3.40€"), 1,
                new Item("Pizza Al Forno + 2 Fanta -> 8.60€"), 1);
        // TODO: Remove list of OfferCard props
        List<OfferProps> offerProps = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            offerProps.add(new OfferProps.Builder()
                    .thumbnail(ImageAsset.SMALL_BURGER)
                    .shopName("Burgerlicious")
                    .rating(5.0f)
                    .items(offerItems)
                    .build());
        }
        // Create a carousel for the available offers
        var offerCarousel = new Carousel(offerProps);
        // Add the carousel for the available offers to the main panel
        panel.add(offerCarousel.getRef());
        // Heading for the carousel of recent orders
        var ordersTile = new ListTile("Recent");
        // Add the heading for the recent orders' carousel to the main panel
        panel.add(ordersTile.getRef());
        // TODO: Remove map of items when the process of loading recent orders is ready
        Map<Item, Integer> orderItems = Map.of(
                new Item("BBQ Burger XL with fries"), 1,
                new Item("Pepsi Cola"), 1,
                new Item("Onion Rings"), 1);
        // TODO: Remove list of OfferCard props
        List<OrderProps> orderProps = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            orderProps.add(new OrderProps.Builder()
                    .thumbnail(ImageAsset.BURGER)
                    .shopName("Burgerlicious")
                    .rating(5f)
                    .items(orderItems)
                    .price(24.78f)
                    .build());
        }
        // Create a carousel for the available offers
        var orderCarousel = new Carousel(orderProps);
        // Add the carousel for the available offers to the main panel
        panel.add(orderCarousel.getRef());
        // Wrap the main panel in a scroll view
        ScrollView view = new ScrollView(panel);
        return view.getRef();
    }

}
