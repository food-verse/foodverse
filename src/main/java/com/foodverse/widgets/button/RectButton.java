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

    public RectButton(String data, ButtonSize size, ButtonType type,
            Consumer<ActionEvent> onPressed) {
        this(data, true, size, type, onPressed);
    }

    public RectButton(String data, boolean isEnabled, ButtonSize size, ButtonType type,
            Consumer<ActionEvent> onPressed) {
        super(data, onPressed, getButtonStyle(isEnabled, size, getButtonTheme(type)));
    }

    public static ButtonStyle getButtonStyle(boolean isEnabled, ButtonSize buttonSize,
            ButtonTheme buttonTheme) {
        final TextStyle textStyle;
        final EdgeInsets padding = new EdgeInsets.Builder().symmetric(6, 10).build();
        final int borderRadius = 8;
        switch (buttonSize) {
            default:
            case XS:
                textStyle = Label.getTextStyle(LabelSize.XS);
                break;
            case S:
                textStyle = Label.getTextStyle(LabelSize.S);
                break;
            case M:
                textStyle = Label.getTextStyle(LabelSize.M);
                break;
            case L:
                textStyle = Label.getTextStyle(LabelSize.L);
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
