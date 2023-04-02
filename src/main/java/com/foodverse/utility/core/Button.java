package com.foodverse.utility.core;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import com.foodverse.utility.Widget;

public abstract class Button extends Widget {

    private final BaseButton component = new BaseButton();

    protected Button(String data, Consumer<ActionEvent> onPressed, ButtonStyle buttonStyle) {
        component.setText(data);
        component.addActionListener(onPressed::accept);
        component.setStyle(buttonStyle);
        component.init();
    }

    public void setText(String data) {
        component.setText(data);
        component.applyStyle();
    }

    public void toggle() {
        component.toggle();
        component.applyStyle();
    }

    protected static ButtonTheme getButtonTheme(ButtonType buttonType) {
        switch (buttonType) {
            case PRIMARY:
                return new ButtonTheme.Builder().build();
            default:
            case SECONDARY:
                return new ButtonTheme.Builder()
                        .defaultBackgroundColor(Colors.buttonSecondaryFill)
                        .defaultTextColor(Colors.buttonSecondaryText)
                        .hoverBackgroundColor(Colors.buttonSecondaryHover)
                        .hoverTextColor(Colors.buttonSecondaryText)
                        .pressBackgroundColor(Colors.buttonSecondaryActive)
                        .pressTextColor(Colors.buttonSecondaryText)
                        .build();
            case TERTIARY:
                return new ButtonTheme.Builder()
                        .defaultBackgroundColor(Colors.buttonTertiaryFill)
                        .defaultTextColor(Colors.buttonTertiaryText)
                        .hoverBackgroundColor(Colors.buttonTertiaryHover)
                        .hoverTextColor(Colors.buttonTertiaryText)
                        .pressBackgroundColor(Colors.buttonTertiaryActive)
                        .pressTextColor(Colors.buttonTertiaryText)
                        .disabledBackgroundColor(Colors.buttonTertiaryFill)
                        .build();
        }
    }

    public enum ButtonSize {
        XS, S, M, L;
    }

    public enum ButtonType {
        PRIMARY, SECONDARY, TERTIARY;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
