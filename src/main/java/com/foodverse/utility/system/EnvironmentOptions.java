package com.foodverse.utility.system;

public final class EnvironmentOptions {

    private static Mode mode;

    private EnvironmentOptions() {}

    public static Mode getMode() {
        return mode;
    }

    public static void setMode(Mode mode) {
        EnvironmentOptions.mode = mode;
    }

    public enum Mode {
        DEBUG, RELEASE
    }

}
