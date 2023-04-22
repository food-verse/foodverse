package com.foodverse.widgets.layout;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;

/**
 * A simple focusable box component. Draws "attention" to itself allowing other components, like
 * text fields, to keep their default state.
 */
public final class SizedBox extends Widget {

    private final JPanel component = new JPanel();

    public SizedBox(int width, int height) {
        component.setPreferredSize(new Dimension(width, height));
        component.setFocusable(true);
    }

    public SizedBox() {
        this(0, 0);
    }

    @Override
    public Component getRef() {
        return component;
    }

}
