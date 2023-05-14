package com.foodverse.utility.system;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.foodverse.models.Config;
import com.foodverse.models.Shop;
import com.foodverse.models.User;

public class Database {

    private final Config configuration;
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

    public boolean signIn(String email, String password) {
        for (User user : users) {
            if (user.email().equals(email) && user.credentials().password().equals(password)) {
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

    public Optional<User> verifyUser(String email, Map<Integer, String> recoveryAnswers) {
        Optional<User> foundUser = findUserByEmail(email);
        if (foundUser.isEmpty()) {
            return Optional.empty();
        }
        for (Map.Entry<Integer, String> entry : configuration.recoveryQuestions().entrySet()) {
            if (!recoveryAnswers.get(entry.getKey())
                .equals(foundUser.get().credentials().recoveryAnswers().get(entry.getKey()))) {
                return Optional.empty();
            }
        }
        return foundUser;
    }

    public Optional<User> findUserByEmail(String email) {
        for (User user : users) {
            if (user.email().equals(email)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public void updateUser(User newUser) {
        Optional<User> foundUser = findUserByEmail(newUser.email());
        if (foundUser.isPresent()) {
            users.remove(foundUser.get());
            users.add(newUser);
            CompletableFuture.runAsync(() -> FileManager.saveUsers(users));
        }
    }

    public void addShop(Shop shop) {
        shops.add(shop);
        CompletableFuture.runAsync(() -> FileManager.saveShops(shops));
    }

    public void updateShop(Shop newShop) {
        Optional<Shop> foundShop = findShopByName(newShop.name());
        if (foundShop.isPresent()) {
            shops.remove(foundShop.get());
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
