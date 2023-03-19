package com.foodverse.utility;

import javax.swing.JFrame;

public abstract class Window {

    private final JFrame frame = new JFrame();

    public void inflate(Widget widget) {
        frame.getContentPane().add(widget.getRef());
    }

}
