package com.foodverse.widgets.text;

import com.foodverse.utility.core.Text;
import com.foodverse.utility.core.TextStyle;
import com.foodverse.utility.core.TextStyle.FontWeight;

public final class Paragraph extends Text {

    public Paragraph(String data, ParagraphSize fontSize) {
        this(data, fontSize, false);
    }

    public Paragraph(String data, ParagraphSize fontSize, boolean isMono) {
        super(data, getTextStyle(fontSize, isMono));
    }

    public static TextStyle getTextStyle(ParagraphSize fontSize, boolean isMono) {
        return new TextStyle.Builder()
                .family(isMono ? "IBM Plex Mono" : "Inter")
                .weight(FontWeight.REGULAR)
                .size(fontSize.getSize())
                .build();
    }

    public static TextStyle getTextStyle(ParagraphSize fontSize) {
        return getTextStyle(fontSize, false);
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
