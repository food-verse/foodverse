package com.foodverse.utility.ui;

import java.awt.font.TextAttribute;
import java.util.Map;

import com.foodverse.utility.navigation.Shell;

public final class FontStyle {

    private final String fontFamily;
    private final FontWeight fontWeight;
    private final int fontSize;

    public static class Builder {

        private String fontFamily = Shell.getOptions().getDefaultFont();
        private FontWeight fontWeight = FontWeight.REGULAR;
        private int fontSize = 12;

        public Builder family(String fontFamily) {
            this.fontFamily = fontFamily;
            return this;
        }

        public Builder weight(FontWeight fontWeight) {
            this.fontWeight = fontWeight;
            return this;
        }

        public Builder size(int fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public FontStyle build() {
            return new FontStyle(this);
        }
    }

    private FontStyle(Builder builder) {
        this.fontFamily = builder.fontFamily;
        this.fontWeight = builder.fontWeight;
        this.fontSize = builder.fontSize;
    }

    public Map<TextAttribute, Object> getAttributes() {
        return Map.of(
            TextAttribute.FAMILY, fontFamily,
            TextAttribute.WEIGHT, fontWeight.getWeight(),
            TextAttribute.SIZE, fontSize,
            TextAttribute.LIGATURES, TextAttribute.LIGATURES_ON);
    }

    public enum FontWeight {
        LIGHT(0.75f), REGULAR(1.0f), MEDIUM(1.5f), SEMIBOLD(1.75f), BOLD(2.0f);

        private final Float weight;

        FontWeight(Float weight) {
            this.weight = weight;
        }

        public Float getWeight() {
            return weight;
        }

    }

}
