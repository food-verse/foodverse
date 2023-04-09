package com.foodverse.widgets.card;

import com.foodverse.utility.Props;
import com.foodverse.utility.core.ImageAsset;
import com.foodverse.utility.core.ShopType;

public final class ShopProps implements Props {

    private final ImageAsset thumbnail;
    private final String shopName;
    private final float rating;
    private final ShopType type;
    private final float minimumOrder;
    private final int time;

    public static class Builder {

        private ImageAsset thumbnail = ImageAsset.BURGER;
        private String shopName = "ShopName";
        private float rating = 0f;
        private ShopType type = ShopType.BURGER;
        private float minimumOrder = 0f;
        private int time = 0;

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

        public Builder type(ShopType type) {
            this.type = type;
            return this;
        }

        public Builder minimumOrder(float minimumOrder) {
            this.minimumOrder = minimumOrder;
            return this;
        }

        public Builder time(int time) {
            this.time = time;
            return this;
        }

        public ShopProps build() {
            return new ShopProps(this);
        }
    }

    private ShopProps(Builder builder) {
        this.thumbnail = builder.thumbnail;
        this.shopName = builder.shopName;
        this.rating = builder.rating;
        this.type = builder.type;
        this.minimumOrder = builder.minimumOrder;
        this.time = builder.time;
    }

    public ImageAsset getThumbnail() {
        return thumbnail;
    }

    public String getShopName() {
        return shopName;
    }

    public double getRating() {
        return rating;
    }

    public ShopType getType() {
        return type;
    }

    public float getMinimumOrder() {
        return minimumOrder;
    }

    public int getTime() {
        return time;
    }

}
