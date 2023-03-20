package com.foodverse.utility;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class Image extends Widget {

    private final JLabel component = new JLabel();

    public Image(ImageAsset asset, ImageStyle imageStyle) {
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
