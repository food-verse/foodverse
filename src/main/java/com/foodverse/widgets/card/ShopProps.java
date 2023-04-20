package com.foodverse.widgets.card;

import com.foodverse.models.Shop;
import com.foodverse.models.ShopType;
import com.foodverse.utility.core.Props;
import com.foodverse.widgets.media.AssetSize;

public record ShopProps(
    String thumbnail,
    String name,
    float rating,
    ShopType type,
    int prepTime,
    float minOrder) implements Props {

    public static ShopProps from(Shop shop) {
        return new ShopProps(
            shop.getThumbnails().get(AssetSize.MEDIUM),
            shop.getName(),
            shop.getRating(),
            shop.getType(),
            shop.getPrepTime(),
            shop.getMinOrder());
    }

}
