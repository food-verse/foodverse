package com.foodverse.widgets.text;

import java.awt.Color;

import com.foodverse.utility.navigation.Shell;
import com.foodverse.utility.ui.FontStyle;
import com.foodverse.utility.ui.FontStyle.FontWeight;
import com.foodverse.utility.ui.Text;
import com.foodverse.utility.ui.TextStyle;

public final class Heading extends Text {

    public Heading(String data, HeadingSize fontSize) {
        this(data, fontSize, new TextStyle.Builder().build());
    }

    public Heading(String data, HeadingSize fontSize, Color color) {
        this(data, fontSize, new TextStyle.Builder().color(color).build());
    }

    public Heading(String data, HeadingSize fontSize, TextStyle textStyle) {
        super(data, getFontStyle(fontSize, textStyle.isMono()), textStyle);
    }

    public static FontStyle getFontStyle(HeadingSize fontSize, boolean isMono) {
        return new FontStyle.Builder()
            .family(isMono
                ? Shell.getOptions().getMonospacedFont()
                : Shell.getOptions().getDefaultFont())
            .weight(FontWeight.MEDIUM)
            .size(fontSize.getSize())
            .build();
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
