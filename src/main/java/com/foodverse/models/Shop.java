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
    @SerializedName("rating") double rating,
    @SerializedName("reviews") int reviews,
    @SerializedName("type") ShopType type,
    @SerializedName("thumbnails") Map<AssetSize, String> thumbnails,
    @SerializedName("preparation_time") int prepTime,
    @SerializedName("minimum_order") double minOrder,
    @SerializedName("offers") List<Offer> offers,
    @SerializedName("menu") List<Item> menu
) {

    public Shop withRating(double rating) {
        if (Double.compare(this.rating, rating) == 0) return this;
        return new Shop(name, address, phone, rating, reviews, type, thumbnails, prepTime, minOrder,
            offers, menu);
    }

    public Shop withReviews(int reviews) {
        if (this.reviews == reviews) return this;
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
