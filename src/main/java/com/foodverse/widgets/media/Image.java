package com.foodverse.widgets.media;

import java.awt.Component;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.foodverse.utility.Widget;
import com.foodverse.utility.core.ui.ImageStyle;
import com.foodverse.utility.system.AssetManager;

public final class Image extends Widget {

    private final JLabel component = new JLabel();

    public Image(String assetName, ImageStyle imageStyle) {
        component.setPreferredSize(imageStyle.getDimension());
        File file = new File(String.format("assets/images/%s", assetName));
        AssetManager
                .getImage(file, imageStyle.getWidth(), imageStyle.getHeight())
                .ifPresent(bufferedImage -> component.setIcon(new ImageIcon(bufferedImage)));
    }

    public Image(IconAsset asset, ImageStyle imageStyle) {
        component.setPreferredSize(imageStyle.getDimension());
        AssetManager
                .getImage(asset.getFile(), imageStyle.getWidth(), imageStyle.getHeight())
                .ifPresent(bufferedImage -> component.setIcon(new ImageIcon(bufferedImage)));
    }

    @Override
    public Component getRef() {
        return component;
    }

}
