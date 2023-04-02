package com.foodverse.utility.core;

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

    public String getType() {
        return type;
    }
}
