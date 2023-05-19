package com.foodverse.utility.factories;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.foodverse.models.Address;
import com.foodverse.models.Credentials;
import com.foodverse.models.Order;
import com.foodverse.models.OrderType;
import com.foodverse.models.PaymentMethod;
import com.foodverse.models.User;
import com.foodverse.utility.system.FileManager;

public final class UserFactory {

    private UserFactory() {
    }

    private static List<User> generateUsers() {

        // Creating user's addresses...
        var addresses = new ArrayList<>(List.of(
            new Address(
                "789 Oak Avenue",
                "4C",
                "1st Floor",
                "Doorbell B - Anderson",
                "Please leave the package with the doorman."
            ),
            new Address(
                "321 Pine Street",
                "12D",
                "5th Floor",
                "Apartment 502 - Thompson",
                "Call upon arrival for delivery instructions."
            )
        ));

        // Creating user's recovery answers...
        var recoveryAnswers = List.of(
            "Fluffy",
            "The Lord of the Rings");

        // Creating user's credentials...
        var credentials = new Credentials(
            "XyZ987!",
            recoveryAnswers);

        // Creating user's favorites...
        var favorites = new HashSet<>(Set.of(
            "Sweet o’ Clock",
            "Taco Mia"));

        // Creating user's ratings...
        var ratings = new HashMap<>(Map.of(
            "Burgerlicious", 3,
            "Pizzantastic", 5));

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
        var recentOrders = new HashSet<>(List.of(
            new Order(
                "Burgerlicious",
                new Date(),
                burgerShopItems,
                1.5f,
                31.47f,
                PaymentMethod.CARD,
                OrderType.DELIVERY,
                "Extra ketchup, no pickles."),
            new Order(
                "Pizzantastic",
                new Date(),
                pizzaShopItems,
                0.5f,
                33.98f,
                PaymentMethod.CASH,
                OrderType.TAKE_AWAY,
                "Please cut the pizza into square slices.")));

        // Creating the user...
        var user = new User(
            "Emily Smith",
            addresses,
            "+1 (555) 555-1234",
            "emilysmith123@gmail.com",
            credentials,
            favorites,
            ratings,
            recentOrders);

        return new ArrayList<>(List.of(user));
    }

    public static void generate() {
        FileManager.saveUsers(generateUsers());
    }

}
