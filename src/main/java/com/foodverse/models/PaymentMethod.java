package com.foodverse.models;

public enum PaymentMethod {
    CARD("Card"), CASH("Cash");

    private final String method;

    PaymentMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return method;
    }

}
