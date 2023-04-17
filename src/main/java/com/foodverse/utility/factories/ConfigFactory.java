package com.foodverse.utility.factories;

import java.util.List;
import com.foodverse.models.Config;
import com.foodverse.utility.system.FileManager;

public final class ConfigFactory {

    private ConfigFactory() {}

    private static Config generateConfiguration() {

        // Creating the list of recovery questions...
        var recoveryQuestions = List.of(
                "What is the name of your first pet?",
                "What is your favorite book/movie/TV show?");

        return new Config(recoveryQuestions);
    }

    public static void generate() {
        FileManager.saveConfig(generateConfiguration());
    }

}
