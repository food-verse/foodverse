package com.foodverse.widgets.button;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import com.foodverse.utility.EdgeInsets;
import com.foodverse.utility.core.Button;
import com.foodverse.utility.core.ButtonStyle;
import com.foodverse.utility.core.ButtonTheme;
import com.foodverse.utility.core.TextStyle;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

public final class RectButton extends Button {

    public RectButton(String data, ButtonSize buttonSize, ButtonType buttonType,
            Consumer<ActionEvent> onPressed, boolean isMono) {
        this(data, true, buttonSize, buttonType, onPressed, isMono);
    }

    public RectButton(String data, ButtonSize buttonSize, ButtonType buttonType,
            Consumer<ActionEvent> onPressed) {
        this(data, true, buttonSize, buttonType, onPressed, false);
    }

    public RectButton(String data, boolean isEnabled, ButtonSize buttonSize,
            ButtonType buttonType, Consumer<ActionEvent> onPressed) {
        this(data, isEnabled, buttonSize, buttonType, onPressed, false);
    }

    public RectButton(String data, boolean isEnabled, ButtonSize buttonSize,
            ButtonType buttonType, Consumer<ActionEvent> onPressed, boolean isMono) {
        super(data, onPressed,
                getButtonStyle(isEnabled, buttonSize, getButtonTheme(buttonType), isMono));
    }

    public static ButtonStyle getButtonStyle(boolean isEnabled, ButtonSize buttonSize,
            ButtonTheme buttonTheme, boolean isMono) {
        final TextStyle textStyle;
        final EdgeInsets padding = new EdgeInsets.Builder().symmetric(6, 10).build();
        final int borderRadius = 8;
        switch (buttonSize) {
            default:
            case XS:
                textStyle = Label.getTextStyle(LabelSize.XS, isMono);
                break;
            case S:
                textStyle = Label.getTextStyle(LabelSize.S, isMono);
                break;
            case M:
                textStyle = Label.getTextStyle(LabelSize.M, isMono);
                break;
            case L:
                textStyle = Label.getTextStyle(LabelSize.L, isMono);
                break;
        }
        return new ButtonStyle.Builder()
                .buttonTheme(buttonTheme)
                .textStyle(textStyle)
                .padding(padding)
                .minimumWidth(52)
                .borderRadius(borderRadius)
                .isEnabled(isEnabled)
                .build();
    }

}
