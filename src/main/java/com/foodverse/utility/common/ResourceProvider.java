package com.foodverse.utility.common;

import java.util.Random;

import com.google.gson.Gson;

/**
 * A utility class that provides access to shared resources used throughout the system.
 */
public final class ResourceProvider {

    // The instance of the random number generator
    private static final Random random = new Random();

    // The instance of the JSON serialization/deserialization library
    private static final Gson gson = new Gson();

    private ResourceProvider() {}

    public static Random getRandom() {
        return random;
    }

    public static Gson getGson() {
        return gson;
    }

}
