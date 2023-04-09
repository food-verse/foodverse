package com.foodverse.models;

public enum ShopType {
    BURGER("Burger"),
    PIZZA("Pizza"),
    PASTA("Pasta"),
    GREEK("Greek"),
    MEXICAN("Mexican"),
    DONUT("Donut");

    private final String type;

    ShopType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

}
