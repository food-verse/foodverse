package com.foodverse.widgets.media;

public enum IconAsset {
    BRAND("brand.svg"), STAR("rating_star.svg"), AVATAR("avatar.svg");

    private final String fileName;

    IconAsset(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return fileName;
    }

}
