package com.foodverse.widgets.media;

import java.awt.Cursor;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.foodverse.utility.core.Widget;
import com.foodverse.utility.system.AssetManager;

public final class VectorImage extends Widget {

    private final JLabel component = new JLabel();
    private Consumer<MouseEvent> onPressed;

    public VectorImage(IconAsset asset) {
        this(asset, null, null);
    }

    public VectorImage(IconAsset asset, Consumer<MouseEvent> onPressed, String ariaLabel) {
        AssetManager
            .getVector(asset.getName())
            .ifPresent(bufferedImage -> component.setIcon(new ImageIcon(bufferedImage)));
        onPressed(onPressed, ariaLabel);
    }

    public void onPressed(Consumer<MouseEvent> onPressed, String ariaLabel) {
        this.onPressed = onPressed;
        if (onPressed != null) {
            component.setToolTipText(ariaLabel);
            component.addMouseListener(new VectorImageListener());
            component.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public Component getRef() {
        return component;
    }

    private class VectorImageListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            onPressed.accept(e);
        }

    }

}
