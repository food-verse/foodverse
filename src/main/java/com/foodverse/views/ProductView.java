package com.foodverse.views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Colors;

public final class ProductView extends Widget {

    private final Component component;

    public ProductView(Map<String, Integer> items) {
        var panel = new JPanel();
        panel.setPreferredSize(new Dimension(50, 300));
        panel.setBackground(Colors.white);

        var products = new JPanel();
        var number = 0;
        for (String item : items.keySet()) {
            number++;
            var view = new JLabel(number + "." + item + "\n");
            changeFont(view, "Arial", Font.PLAIN, 16);
            // var price = new JLabel(item);
            panel.add(view);
        }
        products.setPreferredSize(new Dimension(50, 400));
        products.setBackground(Colors.white);
        panel.add(products);
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

    public static void changeFont(JLabel label, String fontName, int fontStyle, int fontSize) {
        Font font = new Font(fontName, fontStyle, fontSize);
        label.setFont(font);
    }

}
