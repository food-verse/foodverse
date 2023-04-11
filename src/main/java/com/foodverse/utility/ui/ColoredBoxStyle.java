package com.foodverse.utility.ui;

import java.awt.Color;

public final class ColoredBoxStyle {

    private final Color defaultBackgroundColor;
    private final Color hoverBackgroundColor;
    private final Color pressBackgroundColor;
    private final Color borderColor;
    private final int borderRadius;

    public static class Builder {

        private Color defaultBackgroundColor = Colors.buttonTertiaryFill;
        private Color hoverBackgroundColor = Colors.buttonTertiaryHover;
        private Color pressBackgroundColor = Colors.buttonTertiaryActive;
        private Color borderColor = Colors.gray200;
        private int borderRadius = 16;

        public Builder defaultBackgroundColor(Color color) {
            this.defaultBackgroundColor = color;
            return this;
        }

        public Builder hoverBackgroundColor(Color color) {
            this.hoverBackgroundColor = color;
            return this;
        }

        public Builder pressBackgroundColor(Color color) {
            this.pressBackgroundColor = color;
            return this;
        }

        public Builder borderColor(Color color) {
            this.borderColor = color;
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
        this.defaultBackgroundColor = builder.defaultBackgroundColor;
        this.hoverBackgroundColor = builder.hoverBackgroundColor;
        this.pressBackgroundColor = builder.pressBackgroundColor;
        this.borderColor = builder.borderColor;
        this.borderRadius = builder.borderRadius;
    }

    public Color getDefaultBackgroundColor() {
        return defaultBackgroundColor;
    }

    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public Color getPressBackgroundColor() {
        return pressBackgroundColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

}
