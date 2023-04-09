package com.foodverse.widgets.card;

import java.util.Map;
import com.foodverse.models.Item;
import com.foodverse.utility.Props;
import com.foodverse.utility.core.ImageAsset;

public final class OfferProps implements Props {

    private final ImageAsset thumbnail;
    private final String shopName;
    private final float rating;
    private final Map<Item, Integer> items;

    public static class Builder {

        private ImageAsset thumbnail = ImageAsset.SMALL_BURGER;
        private String shopName = "ShopName";
        private float rating = 0f;
        private Map<Item, Integer> items = Map.of();

        public Builder thumbnail(ImageAsset thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder shopName(String name) {
            this.shopName = name;
            return this;
        }

        public Builder rating(float rating) {
            this.rating = rating;
            return this;
        }

        public Builder items(Map<Item, Integer> items) {
            this.items = items;
            return this;
        }

        public OfferProps build() {
            return new OfferProps(this);
        }

    }

    private OfferProps(Builder builder) {
        this.thumbnail = builder.thumbnail;
        this.shopName = builder.shopName;
        this.rating = builder.rating;
        this.items = builder.items;
    }

    public ImageAsset getThumbnail() {
        return thumbnail;
    }

    public String getShopName() {
        return shopName;
    }

    public float getRating() {
        return rating;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

}
