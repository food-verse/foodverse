package com.foodverse.widgets.text;

import com.foodverse.utility.core.Text;
import com.foodverse.utility.core.TextStyle;
import com.foodverse.utility.core.TextStyle.FontWeight;

public final class Heading extends Text {

    public Heading(String data, HeadingSize fontSize) {
        this(data, fontSize, false);
    }

    public Heading(String data, HeadingSize fontSize, boolean isMono) {
        super(data, getTextStyle(fontSize, isMono));
    }

    public static TextStyle getTextStyle(HeadingSize fontSize, boolean isMono) {
        return new TextStyle.Builder()
                .family(isMono ? "IBM Plex Mono" : "Inter")
                .weight(FontWeight.MEDIUM)
                .size(fontSize.getSize())
                .build();
    }

    public static TextStyle getTextStyle(HeadingSize fontSize) {
        return getTextStyle(fontSize, false);
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
