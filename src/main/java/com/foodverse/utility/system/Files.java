package com.foodverse.utility.system;

enum Files {
    DATABASE("database.dat"), QUESTIONS("questions.txt");

    private final String fileName;

    Files(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
