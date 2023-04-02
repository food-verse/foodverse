package com.foodverse.utility;

public enum Pages {
    HOME("HomePage"), TEXTS("TextPage"), BUTTONS("ButtonPage");

    private final String id;

    Pages(String name) {
        id = name;
    }

    @Override
    public String toString() {
        return id;
    }

}
