package com.foodverse.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.foodverse.models.Address;
import com.foodverse.models.Credentials;
import com.foodverse.models.User;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.system.FileManager;

class UserDatabaseTest {

    // Getting a reference to the database...
    private final FlushableDatabase db = new FlushableDatabase();

    @BeforeEach
    void setup() {
        db.flushUsers();
    }

    @Test
    public void testSignIn() {

        // Creating a fake user...
        User user = getFakeUser();

        // Signing up the user to the database...
        db.signUp(user);

        // Test the user sign-in...
        // First, test with correct credentials...
        assertTrue(db.signIn("johndoe456@gmail.com", "Abc123!"));
        Optional<User> authenticatedUser = db.getAuthenticatedUser();
        assertTrue(authenticatedUser.isPresent());
        assertEquals("johndoe456@gmail.com", authenticatedUser.get().email());
        assertEquals("John Doe", authenticatedUser.get().name());

        // Then, test with wrong password and wrong username...
        assertFalse(db.signIn("johndoe456@gmail.com", "WrongPassword123"));
        assertFalse(db.signIn("janesmith789@gmail.com", "Abc123!"));

    }

    @Test
    public void testSignUp() throws InterruptedException {

        // Creating a fake user...
        User user = getFakeUser();

        // Signing up the user to the database...
        db.signUp(user);

        // Retrieving the saved user from the database...
        Optional<User> savedUser = db.getAuthenticatedUser();

        // Verifying that the saved user exists and matches the original user...
        assertTrue(savedUser.isPresent());
        assertEquals("johndoe456@gmail.com", savedUser.get().email());
        assertEquals("John Doe", savedUser.get().name());

        // Ensure that the saved user has been written to the file system...
        Thread.sleep(1000);

        // Loading the list of users from the file system and verifying that the saved user is
        // included...
        List<User> users = FileManager.loadUsers();
        assertTrue(users.contains(savedUser.get()));

    }

    @Test
    public void testUserExists() {

        // Creating a fake user...
        User user = getFakeUser();

        // Signing up the user to the database...
        db.signUp(user);

        // Testing if the user exists in the database using its email...
        assertTrue(db.findUserByEmail("johndoe456@gmail.com").isPresent());

        // Testing with a non-existing email...
        assertTrue(db.findUserByEmail("janesmith789@gmail.com").isEmpty());

    }

    @Test
    public void testUpdateUser() throws InterruptedException {

        // Creating a fake user...
        User user = getFakeUser();

        // Signing up the user to the database...
        db.signUp(user);

        // Creating new user's recovery answers...
        var recoveryAnswers = List.of(
            "Superman",
            "Batman");

        // Creating new user's credentials...
        var credentials = new Credentials(
            "NewPassword123!",
            recoveryAnswers);

        // Creating a new user with the updated credentials...
        User newUser = user.withCredentials(credentials);

        // Updating the user in the database...
        db.updateUser(newUser);

        // Ensure that the saved user has been written to the file system...
        Thread.sleep(1000);

        // Loading the list of users from the file system and verifying that the saved user is
        // included...
        List<User> users = FileManager.loadUsers();
        assertTrue(users.contains(newUser));
        assertEquals(newUser.credentials(), credentials);
        assertEquals("johndoe456@gmail.com", newUser.email());
        assertEquals("John Doe", newUser.name());
    }

    @Test
    public void testChangePassword() throws InterruptedException {

        // Creating a fake user...
        User user = getFakeUser();

        // Signing up the user to the database...
        db.signUp(user);

        // Getting the user from the database...
        Optional<User> signedUser = db.findUserByEmail("johndoe456@gmail.com");

        // Testing if the user exists in the database using its email...
        assertTrue(signedUser.isPresent());

        // Creating new user's credentials...
        var credentials = signedUser.get().credentials().withPassword("NewPassword123!");

        // Creating a new user with the updated credentials...
        User newUser = user.withCredentials(credentials);

        // Updating the user in the database...
        db.updateUser(newUser);

        // Ensure that the saved user has been written to the file system...
        Thread.sleep(1000);

        // Loading the list of users from the file system and verifying that the saved user is
        // included...
        List<User> users = FileManager.loadUsers();
        assertTrue(users.contains(newUser));
        assertEquals(newUser.credentials(), credentials);
    }

    private User getFakeUser() {

        // Creating user's recovery answers...
        var recoveryAnswers = List.of(
            "Scooby Doo",
            "Harry Potter");

        // Creating user's credentials...
        var credentials = new Credentials(
            "Abc123!",
            recoveryAnswers);

        // Creating the user...
        var user = new User(
            "John Doe",
            List.of(
                new Address(
                    "123 Main Street",
                    "1A",
                    "2nd Floor",
                    "Doorbell A - Smith",
                    "Please leave the package at the front porch."
                ),
                new Address(
                    "456 Elm Street",
                    "10B",
                    "3rd Floor",
                    "Apartment 305 - Johnson",
                    "Ring the bell twice for delivery."
                )
            ),
            "+1 (555) 555-5678",
            "johndoe456@gmail.com",
            credentials,
            Set.of(),
            Map.of(),
            Set.of());

        return user;
    }

    private class FlushableDatabase extends Database {

        public void flushUsers() {
            users.clear();
            FileManager.saveUsers(users);
        }

    }

}
