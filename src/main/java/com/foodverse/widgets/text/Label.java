package com.foodverse.widgets.text;

import com.foodverse.utility.Text;
import com.foodverse.utility.TextStyle;
import com.foodverse.utility.TextStyle.FontWeight;

public final class Label extends Text {

    public Label(String data, LabelSize fontSize) {
        this(data, fontSize, false);
    }

    public Label(String data, LabelSize fontSize, boolean isMono) {
        super(data, new TextStyle.Builder()
                .family(isMono ? "IBM Plex Mono" : "Inter")
                .weight(FontWeight.MEDIUM)
                .size(fontSize.getSize())
                .build());
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
