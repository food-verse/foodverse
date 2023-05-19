package com.foodverse.models;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.google.gson.annotations.SerializedName;

public record User(
    @SerializedName("name") String name,
    @SerializedName("addresses") List<Address> addresses,
    @SerializedName("phone") String phone,
    @SerializedName("email") String email,
    @SerializedName("credentials") Credentials credentials,
    @SerializedName("favorites") Set<String> favorites,
    @SerializedName("rating") Map<String, Integer> ratings,
    @SerializedName("orders") Set<Order> orders) {

    public User withName(String name) {
        return new User(name, addresses, phone, email, credentials, favorites, ratings, orders);
    }

    public User withPhone(String phone) {
        return new User(name, addresses, phone, email, credentials, favorites, ratings, orders);
    }

    public User withEmail(String email) {
        return new User(name, addresses, phone, email, credentials, favorites, ratings, orders);
    }

    public User withCredentials(Credentials credentials) {
        return new User(name, addresses, phone, email, credentials, favorites, ratings, orders);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
