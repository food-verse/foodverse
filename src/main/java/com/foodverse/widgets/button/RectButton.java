package com.foodverse.widgets.button;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import com.foodverse.utility.core.Button;
import com.foodverse.utility.core.ButtonStyle;

public final class RectButton extends Button {

    public RectButton(String data, RectButtonSize size, RectButtonType type,
            Consumer<ActionEvent> onPressed) {
        super(data, onPressed, getButtonStyle(size, type));
    }

    public static ButtonStyle getButtonStyle(RectButtonSize size, RectButtonType type) {
        return new ButtonStyle.Builder().build();
    }

    public enum RectButtonSize {
        XS, S, M, L;
    }

    public enum RectButtonType {
        PRIMARY, SECONDARY, TERTIARY;
    }

}
