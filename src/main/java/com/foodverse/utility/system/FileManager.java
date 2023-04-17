package com.foodverse.utility.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.foodverse.models.Config;
import com.foodverse.models.Shop;
import com.foodverse.models.User;
import com.foodverse.utility.system.EnvironmentOptions.Mode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public final class FileManager {

    private static final Logger logger = Logger.getLogger(FileManager.class.getName());

    private static final Gson gson = new Gson();

    private FileManager() {}

    public static AssetIndex loadAssetIndex() {
        InputStream stream = ResourceHandler.loadResourceAsStream("fonts/index.json");
        Type fontsType = new TypeToken<AssetIndex>() {}.getType();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream));) {
            return gson.fromJson(reader, fontsType);
        } catch (IOException e) {
            logger.log(Level.INFO, "Could not load the index of fonts.");
        }
        return null;
    }

    public static Config loadConfig() {
        Type configType = new TypeToken<Config>() {}.getType();
        if (EnvironmentOptions.getMode() == Mode.DEBUG) {
            var file = new File("assets/files/config.json");
            if (!file.exists()) {
                logger.log(Level.INFO, "There is no such file: {0}", file.getName());
                return null;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                return gson.fromJson(reader, configType);
            } catch (IOException e) {
                logger.log(Level.INFO, "Could not load the application's configuration.");
            }
        } else {
            var fileName = "files/config.json";
            InputStream stream = ResourceHandler.loadResourceAsStream(fileName);
            if (stream == null) {
                logger.log(Level.INFO, "There is no such file: {0}", fileName);
                return null;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream));) {
                return gson.fromJson(reader, configType);
            } catch (IOException e) {
                logger.log(Level.INFO, "Could not load the application's configuration.");
            }
        }
        return null;
    }

    public static List<User> loadUsers() {
        var file = FileAsset.USERS.getFile();
        if (file.exists()) {
            Type userListType = new TypeToken<List<User>>() {}.getType();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                return gson.fromJson(reader, userListType);
            } catch (IOException e) {
                logger.log(Level.INFO, "Could not load the list of users.");
            }
        } else {
            logger.log(Level.INFO, "There is no such file: {0}", file.getName());
        }
        return List.of();
    }

    public static void saveUsers(List<User> users) {
        var file = FileAsset.USERS.getFile();
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                logger.log(Level.INFO, "Could not create a file for saving the list of users.");
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            logger.log(Level.INFO, "Could not save the list of users.");
        }
    }

    public static List<Shop> loadShops() {
        var file = FileAsset.SHOPS.getFile();
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
        var file = FileAsset.SHOPS.getFile();
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

}
