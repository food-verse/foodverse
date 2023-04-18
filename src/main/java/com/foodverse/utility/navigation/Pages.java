package com.foodverse.utility.navigation;

public enum Pages {
    OVERVIEW("Overview"),
    ONBOARDING("Onboarding"),
    LOGIN("Sign In"),
    REGISTER("Sign Up"),
    HOME("Home"),
    TEXTS("Text"),
    BUTTONS("Button");

    private final String id;

    Pages(String name) {
        id = name;
    }

    @Override
    public String toString() {
        return id;
    }

}
