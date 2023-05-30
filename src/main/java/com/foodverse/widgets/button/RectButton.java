package com.foodverse.widgets.button;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.ui.Button;
import com.foodverse.utility.ui.ButtonStyle;
import com.foodverse.utility.ui.ButtonTheme;
import com.foodverse.utility.ui.FontStyle;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

public final class RectButton extends Button {

    public RectButton(
        String data,
        ButtonSize buttonSize,
        ButtonType buttonType,
        Consumer<ActionEvent> onPressed,
        boolean isMono
    ) {
        this(data, true, buttonSize, buttonType, onPressed, isMono);
    }

    public RectButton(
        String data,
        ButtonSize buttonSize,
        ButtonType buttonType,
        Consumer<ActionEvent> onPressed
    ) {
        this(data, true, buttonSize, buttonType, onPressed, false);
    }

    public RectButton(
        String data,
        boolean isEnabled,
        ButtonSize buttonSize,
        ButtonType buttonType,
        Consumer<ActionEvent> onPressed
    ) {
        this(data, isEnabled, buttonSize, buttonType, onPressed, false);
    }

    public RectButton(
        String data,
        boolean isEnabled,
        ButtonSize buttonSize,
        ButtonType buttonType,
        Consumer<ActionEvent> onPressed,
        boolean isMono
    ) {
        super(data, onPressed, getButtonStyle(isEnabled, buttonSize, getButtonTheme(buttonType), isMono));
    }

    public static ButtonStyle getButtonStyle(
        boolean isEnabled,
        ButtonSize buttonSize,
        ButtonTheme buttonTheme,
        boolean isMono
    ) {
        FontStyle fontStyle;
        EdgeInsets padding = new EdgeInsets.Builder().symmetric(6, 10).build();
        int borderRadius = 8;
        switch (buttonSize) {
            case XS:
                fontStyle = Label.getFontStyle(LabelSize.XS, isMono);
                break;
            case S:
                fontStyle = Label.getFontStyle(LabelSize.S, isMono);
                break;
            case M:
                fontStyle = Label.getFontStyle(LabelSize.M, isMono);
                break;
            default:
            case L:
                fontStyle = Label.getFontStyle(LabelSize.L, isMono);
                break;
        }
        return new ButtonStyle.Builder()
            .buttonTheme(buttonTheme)
            .textStyle(fontStyle)
            .padding(padding)
            .minimumWidth(52)
            .borderRadius(borderRadius)
            .isEnabled(isEnabled)
            .build();
    }

}
