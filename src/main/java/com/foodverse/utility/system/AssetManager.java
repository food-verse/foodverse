package com.foodverse.utility.system;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

import com.foodverse.utility.system.EnvironmentOptions.Mode;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;

public final class AssetManager {

    private static final Logger logger = Logger.getLogger(AssetManager.class.getName());

    // The directory of the assets
    private static final String dir = EnvironmentOptions.getMode() == Mode.DEBUG ? "assets" : "";

    // A Map object holding the vector images loaded from the assets.
    private static final Map<String, BufferedImage> savedVectors = new HashMap<>();

    // A Map object holding the raster images loaded from the assets.
    private static final Map<String, BufferedImage> savedImages = new HashMap<>();

    private AssetManager() {}

    private static boolean isFontInstalled(String fontFamily) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = ge.getAvailableFontFamilyNames();
        for (String font : fonts) {
            if (font.equals(fontFamily)) {
                return true;
            }
        }
        return false;
    }

    public static void loadFont(String fontFamily) {
        if (isFontInstalled(fontFamily)) return;
        var dir = String.format("assets/fonts/%s/", fontFamily);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        File[] fontFiles = new File(dir).listFiles();
        if (fontFiles == null) {
            logger.log(Level.INFO, "There is no such directory: {0}", dir);
            return;
        }
        Stream.of(fontFiles).map(File::getName).forEach(fileName -> {
            try {
                ge.registerFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File(String.format("%s%s", dir, fileName))
                ));
            } catch (IOException | FontFormatException e) {
                logger.log(Level.INFO, "Could not load \"{0}\" font variant.", fileName);
            }
        });
    }

    public static void loadFont(String fontFamily, List<String> fontFiles) {
        if (isFontInstalled(fontFamily)) return;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (String fontFile : fontFiles) {
            var fileName = String.format("fonts/%s/%s", fontFamily, fontFile);
            try (InputStream stream = ResourceHandler.loadResourceAsStream(fileName)) {
                try {
                    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, stream));
                } catch (IOException | FontFormatException e) {
                    logger.log(Level.INFO, "Could not load \"{0}\" font variant.", fontFile);
                }
            } catch (IOException e) {
                logger.log(Level.INFO, "Failed to close stream for font file: {0}", fontFile);
            }
        }
    }

    public static Optional<Image> getVector(String assetName) {
        return Optional.ofNullable(savedVectors.computeIfAbsent(assetName, source -> {
            String assetLocation = getAssetLocation(AssetType.VECTOR, source);
            return loadVector(assetLocation);
        }));
    }

    public static Optional<Image> getImage(String assetName, int width, int height) {
        BufferedImage bufferedImage = savedImages.computeIfAbsent(assetName, source -> {
            String assetLocation = getAssetLocation(AssetType.RASTER, source);
            return loadImage(assetLocation);
        });
        if (bufferedImage != null) {
            return Optional.of(bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        }
        return Optional.empty();
    }

    private static BufferedImage loadVector(String source) {
        BufferedImage bufferedImage = null;
        try {
            // Create an SVGUniverse
            SVGUniverse universe = new SVGUniverse();

            // Load the SVG file into memory
            URL assetAddress;
            if (EnvironmentOptions.getMode() == Mode.DEBUG) {
                try {
                    File assetFile = new File(source);
                    assetAddress = assetFile.toURI().toURL();
                } catch (MalformedURLException e) {
                    logger.log(Level.INFO, "Failed to convert source to URL: {0}", source);
                    return null;
                }
            } else {
                assetAddress = ResourceHandler.loadResourceAsURL(source);
            }
            SVGDiagram diagram = universe.getDiagram(universe.loadSVG(assetAddress));

            // Create a Graphics2D that the SVG should be rendered to
            bufferedImage = new BufferedImage(
                Math.round(diagram.getWidth()),
                Math.round(diagram.getHeight()),
                BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = bufferedImage.createGraphics();
            graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Render the SVG to the Graphics2D
            diagram.render(graphics);
        } catch (SVGException e) {
            logger.log(Level.INFO, "Could not load image from: {0}", source);
        }
        return bufferedImage;
    }

    private static BufferedImage loadImage(String source) {
        BufferedImage bufferedImage = null;
        try {
            if (EnvironmentOptions.getMode() == Mode.DEBUG) {
                File assetFile = new File(source);
                bufferedImage = ImageIO.read(assetFile);
            } else {
                URL assetAddress = ResourceHandler.loadResourceAsURL(source);
                bufferedImage = ImageIO.read(assetAddress);
            }
        } catch (IOException e) {
            logger.log(Level.INFO, "Could not load \"{0}\" image.", source);
        }
        return bufferedImage;
    }

    private static String getAssetLocation(AssetType assetType, String assetName) {
        var type = assetType == AssetType.VECTOR ? "icons" : "images";
        return dir.isEmpty()
            ? String.format("%s/%s", type, assetName)
            : String.format("%s/%s/%s", dir, type, assetName);
    }

    private enum AssetType {
        VECTOR, RASTER
    }

}
