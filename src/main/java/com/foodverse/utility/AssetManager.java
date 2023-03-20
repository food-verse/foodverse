package com.foodverse.utility;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

public final class AssetManager {

    private static final Logger logger = Logger.getLogger(AssetManager.class.getName());

    private AssetManager() {}

    public static void loadFont(String fontFamily) {
        var dir = String.format("src/main/java/com/foodverse/assets/fonts/%s/", fontFamily);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        File[] fontFiles = new File(dir).listFiles();
        if (fontFiles == null) {
            logger.log(Level.INFO, "There is no such directory: {0}", dir);
            return;
        }
        Stream.of(fontFiles).map(File::getName).forEach(fontName -> {
            try {
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                        new File(String.format("%s%s", dir, fontName))));
            } catch (IOException | FontFormatException e) {
                logger.log(Level.INFO, "Could not load \"{0}\" font family.", fontFamily);
            }
        });
    }

    public static Optional<Image> getImage(File imageFile, int width, int height) {
        BufferedImage bufferedImage = null;
        Image scaledImage = null;
        try {
            bufferedImage = ImageIO.read(imageFile);
            scaledImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            logger.log(Level.INFO, "Could not load \"{0}\" image.", imageFile);
        }
        return Optional.ofNullable(scaledImage);
    }

    public static Optional<Image> getImage(String source, int width, int height) {
        BufferedImage bufferedImage = null;
        Image scaledImage = null;
        try {
            URL address = new URL(source);
            bufferedImage = ImageIO.read(address);
            scaledImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            logger.log(Level.INFO, "Could not load image from: {0}", source);
        }
        return Optional.ofNullable(scaledImage);
    }

}
