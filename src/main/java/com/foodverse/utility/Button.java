package com.foodverse.utility;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.JButton;

public final class Button extends Widget {

    private final JButton component = new JButton();

    public Button(Widget child, Consumer<ActionEvent> onPressed /* ButtonStyle buttonStyle */ ) {
        component.add(child.getRef());
        component.addActionListener(onPressed::accept);
    }

    @Override
    public Component getRef() {
        return component;
    }

}
