package com.foodverse.utility.navigation;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import com.foodverse.utility.factories.ShopFactory;
import com.foodverse.utility.factories.UserFactory;
import com.foodverse.utility.system.AssetManager;

public final class Shell {

    private static ShellOptions options;

    // The underlying frame holding our widgets
    private static final JFrame frame = new JFrame();

    private Shell() {}

    public static void init(ShellOptions options) {
        Shell.options = options;
        UserFactory.generate();
        ShopFactory.generate();
        AssetManager.loadFont("Inter");
        AssetManager.loadFont("IBMPlexMono");
        frame.setSize(options.getDimension());
        frame.getContentPane().setBackground(options.getBackgroundColor());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void render() {
        frame.setVisible(true);
    }

    public static ShellOptions getOptions() {
        return options;
    }

    public static JFrame getFrame() {
        return frame;
    }

}
