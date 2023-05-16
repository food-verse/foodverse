package com.foodverse.views;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;

import com.foodverse.models.PaymentMethod;
import com.foodverse.overlays.CardOverlay;
import com.foodverse.state.Store;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.Colors;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;

public final class PaymentView extends Widget {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setBackground(Colors.white);

        var payment = new JPanel();
        payment.setPreferredSize(new Dimension(100, 100));
        payment.setBackground(Colors.white);

        var CardButton = new RectButton("Card",
                ButtonSize.XL,
                ButtonType.PRIMARY,
                e -> {
                    Store.orderPaymentMethod.setValue(PaymentMethod.CARD);
                    Router.openOverlay(new CardOverlay());
                });

        payment.add(CardButton.getRef());

        var CashButton = new RectButton("Cash",
                ButtonSize.XL,
                ButtonType.PRIMARY,
                e -> {
                    Store.orderPaymentMethod.setValue(PaymentMethod.CASH);
                });
        payment.add(CashButton.getRef());

        panel.add(payment);

        return panel;
    }

}
