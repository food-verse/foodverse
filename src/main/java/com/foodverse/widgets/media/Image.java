package com.foodverse.widgets.media;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.foodverse.utility.Widget;
import com.foodverse.utility.core.ui.ImageAsset;
import com.foodverse.utility.core.ui.ImageStyle;
import com.foodverse.utility.system.AssetManager;

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
