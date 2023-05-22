package com.foodverse.utility.factories;

import java.util.ArrayList;
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
            new Item("Classic Burger", 7.99),
            new Item("Cheeseburger", 8.99),
            new Item("Bacon Burger", 9.99),
            new Item("Veggie Burger", 7.99),
            new Item("Chicken Burger", 9.99),
            new Item("BBQ Burger", 10.99),
            new Item("Double Burger", 12.99),
            new Item("Coca Cola", 2.50),
            new Item("Fanta", 2.50),
            new Item("Soda", 2.00));

        // Creating the burger shop...
        var burgerShop = new Shop(
            "Burgerlicious",
            "7890 Cherry Lane, Westwood, CA 90290",
            "345-678-9012",
            5.0,
            264,
            ShopType.BURGER,
            Map.of(
                AssetSize.SMALL, "burger-small.png",
                AssetSize.MEDIUM, "burger-medium.png",
                AssetSize.LARGE, "burger-large.jpg"),
            30,
            7.00,
            List.of(),
            burgerMenu);

        // Creating menu for pizza shop...
        var pizzeriaMenu = List.of(
            new Item("Margherita Pizza", 12.99),
            new Item("Pepperoni Pizza", 14.99),
            new Item("Hawaiian Pizza", 15.99),
            new Item("Meat Lovers Pizza", 16.99),
            new Item("Veggie Pizza", 14.99),
            new Item("White Pizza", 15.99),
            new Item("Calzone", 13.99),
            new Item("Pesto Pasta", 12.99),
            new Item("Garlic Knots", 5.99),
            new Item("Coca Cola", 2.50));

        // Creating the pizza shop...
        var pizzaShop = new Shop(
            "Pizzantastic",
            "42 Maple Street, Millfield, OH 45761",
            "567-890-1234",
            4.7,
            97,
            ShopType.PIZZA,
            Map.of(
                AssetSize.SMALL, "pizza-small.png",
                AssetSize.MEDIUM, "pizza-medium.png",
                AssetSize.LARGE, "pizza-large.jpg"),
            15,
            9.00,
            List.of(),
            pizzeriaMenu);

        // Creating menu for the pasta shop...
        var pastaMenu = List.of(
            new Item("Spaghetti Carbonara", 12.99),
            new Item("Fettuccine Alfredo", 14.99),
            new Item("Linguine with Clam Sauce", 15.99),
            new Item("Penne Arrabbiata", 16.99),
            new Item("Rigatoni Bolognese", 14.99),
            new Item("Lasagna", 15.99),
            new Item("Chicken Parmesan", 13.99),
            new Item("Tiramisu", 7.99),
            new Item("Coca Cola", 2.50),
            new Item("Sprite", 2.50));

        // Creating the pasta shop...
        var pastaShop = new Shop(
            "La Pastaiole",
            "315 Elmwood Avenue, Riverdale, NY 10471",
            "890-123-4567",
            3.9,
            432,
            ShopType.PASTA,
            Map.of(
                AssetSize.SMALL, "pasta-small.png",
                AssetSize.MEDIUM, "pasta-medium.png",
                AssetSize.LARGE, "pasta-large.jpg"),
            30,
            15.00,
            List.of(),
            pastaMenu);

        // Creating menu for the greek shop...
        var greekMenu = List.of(
            new Item("Gyro Sandwich", 8.99),
            new Item("Greek Salad", 10.99),
            new Item("Moussaka", 12.99),
            new Item("Souvlaki Skewers", 11.99),
            new Item("Spanakopita", 7.99),
            new Item("Tiropita", 7.99),
            new Item("Lamb Chops", 18.99),
            new Item("Roasted Potatoes", 5.99),
            new Item("Coca Cola", 2.50),
            new Item("Sprite", 2.50));

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
            "432-109-8765",
            4.2,
            178,
            ShopType.GREEK,
            Map.of(
                AssetSize.SMALL, "greek-small.png",
                AssetSize.MEDIUM, "greek-medium.png",
                AssetSize.LARGE, "greek-large.jpg"),
            5,
            4.00,
            greekOffers,
            greekMenu);

        // Creating menu for the donut shop...
        var donutMenu = List.of(
            new Item("Glazed Donut", 1.99f),
            new Item("Chocolate Donut", 2.49),
            new Item("Sprinkled Donut", 2.49),
            new Item("Boston Cream Donut", 2.99),
            new Item("Maple Bar Donut", 2.99),
            new Item("Apple Fritter", 3.49),
            new Item("Cinnamon Roll", 3.49),
            new Item("Coffee", 1.99),
            new Item("Milk", 2.49),
            new Item("Orange Juice", 2.49));

        // Creating the donut shop...
        var donutShop = new Shop(
            "Sweet o’ Clock",
            "123 Main Street, Anytown, USA",
            "654-321-0987",
            4.5,
            50,
            ShopType.DONUT,
            Map.of(
                AssetSize.SMALL, "donut-small.png",
                AssetSize.MEDIUM, "donut-medium.png",
                AssetSize.LARGE, "donut-large.jpg"),
            20,
            5.00,
            List.of(),
            donutMenu);

        // Creating menu for the mexican shop...
        var mexicanMenu = List.of(
            new Item("Taco", 3.50),
            new Item("Burrito", 7.99),
            new Item("Quesadilla", 6.99),
            new Item("Enchilada", 5.99),
            new Item("Chimichanga", 8.99),
            new Item("Fajita", 10.99),
            new Item("Tamale", 4.99),
            new Item("Refried Beans", 2.99),
            new Item("Soda", 2.50),
            new Item("Sprite", 2.50));

        // Creating offers for the mexican shop...
        var mexicanOffers = List.of(
            new Offer(Map.of(
                mexicanMenu.get(0).name(), 2,
                mexicanMenu.get(8).name(), 1),
                6.99),
            new Offer(Map.of(
                mexicanMenu.get(1).name(), 2,
                mexicanMenu.get(9).name(), 1),
                13.99),
            new Offer(Map.of(
                mexicanMenu.get(4).name(), 1,
                mexicanMenu.get(7).name(), 1),
                11.99));

        // Creating the mexican shop...
        var mexicanShop = new Shop(
            "Taco Mia",
            "1667 Rosewood Court, Pineville, LA 71360",
            "987-654-3210",
            4.9,
            341,
            ShopType.MEXICAN,
            Map.of(
                AssetSize.SMALL, "mexican-small.png",
                AssetSize.MEDIUM, "mexican-medium.png",
                AssetSize.LARGE, "mexican-large.jpg"),
            45,
            10.00,
            mexicanOffers,
            mexicanMenu);

        return new ArrayList<>(List.of(
            burgerShop,
            donutShop,
            pizzaShop,
            pastaShop,
            greekShop,
            mexicanShop));
    }

    public static void generate() {
        FileManager.saveShops(generateShops());
    }

}
