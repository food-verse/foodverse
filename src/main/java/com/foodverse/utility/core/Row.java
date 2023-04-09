package com.foodverse.utility.core;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import com.foodverse.utility.EdgeInsets;
import com.foodverse.utility.Widget;

public final class Row extends Widget {

    private final JPanel component = new JPanel();
    private int order = 0;

    public Row() {
        component.setLayout(new GridBagLayout());
        component.setOpaque(false);
    }

    public void addWidget(Widget widget, Align align) {
        addWidget(widget, new EdgeInsets.Builder().build(), align);
    }

    public void addWidget(Widget widget, EdgeInsets edgeInsets, Align align) {
        addComponent(widget.getRef(), edgeInsets, align);
    }

    public void addComponent(Component widget, EdgeInsets edgeInsets, Align align) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.gridx = order++;
        constraints.anchor = align.getAnchor();
        constraints.insets = new Insets(
                edgeInsets.getTop(), edgeInsets.getLeft(),
                edgeInsets.getBottom(), edgeInsets.getRight());
        component.add(widget, constraints);
    }

    @Override
    public Component getRef() {
        return component;
    }

}
