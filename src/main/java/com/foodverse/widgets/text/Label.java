package com.foodverse.widgets.text;

import java.awt.Color;

import com.foodverse.utility.navigation.Shell;
import com.foodverse.utility.ui.FontStyle;
import com.foodverse.utility.ui.FontStyle.FontWeight;
import com.foodverse.utility.ui.Text;
import com.foodverse.utility.ui.TextStyle;

public final class Label extends Text {

    public Label(String data, LabelSize fontSize) {
        this(data, fontSize, new TextStyle.Builder().build());
    }

    public Label(String data, LabelSize fontSize, Color color) {
        this(data, fontSize, new TextStyle.Builder().color(color).build());
    }

    public Label(String data, LabelSize fontSize, TextStyle textStyle) {
        super(data, getFontStyle(fontSize, textStyle.isMono()), textStyle);
    }

    public static FontStyle getFontStyle(LabelSize fontSize, boolean isMono) {
        return new FontStyle.Builder()
            .family(isMono
                ? Shell.getOptions().getMonospacedFont()
                : Shell.getOptions().getDefaultFont())
            .weight(FontWeight.MEDIUM)
            .size(fontSize.getSize())
            .build();
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
