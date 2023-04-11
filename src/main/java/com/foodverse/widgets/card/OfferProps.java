package com.foodverse.widgets.card;

import java.util.List;
import com.foodverse.models.Offer;
import com.foodverse.models.Shop;
import com.foodverse.utility.core.Props;
import com.foodverse.widgets.media.AssetSize;

public final class OfferProps implements Props {

    private final String thumbnail;
    private final String name;
    private final float rating;
    private final List<Offer> offers;

    public static class Builder {

        private String thumbnail = "";
        private String name = "";
        private float rating = 0f;
        private List<Offer> offers = List.of();

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

        public Builder offers(List<Offer> offers) {
            this.offers = offers;
            return this;
        }

        public OfferProps build() {
            return new OfferProps(this);
        }

    }

    private OfferProps(Builder builder) {
        this.thumbnail = builder.thumbnail;
        this.name = builder.name;
        this.rating = builder.rating;
        this.offers = builder.offers;
    }

    public static OfferProps from(Shop shop) {
        return new OfferProps.Builder()
                .thumbnail(shop.getThumbnails().get(AssetSize.SMALL))
                .name(shop.getName())
                .rating(shop.getRating())
                .offers(shop.getOffers())
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

    public List<Offer> getOffers() {
        return offers;
    }

}
