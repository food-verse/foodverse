package com.foodverse.widgets.text;

import java.awt.Color;

import com.foodverse.utility.navigation.Shell;
import com.foodverse.utility.ui.FontStyle;
import com.foodverse.utility.ui.FontStyle.FontWeight;
import com.foodverse.utility.ui.Text;
import com.foodverse.utility.ui.TextStyle;

public final class Paragraph extends Text {

    public Paragraph(String data, ParagraphSize fontSize) {
        this(data, fontSize, new TextStyle.Builder().build());
    }

    public Paragraph(String data, ParagraphSize fontSize, Color color) {
        this(data, fontSize, new TextStyle.Builder().color(color).build());
    }

    public Paragraph(String data, ParagraphSize fontSize, TextStyle textStyle) {
        super(data, getFontStyle(fontSize, textStyle.isMono()), textStyle);
    }

    public static FontStyle getFontStyle(ParagraphSize fontSize, boolean isMono) {
        return new FontStyle.Builder()
            .family(isMono
                ? Shell.getOptions().getMonospacedFont()
                : Shell.getOptions().getDefaultFont())
            .weight(FontWeight.REGULAR)
            .size(fontSize.getSize())
            .build();
    }

    public enum ParagraphSize {
        XS(12), S(14), M(16), L(18);

        private final int size;

        ParagraphSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

    }

}
