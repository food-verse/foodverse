package com.foodverse.utility.local;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.foodverse.models.UserCredentials;

public final class FileManager {

    private static final Logger logger = Logger.getLogger(FileManager.class.getName());

    private FileManager() {}

    public static List<UserCredentials> loadDatabase() {
        String fileName = Files.DATABASE.getFileName();
        List<UserCredentials> loadedUsers = new ArrayList<>();
        File file = new File(fileName);
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
                logger.log(Level.INFO, "Could not load \"{0}\" image.", fileName);
            }
        } else {
            logger.log(Level.INFO, "There is no such file: {0}", fileName);
        }
        return loadedUsers;
    }

    public static void saveDatabase(List<UserCredentials> users) {
        String fileName = Files.DATABASE.getFileName();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                ObjectOutputStream stream = new ObjectOutputStream(outputStream);
                stream.writeObject(users);
                outputStream.close();
                stream.close();
            } catch (IOException e) {
                logger.log(Level.INFO, "Could not save user credentials to the database.");
            }
        } else {
            logger.log(Level.INFO, "There is no such file: {0}", fileName);
        }
    }

    public static Optional<String> getRandomRecoveryQuestion(int questionIndex) {
        String fileName = Files.QUESTIONS.getFileName();
        File file = new File(fileName);
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
                logger.log(Level.INFO, "Could not load questions from: {0}", fileName);
            }
        } else {
            logger.log(Level.INFO, "There is no such file: {0}", fileName);
        }
        return Optional.ofNullable(question);
    }

}
