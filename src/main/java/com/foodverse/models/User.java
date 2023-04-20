package com.foodverse.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public record User(
    @SerializedName("id") String id,
    @SerializedName("name") String name,
    @SerializedName("address") String address,
    @SerializedName("phone") String phone,
    @SerializedName("email") String email,
    @SerializedName("credentials") Credentials credentials,
    @SerializedName("orders") List<Order> orders) {
}
