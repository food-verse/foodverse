package com.foodverse.utility.navigation;

import java.awt.Color;
import java.awt.Dimension;

import com.foodverse.utility.ui.Colors;

public final class ShellOptions {

    private final int width;
    private final int height;
    private final Color backgroundColor;
    private final String defaultFont;
    private final String monospacedFont;
    private final String title;

    public static class Builder {

        private int width = 0;
        private int height = 0;
        private Color backgroundColor = Colors.white;
        private String defaultFont = "Inter";
        private String monospacedFont = "IBMPlexMono";
        private String title = "";

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

        public Builder defaultFont(String fontFamily) {
            this.defaultFont = fontFamily;
            return this;
        }

        public Builder monospacedFont(String fontFamily) {
            this.monospacedFont = fontFamily;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
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
        this.defaultFont = builder.defaultFont;
        this.monospacedFont = builder.monospacedFont;
        this.title = builder.title;
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

    public String getDefaultFont() {
        return defaultFont;
    }

    public String getMonospacedFont() {
        return monospacedFont;
    }

    public String getTitle() {
        return title;
    }

    public Dimension getDimension() {
        return new Dimension(width, height);
    }

}
