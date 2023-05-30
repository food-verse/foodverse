package com.foodverse.models;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public record Offer(
    @SerializedName("items") Map<String, Integer> items,
    @SerializedName("total") double total
) implements Purchasable {

    @Override
    public double cost() {
        return total;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            builder.append(entry.getValue())
                .append(" ")
                .append(entry.getKey())
                .append(" & ");
        }
        builder.setLength(builder.length() - 3);
        return builder.toString();
    }

}
