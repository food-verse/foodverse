package com.foodverse.utility;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * Router is a utility class for managing the navigation inside the application, with helper methods
 * for opening and closing pages and overlays.
 */
public final class Router {

    private static final Logger logger = Logger.getLogger(Router.class.getName());

    // A map that stores the pages in the application, indexed by their IDs
    private static final Map<String, Page> pages = new HashMap<>();

    // Double-ended queue that stores the overlays that have been opened, in the order they were
    // opened
    private static final Deque<Overlay> overlays = new ArrayDeque<>();

    // A reference to the main frame of the application
    private static final JFrame frame = Shell.getFrame();

    private Router() {}

    /**
     * Adds the specified page to the router.
     *
     * @param page The page to be added
     */
    public static void addPage(Page page) {
        pages.put(page.getId(), page);
    }

    /**
     * Opens the next page and closes the current one.
     *
     * @param nextPage The page to be shown
     */
    public static void pushPage(Pages nextPage) {
        Page p = pages.get(nextPage.toString());
        if (p == null) {
            logger.log(Level.WARNING, "There is no such page: {0}", nextPage);
        } else {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(p.getRef());
            frame.revalidate();
            frame.repaint();
        }
    }

    /**
     * Opens a new overlay.
     *
     * @param newOverlay The overlay to be shown
     */
    public static void openOverlay(Overlay newOverlay) {
        overlays.add(newOverlay);
        newOverlay.open();
    }

    /**
     * Closes the most recently pushed overlay.
     */
    public static void closeOverlay() {
        Overlay oldOverlay = overlays.pop();
        oldOverlay.close();
    }

}
