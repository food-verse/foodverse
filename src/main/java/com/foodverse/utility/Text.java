package com.foodverse.utility;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;

public abstract class Text extends Widget {

    private final JLabel component;

    protected Text(String data, TextStyle textStyle) {
        component = new JLabel(data);
        component.setFont(new Font(textStyle.getAttributes()));
    }

    @Override
    public Component getRef() {
        return component;
    }

}
