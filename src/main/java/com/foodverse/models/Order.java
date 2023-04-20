package com.foodverse.models;

import java.util.Date;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public record Order(
    @SerializedName("merchant") String merchant,
    @SerializedName("date") Date date,
    @SerializedName("items") Map<String, Integer> items,
    @SerializedName("delivery_tip") float deliveryTip,
    @SerializedName("total") float total,
    @SerializedName("payment") PaymentMethod method,
    @SerializedName("type") OrderType type) {
}
