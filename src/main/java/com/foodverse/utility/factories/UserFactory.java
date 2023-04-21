package com.foodverse.utility.factories;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.foodverse.models.Credentials;
import com.foodverse.models.Order;
import com.foodverse.models.OrderType;
import com.foodverse.models.PaymentMethod;
import com.foodverse.models.User;
import com.foodverse.utility.system.FileManager;

public final class UserFactory {

    private UserFactory() {}

    private static Set<User> generateUsers() {

        // Creating user's recovery answers...
        var recoveryAnswers = List.of(
                "Fluffy",
                "The Lord of the Rings");

        // Creating user's credentials...
        var credentials = new Credentials(
                "XyZ987!",
                recoveryAnswers);

        // Creating burger shops items for the burger order
        var burgerShopItems = Map.of(
                "Classic Burger", 1,
                "Veggie Burger", 2,
                "Coca Cola", 3);

        // Creating pizza shop items for the pizza order
        var pizzaShopItems = Map.of(
                "Margherita Pizza", 1,
                "Hawaiian Pizza", 1,
                "Coca Cola", 2);

        // Creating user's recent orders...
        var recentOrders = List.of(
                new Order(
                        "Burgerlicious",
                        new Date(),
                        burgerShopItems,
                        1.5f,
                        31.47f,
                        PaymentMethod.CARD,
                        OrderType.DELIVERY),
                new Order(
                        "Pizzantastic",
                        new Date(),
                        pizzaShopItems,
                        0.5f,
                        33.98f,
                        PaymentMethod.CASH,
                        OrderType.TAKE_AWAY));

        // Creating the user...
        var user = new User(
                "emilysmith123",
                "Emily Smith",
                "123 Main St, Anytown, USA 12345",
                "+1 (555) 555-1234",
                "emilysmith123@gmail.com",
                credentials,
                recentOrders);

        return Set.of(user);
    }

    public static void generate() {
        FileManager.saveUsers(generateUsers());
    }

}
