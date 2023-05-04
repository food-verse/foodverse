package com.foodverse.views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.PillButton;
import java.awt.Component;
import java.awt.Dimension;


public final class showItemsView extends Widget {

    @Override
    public Component getRef() {


        var productAddButton = new PillButton("Add", ButtonSize.XS, ButtonType.SECONDARY, e -> {
            // addproducts();

        });
        var panel = new JPanel();
        var menuItem = new JLabel("Item");

        panel.setPreferredSize(new Dimension(1200, 60));
        panel.add(menuItem);
        panel.add(productAddButton.getRef());

        return panel;

    }
}
