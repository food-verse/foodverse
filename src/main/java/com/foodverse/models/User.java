package com.foodverse.models;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

public record User(
    @SerializedName("id") String id,
    @SerializedName("name") String name,
    @SerializedName("addresses") List<String> addresses,
    @SerializedName("phone") String phone,
    @SerializedName("email") String email,
    @SerializedName("credentials") Credentials credentials,
    @SerializedName("favorites") List<String> favorites,
    @SerializedName("orders") List<Order> orders) {

    public User withCredentials(Credentials credentials) {
        return new User(id, name, addresses, phone, email, credentials, favorites, orders);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
