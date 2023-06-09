package com.foodverse.utility.common;

public enum Endpoints {
    REPOSITORY, LICENSE("LICENSE"), README("README.md");

    private static final String repository = "https://github.com/food-verse/foodverse";
    private static final String commonRoute = "blob/main";
    private final String link;

    Endpoints(String route) {
        this.link = String.format("%s/%s/%s", repository, commonRoute, route);
    }

    Endpoints() {
        this.link = repository;
    }

    public String getLink() {
        return link;
    }

}
