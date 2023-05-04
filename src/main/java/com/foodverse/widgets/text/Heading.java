package com.foodverse.widgets.text;

import java.awt.Color;

import com.foodverse.utility.navigation.Shell;
import com.foodverse.utility.ui.Colors;
import com.foodverse.utility.ui.Text;
import com.foodverse.utility.ui.TextStyle;
import com.foodverse.utility.ui.TextStyle.FontWeight;

public final class Heading extends Text {

    public Heading(String data, HeadingSize fontSize) {
        this(data, fontSize, false, Colors.black);
    }

    public Heading(String data, HeadingSize fontSize, Color color) {
        this(data, fontSize, false, color);
    }

    public Heading(String data, HeadingSize fontSize, boolean isMono) {
        super(data, getTextStyle(fontSize, isMono, Colors.black));
    }

    public Heading(String data, HeadingSize fontSize, boolean isMono, Color color) {
        super(data, getTextStyle(fontSize, isMono, color));
    }

    public static TextStyle getTextStyle(HeadingSize fontSize, boolean isMono, Color color) {
        return new TextStyle.Builder()
            .family(isMono
                ? Shell.getOptions().getMonospacedFont()
                : Shell.getOptions().getDefaultFont())
            .weight(FontWeight.MEDIUM)
            .size(fontSize.getSize())
            .color(color)
            .build();
    }

    public static TextStyle getTextStyle(HeadingSize fontSize, boolean isMono) {
        return getTextStyle(fontSize, isMono, Colors.black);
    }

    public static TextStyle getTextStyle(HeadingSize fontSize) {
        return getTextStyle(fontSize, false, Colors.black);
    }

    public enum HeadingSize {
        XS(20), S(24), M(28), L(32), XL(36), XXL(40);

        private final int size;

        HeadingSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

    }

}
