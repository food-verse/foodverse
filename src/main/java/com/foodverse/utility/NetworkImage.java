package com.foodverse.utility;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class NetworkImage extends Widget {

    private final JLabel component = new JLabel();

    public NetworkImage(String src, ImageStyle imageStyle) {
        component.setPreferredSize(imageStyle.getDimension());
        AssetManager
                .getImage(src, imageStyle.getImageWidth(), imageStyle.getImageHeight())
                .ifPresent(bufferedImage -> component.setIcon(new ImageIcon(bufferedImage)));
    }

    @Override
    public Component getRef() {
        return component;
    }
}
