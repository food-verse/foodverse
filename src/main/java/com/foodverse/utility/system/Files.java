package com.foodverse.utility.system;

import java.io.File;

enum Files {
    USERS("users.json"), SHOPS("shops.json"), QUESTIONS("questions.json");

    private final String fileName;

    Files(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return new File(String.format("db/%s", fileName));
    }

}
