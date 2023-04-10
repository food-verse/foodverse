package com.foodverse.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Shop {

    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private String address;

    @SerializedName("rating")
    private float rating;

    @SerializedName("type")
    private ShopType type;

    @SerializedName("preparation_time")
    private int prepTime;

    @SerializedName("minimum_order")
    private float minOrder;

    @SerializedName("offers")
    private List<Offer> offers;

    @SerializedName("menu")
    private List<Item> menu;

    public Shop() {}

    public Shop(String name, String address, float rating, ShopType type, int prepTime,
            float minOrder, List<Offer> offers, List<Item> menu) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.type = type;
        this.prepTime = prepTime;
        this.minOrder = minOrder;
        this.offers = offers;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public ShopType getType() {
        return type;
    }

    public void setType(ShopType type) {
        this.type = type;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public float getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(float minOrder) {
        this.minOrder = minOrder;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Item> getMenu() {
        return menu;
    }

    public void setMenu(List<Item> menu) {
        this.menu = menu;
    }

}
