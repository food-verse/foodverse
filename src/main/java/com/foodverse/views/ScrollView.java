package com.foodverse.views;

import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import com.foodverse.utility.Widget;
import com.foodverse.utility.core.ui.Colors;

public final class ScrollView extends Widget {

    private final JScrollPane component;

    public ScrollView(Component view) {
        component = new JScrollPane(view);
        component.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        component.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        component.getViewport().setOpaque(false);
        component.setBackground(Colors.white);
        component.setBorder(null);
    }

    @Override
    public Component getRef() {
        return component;
    }

}
