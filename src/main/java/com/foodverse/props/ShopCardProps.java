package com.foodverse.props;

import com.foodverse.models.Shop;
import com.foodverse.models.ShopType;
import com.foodverse.utility.core.Props;
import com.foodverse.widgets.media.AssetSize;

public record ShopCardProps(
    String thumbnail,
    String name,
    double rating,
    int reviews,
    ShopType type,
    int prepTime,
    double minOrder
) implements Props {

    public static ShopCardProps from(Shop shop) {
        return new ShopCardProps(
            shop.thumbnails().get(AssetSize.MEDIUM),
            shop.name(),
            shop.rating(),
            shop.reviews(),
            shop.type(),
            shop.prepTime(),
            shop.minOrder());
    }

}
