package com.foodverse.models;

import com.google.gson.annotations.SerializedName;

public record Item(
    @SerializedName("name") String name,
    @SerializedName("cost") double price) implements Purchasable {

    @Override
    public double cost() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }

}
