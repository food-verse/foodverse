package com.foodverse.utility;

import java.io.File;

public enum ImageAsset {
    BRAND("brand.png");

    private final String fileName;

    ImageAsset(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return new File(
                String.format("src/main/java/com/foodverse/assets/images/%s", fileName));
    }

}
