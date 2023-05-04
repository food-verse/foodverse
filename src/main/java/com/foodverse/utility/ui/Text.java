package com.foodverse.utility.ui;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;

import com.foodverse.utility.core.Widget;

public abstract class Text extends Widget {

    private final JLabel component = new JLabel();

    protected Text(String data, TextStyle textStyle) {
        component.setText(data);
        component.setForeground(textStyle.getColor());
        component.setFont(new Font(textStyle.getAttributes()));
    }

    public void setText(String data) {
        component.setText(data);
    }

    @Override
    public Component getRef() {
        return component;
    }

}
