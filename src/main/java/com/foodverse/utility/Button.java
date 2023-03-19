package com.foodverse.utility;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import javax.swing.JButton;

public abstract class Button extends Widget {

    private final JButton component;

    protected Button(Widget child, Consumer<ActionEvent> onPressed) {
        component = new JButton();
        component.add(child.getRef());
        component.addActionListener(onPressed::accept);
    }

    @Override
    public Component getRef() {
        return component;
    }

}
