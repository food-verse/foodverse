package com.foodverse.widgets.layout;

import java.awt.*;
import javax.swing.*;
import com.foodverse.utility.Widget;
import com.foodverse.utility.core.layout.Align;
import com.foodverse.utility.core.layout.EdgeInsets;

public final class Column extends Widget {

    private final JPanel component = new JPanel();
    private int order = 0;

    public Column() {
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
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.weightx = 1.0;
        constraints.gridy = order++;
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
