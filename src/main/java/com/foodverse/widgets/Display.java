package com.foodverse.widgets;

import com.foodverse.utility.Text;
import com.foodverse.utility.TextStyle;
import com.foodverse.utility.TextStyle.FontWeight;

public final class Display extends Text {

    public Display(String data, DisplaySize fontSize) {
        this(data, fontSize, false);
    }

    public Display(String data, DisplaySize fontSize, boolean isMono) {
        super(data, new TextStyle.Builder()
                .family(isMono ? "IBM Plex Mono" : null)
                .weight(FontWeight.MEDIUM)
                .size(fontSize.getSize())
                .build());
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
