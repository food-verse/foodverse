package com.foodverse.utility.factories;

import java.util.*;

import com.foodverse.models.*;
import com.foodverse.utility.system.FileManager;

public final class UserFactory {

    private UserFactory() {}

    private static List<User> generateUsers() {

        // Creating user's addresses...
        var addresses = new ArrayList<>(List.of(
            new Address(
                "789 Oak Avenue",
                "4C",
                "1st Floor",
                "Doorbell B - Anderson",
                "Please leave the package with the doorman."),
            new Address(
                "321 Pine Street",
                "12D",
                "5th Floor",
                "Apartment 502 - Thompson",
                "Call upon arrival for delivery instructions.")));

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
                UUID.randomUUID(),
                "Burgerlicious",
                new Date(),
                burgerShopItems,
                2,
                31.47,
                PaymentMethod.CARD,
                "Extra ketchup, no pickles."),
            new Order(
                UUID.randomUUID(),
                "Pizzantastic",
                new Date(),
                pizzaShopItems,
                0.5,
                33.98,
                PaymentMethod.CASH,
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
