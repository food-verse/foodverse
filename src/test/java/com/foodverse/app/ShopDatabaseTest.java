package com.foodverse.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.foodverse.models.Item;
import com.foodverse.models.Shop;
import com.foodverse.models.ShopType;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.system.FileManager;
import com.foodverse.widgets.media.AssetSize;

class ShopDatabaseTest {

    // Getting a reference to the database...
    private final FlushableDatabase db = new FlushableDatabase();

    @BeforeEach
    void setup() {
        db.flushShops();
    }

    @Test
    void testFindShopByName() {

        // Creating a fake shop
        Shop shop = getFakeShop();

        // Saving the fake shop to the database...
        db.addShop(shop);

        // Searching for the shop by name...
        Optional<Shop> foundShop = db.findShopByName(shop.name());

        // Asserting that the shop was found and that its properties match...
        assertTrue(foundShop.isPresent());
        assertEquals("7890 Cherry Lane, Westwood, CA 90290", foundShop.get().address());
        assertEquals("345-678-9012", foundShop.get().phone());

    }

    @Test
    public void testUpdateShop() throws InterruptedException {

        // Creating a fake shop
        Shop shop = getFakeShop();

        // Saving the fake shop to the database...
        db.addShop(shop);

        // Creating a new shop with the updated rating and reviews...
        Shop newShop = shop.withRating(4.5f).withReviews(shop.reviews() + 1);

        // Ensure that the shop has been written to the file system...
        Thread.sleep(1000);

        // Updating the shop in the database...
        db.updateShop(newShop);

        // Ensure that the new shop has been written to the file system...
        Thread.sleep(1000);

        // Loading the list of shops from the file system and verifying that the new shop is
        // included...
        List<Shop> shops = FileManager.loadShops();
        assertTrue(shops.contains(newShop));

        // Verify that the shop's rating and number of reviews has been updated...
        Optional<Shop> updatedShop = db.findShopByName(newShop.name());
        assertTrue(updatedShop.isPresent());
        assertEquals(4.5f, updatedShop.get().rating());
        assertEquals(265, updatedShop.get().reviews());
    }

    private Shop getFakeShop() {

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
            "345-678-9012",
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

        return burgerShop;
    }

    private class FlushableDatabase extends Database {

        public void flushShops() {
            shops.clear();
            FileManager.saveShops(shops);
        }

    }

}
