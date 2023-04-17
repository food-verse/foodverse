package com.foodverse.utility.system;

import java.io.File;

enum Files {
    CONFIG("config.json"), USERS("users.json"), SHOPS("shops.json");

    private final String fileName;

    Files(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return new File(String.format("db/%s", fileName));
    }

}
