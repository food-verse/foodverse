package com.foodverse.utility;

import java.awt.Color;
import java.awt.Dimension;
import com.foodverse.utility.core.Colors;

public final class ShellOptions {

    private final int width;
    private final int height;
    private final Color backgroundColor;

    public static class Builder {

        private int width = 0;
        private int height = 0;
        private Color backgroundColor = Colors.white;

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder backgroundColor(Color color) {
            this.backgroundColor = color;
            return this;
        }

        public ShellOptions build() {
            return new ShellOptions(this);
        }
    }

    private ShellOptions(Builder builder) {
        this.width = builder.width;
        this.height = builder.height;
        this.backgroundColor = builder.backgroundColor;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Dimension getDimension() {
        return new Dimension(width, height);
    }

}
