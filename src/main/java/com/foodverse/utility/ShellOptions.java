package com.foodverse.utility;

import java.awt.Dimension;

public final class ShellOptions {

    private final int width;
    private final int height;

    public static class Builder {

        private int width = 0;
        private int height = 0;

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public ShellOptions build() {
            return new ShellOptions(this);
        }
    }

    private ShellOptions(Builder builder) {
        this.width = builder.width;
        this.height = builder.height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Dimension getDimension() {
        return new Dimension(width, height);
    }

}
