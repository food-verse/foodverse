package com.foodverse.widgets.media;

public enum IconAsset {
    AVATAR("avatar.svg"),
    BRAND("brand.svg"),
    CREDIT_CARD("credit_card.svg"),
    HEART_FILL("heart-fill.svg"),
    HEART("heart.svg"),
    LOCATION("location.svg"),
    PAYMENTS("payments.svg"),
    STAR_MEDIUM_FILL("star_medium_fill.svg"),
    STAR_MEDIUM_OUTLINE("star_medium_outline.svg"),
    STAR_SMALL_FILL("star_small_fill.svg");

    private final String fileName;

    IconAsset(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return fileName;
    }

}
