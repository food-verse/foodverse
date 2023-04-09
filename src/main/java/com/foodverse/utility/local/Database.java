package com.foodverse.utility.local;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import com.foodverse.models.UserCredentials;

public final class Database {

    private static Database database;
    private static final Random generator = new Random();
    private final int numberOfRecoveryQuestions = 3;
    private List<UserCredentials> users;

    private Database() {
        users = FileManager.loadDatabase();
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

    public Optional<UserCredentials> userExists(String username) {
        if (!users.isEmpty()) {
            for (UserCredentials user : users) {
                if (user.getUsername().equals(username)) {
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }

    public Optional<UserCredentials> signIn(String username, String password) {
        if (!users.isEmpty()) {
            for (UserCredentials user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }

    public void saveUser(UserCredentials user) {
        for (UserCredentials credentials : users) {
            if (credentials.getUsername().equals(user.getUsername())) {
                users.remove(user);
                break;
            }
        }
        users.add(user);
        FileManager.saveDatabase(users);
        users = FileManager.loadDatabase();
    }

    public List<String> getRecoveryCredentials(UserCredentials user) {
        List<String> credentials = new ArrayList<>();
        int randomNumber = generator.nextInt(1, numberOfRecoveryQuestions);
        String question = FileManager.getRandomRecoveryQuestion(randomNumber).get();
        String answer;
        if (randomNumber == 1) {
            answer = user.getFirstRecoveryAnswer();
        } else {
            answer = user.getSecondRecoveryAnswer();
        }
        credentials.add(question);
        credentials.add(answer);
        return credentials;
    }

}
