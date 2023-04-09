package com.foodverse.widgets.card;

import java.util.Map;
import com.foodverse.models.Item;
import com.foodverse.utility.Props;
import com.foodverse.utility.system.ImageAsset;

public final class OrderProps implements Props {

    private final ImageAsset thumbnail;
    private final String shopName;
    private final float rating;
    private final Map<Item, Integer> items;
    private final float price;

    public static class Builder {

        private ImageAsset thumbnail = ImageAsset.BURGER;
        private String shopName = "ShopName";
        private float rating = 0f;
        private Map<Item, Integer> items = Map.of();
        private float price = 0f;

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

        public Builder price(float price) {
            this.price = price;
            return this;
        }

        public OrderProps build() {
            return new OrderProps(this);
        }

    }

    private OrderProps(Builder builder) {
        this.thumbnail = builder.thumbnail;
        this.shopName = builder.shopName;
        this.rating = builder.rating;
        this.items = builder.items;
        this.price = builder.price;
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

    public float getPrice() {
        return price;
    }

}
