package com.foodverse.utility.system;

import java.util.List;
import java.util.Optional;
import com.foodverse.models.Config;
import com.foodverse.models.Shop;
import com.foodverse.models.User;

public final class Database {

    private static Database database = new Database();

    private Config configuration;
    private User authenticatedUser;
    private List<User> users;
    private List<Shop> shops;

    private Database() {
        configuration = FileManager.loadConfig();
        users = FileManager.loadUsers();
        shops = FileManager.loadShops();
    }

    /**
     * Returns the database instance.
     *
     * @return {@link Database} The database instance
     */
    public static Database getInstance() {
        return database;
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

    public void saveUser(User user) {
        for (User credentials : users) {
            if (credentials.id().equals(user.id())) {
                users.remove(user);
                break;
            }
        }
        users.add(user);
        FileManager.saveUsers(users);
        users = FileManager.loadUsers();
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
