package com.foodverse.views;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Colors;
import javax.swing.border.Border;

public final class ProductView extends Widget {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Colors.black));
        panel.setPreferredSize(new Dimension(1250, 100));
        panel.setBackground(Colors.white);

        // Border
        Border boarder = BorderFactory.createLineBorder(Colors.black);

        var products = new JPanel();
        products.setBorder(boarder);
        products.setPreferredSize(new Dimension(1000, 200));
        products.setBackground(Colors.white);
        panel.add(products);
        return panel;

    }

}
