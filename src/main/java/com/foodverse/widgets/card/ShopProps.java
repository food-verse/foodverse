package com.foodverse.widgets.card;

import com.foodverse.models.ShopType;
import com.foodverse.utility.Props;
import com.foodverse.utility.system.ImageAsset;

public final class ShopProps implements Props {

    private final ImageAsset thumbnail;
    private final String name;
    private final float rating;
    private final ShopType type;
    private final int prepTime;
    private final float minOrder;

    public static class Builder {

        private ImageAsset thumbnail = ImageAsset.BURGER;
        private String name = "";
        private float rating = 0f;
        private ShopType type = ShopType.BURGER;
        private int prepTime = 0;
        private float minOrder = 0f;

        public Builder thumbnail(ImageAsset thumbnail) {
            this.thumbnail = thumbnail;
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

        public Builder type(ShopType type) {
            this.type = type;
            return this;
        }

        public Builder prepTime(int time) {
            this.prepTime = time;
            return this;
        }

        public Builder minOrder(float minimumOrder) {
            this.minOrder = minimumOrder;
            return this;
        }

        public ShopProps build() {
            return new ShopProps(this);
        }
    }

    private ShopProps(Builder builder) {
        this.thumbnail = builder.thumbnail;
        this.name = builder.name;
        this.rating = builder.rating;
        this.type = builder.type;
        this.minOrder = builder.minOrder;
        this.prepTime = builder.prepTime;
    }

    public ImageAsset getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public ShopType getType() {
        return type;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public float getMinOrder() {
        return minOrder;
    }

}
