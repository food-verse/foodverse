package com.foodverse.widgets.text;

import java.awt.Color;
import com.foodverse.utility.ui.Colors;
import com.foodverse.utility.ui.Text;
import com.foodverse.utility.ui.TextStyle;
import com.foodverse.utility.ui.TextStyle.FontWeight;

public final class Label extends Text {

    public Label(String data, LabelSize fontSize) {
        this(data, fontSize, false, Colors.black);
    }

    public Label(String data, LabelSize fontSize, Color color) {
        this(data, fontSize, false, color);
    }

    public Label(String data, LabelSize fontSize, boolean isMono) {
        super(data, getTextStyle(fontSize, isMono, Colors.black));
    }

    public Label(String data, LabelSize fontSize, boolean isMono, Color color) {
        super(data, getTextStyle(fontSize, isMono, color));
    }

    public static TextStyle getTextStyle(LabelSize fontSize, boolean isMono, Color color) {
        return new TextStyle.Builder()
                .family(isMono ? "IBM Plex Mono" : "Inter")
                .weight(FontWeight.MEDIUM)
                .size(fontSize.getSize())
                .color(color)
                .build();
    }

    public static TextStyle getTextStyle(LabelSize fontSize, boolean isMono) {
        return getTextStyle(fontSize, isMono, Colors.black);
    }

    public static TextStyle getTextStyle(LabelSize fontSize) {
        return getTextStyle(fontSize, false, Colors.black);
    }

    public enum LabelSize {
        XS(12), S(14), M(16), L(18);

        private final int size;

        LabelSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

    }

}
