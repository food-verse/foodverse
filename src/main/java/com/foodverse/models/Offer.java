package com.foodverse.models;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public record Offer(
    @SerializedName("items") Map<String, Integer> items,
    @SerializedName("total") float total) {
}
