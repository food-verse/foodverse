package com.foodverse.overlays;

import java.awt.Component;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.foodverse.models.Offer;
import com.foodverse.models.Order;
import com.foodverse.models.PaymentMethod;
import com.foodverse.models.Purchasable;
import com.foodverse.models.Shop;
import com.foodverse.models.User;
import com.foodverse.state.Store;
import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.core.State;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.utility.ui.ColoredBox;
import com.foodverse.utility.ui.Colors;
import com.foodverse.utility.ui.Divider;
import com.foodverse.utility.ui.TextArea;
import com.foodverse.views.AddressView;
import com.foodverse.views.EmptyView;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.input.InputForm;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.VectorImage;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class OrderOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();
    private final String merchant;
    private final Map<Purchasable, Integer> items;
    private Shop shop;
    private User user;
    private double deliveryTip;
    private double total;
    private String comments;

    public OrderOverlay(UUID id) {
        super(800, 680);
        Optional<Order> order = db.findOrderById(id);
        if (order.isPresent()) {
            deliveryTip = order.get().deliveryTip();
            total = order.get().total() - deliveryTip;
            comments = order.get().comments();
            Store.orderPaymentMethod.setValue(order.get().method());
            merchant = order.get().merchant();
            Optional<Shop> issuer = db.findShopByName(merchant);
            if (issuer.isPresent()) {
                shop = issuer.get();
            } else {
                items = Map.of();
                return;
            }
            items = order.get().items().entrySet().stream()
                .flatMap(entry -> Stream.concat(
                    shop.menu().stream().filter(item -> entry.getKey().equals(item.name()))
                        .map(item -> new AbstractMap.SimpleEntry<>(item,
                            entry.getValue())),
                    shop.offers().stream()
                        .filter(offer -> entry.getKey().equals(offer.toString()))
                        .map(offer -> new AbstractMap.SimpleEntry<>(offer,
                            entry.getValue()))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } else {
            this.merchant = "";
            this.items = Map.of();
        }
    }

    public OrderOverlay(String merchant, Map<Purchasable, Integer> items) {
        super(800, 680, e -> {
            if (!items.isEmpty()) {
                Router.openOverlay(new ShopOverlay(merchant, items));
            }
        });
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
        shop = openedShop.get();
        user = signedUser.get();

        // Creating main panel...
        var panel = new Column();

        // Calculating total...
        final State<Double> totalWithTip;
        if (total == 0) {
            double totalFromItems = shop.menu().stream()
                .filter(items::containsKey)
                .mapToDouble(item -> item.price() * items.get(item))
                .sum();
            double totalFromOffers = shop.offers().stream()
                .filter(items::containsKey)
                .mapToDouble(Offer::total)
                .sum();
            total = totalFromItems + totalFromOffers;
            totalWithTip = new State<>(total);
        } else {
            totalWithTip = new State<>(total + deliveryTip);
        }

        var orderInfo = formatPaymentDetails(
            totalWithTip.getValue(),
            Store.orderPaymentMethod.getValue());

        // Creating view's info widget...
        var infoWidget = new Column();
        var orderText = new Heading("Your Order", HeadingSize.L);
        var orderInfoText = new Label(orderInfo, LabelSize.M, Colors.gray600);
        infoWidget.addWidget(orderText);
        infoWidget.addWidget(orderInfoText, new EdgeInsets.Builder()
            .top(8)
            .build());

        // Add the padded info widget to the main panel
        var paddedInfo = new Column();
        paddedInfo.addWidget(infoWidget, new EdgeInsets.Builder()
            .symmetric(24, 40)
            .build());
        panel.addWidget(paddedInfo);

        panel.addComponent(new Divider());

        // Adding the change address button to the main panel
        var changeAddressButton = new PillButton(
            "Change Address",
            ButtonSize.S,
            Button.ButtonType.TERTIARY,
            e -> Router.openOverlay(new AddressesOverlay()));
        panel.addWidget(changeAddressButton, new EdgeInsets.Builder()
                .symmetric(14, 40)
                .build(),
            Align.CENTER_RIGHT);

        // TODO: Refactor the way we get the address
        // Adding the address view to the main panel
        var addressView = new AddressView(user.addresses().get(0).toString());
        panel.addWidget(addressView, new EdgeInsets.Builder()
            .top(-62)
            .left(40)
            .build());

        panel.addComponent(new Divider());

        // Creating the view for the items in the cart...
        var itemsView = new Column();

        // Adding the heading for the items to the items view
        var itemsHeading = new Heading("Items", HeadingSize.M);
        itemsView.addWidget(itemsHeading);

        // Add the list of items to the corresponding view
        for (Map.Entry<? extends Purchasable, Integer> entry : items.entrySet()) {
            var item = entry.getKey();
            var itemWidget = new Column();
            var itemText = new Label(item.toString(), LabelSize.S);
            var priceText = new Label(
                String.format("%.2f", item.cost()),
                LabelSize.S,
                Colors.gray600);
            itemWidget.addWidget(itemText);
            itemWidget.addWidget(priceText, new EdgeInsets.Builder()
                .top(4)
                .build());
            itemsView.addWidget(itemWidget, new EdgeInsets.Builder()
                .top(24)
                .build());
        }

        // Add the padded list of items to the main panel
        var paddedItemsView = new Column();
        paddedItemsView.addWidget(itemsView, new EdgeInsets.Builder()
            .symmetric(24, 40)
            .build());
        panel.addWidget(paddedItemsView);

        panel.addComponent(new Divider());

        // Heading for the list of offers
        var paymentHeading = new Heading("Payment", HeadingSize.M);

        // Add the heading for the offers to the main panel
        var paymentView = new Column();
        paymentView.addWidget(paymentHeading, new EdgeInsets.Builder()
            .bottom(8)
            .build());

        // Creating the payment method buttons...
        for (PaymentMethod method : PaymentMethod.values()) {
            var methodText = new Label(method.toString(), LabelSize.M);
            var methodView = new Row();
            var methodIcon = new VectorImage(method == PaymentMethod.CARD
                ? IconAsset.CREDIT_CARD
                : IconAsset.PAYMENTS);
            methodView.addWidget(methodIcon, new EdgeInsets.Builder()
                    .right(12)
                    .build(),
                Align.CENTER_LEFT);
            methodView.addWidget(methodText, Align.CENTER_LEFT);
            var paddedMethodView = new Row();
            paddedMethodView.addWidget(methodView, new EdgeInsets.Builder()
                    .all(16)
                    .build(),
                Align.CENTER);
            var container = new ColoredBox(paddedMethodView, e -> {
                Store.orderPaymentMethod.setValue(method);
                orderInfoText.setText(formatPaymentDetails(
                    totalWithTip.getValue(),
                    method));
            });
            paymentView.addComponent(container, new EdgeInsets.Builder()
                .top(16)
                .build());
        }

        var paddedPaymentView = new Column();
        paddedPaymentView.addWidget(paymentView, new EdgeInsets.Builder()
            .symmetric(24, 40)
            .bottom(16)
            .build());
        panel.addWidget(paddedPaymentView);

        panel.addComponent(new Divider());

        // Heading for the list of offers
        var commentsHeading = new Heading("Additional information", HeadingSize.M);

        // Creating the comments view...
        var commentsView = new Column();
        var commentsField = new TextArea();
        if (comments != null) {
            commentsField.setText(comments);
        }
        var commentsForm = new InputForm(
            "Comments",
            UIConstants.ORDER_COMMENTS_CAPTION,
            commentsField);
        commentsView.addWidget(commentsHeading);
        commentsView.addWidget(commentsForm, new EdgeInsets.Builder()
            .top(24)
            .build());

        // Add the padded comments view to the main panel
        var paddedCommentsView = new Column();
        paddedCommentsView.addWidget(commentsView, new EdgeInsets.Builder()
            .symmetric(24, 40)
            .bottom(16)
            .build());
        panel.addWidget(paddedCommentsView);

        panel.addComponent(new Divider());

        // Heading for the list of offers
        var tipsHeading = new Heading("Delivery tip", HeadingSize.M);

        // Creating the tips view...
        var tipsView = new Column();
        var tipsParagraph = new Paragraph(
            UIConstants.ORDER_DELIVERY_TIP_PROMO,
            ParagraphSize.M,
            Colors.gray600);
        tipsView.addWidget(tipsHeading);
        tipsView.addWidget(tipsParagraph, new EdgeInsets.Builder()
            .top(24)
            .bottom(16)
            .build());

        // Add the padded tips view to the main panel
        var paddedTipsView = new Column();
        paddedTipsView.addWidget(tipsView, new EdgeInsets.Builder()
            .symmetric(24, 40)
            .bottom(16)
            .build());
        panel.addWidget(paddedTipsView);

        // Creating the tip buttons...
        var tips = new double[]{0.5f, 1f, 2f, 3f, 5f};
        var tipsWidget = new Row();
        for (double tip : tips) {
            var tipButton = new PillButton(
                String.format(tip % 1 == 0 ? "%.0f€" : "%.2f€", tip),
                ButtonSize.S,
                ButtonType.SECONDARY,
                e -> {
                    totalWithTip.setValue(total + tip);
                    Store.orderDeliveryTip.setValue(tip);
                    orderInfoText.setText(formatPaymentDetails(
                        totalWithTip.getValue(),
                        Store.orderPaymentMethod.getValue()));
                });
            tipsWidget.addWidget(tipButton, new EdgeInsets.Builder()
                .right(16)
                .build());
        }

        // Add the tip buttons to the main panel
        panel.addWidget(tipsWidget, new EdgeInsets.Builder()
                .bottom(24)
                .build(),
            Align.CENTER);

        // Creating the checkout button...
        var checkoutButton = new PillButton(
            "Place Order",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                var cart = items.entrySet().stream()
                    .collect(Collectors.toMap(
                        entry -> entry.getKey().toString(),
                        Map.Entry::getValue));
                var newOrder = new Order(
                    UUID.randomUUID(),
                    merchant,
                    new Date(),
                    cart,
                    Store.orderDeliveryTip.getValue(),
                    totalWithTip.getValue(),
                    Store.orderPaymentMethod.getValue(),
                    commentsField.getText());
                placeOrder(newOrder);
                Router.pushPage(Pages.HOME);
            });

        // Add the checkout button to the main panel
        var paddedCheckoutButton = new Row();
        paddedCheckoutButton.addWidget(checkoutButton, new EdgeInsets.Builder()
            .symmetric(10, 40)
            .bottom(24)
            .build());
        panel.addWidget(paddedCheckoutButton, Align.CENTER);

        return new ScrollView(panel.getRef()).getRef();
    }

    private String formatPaymentDetails(double total, PaymentMethod method) {
        return String.format("Total: %.2f€ | Payment: %s", total, method);
    }

    private void placeOrder(Order order) {
        user.orders().add(order);
        db.updateUser(user);
        items.clear();
        Router.openOverlay(new Alert(
            UIConstants.ORDER_SUCCESSFUL_TITLE,
            UIConstants.ORDER_SUCCESSFUL_MESSAGE,
            Router::closeOverlay));
    }

}
