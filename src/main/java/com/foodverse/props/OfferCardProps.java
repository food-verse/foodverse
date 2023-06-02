package com.foodverse.props;

import java.util.List;

import com.foodverse.models.Offer;
import com.foodverse.models.Shop;
import com.foodverse.utility.core.Props;
import com.foodverse.widgets.media.AssetSize;

public record OfferCardProps(
    String thumbnail,
    String name,
    List<Offer> offers
) implements Props {

    public static OfferCardProps from(Shop shop) {
        return new OfferCardProps(
            shop.thumbnails().get(AssetSize.SMALL),
            shop.name(),
            shop.offers());
    }

}
