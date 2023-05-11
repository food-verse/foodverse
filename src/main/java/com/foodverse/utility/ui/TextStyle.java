package com.foodverse.utility.ui;

import java.awt.Color;
import javax.swing.SwingConstants;

public final class TextStyle {

    private final TextAlign textAlign;
    private final boolean isMono;
    private final Color color;

    public static class Builder {

        private TextAlign textAlign = TextAlign.LEFT;
        private boolean isMono = false;
        private Color color = Colors.black;

        public Builder textAlign(TextAlign textAlign) {
            this.textAlign = textAlign;
            return this;
        }

        public Builder isMono(boolean isMono) {
            this.isMono = isMono;
            return this;
        }

        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        public TextStyle build() {
            return new TextStyle(this);
        }
    }

    private TextStyle(Builder builder) {
        this.textAlign = builder.textAlign;
        this.color = builder.color;
        this.isMono = builder.isMono;
    }

    public TextAlign getTextAlign() {
        return textAlign;
    }

    public Color getColor() {
        return color;
    }

    public boolean isMono() {
        return isMono;
    }

    public enum TextAlign {
        LEFT(SwingConstants.LEFT), CENTER(SwingConstants.CENTER), RIGHT(SwingConstants.RIGHT);

        private final int alignment;

        TextAlign(int alignment) {
            this.alignment = alignment;
        }

        public int getAlignment() {
            return alignment;
        }

    }

}

