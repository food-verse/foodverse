package com.foodverse.widgets.card;

import java.util.Map;
import java.util.Optional;

import com.foodverse.models.Order;
import com.foodverse.models.Shop;
import com.foodverse.utility.core.Props;
import com.foodverse.utility.system.Database;
import com.foodverse.widgets.media.AssetSize;

public record OrderProps(
    String thumbnail,
    String name,
    float rating,
    Map<String, Integer> items,
    float price) implements Props {

    public static OrderProps from(Order order) {
        Optional<Shop> foundShop = Database.getInstance().findShopByName(order.merchant());
        if (foundShop.isPresent()) {
            return new OrderProps(
                foundShop.get().thumbnails().get(AssetSize.MEDIUM),
                foundShop.get().name(),
                foundShop.get().rating(),
                order.items(),
                order.total());
        } else {
            return new OrderProps("", "", 0f, order.items(), order.total());
        }
    }

}
