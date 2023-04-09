package com.foodverse.utility.navigation;

public enum Pages {
    OVERVIEW("OverviewPage"),
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
