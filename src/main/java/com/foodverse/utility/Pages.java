package com.foodverse.utility;

public enum Pages {
    ONBOARDING("OnboardingPage"),
    LOGIN("SignInPage"),
    REGISTER("SignUpPage"),
    HOME("HomePage"),
    TEXTS("TextPage"),
    BUTTONS("ButtonPage");

    private final String id;

    Pages(String name) {
        id = name;
    }

    @Override
    public String toString() {
        return id;
    }

}
