package com.foodverse.widgets.layout;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;

abstract class Flex extends Widget {

    protected final JPanel container = new JPanel();
    protected final Map<Component, GridBagConstraints> flexItems = new HashMap<>();
    protected int order = 0;

    public Flex() {
        container.setLayout(new GridBagLayout());
        container.setOpaque(false);
    }

    public abstract void addComponent(Component widget, EdgeInsets edgeInsets, Align align);

    public void addComponent(Component widget, Align align) {
        addComponent(widget, new EdgeInsets.Builder().build(), align);
    }

    public void addWidget(Widget widget, EdgeInsets edgeInsets, Align align) {
        addComponent(widget.getRef(), edgeInsets, align);
    }

    public void addWidget(Widget widget, Align align) {
        addComponent(widget.getRef(), new EdgeInsets.Builder().build(), align);
    }

    public abstract void replaceComponent(Component oldComponent, Component newComponent);

    public void replaceWidget(Widget oldWidget, Widget newWidget) {
        replaceComponent(oldWidget.getRef(), newWidget.getRef());
    }

    protected abstract void add(Component component, GridBagConstraints constraints);

    public void refresh() {
        container.revalidate();
        container.repaint();
    }

    @Override
    public Component getRef() {
        return container;
    }

}
