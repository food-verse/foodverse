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

public final class PillButton extends Button {

    public PillButton(String data, ButtonSize buttonSize, ButtonType buttonType,
            Consumer<ActionEvent> onPressed) {
        this(data, true, buttonSize, buttonType, onPressed);
    }

    public PillButton(String data, boolean isEnabled, ButtonSize buttonSize,
            ButtonType buttonType, Consumer<ActionEvent> onPressed) {
        super(data, onPressed, getButtonStyle(isEnabled, buttonSize, getButtonTheme(buttonType)));
    }

    public static ButtonStyle getButtonStyle(boolean isEnabled, ButtonSize buttonSize,
            ButtonTheme buttonTheme) {
        final TextStyle textStyle;
        final EdgeInsets padding;
        final int minimumWidth;
        final int borderRadius;
        switch (buttonSize) {
            default:
            case XS:
                textStyle = Label.getTextStyle(LabelSize.XS);
                padding = new EdgeInsets.Builder().symmetric(6, 10).build();
                minimumWidth = 52;
                borderRadius = 28;
                break;
            case S:
                textStyle = Label.getTextStyle(LabelSize.S);
                padding = new EdgeInsets.Builder().symmetric(10, 12).build();
                minimumWidth = 60;
                borderRadius = 36;
                break;
            case M:
                textStyle = Label.getTextStyle(LabelSize.M);
                padding = new EdgeInsets.Builder().symmetric(14, 16).build();
                minimumWidth = 72;
                borderRadius = 999;
                break;
        }
        return new ButtonStyle.Builder()
                .buttonTheme(buttonTheme)
                .textStyle(textStyle)
                .padding(padding)
                .minimumWidth(minimumWidth)
                .borderRadius(borderRadius)
                .isEnabled(isEnabled)
                .build();
    }

}
