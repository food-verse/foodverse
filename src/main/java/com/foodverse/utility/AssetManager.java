package com.foodverse.utility;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.stream.Stream;

public final class AssetManager {

    private static final Logger logger = Logger.getLogger(AssetManager.class.getName());

    private AssetManager() {}

    public static void loadFont(String fontFamily) {
        var dir = String.format("src/main/java/com/foodverse/assets/fonts/%s/", fontFamily);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Stream.of(new File(dir).listFiles())
                .map(File::getName)
                .forEach(fontName -> {
                    try {
                        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                                new File(String.format("%s%s", dir, fontName))));
                    } catch (IOException | FontFormatException e) {
                        logger.info(
                                "Couldn't load %s font family. A fallback font will be used.");
                    }
                });
    }

}
