package com.foodverse.models;

public enum PaymentMethod {
    CARD, CASH;

    @Override
    public String toString() {
        String name = name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

}
