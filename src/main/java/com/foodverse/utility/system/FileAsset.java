package com.foodverse.utility.system;

import java.io.File;

enum FileAsset {
    CONFIG("files/config.json"), USERS("db/users.json"), SHOPS("db/shops.json");

    private final String fileName;

    FileAsset(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return new File(fileName);
    }

    public String getName() {
        return fileName;
    }

}
