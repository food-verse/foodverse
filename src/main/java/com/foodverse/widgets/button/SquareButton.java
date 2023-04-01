package com.foodverse.widgets.button;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import com.foodverse.utility.core.Button;
import com.foodverse.utility.core.ButtonStyle;

public final class SquareButton extends Button {

    public SquareButton(String data, SquareButtonSize size, SquareButtonType type,
            Consumer<ActionEvent> onPressed) {
        super(data, onPressed, getButtonStyle(size, type));
    }

    public static ButtonStyle getButtonStyle(SquareButtonSize size, SquareButtonType type) {
        return new ButtonStyle.Builder().build();
    }

    public enum SquareButtonSize {
        XS, S, M, L;
    }

    public enum SquareButtonType {
        SECONDARY, TERTIARY;
    }

}
