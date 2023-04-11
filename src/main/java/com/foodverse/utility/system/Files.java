package com.foodverse.utility.system;

import java.io.File;

enum Files {
    DATABASE("database.dat"), SHOPS("shops.json"), QUESTIONS("questions.txt");

    private final String fileName;

    Files(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return new File(String.format("db/%s", fileName));
    }

}
