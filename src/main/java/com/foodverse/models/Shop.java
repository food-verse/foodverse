package com.foodverse.models;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.foodverse.widgets.media.AssetSize;
import com.google.gson.annotations.SerializedName;

public record Shop(
    @SerializedName("name") String name,
    @SerializedName("address") String address,
    @SerializedName("phone") String phone,
    @SerializedName("rating") float rating,
    @SerializedName("reviews") int reviews,
    @SerializedName("type") ShopType type,
    @SerializedName("thumbnails") Map<AssetSize, String> thumbnails,
    @SerializedName("preparation_time") int prepTime,
    @SerializedName("minimum_order") float minOrder,
    @SerializedName("offers") List<Offer> offers,
    @SerializedName("menu") List<Item> menu) {

    public Shop withRating(float rating) {
        return new Shop(name, address, phone, rating, reviews, type, thumbnails, prepTime, minOrder,
            offers, menu);
    }

    public Shop withReviews(int reviews) {
        return new Shop(name, address, phone, rating, reviews, type, thumbnails, prepTime, minOrder,
            offers, menu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Shop shop = (Shop) o;
        return name.equals(shop.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
