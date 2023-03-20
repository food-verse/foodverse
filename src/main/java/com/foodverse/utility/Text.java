package com.foodverse.utility;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;

public abstract class Text extends Widget {

    private final JLabel component = new JLabel();

    protected Text(String data, TextStyle textStyle) {
        component.setText(data);
        component.setFont(new Font(textStyle.getAttributes()));
    }

    @Override
    public Component getRef() {
        return component;
    }

}
