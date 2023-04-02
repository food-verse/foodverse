package com.foodverse.utility;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public final class Shell {

    private static Shell shell;
    private static ShellOptions options;

    // The underlying frame holding our widgets
    private static final JFrame mainFrame = new JFrame();

    private Shell() {}

    public static Shell getInstance() {
        if (shell == null) {
            shell = new Shell();
        }
        return shell;
    }

    public static void init(ShellOptions options) {
        Shell.options = options;
        AssetManager.loadFont("Inter");
        AssetManager.loadFont("IBMPlexMono");
        mainFrame.setSize(options.getDimension());
        mainFrame.getContentPane().setBackground(options.getBackgroundColor());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public static ShellOptions getOptions() {
        return options;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

}
