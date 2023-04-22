package com.foodverse.models;

import com.google.gson.annotations.SerializedName;

public record Item(
    @SerializedName("name") String name,
    @SerializedName("price") float price) {
}
