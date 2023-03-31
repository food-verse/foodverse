package com.foodverse.utility;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public final class Shell {

    private static Shell shell;

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
        AssetManager.loadFont("Inter");
        AssetManager.loadFont("IBMPlexMono");
        mainFrame.setSize(options.getDimension());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

}
