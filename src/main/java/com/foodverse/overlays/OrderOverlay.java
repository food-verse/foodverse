package com.foodverse.overlays;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.foodverse.models.Order;
import com.foodverse.models.OrderType;
import com.foodverse.models.PaymentMethod;
import com.foodverse.models.Shop;
import com.foodverse.models.User;
import com.foodverse.state.Store;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Colors;
import com.foodverse.utility.ui.Divider;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.views.AddressView;
import com.foodverse.views.AmountView;
import com.foodverse.views.PaymentView;
import com.foodverse.views.ProductView;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Label.LabelSize;

public final class OrderOverlay extends Overlay {

    private final Component component;

    // Getting a reference to the database...
    private final Database db = Database.getInstance();
    float total = 0;
    PaymentMethod method = PaymentMethod.CASH;
    OrderType type = OrderType.DELIVERY;
    PillButton tip1, tip2, tip3, tip4;
    float deltip = 0;

    public OrderOverlay(String merchant, Map<String, Integer> items) {
        super(800, 680);

        // Alternative
        Optional<User> signedUser = db.getAuthenticatedUser();
        if (signedUser.isPresent()) {
            // System.out.println(signedUser.get());
            // signedUser.get().orders().add();
        } else {
            // System.out.println("Authenticated user not found");
        }

        // Heading
        var panel = new JPanel();
        var orderLabel = new Heading("Your Order", HeadingSize.XL);

        panel.add(orderLabel.getRef());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Amount
        Optional<Shop> shop = db.findShopByName(merchant);

        for (int j = 0; j < shop.get().menu().size(); j++) {
            for (String item : items.keySet()) {
                if (item.equals(shop.get().menu().get(j).name())) {
                    total = total + shop.get().menu().get(j).price();
                }
            }
        }
        AmountView amount = new AmountView(total);
        panel.add(amount.getRef());
        panel.add(new Divider());

        // Address
        AddressView view = new AddressView(signedUser);
        panel.add(view.getRef());
        panel.add(new Divider());

        // Payment
        PaymentView payment = new PaymentView();
        panel.add(payment.getRef());
        panel.add(new Divider());

        // Products
        ProductView products = new ProductView(items);
        panel.add(products.getRef());
        panel.add(new Divider());

        // Tips

        var tips = new JPanel();
        tips.setPreferredSize(new Dimension(200, 80));
        tips.setBackground(Colors.white);
        var tipLabel = new Label("Tips:", LabelSize.L);
        tips.add(tipLabel.getRef());

        tip1 = new PillButton("0.5$",
            ButtonSize.XS,
            ButtonType.SECONDARY,
            e -> {
                addTip(e, total);
                deltip = (float) 0.5;
            });
        tip2 = new PillButton("1$",
            ButtonSize.XS,
            ButtonType.SECONDARY,
            e -> {
                addTip(e, total);
                deltip = 1;
            });
        tip3 = new PillButton("2$",
            ButtonSize.XS,
            ButtonType.SECONDARY,
            e -> {
                addTip(e, total);
                deltip = 2;
            });
        tip4 = new PillButton("5$",
            ButtonSize.XS,
            ButtonType.SECONDARY,
            e -> {
                addTip(e, total);
                deltip = 5;
            });

        tips.add(tip1.getRef());
        tips.add(tip2.getRef());
        tips.add(tip3.getRef());
        tips.add(tip4.getRef());

        panel.add(tips);

        // Checkout button
        var checkoutButton = new RectButton("Checkout ->",
            ButtonSize.XL,
            ButtonType.PRIMARY,
            e -> {
                if (total > shop.get().minOrder()) {
                    // take the date
                    Date currentDate = new Date();
                    // Save the user's order
                    method = Store.orderPaymentMethod.getValue();
                    signedUser.get().orders()
                        .add(new Order(merchant, currentDate, items, deltip, total,
                            method, null, "No comments"));
                    showSuccessfulOrderMessage();
                    Router.closeOverlay();
                } else {
                    var x = shop.get().minOrder() - total;
                    JOptionPane.showMessageDialog(getFrame(),
                        "You need " + x + "$ to reach the minimum order!");
                }
            });

        panel.add(checkoutButton.getRef());
        panel.setOpaque(false);

        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

    private void showSuccessfulOrderMessage() {
        JOptionPane.showMessageDialog(getFrame(), "Your order has been successfully registered!");
    }

    private void addTip(ActionEvent e, float total) {
        if (e.getSource() == tip1.getRef()) {
            total += 0.5;
        } else if (e.getSource() == tip2.getRef()) {
            total += 1;
        } else if (e.getSource() == tip3.getRef()) {
            total += 2;
        } else {
            total += 5;
        }

    }

}
