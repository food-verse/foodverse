package com.foodverse.overlays;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
// import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import com.foodverse.utility.Overlay;
import com.foodverse.utility.Router;
import com.foodverse.utility.core.Colors;
import com.foodverse.utility.core.Button.ButtonSize;
import com.foodverse.utility.core.Button.ButtonType;
import com.foodverse.views.AddressView;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Heading.HeadingSize;
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

                // Payment
                var payment = new JPanel();
                payment.setBorder(boarder);
                payment.setPreferredSize(new Dimension(600, 100));
                payment.setBackground(Colors.white);

                // Card Details

                var cardNumber = new JTextArea("Card Number ");
                cardNumber.setBorder(boarder);
                var Name = new JTextArea("Name ");
                Name.setBorder(boarder);
                var secCode = new JTextArea("Secret Code ");
                secCode.setBorder(boarder);
                var Date = new JTextArea("Day/Year ");
                Date.setBorder(boarder);

                payment.add(cardNumber);
                payment.add(Name);
                payment.add(secCode);
                payment.add(Date);

                // Buttons for payment

                var CardDetails = new JRadioButton("Card ");
                var Cash = new JRadioButton("Cash ");
                var Take = new JRadioButton("Take away ");
                var group = new ButtonGroup();
                group.add(CardDetails);
                group.add(Take);
                group.add(Cash);

                payment.add(CardDetails);
                payment.add(Cash);
                payment.add(Take);

                panel.add(payment);

                // Amount
                var amount = new JPanel();
                amount = new JPanel();
                amount.setBorder(boarder);
                amount.setPreferredSize(new Dimension(600, 100));
                var amountLabel = new Label("Total:", LabelSize.L);
                amount.add(amountLabel.getRef());
                amount.setBackground(Colors.white);
                panel.add(amount);

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
                JOptionPane.showMessageDialog(getFrame(), "Your order has been successfully registered!");
        }

}
