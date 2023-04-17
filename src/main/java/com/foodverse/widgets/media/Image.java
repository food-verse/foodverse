package com.foodverse.widgets.media;

import java.awt.Component;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.system.AssetManager;
import com.foodverse.utility.system.EnvironmentOptions;
import com.foodverse.utility.system.ResourceHandler;
import com.foodverse.utility.system.EnvironmentOptions.Mode;
import com.foodverse.utility.ui.ImageStyle;

public final class Image extends Widget {

    private final JLabel component = new JLabel();

    public Image(String assetName, ImageStyle imageStyle) {
        component.setPreferredSize(imageStyle.getDimension());
        if (EnvironmentOptions.getMode() == Mode.DEBUG) {
            AssetManager
                    .getImage(new File(String.format("assets/images/%s", assetName)),
                            imageStyle.getWidth(), imageStyle.getHeight())
                    .ifPresent(bufferedImage -> component.setIcon(new ImageIcon(bufferedImage)));
        } else {
            AssetManager
                    .getImage(ResourceHandler
                            .loadResourceAsURL(String.format("images/%s", assetName)),
                            imageStyle.getWidth(), imageStyle.getHeight())
                    .ifPresent(bufferedImage -> component.setIcon(new ImageIcon(bufferedImage)));
        }
    }

    @Override
    public Component getRef() {
        return component;
    }

}
