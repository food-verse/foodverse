package com.foodverse.overlays;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.Overlay;
import com.foodverse.utility.Router;
import com.foodverse.utility.core.Button.ButtonSize;
import com.foodverse.utility.core.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class AddressOverlay extends Overlay {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        var text = new Heading("AddressPage", HeadingSize.L);
        var button = new RectButton(
                "<- Go back",
                ButtonSize.XS,
                ButtonType.PRIMARY,
                e -> {
                    Router.closeOverlay();
                });
        panel.add(text.getRef());
        panel.add(button.getRef());
        panel.setOpaque(false);
        return panel;
    }

}
