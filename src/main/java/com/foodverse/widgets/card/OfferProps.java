package com.foodverse.widgets.card;

import com.foodverse.models.Offer;
import com.foodverse.utility.Props;
import com.foodverse.utility.system.ImageAsset;

public final class OfferProps implements Props {

    private final ImageAsset thumbnail;
    private final String name;
    private final float rating;
    private final Offer[] offers;

    public static class Builder {

        private ImageAsset thumbnail = ImageAsset.SMALL_BURGER;
        private String name = "";
        private float rating = 0f;
        private Offer[] offers = {};

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

        public Builder offers(Offer[] offers) {
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

    public ImageAsset getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public Offer[] getOffers() {
        return offers;
    }

}
