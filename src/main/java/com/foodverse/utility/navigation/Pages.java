package com.foodverse.utility.navigation;

public enum Pages {

    ONBOARDING("Onboarding"),
    LOGIN("Sign In"),
    REGISTER("Sign Up"),
    HOME("Home");

    private final String id;

    Pages(String name) {
        id = name;
    }

    @Override
    public String toString() {
        return id;
    }

}
