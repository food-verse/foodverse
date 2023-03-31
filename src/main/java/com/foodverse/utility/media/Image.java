package com.foodverse.utility.media;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.foodverse.utility.AssetManager;
import com.foodverse.utility.Widget;

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
