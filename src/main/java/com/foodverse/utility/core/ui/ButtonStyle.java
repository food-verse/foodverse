package com.foodverse.utility.core.ui;

import java.awt.Color;
import com.foodverse.utility.core.layout.EdgeInsets;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

public final class ButtonStyle {

    private final ButtonTheme buttonTheme;
    private final TextStyle textStyle;
    private final EdgeInsets padding;
    private final int minimumWidth;
    private final int borderRadius;
    private final boolean isEnabled;

    public static class Builder {

        private ButtonTheme buttonTheme = new ButtonTheme.Builder()
                .defaultBackgroundColor(Colors.buttonPrimaryFill)
                .defaultTextColor(Colors.buttonPrimaryText)
                .hoverBackgroundColor(Colors.buttonPrimaryHover)
                .hoverTextColor(Colors.buttonPrimaryText)
                .pressBackgroundColor(Colors.buttonPrimaryActive)
                .pressTextColor(Colors.buttonPrimaryText)
                .disabledBackgroundColor(Colors.buttonDisabledFill)
                .disabledTextColor(Colors.buttonDisabledText)
                .build();
        private TextStyle textStyle = Label.getTextStyle(LabelSize.XS);
        private EdgeInsets padding = new EdgeInsets.Builder().build();
        private int minimumWidth = 0;
        private int borderRadius = 8;
        private boolean isEnabled = true;

        public Builder buttonTheme(ButtonTheme buttonTheme) {
            this.buttonTheme = buttonTheme;
            return this;
        }

        public Builder textStyle(TextStyle textStyle) {
            this.textStyle = textStyle;
            return this;
        }

        public Builder padding(EdgeInsets padding) {
            this.padding = padding;
            return this;
        }

        public Builder minimumWidth(int minimumWidth) {
            this.minimumWidth = minimumWidth;
            return this;
        }

        public Builder borderRadius(int borderRadius) {
            this.borderRadius = borderRadius;
            return this;
        }

        public Builder isEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }

        public ButtonStyle build() {
            return new ButtonStyle(this);
        }
    }

    private ButtonStyle(Builder builder) {
        this.buttonTheme = builder.buttonTheme;
        this.textStyle = builder.textStyle;
        this.padding = builder.padding;
        this.minimumWidth = builder.minimumWidth;
        this.borderRadius = builder.borderRadius;
        this.isEnabled = builder.isEnabled;
    }

    public Color getDefaultBackgroundColor() {
        return buttonTheme.getDefaultBackgroundColor();
    }

    public Color getDefaultTextColor() {
        return buttonTheme.getDefaultTextColor();
    }

    public Color getHoverBackgroundColor() {
        return buttonTheme.getHoverBackgroundColor();
    }

    public Color getHoverTextColor() {
        return buttonTheme.getHoverTextColor();
    }

    public Color getPressBackgroundColor() {
        return buttonTheme.getPressBackgroundColor();
    }

    public Color getPressTextColor() {
        return buttonTheme.getPressTextColor();
    }

    public Color getDisabledBackgroundColor() {
        return buttonTheme.getDisabledBackgroundColor();
    }

    public Color getDisabledTextColor() {
        return buttonTheme.getDisabledTextColor();
    }

    public TextStyle getTextStyle() {
        return textStyle;
    }

    public EdgeInsets getPadding() {
        return padding;
    }

    public int getMinimumWidth() {
        return minimumWidth;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

}
