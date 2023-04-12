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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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

    public static void loadFont(String fontFamily) {
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
            URL address = new URI(source).toURL();
            bufferedImage = ImageIO.read(address);
            scaledImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException | URISyntaxException e) {
            logger.log(Level.INFO, "Could not load image from: {0}", source);
        }
        return Optional.ofNullable(scaledImage);
    }

    public static Optional<Image> getVector(File vectorFile) {
        BufferedImage bufferedImage = null;
        try {
            // Create an SVGUniverse
            SVGUniverse universe = new SVGUniverse();

            // Load the SVG file into memory
            URL address = vectorFile.toURI().toURL();
            SVGDiagram diagram = universe.getDiagram(universe.loadSVG(address));

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
        } catch (MalformedURLException | SVGException e) {
            logger.log(Level.INFO, "Could not load image from: {0}", vectorFile.getAbsolutePath());
        }
        return Optional.ofNullable(bufferedImage);
    }

}
