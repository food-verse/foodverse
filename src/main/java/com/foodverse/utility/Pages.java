package com.foodverse.utility;

public enum Pages {
    HOME("HomePage");

    private final String id;

    Pages(String name) {
        id = name;
    }

    @Override
    public String toString() {
        return id;
    }

}
