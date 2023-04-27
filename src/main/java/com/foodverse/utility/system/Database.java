package com.foodverse.utility.system;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import com.foodverse.models.Config;
import com.foodverse.models.Shop;
import com.foodverse.models.User;

public class Database {

    private Config configuration;
    private User authenticatedUser;
    protected List<User> users;
    protected List<Shop> shops;

    protected Database() {
        configuration = FileManager.loadConfig();
        users = FileManager.loadUsers();
        shops = FileManager.loadShops();
    }

    private static class SingletonHelper {
        private static final Database INSTANCE = new Database();
    }

    /**
     * Returns the database instance. If the database hasn't been instantiated yet, it creates a new
     * one and the new instance gets returned.
     *
     * @return {@link Database} The database instance
     */
    public static Database getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public Optional<Config> getConfiguration() {
        return Optional.ofNullable(configuration);
    }

    public Optional<User> getAuthenticatedUser() {
        return Optional.ofNullable(authenticatedUser);
    }

    public boolean signIn(String id, String password) {
        for (User user : users) {
            if (user.id().equals(id) && user.credentials().password().equals(password)) {
                authenticatedUser = user;
                return true;
            }
        }
        return false;
    }

    public void signUp(User user) {
        authenticatedUser = user;
        users.add(user);
        CompletableFuture.runAsync(() -> FileManager.saveUsers(users));
    }

    public void signOut() {
        authenticatedUser = null;
    }

    public boolean userExists(String id) {
        for (User user : users) {
            if (user.id().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void updateUser(User newUser) {
        boolean found = false;
        for (User user : users) {
            if (user.equals(newUser)) {
                found = true;
                users.remove(user);
                break;
            }
        }
        if (found) {
            users.add(newUser);
            CompletableFuture.runAsync(() -> FileManager.saveUsers(users));
        }
    }

    public void addShop(Shop shop) {
        shops.add(shop);
        CompletableFuture.runAsync(() -> FileManager.saveShops(shops));
    }

    public void updateShop(Shop newShop) {
        boolean found = false;
        for (Shop shop : shops) {
            if (shop.equals(newShop)) {
                found = true;
                shops.remove(shop);
                break;
            }
        }
        if (found) {
            shops.add(newShop);
            CompletableFuture.runAsync(() -> FileManager.saveShops(shops));
        }
    }

    public List<Shop> getShops() {
        return shops;
    }

    public Optional<Shop> findShopByName(String name) {
        for (Shop shop : shops) {
            if (shop.name().equals(name)) {
                return Optional.of(shop);
            }
        }
        return Optional.empty();
    }

}
