package com.foodverse.widgets.media;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.foodverse.utility.AssetManager;
import com.foodverse.utility.Widget;
import com.foodverse.utility.core.ImageStyle;

public final class NetworkImage extends Widget {

    private final JLabel component = new JLabel();

    public NetworkImage(String src, ImageStyle imageStyle) {
        component.setPreferredSize(imageStyle.getDimension());
        AssetManager
                .getImage(src, imageStyle.getWidth(), imageStyle.getHeight())
                .ifPresent(bufferedImage -> component.setIcon(new ImageIcon(bufferedImage)));
    }

    @Override
    public Component getRef() {
        return component;
    }
}
