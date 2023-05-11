package com.foodverse.overlays;

import java.awt.Component;
import javax.swing.JPanel;

import com.foodverse.utility.navigation.Overlay;
import com.foodverse.widgets.text.Heading;

public class InfoOverlay extends Overlay {

    private final Component component;

    public InfoOverlay() {
        super(600, 672);

        // Heading
        var panel = new JPanel();
        var text = new Heading(
            "<html>Order delivery online in 3<br>simple steps</html>",
            Heading.HeadingSize.XXL);
        panel.add(text.getRef());
        panel.setOpaque(false);
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
