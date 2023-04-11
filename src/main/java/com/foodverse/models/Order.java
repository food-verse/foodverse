package com.foodverse.models;

import java.util.Map;
import com.google.gson.annotations.SerializedName;

public final class Order {

    @SerializedName("merchant")
    private String merchant;

    @SerializedName("items")
    private Map<String, Integer> items;

    @SerializedName("delivery_tip")
    private float deliveryTip;

    @SerializedName("total")
    private float total;

    public Order(String merchant, Map<String, Integer> items, float deliveryTip, float total) {
        this.merchant = merchant;
        this.items = items;
        this.deliveryTip = deliveryTip;
        this.total = total;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public float getDeliveryTip() {
        return deliveryTip;
    }

    public void setDeliveryTip(float deliveryTip) {
        this.deliveryTip = deliveryTip;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
