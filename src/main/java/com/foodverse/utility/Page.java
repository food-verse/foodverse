package com.foodverse.utility;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * The parent class of every page. Pages extending this class get their own id and can be later
 * added to the Router.
 */
public abstract class Page {

    // The identifier of the page
    private final String id = getClass().getSimpleName();

    // The underlying frame holding our widgets
    private final JFrame frame = new JFrame();

    // The preferred size for the page
    private final Dimension dimension;

    /**
     * Creates a new {@link Page} with the specified width and height.
     *
     * @param width The preferred width for the page
     * @param height The preferred height for the page
     */
    protected Page(int width, int height) {
        dimension = new Dimension(width, height);
    }

    /**
     * Creates a new {@link Page} with no specified dimensions.
     */
    protected Page() {
        this(0, 0);
    }

    protected String getId() {
        return id;
    }

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
    public void open() {
        if (dimension.getWidth() < frame.getPreferredSize().getWidth()
                || dimension.getHeight() < frame.getPreferredSize().getHeight()) {
            frame.pack();
        } else {
            frame.setSize(dimension);
        }
        frame.setVisible(true);
    }

    /**
     * Hides the graphical user interface window by setting the visibility of the page's frame to
     * false.
     */
    public void close() {
        frame.setVisible(false);
    }

}
