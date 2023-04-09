package com.foodverse.widgets.text;

import java.awt.Color;
import com.foodverse.utility.core.Colors;
import com.foodverse.utility.core.Text;
import com.foodverse.utility.core.TextStyle;
import com.foodverse.utility.core.TextStyle.FontWeight;

public final class Paragraph extends Text {

    public Paragraph(String data, ParagraphSize fontSize) {
        this(data, fontSize, false, Colors.black);
    }

    public Paragraph(String data, ParagraphSize fontSize, Color color) {
        this(data, fontSize, false, color);
    }

    public Paragraph(String data, ParagraphSize fontSize, boolean isMono) {
        super(data, getTextStyle(fontSize, isMono, Colors.black));
    }

    public Paragraph(String data, ParagraphSize fontSize, boolean isMono, Color color) {
        super(data, getTextStyle(fontSize, isMono, color));
    }

    public static TextStyle getTextStyle(ParagraphSize fontSize, boolean isMono, Color color) {
        return new TextStyle.Builder()
                .family(isMono ? "IBM Plex Mono" : "Inter")
                .weight(FontWeight.REGULAR)
                .size(fontSize.getSize())
                .color(color)
                .build();
    }

    public static TextStyle getTextStyle(ParagraphSize fontSize, boolean isMono) {
        return getTextStyle(fontSize, isMono, Colors.black);
    }

    public static TextStyle getTextStyle(ParagraphSize fontSize) {
        return getTextStyle(fontSize, false, Colors.black);
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
