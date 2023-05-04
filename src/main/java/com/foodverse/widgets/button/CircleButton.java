package com.foodverse.widgets.button;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.ui.Button;
import com.foodverse.utility.ui.ButtonStyle;
import com.foodverse.utility.ui.ButtonTheme;
import com.foodverse.utility.ui.TextStyle;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

public final class CircleButton extends Button {

    public CircleButton(String data, ButtonSize buttonSize, ButtonType buttonType,
            Consumer<ActionEvent> onPressed, boolean isMono) {
        this(data, true, buttonSize, buttonType, onPressed, isMono);
    }

    public CircleButton(String data, ButtonSize buttonSize, ButtonType buttonType,
            Consumer<ActionEvent> onPressed) {
        this(data, true, buttonSize, buttonType, onPressed, false);
    }

    public CircleButton(String data, boolean isEnabled, ButtonSize buttonSize,
            ButtonType buttonType, Consumer<ActionEvent> onPressed) {
        super(data, onPressed,
                getButtonStyle(isEnabled, buttonSize, getButtonTheme(buttonType), false));
    }

    public CircleButton(String data, boolean isEnabled, ButtonSize buttonSize,
            ButtonType buttonType, Consumer<ActionEvent> onPressed, boolean isMono) {
        super(data, onPressed,
                getButtonStyle(isEnabled, buttonSize, getButtonTheme(buttonType), isMono));
    }

    public static ButtonStyle getButtonStyle(boolean isEnabled, ButtonSize buttonSize,
            ButtonTheme buttonTheme, boolean isMono) {
        final TextStyle textStyle;
        final EdgeInsets padding;
        final int borderRadius;
        switch (buttonSize) {
            default:
            case XS:
                textStyle = Label.getTextStyle(LabelSize.XS, isMono);
                padding = new EdgeInsets.Builder().symmetric(6, 7).build();
                borderRadius = 36;
                break;
            case S:
                textStyle = Label.getTextStyle(LabelSize.S, isMono);
                padding = new EdgeInsets.Builder().symmetric(10, 10).build();
                borderRadius = 36;
                break;
            case M:
                textStyle = Label.getTextStyle(LabelSize.M, isMono);
                padding = new EdgeInsets.Builder().symmetric(14, 15).build();
                borderRadius = 48;
                break;
            case L:
                textStyle = Label.getTextStyle(LabelSize.L, isMono);
                padding = new EdgeInsets.Builder().symmetric(16, 18).build();
                borderRadius = 56;
                break;
        }
        return new ButtonStyle.Builder()
                .buttonTheme(buttonTheme)
                .textStyle(textStyle)
                .padding(padding)
                .borderRadius(borderRadius)
                .isEnabled(isEnabled)
                .build();
    }

}
