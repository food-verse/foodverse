package com.foodverse.utility.core.ui;

import java.awt.Color;

public final class ColoredBoxStyle {

    private final Color borderColor;
    private final Color fillColor;
    private final int borderRadius;

    public static class Builder {

        private Color borderColor = Colors.gray200;
        private Color fillColor = Colors.white;
        private int borderRadius = 16;

        public Builder borderColor(Color color) {
            this.borderColor = color;
            return this;
        }

        public Builder fillColor(Color color) {
            this.fillColor = color;
            return this;
        }

        public Builder borderRadius(int borderRadius) {
            this.borderRadius = borderRadius;
            return this;
        }

        public ColoredBoxStyle build() {
            return new ColoredBoxStyle(this);
        }

    }

    private ColoredBoxStyle(Builder builder) {
        this.borderColor = builder.borderColor;
        this.fillColor = builder.fillColor;
        this.borderRadius = builder.borderRadius;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

}
