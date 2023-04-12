package com.foodverse.pages;

import java.awt.Component;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import com.foodverse.models.Order;
import com.foodverse.models.Shop;
import com.foodverse.models.User;
import com.foodverse.overlays.ProfileOverlay;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.system.URLHandler;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.card.ShopProps;
import com.foodverse.widgets.layout.Carousel;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.ListTile;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.VectorImage;
import com.foodverse.widgets.card.OfferProps;
import com.foodverse.widgets.card.OrderProps;

public final class HomePage extends Page {

    @Override
    public Component getRef() {

        // Getting a reference to the database...
        var db = Database.getInstance();

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
        var brandImage = new VectorImage(IconAsset.BRAND, e -> {
            URLHandler.open("https://github.com/food-verse/foodverse");
        });
        var avatarImage = new VectorImage(IconAsset.AVATAR, e -> {
            Router.openOverlay(new ProfileOverlay());
        });

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

        // Getting the list of shops...
        List<Shop> shops = db.getShops();

        // Turning Shop list into ShopProp list...
        List<ShopProps> shopProps = shops.stream()
                .map(ShopProps::from)
                .sorted(Comparator.comparingDouble(ShopProps::getRating).reversed())
                .collect(Collectors.toList());

        // Create a carousel for the nearby shops
        var shopCarousel = new Carousel(shopProps);

        // Add the carousel for the nearby shops to the main panel
        panel.add(shopCarousel.getRef());

        // Heading for the carousel of offers
        var offersTile = new ListTile("Offers");

        // Add the heading for the offers' carousel to the main panel
        panel.add(offersTile.getRef());

        // Turning Shop list into ShopProp list...
        List<OfferProps> offerProps = shops.stream()
                .filter(shop -> !shop.getOffers().isEmpty())
                .map(OfferProps::from)
                .sorted(Comparator.comparingDouble(OfferProps::getRating).reversed())
                .collect(Collectors.toList());

        // Create a carousel for the available offers
        var offerCarousel = new Carousel(offerProps);

        // Add the carousel for the available offers to the main panel
        panel.add(offerCarousel.getRef());

        // Heading for the carousel of recent orders
        var ordersTile = new ListTile("Recent");

        // Add the heading for the recent orders' carousel to the main panel
        panel.add(ordersTile.getRef());

        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();

        // Turning signed user's order list into order prop list...
        List<OrderProps> orderProps;
        if (signedUser.isPresent()) {
            orderProps = signedUser.get().getOrders().stream()
                    .sorted(Comparator.comparing(Order::getDate).reversed())
                    .map(OrderProps::from)
                    .collect(Collectors.toList());
        } else {
            orderProps = List.of();
        }

        // Create a carousel for the available offers
        var orderCarousel = new Carousel(orderProps);

        // Add the carousel for the available offers to the main panel
        panel.add(orderCarousel.getRef());

        // Wrap the main panel in a scroll view
        return new ScrollView(panel).getRef();

    }

}
