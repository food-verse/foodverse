package com.foodverse.utility.system;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class URLHandler {

    private static final Logger logger = Logger.getLogger(URLHandler.class.getName());

    public static void open(String source) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    URI address = new URI(source);
                    desktop.browse(address);
                } catch (URISyntaxException | IOException e) {
                    logger.log(Level.INFO, "Could not open link: {0}", source);
                }
            }
        }
    }

}
