package com.foodverse.widgets.layout;

import java.awt.Component;
import javax.swing.*;

import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Colors;

public final class ScrollView extends Widget {

    private final JScrollPane component;

    public ScrollView(Component view) {
        component = new JScrollPane(view);
        component.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        component.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        component.getViewport().setOpaque(false);
        component.setBackground(Colors.white);
        component.setBorder(null);
        JScrollBar scrollBar = component.getVerticalScrollBar();
        scrollBar.setUnitIncrement(10);
        scrollBar.setBlockIncrement(120);
    }

    @Override
    public Component getRef() {
        return component;
    }

}
