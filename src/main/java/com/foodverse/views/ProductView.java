package com.foodverse.views;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Colors;

public final class ProductView extends Widget {

    private final Component component;

    public ProductView(Map<String, Integer> items) {
        var panel = new JPanel();
        panel.setPreferredSize(new Dimension(1250, 100));
        panel.setBackground(Colors.white);

        var products = new JPanel();
        var number = 0;
        for (String item : items.keySet()) {
            number++;
            var view = new JLabel(number + "." + item);
            panel.add(view);
        }
        products.setPreferredSize(new Dimension(200, 400));
        products.setBackground(Colors.white);
        panel.add(products);
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
