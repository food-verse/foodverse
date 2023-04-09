package com.foodverse.utility.core;

import java.io.File;

public enum ImageAsset {
    BRAND("brand.png"),
    SMALL_BURGER("small_burger.png"),
    BURGER("burger.png"),
    STAR("rating_star.png"),
    AVATAR("avatar.png");

    private final String fileName;

    ImageAsset(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return new File(
            String.format("src/main/java/com/foodverse/assets/images/%s", fileName));
    }

}
