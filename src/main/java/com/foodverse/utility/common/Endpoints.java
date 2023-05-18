package com.foodverse.utility.common;

public enum Endpoints {
    REPOSITORY, LICENSE("LICENSE"), README("README.md");

    private static final String repository = "https://github.com/food-verse/foodverse";
    private static final String commonRoute = "blob/424892ec2ddbe074cfcb2eb0283119e1c46ec9a3";
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
