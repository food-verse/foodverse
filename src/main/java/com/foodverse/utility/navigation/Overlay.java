package com.foodverse.utility.navigation;

import java.awt.Dimension;
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

    // The preferred size for the page
    private final Dimension dimension;

    // A callback to be executed when the window is closed
    private final Consumer<WindowEvent> onClose;

    /**
     * Creates a new {@link Overlay} with the specified width and height.
     *
     * @param width   The preferred width for the page
     * @param height  The preferred height for the page
     * @param onClose A callback to be executed when the window is closed
     */
    protected Overlay(int width, int height, Consumer<WindowEvent> onClose) {
        this.onClose = onClose;
        dimension = new Dimension(width, height);
        frame.getContentPane().setBackground(Shell.getOptions().getBackgroundColor());
        frame.setTitle(getId());
        frame.addWindowListener(new OverlayListener());
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
     * It automatically sizes the window to fit its contents if the dimensions are smaller than the
     * preferred size, and sets the window size to match the dimensions if they are greater than or
     * equal to the preferred size. This sets the size of the frame to match the dimensions
     * provided.
     * <p>
     * Finally, the setVisible() method is called on the frame object with the argument true, which
     * makes the frame visible to the user.
     */
    public void open(JFrame oldFrame) {
        if (dimension.getWidth() < frame.getPreferredSize().getWidth()
            || dimension.getHeight() < frame.getPreferredSize().getHeight()) {
            frame.pack();
        } else {
            frame.setSize(dimension);
        }
        frame.setLocationRelativeTo(oldFrame);
        frame.setVisible(true);
    }

    /**
     * Hides the graphical user interface window by setting the visibility of the page's frame to
     * false.
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
