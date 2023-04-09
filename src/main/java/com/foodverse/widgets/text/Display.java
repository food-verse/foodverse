package com.foodverse.widgets.text;

import java.awt.Color;
import com.foodverse.utility.core.ui.Colors;
import com.foodverse.utility.core.ui.Text;
import com.foodverse.utility.core.ui.TextStyle;
import com.foodverse.utility.core.ui.TextStyle.FontWeight;

public final class Display extends Text {

    public Display(String data, DisplaySize fontSize) {
        this(data, fontSize, false, Colors.black);
    }

    public Display(String data, DisplaySize fontSize, Color color) {
        this(data, fontSize, false, color);
    }

    public Display(String data, DisplaySize fontSize, boolean isMono) {
        super(data, getTextStyle(fontSize, isMono, Colors.black));
    }

    public Display(String data, DisplaySize fontSize, boolean isMono, Color color) {
        super(data, getTextStyle(fontSize, isMono, color));
    }

    public static TextStyle getTextStyle(DisplaySize fontSize, boolean isMono, Color color) {
        return new TextStyle.Builder()
                .family(isMono ? "IBM Plex Mono" : "Inter")
                .weight(FontWeight.MEDIUM)
                .size(fontSize.getSize())
                .color(color)
                .build();
    }

    public static TextStyle getTextStyle(DisplaySize fontSize, boolean isMono) {
        return getTextStyle(fontSize, isMono, Colors.black);
    }

    public static TextStyle getTextStyle(DisplaySize fontSize) {
        return getTextStyle(fontSize, false, Colors.black);
    }

    public enum DisplaySize {
        XS(36), S(44), M(52), L(96);

        private final int size;

        DisplaySize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

    }

}
