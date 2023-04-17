package com.foodverse.widgets.media;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.system.AssetManager;
import com.foodverse.utility.system.EnvironmentOptions;
import com.foodverse.utility.system.ResourceHandler;
import com.foodverse.utility.system.EnvironmentOptions.Mode;

public final class VectorImage extends Widget {

    private final JLabel component = new JLabel();
    private final Consumer<MouseEvent> onPressed;

    public VectorImage(IconAsset asset) {
        this(asset, null);
    }

    public VectorImage(IconAsset asset, Consumer<MouseEvent> onPressed) {
        this.onPressed = onPressed;
        if (EnvironmentOptions.getMode() == Mode.DEBUG) {
            AssetManager
                    .getVector(asset.getFile())
                    .ifPresent(bufferedImage -> component.setIcon(new ImageIcon(bufferedImage)));
        } else {
            AssetManager
                    .getVector(ResourceHandler.loadResourceAsURL(asset.getName()))
                    .ifPresent(bufferedImage -> component.setIcon(new ImageIcon(bufferedImage)));
        }
        if (onPressed != null) {
            component.addMouseListener(new VectorImageListener());
        }
    }

    private class VectorImageListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            onPressed.accept(e);
        }

    }

    @Override
    public Component getRef() {
        return component;
    }

}
