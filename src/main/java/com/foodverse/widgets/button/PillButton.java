package com.foodverse.widgets.button;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import com.foodverse.utility.core.Button;
import com.foodverse.utility.core.ButtonStyle;

public final class PillButton extends Button {

    public PillButton(String data, Consumer<ActionEvent> onPressed) {
        super(data, onPressed, new ButtonStyle.Builder().build());
    }

    public enum PillButtonSize {
        XS, S, M;
    }

    public enum PillButtonType {
        SECONDARY, TERTIARY;
    }

}
