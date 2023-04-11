package com.foodverse.models;

import java.util.Map;
import com.google.gson.annotations.SerializedName;

public final class Offer {

    @SerializedName("items")
    private Map<String, Integer> items;

    @SerializedName("total")
    private float total;

    public Offer(Map<String, Integer> items, float total) {
        this.items = items;
        this.total = total;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
