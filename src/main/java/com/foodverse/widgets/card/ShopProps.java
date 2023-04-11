package com.foodverse.widgets.card;

import com.foodverse.models.Shop;
import com.foodverse.models.ShopType;
import com.foodverse.utility.core.Props;
import com.foodverse.widgets.media.AssetSize;

public final class ShopProps implements Props {

    private final String thumbnail;
    private final String name;
    private final float rating;
    private final ShopType type;
    private final int prepTime;
    private final float minOrder;

    public static class Builder {

        private String thumbnail = "";
        private String name = "";
        private float rating = 0f;
        private ShopType type = ShopType.NONE;
        private int prepTime = 0;
        private float minOrder = 0f;

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

    public static ShopProps from(Shop shop) {
        return new ShopProps.Builder()
                .thumbnail(shop.getThumbnails().get(AssetSize.MEDIUM))
                .name(shop.getName())
                .rating(shop.getRating())
                .type(shop.getType())
                .minOrder(shop.getMinOrder())
                .prepTime(shop.getPrepTime())
                .build();
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
