package com.foodverse.utility.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import com.foodverse.models.Shop;
import com.foodverse.models.User;

public final class Database {

    private static Database database;
    private static final Random generator = new Random();
    private final int numberOfRecoveryQuestions = 3;
    private List<User> users;
    private List<Shop> shops;

    private Database() {
        users = FileManager.loadUsers();
        shops = FileManager.loadShops();
    }

    /**
     * Returns the database instance. If the database hasn't been instantiated yet, it creates a new
     * one and the new instance gets returned.
     *
     * @return {@link Database} The database instance
     */
    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public Optional<User> userExists(String id) {
        if (!users.isEmpty()) {
            for (User user : users) {
                if (user.getId().equals(id)) {
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }

    public Optional<User> signIn(String id, String password) {
        if (!users.isEmpty()) {
            for (User user : users) {
                if (user.getId().equals(id)
                        && user.getCredentials().getPassword().equals(password)) {
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }

    public void saveUser(User user) {
        for (User credentials : users) {
            if (credentials.getId().equals(user.getId())) {
                users.remove(user);
                break;
            }
        }
        users.add(user);
        FileManager.saveUsers(users);
        users = FileManager.loadUsers();
    }

    public Optional<Shop> findShopByName(String name) {
        for (Shop shop : shops) {
            if (shop.getName().equals(name)) {
                return Optional.of(shop);
            }
        }
        return Optional.empty();
    }

    public List<String> getRecoveryCredentials(User user) {
        List<String> credentials = new ArrayList<>();
        int randomNumber = generator.nextInt(1, numberOfRecoveryQuestions);
        String question = FileManager.getRandomRecoveryQuestion(randomNumber).get();
        String answer;
        if (randomNumber == 1) {
            answer = user.getCredentials().getFirstRecoveryAnswer();
        } else {
            answer = user.getCredentials().getSecondRecoveryAnswer();
        }
        credentials.add(question);
        credentials.add(answer);
        return credentials;
    }

}
