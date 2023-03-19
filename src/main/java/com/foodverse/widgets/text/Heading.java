package com.foodverse.widgets.text;

import com.foodverse.utility.Text;
import com.foodverse.utility.TextStyle;
import com.foodverse.utility.TextStyle.FontWeight;

public final class Heading extends Text {

    public Heading(String data, HeadingSize fontSize) {
        this(data, fontSize, false);
    }

    public Heading(String data, HeadingSize fontSize, boolean isMono) {
        super(data, new TextStyle.Builder()
                .family(isMono ? "IBM Plex Mono" : null)
                .weight(FontWeight.MEDIUM)
                .size(fontSize.getSize())
                .build());
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
