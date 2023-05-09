package com.foodverse.widgets.card;

import java.util.Map;
import java.util.Optional;

import com.foodverse.models.Order;
import com.foodverse.models.Shop;
import com.foodverse.utility.core.Props;
import com.foodverse.utility.system.Database;
import com.foodverse.widgets.media.AssetSize;

public record OrderCardProps(
    String thumbnail,
    String name,
    Map<String, Integer> items,
    float price) implements Props {

    public static OrderCardProps from(Order order) {
        Optional<Shop> foundShop = Database.getInstance().findShopByName(order.merchant());
        if (foundShop.isPresent()) {
            return new OrderCardProps(
                foundShop.get().thumbnails().get(AssetSize.MEDIUM),
                foundShop.get().name(),
                order.items(),
                order.total());
        } else {
            return new OrderCardProps("", "", order.items(), order.total());
        }
    }

}
