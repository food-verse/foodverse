package com.foodverse.overlays;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.Overlay;
import com.foodverse.utility.Router;
import com.foodverse.utility.core.Button.ButtonSize;
import com.foodverse.utility.core.Button.ButtonType;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public class ProfileOverlay extends Overlay {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        var text = new Heading("ProfileOverlay", HeadingSize.L);
        var button = new PillButton(
                "Close ProfileOverlay ->",
                ButtonSize.XS,
                ButtonType.SECONDARY,
                e -> {
                    Router.closeOverlay();
                });
        var addressPage = new RectButton(
                "Addresses ->",
                ButtonSize.XS,
                ButtonType.TERTIARY,
                e -> {
                    Router.openOverlay(new AddressOverlay());
                });
        panel.add(text.getRef());
        panel.add(button.getRef());
        panel.add(addressPage.getRef());
        panel.setOpaque(false);
        return panel;
    }

}

