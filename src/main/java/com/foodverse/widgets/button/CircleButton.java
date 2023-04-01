package com.foodverse.widgets.button;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import com.foodverse.utility.core.Button;
import com.foodverse.utility.core.ButtonStyle;

public final class CircleButton extends Button {

    public CircleButton(String data, Consumer<ActionEvent> onPressed) {
        super(data, onPressed, new ButtonStyle.Builder().build());
    }

    public enum CircleButtonSize {
        XS, S, M, L;
    }

    public enum CircleButtonType {
        SECONDARY, TERTIARY;
    }

}
