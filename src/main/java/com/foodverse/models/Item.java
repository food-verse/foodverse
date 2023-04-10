package com.foodverse.models;

import com.google.gson.annotations.SerializedName;

public final class Item {

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private float price;

    public Item(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
