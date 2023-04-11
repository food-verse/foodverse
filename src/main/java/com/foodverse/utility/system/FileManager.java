package com.foodverse.utility.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.foodverse.models.Shop;
import com.foodverse.models.UserCredentials;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public final class FileManager {

    private static final Logger logger = Logger.getLogger(FileManager.class.getName());

    private static final Gson gson = new Gson();

    private FileManager() {}

    public static List<UserCredentials> loadDatabase() {
        List<UserCredentials> loadedUsers = new ArrayList<>();
        File file = Files.DATABASE.getFile();
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                var list = (List<?>) objectInputStream.readObject();
                for (Object user : list) {
                    if (user instanceof UserCredentials) {
                        loadedUsers.add((UserCredentials) user);
                    }
                }
                fileInputStream.close();
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                logger.log(Level.INFO, "Could not load database.");
            }
        } else {
            logger.log(Level.INFO, "There is no such file: {0}", file.getName());
        }
        return loadedUsers;
    }

    public static void saveDatabase(List<UserCredentials> users) {
        File file = Files.DATABASE.getFile();
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                logger.log(Level.INFO, "Could not create a file for saving the list of users.");
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream stream = new ObjectOutputStream(outputStream);
            stream.writeObject(users);
            outputStream.close();
            stream.close();
        } catch (IOException e) {
            logger.log(Level.INFO, "Could not save user credentials to the database.");
        }
    }

    public static List<Shop> loadShops() {
        File file = Files.SHOPS.getFile();
        if (file.exists()) {
            Type shopListType = new TypeToken<List<Shop>>() {}.getType();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                return gson.fromJson(reader, shopListType);
            } catch (IOException e) {
                logger.log(Level.INFO, "Could not load the list of shops.");
            }
        } else {
            logger.log(Level.INFO, "There is no such file: {0}", file.getName());
        }
        return List.of();
    }

    public static void saveShops(List<Shop> shops) {
        File file = Files.SHOPS.getFile();
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                logger.log(Level.INFO, "Could not create a file for saving the list of shops.");
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            gson.toJson(shops, writer);
        } catch (IOException e) {
            logger.log(Level.INFO, "Could not save the list of shops.");
        }
    }

    public static Optional<String> getRandomRecoveryQuestion(int questionIndex) {
        File file = Files.QUESTIONS.getFile();
        String question = null;
        int index = 1;
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((question = bufferedReader.readLine()) != null) {
                    if (index == questionIndex) {
                        break;
                    } else {
                        index++;
                    }
                }
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                logger.log(Level.INFO, "Could not load questions from: {0}", file.getName());
            }
        } else {
            logger.log(Level.INFO, "There is no such file: {0}", file.getName());
        }
        return Optional.ofNullable(question);
    }

}
