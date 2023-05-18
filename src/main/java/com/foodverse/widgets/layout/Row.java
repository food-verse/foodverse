package com.foodverse.widgets.layout;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;

public final class Row extends Flex {

    @Override
    public void addComponent(Component widget, EdgeInsets edgeInsets, Align align) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.gridx = order++;
        constraints.anchor = align.getAnchor();
        constraints.insets = new Insets(
            edgeInsets.getTop(), edgeInsets.getLeft(),
            edgeInsets.getBottom(), edgeInsets.getRight());
        add(widget, constraints);
    }

    @Override
    public void replaceComponent(Component oldComponent, Component newComponent) {
        var constraints = flexItems.get(oldComponent);
        if (constraints != null) {
            container.remove(constraints.gridx);
            add(newComponent, constraints);
            container.revalidate();
            container.repaint();
        }
    }

}
