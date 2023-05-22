package com.foodverse.props;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.foodverse.models.*;
import com.foodverse.utility.core.Props;
import com.foodverse.utility.system.Database;
import com.foodverse.widgets.media.AssetSize;

public record OrderCardProps(

    String thumbnail,
    String name,
    UUID id,
    Map<String, Integer> items,
    double price) implements Props {

    public static OrderCardProps from(Order order) {
        Optional<Shop> foundShop = Database.getInstance().findShopByName(order.merchant());
        return foundShop.map(shop -> new OrderCardProps(
            shop.thumbnails().get(AssetSize.MEDIUM),
            shop.name(),
            order.id(),
            order.items(),
            order.total())).orElseGet(() -> new OrderCardProps(
            "",
            "",
            UUID.randomUUID(),
            order.items(),
            order.total())
        );
    }

}
