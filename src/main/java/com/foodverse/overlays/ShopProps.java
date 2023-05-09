package com.foodverse.overlays;

import java.util.List;

import com.foodverse.models.Item;
import com.foodverse.models.Offer;
import com.foodverse.models.Shop;
import com.foodverse.models.ShopType;
import com.foodverse.utility.core.Props;
import com.foodverse.widgets.media.AssetSize;

public record ShopProps(
    String thumbnail,
    String name,
    String address,
    String phone,
    ShopType type,
    int prepTime,
    float minOrder,
    List<Offer> offers,
    List<Item> menu) implements Props {

    public static ShopProps from(Shop shop) {
        return new ShopProps(
            shop.thumbnails().get(AssetSize.LARGE),
            shop.name(),
            shop.address(),
            shop.phone(),
            shop.type(),
            shop.prepTime(),
            shop.minOrder(),
            shop.offers(),
            shop.menu());
    }

}
