package com.foodverse.widgets.media;

public enum IconAsset {
    AVATAR("avatar.svg"),
    BRAND("brand.svg"),
    HEART("heart.svg"),
    HEART_FILL("heart-fill.svg"),
    STAR("rating-star.svg");

    private final String fileName;

    IconAsset(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return fileName;
    }

}
