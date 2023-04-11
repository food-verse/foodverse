package com.foodverse.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public final class User {

    @SerializedName("name")
    private String name;

    @SerializedName("credentials")
    private UserCredentials credentials;

    @SerializedName("orders")
    private List<Order> orders;

    public User(String name, UserCredentials credentials, List<Order> orders) {
        this.name = name;
        this.credentials = credentials;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(UserCredentials credentials) {
        this.credentials = credentials;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
