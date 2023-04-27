package com.foodverse.utility.factories;

import java.util.List;
import java.util.Map;

import com.foodverse.models.Item;
import com.foodverse.models.Offer;
import com.foodverse.models.Shop;
import com.foodverse.models.ShopType;
import com.foodverse.utility.system.FileManager;
import com.foodverse.widgets.media.AssetSize;

public final class ShopFactory {

    private ShopFactory() {}

    private static List<Shop> generateShops() {

        // Creating menu for the burger shop...
        var burgerMenu = List.of(
            new Item("Classic Burger", 7.99f),
            new Item("Cheeseburger", 8.99f),
            new Item("Bacon Burger", 9.99f),
            new Item("Veggie Burger", 7.99f),
            new Item("Chicken Burger", 9.99f),
            new Item("BBQ Burger", 10.99f),
            new Item("Double Burger", 12.99f),
            new Item("Coca Cola", 2.50f),
            new Item("Fanta", 2.50f),
            new Item("Soda", 2.00f));

        // Creating the burger shop...
        var burgerShop = new Shop(
            "Burgerlicious",
            "7890 Cherry Lane, Westwood, CA 90290",
            5.0f,
            264,
            ShopType.BURGER,
            Map.of(
                AssetSize.SMALL, "burger-small.png",
                AssetSize.MEDIUM, "burger-medium.png"),
            30,
            7.00f,
            List.of(),
            burgerMenu);

        // Creating menu for pizza shop...
        var pizzeriaMenu = List.of(
            new Item("Margherita Pizza", 12.99f),
            new Item("Pepperoni Pizza", 14.99f),
            new Item("Hawaiian Pizza", 15.99f),
            new Item("Meat Lovers Pizza", 16.99f),
            new Item("Veggie Pizza", 14.99f),
            new Item("White Pizza", 15.99f),
            new Item("Calzone", 13.99f),
            new Item("Pesto Pasta", 12.99f),
            new Item("Garlic Knots", 5.99f),
            new Item("Coca Cola", 2.50f));

        // Creating the pizza shop...
        var pizzaShop = new Shop(
            "Pizzantastic",
            "42 Maple Street, Millfield, OH 45761",
            4.7f,
            97,
            ShopType.PIZZA,
            Map.of(
                AssetSize.SMALL, "pizza-small.png",
                AssetSize.MEDIUM, "pizza-medium.png"),
            15,
            9.00f,
            List.of(),
            pizzeriaMenu);

        // Creating menu for the pasta shop...
        var pastaMenu = List.of(
            new Item("Spaghetti Carbonara", 12.99f),
            new Item("Fettuccine Alfredo", 14.99f),
            new Item("Linguine with Clam Sauce", 15.99f),
            new Item("Penne Arrabbiata", 16.99f),
            new Item("Rigatoni Bolognese", 14.99f),
            new Item("Lasagna", 15.99f),
            new Item("Chicken Parmesan", 13.99f),
            new Item("Tiramisu", 7.99f),
            new Item("Coca Cola", 2.50f),
            new Item("Sprite", 2.50f));

        // Creating the pasta shop...
        var pastaShop = new Shop(
            "La Pastaiole",
            "315 Elmwood Avenue, Riverdale, NY 10471",
            3.9f,
            432,
            ShopType.PASTA,
            Map.of(
                AssetSize.SMALL, "pasta-small.png",
                AssetSize.MEDIUM, "pasta-medium.png"),
            30,
            15.00f,
            List.of(),
            pastaMenu);

        // Creating menu for the greek shop...
        var greekMenu = List.of(
            new Item("Gyro Sandwich", 8.99f),
            new Item("Greek Salad", 10.99f),
            new Item("Moussaka", 12.99f),
            new Item("Souvlaki Skewers", 11.99f),
            new Item("Spanakopita", 7.99f),
            new Item("Tiropita", 7.99f),
            new Item("Lamb Chops", 18.99f),
            new Item("Roasted Potatoes", 5.99f),
            new Item("Coca Cola", 2.50f),
            new Item("Sprite", 2.50f));

        // Creating offers for the greek shop...
        var greekOffers = List.of(
            new Offer(Map.of(
                greekMenu.get(0).name(), 2,
                greekMenu.get(8).name(), 1),
                15.99f),
            new Offer(Map.of(
                greekMenu.get(2).name(), 1,
                greekMenu.get(1).name(), 1),
                18.99f),
            new Offer(Map.of(
                greekMenu.get(4).name(), 1,
                greekMenu.get(9).name(), 1),
                10.99f));

        // Creating the greek shop...
        var greekShop = new Shop(
            "Ta Laladika",
            "2245 Oakwood Drive, Silverton, TX 79257",
            4.2f,
            178,
            ShopType.GREEK,
            Map.of(
                AssetSize.SMALL, "greek-small.png",
                AssetSize.MEDIUM, "greek-medium.png"),
            5,
            4.00f,
            greekOffers,
            greekMenu);

        // Creating menu for the donut shop...
        var donutMenu = List.of(
            new Item("Glazed Donut", 1.99f),
            new Item("Chocolate Donut", 2.49f),
            new Item("Sprinkled Donut", 2.49f),
            new Item("Boston Cream Donut", 2.99f),
            new Item("Maple Bar Donut", 2.99f),
            new Item("Apple Fritter", 3.49f),
            new Item("Cinnamon Roll", 3.49f),
            new Item("Coffee", 1.99f),
            new Item("Milk", 2.49f),
            new Item("Orange Juice", 2.49f));

        // Creating the donut shop...
        var donutShop = new Shop(
            "Sweet o’ Clock",
            "123 Main Street, Anytown, USA",
            4.5f,
            50,
            ShopType.DONUT,
            Map.of(
                AssetSize.SMALL, "donut-small.png",
                AssetSize.MEDIUM, "donut-medium.png"),
            20,
            5.00f,
            List.of(),
            donutMenu);

        // Creating menu for the mexican shop...
        var mexicanMenu = List.of(
            new Item("Taco", 3.50f),
            new Item("Burrito", 7.99f),
            new Item("Quesadilla", 6.99f),
            new Item("Enchilada", 5.99f),
            new Item("Chimichanga", 8.99f),
            new Item("Fajita", 10.99f),
            new Item("Tamale", 4.99f),
            new Item("Refried Beans", 2.99f),
            new Item("Soda", 2.50f),
            new Item("Sprite", 2.50f));

        // Creating offers for the mexican shop...
        var mexicanOffers = List.of(
            new Offer(Map.of(
                mexicanMenu.get(0).name(), 2,
                mexicanMenu.get(8).name(), 1),
                6.99f),
            new Offer(Map.of(
                mexicanMenu.get(1).name(), 2,
                mexicanMenu.get(9).name(), 1),
                13.99f),
            new Offer(Map.of(
                mexicanMenu.get(4).name(), 1,
                mexicanMenu.get(7).name(), 1),
                11.99f));

        // Creating the mexican shop...
        var mexicanShop = new Shop(
            "Taco Mia",
            "1667 Rosewood Court, Pineville, LA 71360",
            4.9f,
            341,
            ShopType.MEXICAN,
            Map.of(
                AssetSize.SMALL, "mexican-small.png",
                AssetSize.MEDIUM, "mexican-medium.png"),
            45,
            10.00f,
            mexicanOffers,
            mexicanMenu);

        return List.of(burgerShop, donutShop, pizzaShop, pastaShop, greekShop, mexicanShop);
    }

    public static void generate() {
        FileManager.saveShops(generateShops());
    }

}
