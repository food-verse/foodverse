package com.foodverse.widgets.button;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import com.foodverse.utility.core.Button;
import com.foodverse.utility.core.ButtonStyle;

public final class SquareButton extends Button {

    public SquareButton(String data, Consumer<ActionEvent> onPressed) {
        super(data, onPressed, new ButtonStyle.Builder().build());
    }

    public enum SquareButtonSize {
        XS, S, M, L;
    }

    public enum SquareButtonType {
        SECONDARY, TERTIARY;
    }

}
