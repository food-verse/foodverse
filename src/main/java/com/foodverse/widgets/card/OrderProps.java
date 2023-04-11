package com.foodverse.widgets.card;

import java.util.Map;
import com.foodverse.models.Order;
import com.foodverse.models.Shop;
import com.foodverse.utility.Props;
import com.foodverse.utility.State;
import com.foodverse.utility.system.Database;
import com.foodverse.widgets.media.AssetSize;

public final class OrderProps implements Props {

    private final String thumbnail;
    private final String name;
    private final float rating;
    private final Map<String, Integer> items;
    private final float price;

    public static class Builder {

        private String thumbnail = "";
        private String name = "";
        private float rating = 0f;
        private Map<String, Integer> items = Map.of();
        private float price = 0f;

        public Builder thumbnail(String assetName) {
            this.thumbnail = assetName;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder rating(float rating) {
            this.rating = rating;
            return this;
        }

        public Builder items(Map<String, Integer> items) {
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
        this.name = builder.name;
        this.rating = builder.rating;
        this.items = builder.items;
        this.price = builder.price;
    }

    public static OrderProps from(Order order) {
        State<Shop> foundShop = new State<Shop>(null);
        Database.getInstance().findShopByName(order.getMerchant())
                .ifPresent(shop -> foundShop.setValue(shop));
        if (foundShop.getValue() != null) {
            return new OrderProps.Builder()
                    .thumbnail(foundShop.getValue().getThumbnails().get(AssetSize.MEDIUM))
                    .name(foundShop.getValue().getName())
                    .rating(foundShop.getValue().getRating())
                    .items(order.getItems())
                    .price(order.getTotal())
                    .build();
        } else {
            return new OrderProps.Builder().build();
        }
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public float getPrice() {
        return price;
    }

}
