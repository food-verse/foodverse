package com.foodverse.models;

import java.util.Map;
import com.google.gson.annotations.SerializedName;

public final class Offer {

    @SerializedName("items")
    private Map<Item, Integer> items;

    @SerializedName("total")
    private float total;

    public Offer() {}

    public Offer(Map<Item, Integer> items, float total) {
        this.items = items;
        this.total = total;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
