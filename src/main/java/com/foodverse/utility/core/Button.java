package com.foodverse.utility.core;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.JButton;
import com.foodverse.utility.Widget;

public abstract class Button extends Widget {

    private final JButton component = new JButton();

    protected Button(Widget child, Consumer<ActionEvent> onPressed, ButtonStyle buttonStyle) {
        component.add(child.getRef());
        component.addActionListener(onPressed::accept);
    }

    protected Button(String data, Consumer<ActionEvent> onPressed, ButtonStyle buttonStyle) {
        component.setText(data);
        component.addActionListener(onPressed::accept);
    }

    @Override
    public Component getRef() {
        return component;
    }

}
