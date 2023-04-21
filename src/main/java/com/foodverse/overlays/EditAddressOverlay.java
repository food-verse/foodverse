package com.foodverse.overlays;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.input.InputForm;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public class EditAddressOverlay extends Overlay {

    private final Component component;

    public EditAddressOverlay() {

        // creating the page panel.
        var panel = new JPanel();
        var text = new Heading("Add Address", HeadingSize.L);
        var address = new InputForm("Address", " ");
        var floor = new InputForm("Floor", " ");
        var bell = new InputForm("Bell", " ");
        var comments = new InputForm("Comments", " ");

        // Confirm the new Address that's gonna be added to the AddressesPage using the static
        // method addAddress.
        var confirmButton = new RectButton(
                "Confirm new Address",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.closeOverlay();
                });

        // Abort any changes and return to the AddressesPage
        var returnButton = new RectButton(
                "<- Abort changes",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.closeOverlay();
                });

        // Adding the components to the panel
        panel.add(text.getRef());
        panel.add(address.getRef());
        panel.add(floor.getRef());
        panel.add(bell.getRef());
        panel.add(comments.getRef());
        panel.add(confirmButton.getRef());
        panel.add(returnButton.getRef());
        panel.setOpaque(false);
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
