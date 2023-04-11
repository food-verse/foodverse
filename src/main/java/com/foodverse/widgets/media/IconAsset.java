package com.foodverse.widgets.media;

import java.io.File;

public enum IconAsset {
    BRAND("brand.png"), STAR("rating_star.png"), AVATAR("avatar.png");

    private final String fileName;

    IconAsset(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return new File(String.format("assets/icons/%s", fileName));
    }

}
