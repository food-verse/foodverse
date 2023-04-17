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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;

public final class AssetManager {

    private static final Logger logger = Logger.getLogger(AssetManager.class.getName());

    private AssetManager() {}

    public static boolean isFontInstalled(String fontFamily) {
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
        if (isFontInstalled(fontFamily)) {
            return;
        }
        var dir = String.format("assets/fonts/%s/", fontFamily);
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

    public static void loadFont(String fontFamily, List<String> fontFiles) {
        if (isFontInstalled(fontFamily)) {
            return;
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (String fontFile : fontFiles) {
            var fileName = String.format("fonts/%s/%s", fontFamily, fontFile);
            InputStream stream = ResourceHandler.loadResourceAsStream(fileName);
            if (stream == null) {
                logger.log(Level.INFO, "There is no such file: {0}", fontFile);
                return;
            }
            try {
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, stream));
            } catch (IOException | FontFormatException e) {
                logger.log(Level.INFO, "Could not load \"{0}\" font family.", fontFamily);
            } finally {
                try {
                    stream.close();
                } catch (IOException e) {
                    logger.log(Level.INFO, "Failed to close stream for font file {0}", fontFile);
                }
            }
        }
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

    public static Optional<Image> getImage(URL imageURL, int width, int height) {
        BufferedImage bufferedImage = null;
        Image scaledImage = null;
        try {
            bufferedImage = ImageIO.read(imageURL);
            scaledImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            logger.log(Level.INFO, "Could not load \"{0}\" image.", imageURL.toString());
        }
        return Optional.ofNullable(scaledImage);
    }

    public static Optional<Image> getImage(String source, int width, int height) {
        BufferedImage bufferedImage = null;
        Image scaledImage = null;
        try {
            URL address = new URI(source).toURL();
            bufferedImage = ImageIO.read(address);
            scaledImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException | URISyntaxException e) {
            logger.log(Level.INFO, "Could not load image from: {0}", source);
        }
        return Optional.ofNullable(scaledImage);
    }

    public static Optional<Image> getVector(File vectorFile) {
        try {
            URL vectorURL = vectorFile.toURI().toURL();
            return getVector(vectorURL);
        } catch (MalformedURLException e) {
            logger.log(Level.INFO, "Failed to convert file to URL: {0}", vectorFile.toString());
        }
        return Optional.empty();
    }

    public static Optional<Image> getVector(URL vectorURL) {
        BufferedImage bufferedImage = null;
        try {
            // Create an SVGUniverse
            SVGUniverse universe = new SVGUniverse();

            // Load the SVG file into memory
            SVGDiagram diagram = universe.getDiagram(universe.loadSVG(vectorURL));

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
            logger.log(Level.INFO, "Could not load image from: {0}", vectorURL.toString());
        }
        return Optional.ofNullable(bufferedImage);
    }

}
