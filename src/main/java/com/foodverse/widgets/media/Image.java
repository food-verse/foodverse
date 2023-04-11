package com.foodverse.widgets.media;

import java.awt.Component;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.system.AssetManager;
import com.foodverse.utility.ui.ImageStyle;

public final class Image extends Widget {

    private final JLabel component = new JLabel();

    public Image(String assetName, ImageStyle imageStyle) {
        component.setPreferredSize(imageStyle.getDimension());
        File file = new File(String.format("assets/images/%s", assetName));
        AssetManager
                .getImage(file, imageStyle.getWidth(), imageStyle.getHeight())
                .ifPresent(bufferedImage -> component.setIcon(new ImageIcon(bufferedImage)));
    }

    @Override
    public Component getRef() {
        return component;
    }

}
