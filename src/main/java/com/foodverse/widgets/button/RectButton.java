package com.foodverse.widgets.button;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import com.foodverse.utility.core.Button;
import com.foodverse.utility.core.ButtonStyle;

public final class RectButton extends Button {

    public RectButton(String data, Consumer<ActionEvent> onPressed) {
        super(data, onPressed, new ButtonStyle.Builder().build());
    }

    public enum PillButtonSize {
        XS, S, M, L;
    }

    public enum PillButtonType {
        PRIMARY, SECONDARY, TERTIARY;
    }

}
