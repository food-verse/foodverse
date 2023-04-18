package com.foodverse.views;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Colors;
import javax.swing.border.Border;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

public final class AmountView extends Widget {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Colors.black));
        panel.setPreferredSize(new Dimension(1250, 100));
        panel.setBackground(Colors.white);

        // Border
        Border boarder = BorderFactory.createLineBorder(Colors.black);

        var amount = new JPanel();
        amount = new JPanel();
        amount.setBorder(boarder);
        amount.setPreferredSize(new Dimension(600, 100));
        var amountLabel = new Label("Total:", LabelSize.L);
        amount.add(amountLabel.getRef());
        amount.setBackground(Colors.white);
        panel.add(amount);

        return panel;
    }

}
