package com.foodverse.utility.navigation;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.foodverse.utility.core.Widget;

/**
 * The parent class of every overlay. Overlays extending this class get their own id and an
 * underlying frame.
 */
public abstract class Overlay extends Widget implements Identifiable {

    // The underlying frame holding our widgets
    private final JFrame frame = new JFrame();

    // A callback to be executed when the window is closed
    private final Consumer<WindowEvent> onClose;

    /**
     * Creates a new {@link Overlay} with the specified width, height and a callback to be executed
     * when the window is closed.
     *
     * @param width   The preferred width for the page
     * @param height  The preferred height for the page
     * @param onClose A callback to be executed when the window is closed
     */
    protected Overlay(int width, int height, Consumer<WindowEvent> onClose) {
        this.onClose = onClose;
        frame.getContentPane().setBackground(Shell.getOptions().getBackgroundColor());
        frame.setSize(width, height);
        frame.setTitle(getId());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new OverlayListener());
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    /**
     * Creates a new {@link Overlay} with the specified width and height.
     *
     * @param width  The preferred width for the page
     * @param height The preferred height for the page
     */
    protected Overlay(int width, int height) {
        this(width, height, null);
    }

    /**
     * Creates a new {@link Overlay} with no specified dimensions.
     */
    protected Overlay() {
        this(0, 0, null);
    }

    /**
     * Returns the underlying JFrame of the overlay.
     */
    protected JFrame getFrame() {
        return frame;
    }

    /**
     * Adds the specified widget to the page's frame.
     *
     * @param widget The widget to be added
     */
    public void inflate(Widget widget) {
        frame.getContentPane().add(widget.getRef());
    }

    /**
     * Opens the overlay by setting its frame's visibility to true.
     */
    public void open() {
        frame.setVisible(true);
    }

    /**
     * Closes the overlay by setting its frame's visibility to false.
     */
    public void close() {
        frame.setVisible(false);
    }

    /**
     * A listener to handle the window closing event and execute the onClose callback.
     */
    private class OverlayListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            if (onClose != null) {
                onClose.accept(e);
            }
            Router.closeOverlay();
        }

    }

}
