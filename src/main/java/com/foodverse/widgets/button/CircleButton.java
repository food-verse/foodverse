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

public final class CircleButton extends Button {

    public CircleButton(
        String data,
        ButtonSize buttonSize,
        ButtonType buttonType,
        Consumer<ActionEvent> onPressed,
        boolean isMono
    ) {
        this(data, true, buttonSize, buttonType, onPressed, isMono);
    }

    public CircleButton(
        String data,
        ButtonSize buttonSize,
        ButtonType buttonType,
        Consumer<ActionEvent> onPressed
    ) {
        this(data, true, buttonSize, buttonType, onPressed, false);
    }

    public CircleButton(
        String data,
        boolean isEnabled,
        ButtonSize buttonSize,
        ButtonType buttonType, Consumer<ActionEvent> onPressed
    ) {
        this(data, isEnabled, buttonSize, buttonType, onPressed, false);
    }

    public CircleButton(
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
        EdgeInsets padding;
        int borderRadius;
        switch (buttonSize) {
            case XS:
                fontStyle = Label.getFontStyle(LabelSize.XS, isMono);
                padding = new EdgeInsets.Builder().symmetric(6, 7).build();
                borderRadius = 36;
                break;
            case S:
                fontStyle = Label.getFontStyle(LabelSize.S, isMono);
                padding = new EdgeInsets.Builder().symmetric(10, 10).build();
                borderRadius = 36;
                break;
            case M:
                fontStyle = Label.getFontStyle(LabelSize.M, isMono);
                padding = new EdgeInsets.Builder().symmetric(14, 15).build();
                borderRadius = 48;
                break;
            default:
            case L:
                fontStyle = Label.getFontStyle(LabelSize.L, isMono);
                padding = new EdgeInsets.Builder().symmetric(16, 18).build();
                borderRadius = 56;
                break;
        }
        return new ButtonStyle.Builder()
            .buttonTheme(buttonTheme)
            .textStyle(fontStyle)
            .padding(padding)
            .borderRadius(borderRadius)
            .isEnabled(isEnabled)
            .build();
    }

}
