package com.foodverse.models;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

public record Order(
    @SerializedName("id") UUID id,
    @SerializedName("merchant") String merchant,
    @SerializedName("date") Date date,
    @SerializedName("items") Map<String, Integer> items,
    @SerializedName("delivery_tip") double deliveryTip,
    @SerializedName("total") double total,
    @SerializedName("payment") PaymentMethod method,
    @SerializedName("comments") String comments
) {}
