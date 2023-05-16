package com.foodverse.views;

import java.awt.Component;
import java.awt.Dimension;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Colors;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

public final class AmountView extends Widget {

    private final Component component;

    public AmountView(float total) {
        var panel = new JPanel();

        panel.setPreferredSize(new Dimension(1250, 100));
        panel.setBackground(Colors.white);

        // // Border
        // Border boarder = BorderFactory.createLineBorder(Colors.black);

        var amount = new JPanel();

        DecimalFormat df = new DecimalFormat("#0.00");
        String towDigitTotal = df.format(total);

        // amount.setBorder(boarder);
        amount.setPreferredSize(new Dimension(600, 100));
        var amountLabel = new Label("Total: " + towDigitTotal, LabelSize.L);
        amount.add(amountLabel.getRef());
        amount.setBackground(Colors.white);
        panel.add(amount);

        component = panel;

    }

    @Override
    public Component getRef() {
        return component;
    }

}
