package com.foodverse.overlays;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
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

        @Override
        public Component getRef() {

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

                // Address
                AddressView view = new AddressView();
                panel.add(view.getRef());

                // // Payment

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
                                        showSuccessfulOrderMessage(e);
                                });

                panel.add(checkoutButton.getRef());
                panel.setOpaque(false);
                return panel;
        }

        private void showSuccessfulOrderMessage(ActionEvent e) {
                JOptionPane.showMessageDialog(getFrame(),
                                "Your order has been successfully registered!");
        }

}
