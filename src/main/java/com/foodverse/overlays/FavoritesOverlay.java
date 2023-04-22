package com.foodverse.overlays;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.navigation.Overlay;

public final class FavoritesOverlay extends Overlay {

    private final Component component = new JPanel();

    @Override
    public Component getRef() {
        return component;
    }

}
