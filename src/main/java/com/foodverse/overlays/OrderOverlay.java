package com.foodverse.overlays;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.Border;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.utility.ui.Colors;
import com.foodverse.views.AddressView;
import com.foodverse.views.AmountView;
import com.foodverse.views.PaymentView;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

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

                // Border
                Border boarder = BorderFactory.createLineBorder(Colors.black);

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
                var products = new JPanel();
                products.setBorder(boarder);
                products.setPreferredSize(new Dimension(1000, 200));
                products.setBackground(Colors.white);
                panel.add(products);

                // Tips
                var tips = new JPanel();
                tips.setBorder(boarder);
                tips.setPreferredSize(new Dimension(1200, 80));
                tips.setBackground(Colors.white);
                var tipLabel = new Label("Tips:", LabelSize.L);
                tips.add(tipLabel.getRef());

                var tip1 = new PillButton("0.5$",
                                ButtonSize.XS,
                                ButtonType.SECONDARY,
                                e -> {
                                });
                var tip2 = new PillButton("1$",
                                ButtonSize.XS,
                                ButtonType.SECONDARY,
                                e -> {
                                });
                var tip3 = new PillButton("2$",
                                ButtonSize.XS,
                                ButtonType.SECONDARY,
                                e -> {
                                });
                var tip4 = new PillButton("5$",
                                ButtonSize.XS,
                                ButtonType.SECONDARY,
                                e -> {
                                });

                tips.add(tip1.getRef());
                tips.add(tip2.getRef());
                tips.add(tip3.getRef());
                tips.add(tip4.getRef());
                panel.add(tips);

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
