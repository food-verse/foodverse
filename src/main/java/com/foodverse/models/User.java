package com.foodverse.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public record User(
    @SerializedName("id") String id,
    @SerializedName("name") String name,
    @SerializedName("addresses") List<String> addresses,
    @SerializedName("phone") String phone,
    @SerializedName("email") String email,
    @SerializedName("credentials") Credentials credentials,
    @SerializedName("orders") List<Order> orders) {

    public User withCredentials(Credentials credentials) {
        return new User(id, name, addresses, phone, email, credentials, orders);
    }

}
