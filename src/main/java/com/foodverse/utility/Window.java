package com.foodverse.utility;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public final class Window {

    private final JFrame frame = new JFrame();

    public void inflate(Widget widget) {
        frame.getContentPane().add(widget.getRef());
    }

    // TODO:
    public void open() {
        frame.pack();
        frame.setVisible(true);
    }

    // TODO:
    public void close() {
        frame.setVisible(false);
    }

    // TODO:
    public void setPreferences(String title) {
        frame.setTitle(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
