package com.foodverse.widgets.text;

import java.awt.Color;

import com.foodverse.utility.navigation.Shell;
import com.foodverse.utility.ui.FontStyle;
import com.foodverse.utility.ui.FontStyle.FontWeight;
import com.foodverse.utility.ui.Text;
import com.foodverse.utility.ui.TextStyle;

public final class Display extends Text {

    public Display(String data, DisplaySize fontSize) {
        this(data, fontSize, new TextStyle.Builder().build());
    }

    public Display(String data, DisplaySize fontSize, Color color) {
        this(data, fontSize, new TextStyle.Builder().color(color).build());
    }

    public Display(String data, DisplaySize fontSize, TextStyle textStyle) {
        super(data, getFontStyle(fontSize, textStyle.isMono()), textStyle);
    }

    public static FontStyle getFontStyle(DisplaySize fontSize, boolean isMono) {
        return new FontStyle.Builder()
            .family(isMono
                ? Shell.getOptions().getMonospacedFont()
                : Shell.getOptions().getDefaultFont())
            .weight(FontWeight.MEDIUM)
            .size(fontSize.getSize())
            .build();
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
