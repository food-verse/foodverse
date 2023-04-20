package com.foodverse.models;

import java.util.List;
import java.util.Map;

import com.foodverse.widgets.media.AssetSize;
import com.google.gson.annotations.SerializedName;

public record Shop(
    @SerializedName("name") String name,
    @SerializedName("address") String address,
    @SerializedName("rating") float rating,
    @SerializedName("type") ShopType type,
    @SerializedName("thumbnails") Map<AssetSize, String> thumbnails,
    @SerializedName("preparation_time") int prepTime,
    @SerializedName("minimum_order") float minOrder,
    @SerializedName("offers") List<Offer> offers,
    @SerializedName("menu") List<Item> menu) {
}
