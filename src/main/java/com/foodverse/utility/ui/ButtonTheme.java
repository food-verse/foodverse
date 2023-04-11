package com.foodverse.utility.ui;

import java.awt.Color;

public class ButtonTheme {

    private final Color defaultBackgroundColor;
    private final Color defaultTextColor;
    private final Color hoverBackgroundColor;
    private final Color hoverTextColor;
    private final Color pressBackgroundColor;
    private final Color pressTextColor;
    private final Color disabledBackgroundColor;
    private final Color disabledTextColor;

    public static class Builder {

        private Color defaultBackgroundColor = Colors.buttonPrimaryFill;
        private Color defaultTextColor = Colors.buttonPrimaryText;
        private Color hoverBackgroundColor = Colors.buttonPrimaryHover;
        private Color hoverTextColor = Colors.buttonPrimaryText;
        private Color pressBackgroundColor = Colors.buttonPrimaryActive;
        private Color pressTextColor = Colors.buttonPrimaryText;
        private Color disabledBackgroundColor = Colors.buttonDisabledFill;
        private Color disabledTextColor = Colors.buttonDisabledText;

        public Builder defaultBackgroundColor(Color color) {
            this.defaultBackgroundColor = color;
            return this;
        }

        public Builder defaultTextColor(Color color) {
            this.defaultTextColor = color;
            return this;
        }

        public Builder hoverBackgroundColor(Color color) {
            this.hoverBackgroundColor = color;
            return this;
        }

        public Builder hoverTextColor(Color color) {
            this.hoverTextColor = color;
            return this;
        }

        public Builder pressBackgroundColor(Color color) {
            this.pressBackgroundColor = color;
            return this;
        }

        public Builder pressTextColor(Color color) {
            this.pressTextColor = color;
            return this;
        }

        public Builder disabledBackgroundColor(Color color) {
            this.disabledBackgroundColor = color;
            return this;
        }

        public Builder disabledTextColor(Color color) {
            this.disabledTextColor = color;
            return this;
        }

        public ButtonTheme build() {
            return new ButtonTheme(this);
        }
    }

    private ButtonTheme(Builder builder) {
        this.defaultBackgroundColor = builder.defaultBackgroundColor;
        this.defaultTextColor = builder.defaultTextColor;
        this.hoverBackgroundColor = builder.hoverBackgroundColor;
        this.hoverTextColor = builder.hoverTextColor;
        this.pressBackgroundColor = builder.pressBackgroundColor;
        this.pressTextColor = builder.pressTextColor;
        this.disabledBackgroundColor = builder.disabledBackgroundColor;
        this.disabledTextColor = builder.disabledTextColor;
    }

    public Color getDefaultBackgroundColor() {
        return defaultBackgroundColor;
    }

    public Color getDefaultTextColor() {
        return defaultTextColor;
    }

    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public Color getHoverTextColor() {
        return hoverTextColor;
    }

    public Color getPressBackgroundColor() {
        return pressBackgroundColor;
    }

    public Color getPressTextColor() {
        return pressTextColor;
    }

    public Color getDisabledBackgroundColor() {
        return disabledBackgroundColor;
    }

    public Color getDisabledTextColor() {
        return disabledTextColor;
    }

}
