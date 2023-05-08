package com.foodverse.overlays;

import java.awt.Component;
import java.util.Map;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.foodverse.models.User;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.views.AddressView;
import com.foodverse.views.AmountView;
import com.foodverse.views.PaymentView;
import com.foodverse.views.ProductView;
import com.foodverse.views.TipsView;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class OrderOverlay extends Overlay {

    private final Component component;

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    public OrderOverlay(String merchant, Map<String, Integer> items) {

        // Getting the authenticated user...
        db.getAuthenticatedUser().ifPresentOrElse(signedUser -> {
            // System.out.println(signedUser);
        }, () -> {
            // System.out.println("Authenticated user not found");
        });

        // Alternative
        Optional<User> signedUser = db.getAuthenticatedUser();
        if (signedUser.isPresent()) {
            // System.out.println(signedUser.get());
        } else {
            // System.out.println("Authenticated user not found");
        }

        // Heading
        var panel = new JPanel();
        var orderLabel = new Heading("Order", HeadingSize.XL);
        var button = new PillButton(
                "Close ProfileOverlay ->",
                ButtonSize.XS,
                ButtonType.SECONDARY,
                e -> {
                    Router.closeOverlay();
                });
        panel.add(button.getRef());
        panel.add(orderLabel.getRef());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Address
        AddressView view = new AddressView();
        panel.add(view.getRef());

        // Payment
        PaymentView payment = new PaymentView();
        panel.add(payment.getRef());

        // Amount
        AmountView amount = new AmountView();
        panel.add(amount.getRef());

        // Products
        ProductView products = new ProductView();
        panel.add(products.getRef());

        // Tips
        TipsView tips = new TipsView();
        panel.add(tips.getRef());

        var checkoutButton = new RectButton("Checkout ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    showSuccessfulOrderMessage();
                    Router.closeOverlay();
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

}
