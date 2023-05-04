package com.foodverse.utility.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import com.foodverse.utility.core.Widget;

public abstract class Button extends Widget {

    private final BaseButton component = new BaseButton();

    protected Button(String data, Consumer<ActionEvent> onPressed, ButtonStyle buttonStyle) {
        component.setText(data);
        component.addActionListener(onPressed::accept);
        component.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        component.setStyle(buttonStyle);
        component.init();
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

    public void setText(String data) {
        component.setText(data);
        component.applyStyle();
    }

    public void toggle() {
        component.toggle();
        component.applyStyle();
    }

    @Override
    public Component getRef() {
        return component;
    }

    public enum ButtonSize {
        XS, S, M, L
    }

    public enum ButtonType {
        PRIMARY, SECONDARY, TERTIARY
    }

}
