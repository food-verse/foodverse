package com.foodverse.pages;

import java.awt.Component;
import java.time.LocalDate;
import java.time.ZoneId;
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
import com.foodverse.props.OfferCardProps;
import com.foodverse.props.OrderCardProps;
import com.foodverse.props.ShopCardProps;
import com.foodverse.utility.common.DateUtils;
import com.foodverse.utility.common.Endpoints;
import com.foodverse.utility.common.URLHandler;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.widgets.layout.Carousel;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.ListTile;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.VectorImage;

public final class HomePage extends Page {

    private final JPanel panel;

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    private boolean didRender = false;

    public HomePage() {

        // Creating main panel...
        panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Row with the brand's logo and the user's avatar
        // Creating parent panel for the images...
        var headingRow = new JPanel();
        headingRow.setOpaque(false);
        headingRow.setLayout(new BoxLayout(headingRow, BoxLayout.X_AXIS));

        // Creating image widgets...
        var repositoryUrl = Endpoints.REPOSITORY.getLink();
        var brandImage = new VectorImage(IconAsset.BRAND, e -> {
            URLHandler.open(repositoryUrl);
        }, repositoryUrl);
        var avatarImage = new VectorImage(IconAsset.AVATAR, e -> {
            Router.openOverlay(new ProfileOverlay());
        }, "Profile");

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
        List<ShopCardProps> shopProps = shops.stream()
            .map(ShopCardProps::from)
            .sorted(Comparator.comparingDouble(ShopCardProps::rating).reversed())
            .collect(Collectors.toList());

        // Create a carousel for the nearby shops
        var shopCarousel = new Carousel(shopProps);

        // Add the carousel for the nearby shops to the main panel
        panel.add(shopCarousel.getRef());

        // Heading for the carousel of offers
        var offersTile = new ListTile("Offers");

        // Add the heading for the offers' carousel to the main panel
        panel.add(offersTile.getRef());

        // Turning Shop list into OfferProp list...
        List<OfferCardProps> offerProps = shops.stream()
            .filter(shop -> !shop.offers().isEmpty())
            .sorted(Comparator.comparingDouble(Shop::rating).reversed())
            .map(OfferCardProps::from)
            .collect(Collectors.toList());

        // Create a carousel for the available offers
        var offerCarousel = new Carousel(offerProps);

        // Add the carousel for the available offers to the main panel
        panel.add(offerCarousel.getRef());

        // Heading for the carousel of recent orders
        var ordersTile = new ListTile("Recent");

        // Add the heading for the recent orders' carousel to the main panel
        panel.add(ordersTile.getRef());

    }

    @Override
    public Component getRef() {

        // Remove the last component (carousel) if the page has already rendered once
        if (!didRender) {
            didRender = true;
        } else {
            int lastComponentIndex = panel.getComponentCount() - 1;
            panel.remove(lastComponentIndex);
        }

        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();

        // Turning signed user's order list into OrderProp list...
        List<OrderCardProps> orderProps;
        if (signedUser.isPresent()) {
            var startDate = LocalDate.now(ZoneId.systemDefault()).minusWeeks(1);
            var endDate = LocalDate.now(ZoneId.systemDefault());
            orderProps = signedUser.get().orders().stream()
                .filter(order -> DateUtils.isInRange(order.date(), startDate, endDate))
                .sorted(Comparator.comparing(Order::date).reversed())
                .map(OrderCardProps::from)
                .collect(Collectors.toList());
        } else {
            orderProps = List.of();
        }

        // Create a carousel for the recent orders
        var orderCarousel = new Carousel(orderProps);

        // Add the carousel for the recent orders to the main panel
        panel.add(orderCarousel.getRef());

        // Wrap the main panel in a scroll view
        return new ScrollView(panel).getRef();

    }

}
