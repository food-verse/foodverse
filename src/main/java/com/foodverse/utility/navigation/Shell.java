package com.foodverse.utility.navigation;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import com.foodverse.utility.factories.ShopFactory;
import com.foodverse.utility.factories.UserFactory;
import com.foodverse.utility.system.AssetManager;
import com.foodverse.utility.system.EnvironmentOptions;
import com.foodverse.utility.system.FileManager;
import com.foodverse.utility.system.EnvironmentOptions.Mode;

public final class Shell {

    private static ShellOptions options;

    // The underlying frame holding our widgets
    private static final JFrame frame = new JFrame();

    private Shell() {}

    public static void init(ShellOptions options) {
        Shell.options = options;
        if (FileManager.loadUsers().isEmpty()) {
            UserFactory.generate();
        }
        if (FileManager.loadShops().isEmpty()) {
            ShopFactory.generate();
        }
        if (EnvironmentOptions.getMode() == Mode.DEBUG) {
            AssetManager.loadFont(options.getDefaultFont());
            AssetManager.loadFont(options.getMonospacedFont());
        } else {
            var assetIndex = FileManager.loadAssetIndex();
            AssetManager.loadFont(
                    options.getDefaultFont(),
                    assetIndex.fonts().get(options.getDefaultFont()));
            AssetManager.loadFont(
                    options.getDefaultFont(),
                    assetIndex.fonts().get(options.getMonospacedFont()));
        }
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
