package com.foodverse.widgets.text;

import com.foodverse.utility.Text;
import com.foodverse.utility.TextStyle;
import com.foodverse.utility.TextStyle.FontWeight;

public final class Paragraph extends Text {

    public Paragraph(String data, ParagraphSize fontSize) {
        this(data, fontSize, false);
    }

    public Paragraph(String data, ParagraphSize fontSize, boolean isMono) {
        super(data, new TextStyle.Builder()
                .family(isMono ? "IBM Plex Mono" : null)
                .weight(FontWeight.REGULAR)
                .size(fontSize.getSize())
                .build());
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
