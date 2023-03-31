package com.foodverse.utility;

public final class EdgeInsets {

    private final int left;
    private final int top;
    private final int right;
    private final int bottom;

    public static class Builder {

        private int left = 0;
        private int top = 0;
        private int right = 0;
        private int bottom = 0;

        public Builder left(int left) {
            this.left = left;
            return this;
        }

        public Builder top(int top) {
            this.top = top;
            return this;
        }

        public Builder right(int right) {
            this.right = right;
            return this;
        }

        public Builder bottom(int bottom) {
            this.bottom = bottom;
            return this;
        }

        public Builder all(int value) {
            this.left = value;
            this.top = value;
            this.right = value;
            this.bottom = value;
            return this;
        }

        public Builder symmetric(int vertical, int horizontal) {
            this.left = horizontal;
            this.top = vertical;
            this.right = horizontal;
            this.bottom = vertical;
            return this;
        }

        public EdgeInsets build() {
            return new EdgeInsets(this);
        }
    }

    private EdgeInsets(Builder builder) {
        this.left = builder.left;
        this.top = builder.top;
        this.right = builder.right;
        this.bottom = builder.bottom;

    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }

}
