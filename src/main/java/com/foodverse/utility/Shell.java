package com.foodverse.utility;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public final class Shell {

    private static ShellOptions options;

    // The underlying frame holding our widgets
    private static final JFrame frame = new JFrame();

    private Shell() {}

    public static void init(ShellOptions options) {
        Shell.options = options;
        AssetManager.loadFont("Inter");
        AssetManager.loadFont("IBMPlexMono");
        frame.setSize(options.getDimension());
        frame.getContentPane().setBackground(options.getBackgroundColor());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static ShellOptions getOptions() {
        return options;
    }

    public static JFrame getFrame() {
        return frame;
    }

}
