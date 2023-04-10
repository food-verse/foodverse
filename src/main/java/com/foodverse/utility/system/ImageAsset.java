package com.foodverse.utility.system;

import java.io.File;

public enum ImageAsset {
    BRAND("brand.png"), BURGER_SMALL("burger-small.png"), BURGER("burger.png"), STAR(
            "rating_star.png"), AVATAR("avatar.png");

    private final String fileName;

    ImageAsset(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return new File(String.format("assets/images/%s", fileName));
    }

}
