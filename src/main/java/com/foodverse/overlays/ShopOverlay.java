package com.foodverse.overlays;

import com.foodverse.models.*;
import com.foodverse.utility.common.URLHandler;
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
import com.foodverse.views.AddressView;
import com.foodverse.views.EmptyView;
import com.foodverse.widgets.button.IconButton;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.ListTile;
import com.foodverse.widgets.layout.ListTile.ListTileSize;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.media.AssetSize;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.pickers.StarRating;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class ShopOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    private final String merchant;

    private final Map<Purchasable, Integer> items;

    public ShopOverlay(String merchant) {
        super(800, 680);
        this.merchant = merchant;
        this.items = new HashMap<>();
    }

    public ShopOverlay(String merchant, Map<Purchasable, Integer> items) {
        super(800, 680);
        this.merchant = merchant;
        this.items = items;
    }

    @Override
    public Component getRef() {

        // Searching for the shop in the database...
        Optional<Shop> openedShop = db.findShopByName(merchant);

        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();

        // If the shop or the user are not present, return an empty view...
        if (openedShop.isEmpty() || signedUser.isEmpty()) {
            return new EmptyView().getRef();
        }

        // Unwrapping shop and user...
        var shop = openedShop.get();
        var user = signedUser.get();

        // Creating main panel...
        var panel = new Column();

        // Creating the info of the order...
        var orderInfo = String.format(
            "%s | %d’ | Minimum %.2f€",
            shop.type(),
            shop.prepTime(),
            shop.minOrder());
        var formattedAddress = String.format(
            "https://www.google.com/maps/search/?api=1&query=%s",
            shop.address().replace(" ", "+"));

        // Creating text widgets...
        var shopNameText = new Heading(shop.name(), HeadingSize.L);
        var shopInfoText = new Label(orderInfo, LabelSize.M, Colors.gray600);

        // Creating image widgets...
        var thumbnailImage = new Image(shop.thumbnails().get(AssetSize.LARGE),
            new ImageStyle.Builder()
                .width(800)
                .height(200)
                .build());

        // Adding the thumbnail widget to the main panel
        panel.addWidget(thumbnailImage);

        // Creating rating widget...
        var oldShop = shop;
        var starRating = new StarRating(5, rating -> {
            if (rating == 0) {
                user.ratings().remove(merchant);
                db.updateShop(oldShop);
            } else {
                user.ratings().put(merchant, rating);
                var oldSum = oldShop.rating() * oldShop.reviews();
                var newRating = (oldSum + rating) / (oldShop.reviews() + 1);
                var newShop = shop.withRating(newRating).withReviews(oldShop.reviews() + 1);
                db.updateShop(newShop);
            }
            db.updateUser(user);
        }, user.ratings().getOrDefault(merchant, 0));

        // Creating checkout button...
        var checkoutButton = new PillButton("Checkout",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                Router.closeOverlay();
                Router.openOverlay(new OrderOverlay(merchant, items));
            });

        // Creating favorites button...
        var favoritesButton = new IconButton(
            IconAsset.HEART_FILL,
            "Remove from favorites",
            IconAsset.HEART,
            "Add to favorites",
            isEnabled -> {
                if (Boolean.TRUE.equals(isEnabled)) {
                    user.favorites().add(merchant);
                } else {
                    user.favorites().remove(merchant);
                }
                db.updateUser(user);
            },
            user.favorites().contains(merchant));
        var paddedFavoritesButton = new Column();
        paddedFavoritesButton.addWidget(favoritesButton, new EdgeInsets.Builder()
            .all(4)
            .build());

        // Creating view's header widget...
        var headerWidget = new Row();
        headerWidget.addWidget(shopNameText, new EdgeInsets.Builder()
                .right(16)
                .build(),
            Align.CENTER);
        headerWidget.addWidget(paddedFavoritesButton, Align.CENTER);

        // Creating view's info widget...
        var infoWidget = new Column();
        infoWidget.addWidget(headerWidget);
        infoWidget.addWidget(shopInfoText, new EdgeInsets.Builder()
            .top(8)
            .bottom(12)
            .build());
        infoWidget.addWidget(starRating);

        // Add the padded info widget to the main panel
        var paddedInfo = new Column();
        paddedInfo.addWidget(infoWidget, new EdgeInsets.Builder()
            .symmetric(24, 40)
            .build());
        panel.addWidget(paddedInfo);

        panel.addComponent(new Divider());

        // Create the button for opening Google Maps
        var openMapsButton = new PillButton(
            "Open Google Maps",
            ButtonSize.S,
            ButtonType.TERTIARY,
            e -> URLHandler.open(formattedAddress));
        panel.addWidget(openMapsButton, new EdgeInsets.Builder()
                .symmetric(14, 40)
                .build(),
            Align.CENTER_RIGHT);

        // Add the address to the main panel
        var addressView = new AddressView(shop.address());
        panel.addWidget(addressView, new EdgeInsets.Builder()
            .top(-62)
            .left(40)
            .build());

        panel.addComponent(new Divider());

        // If the shop has no offers, add an empty view to the main panel
        if (!shop.offers().isEmpty()) {
            // Heading for the list of offers
            var offersTile = new ListTile("Offers", ListTileSize.S);
            // Add the heading for the offers to the main panel
            panel.addWidget(offersTile, new EdgeInsets.Builder()
                .bottom(10)
                .build());
            // Add the offers to the main panel
            for (Offer offer : shop.offers()) {
                // Create the add to cart button
                var addToCartButton = new PillButton(
                    "Add",
                    ButtonSize.S,
                    ButtonType.SECONDARY,
                    e -> {
                        if (items.containsKey(offer)) {
                            items.put(offer, items.get(offer) + 1);
                        } else {
                            if (items.isEmpty()) {
                                checkoutButton.toggle();
                            }
                            items.put(offer, 1);
                        }
                    });
                panel.addWidget(addToCartButton, new EdgeInsets.Builder()
                        .top(14)
                        .right(40)
                        .build(),
                    Align.CENTER_RIGHT);

                // Add the item to the main panel
                var itemWidget = new Column();
                var itemText = new Label(offer.toString(), LabelSize.S);
                var priceText = new Label(
                    String.format("%.2f", offer.total()),
                    LabelSize.S,
                    Colors.gray600);
                itemWidget.addWidget(itemText);
                itemWidget.addWidget(priceText, new EdgeInsets.Builder()
                    .top(4)
                    .build());
                panel.addWidget(itemWidget, new EdgeInsets.Builder()
                    .top(-40)
                    .left(40)
                    .bottom(10)
                    .build());
            }
            panel.addComponent(new Divider(), new EdgeInsets.Builder()
                .top(14)
                .build());
        }

        // Heading for the menu items
        var menuTile = new ListTile("Menu", ListTile.ListTileSize.S);

        // Add the heading for the menu to the main panel
        panel.addWidget(menuTile, new EdgeInsets.Builder()
            .bottom(10)
            .build());

        // Add the menu items to the main panel
        for (Item item : shop.menu()) {
            // Create the add to cart button
            var addToCartButton = new PillButton(
                "Add",
                ButtonSize.S,
                ButtonType.SECONDARY,
                e -> {
                    if (items.containsKey(item)) {
                        items.put(item, items.get(item) + 1);
                    } else {
                        if (items.isEmpty()) {
                            checkoutButton.toggle();
                        }
                        items.put(item, 1);
                    }
                });
            panel.addWidget(addToCartButton, new EdgeInsets.Builder()
                    .top(14)
                    .right(40)
                    .build(),
                Align.CENTER_RIGHT);

            // Add the item to the main panel
            var itemWidget = new Column();
            var itemText = new Label(item.name(), LabelSize.S);
            var priceText = new Label(
                String.format("%.2f", item.price()),
                LabelSize.S,
                Colors.gray600);
            itemWidget.addWidget(itemText);
            itemWidget.addWidget(priceText, new EdgeInsets.Builder()
                .top(4)
                .build());
            panel.addWidget(itemWidget, new EdgeInsets.Builder()
                .top(-40)
                .left(40)
                .bottom(10)
                .build());
        }

        // If the cart is empty, disable the checkout button
        if (items.isEmpty()) {
            checkoutButton.toggle();
        }

        // Add the checkout button to the main panel
        var paddedCheckoutButton = new Row();
        paddedCheckoutButton.addWidget(checkoutButton, new EdgeInsets.Builder()
            .symmetric(10, 40)
            .bottom(24)
            .build());
        panel.addWidget(paddedCheckoutButton, Align.CENTER);

        return new ScrollView(panel.getRef()).getRef();
    }

}
