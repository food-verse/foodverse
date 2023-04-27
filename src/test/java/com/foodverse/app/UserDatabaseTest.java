package com.foodverse.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

        // Creating a fake user
        User user = getFakeUser();

        // Signing up the user to the database...
        db.signUp(user);

        // Test the user sign-in...
        // First, test with correct credentials...
        assertTrue(db.signIn("johndoe456", "Abc123!"));
        Optional<User> authenticatedUser = db.getAuthenticatedUser();
        assertTrue(authenticatedUser.isPresent());
        assertEquals("johndoe456", authenticatedUser.get().id());
        assertEquals("John Doe", authenticatedUser.get().name());

        // Then, test with wrong password and wrong username...
        assertFalse(db.signIn("johndoe456", "WrongPassword123"));
        assertFalse(db.signIn("janesmith789", "Abc123!"));

    }

    @Test
    public void testSignUp() throws InterruptedException {

        // Creating a fake user
        User user = getFakeUser();

        // Signing up the user to the database...
        db.signUp(user);

        // Retrieving the saved user from the database...
        Optional<User> savedUser = db.getAuthenticatedUser();

        // Verifying that the saved user exists and matches the original user...
        assertTrue(savedUser.isPresent());
        assertEquals("johndoe456", savedUser.get().id());
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

        // Creating a fake user
        User user = getFakeUser();

        // Signing up the user to the database...
        db.signUp(user);

        // Testing if the user exists in the database using its username
        assertTrue(db.userExists("johndoe456"));

        // Testing with a non-existing username
        assertFalse(db.userExists("janesmith789"));

    }

    @Test
    public void testUpdateUser() throws InterruptedException {

        // Creating a fake user
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
        assertEquals("johndoe456", newUser.id());
        assertEquals("John Doe", newUser.name());
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
                "johndoe456",
                "John Doe",
                List.of(
                        "789 Oak St, Anytown, USA 12345",
                        "321 Pine St, Anycity, USA 54321"),
                "+1 (555) 555-5678",
                "johndoe456@gmail.com",
                credentials,
                List.of());

        return user;
    }

    private class FlushableDatabase extends Database {

        public void flushUsers() {
            users.clear();
            FileManager.saveUsers(users);
        }

    }

}
