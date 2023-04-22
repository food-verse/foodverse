package com.foodverse.overlays;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.TextField;
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

        //
        var addressField = new TextField();
        var addressForm = new InputForm("Address", " ", addressField);

        //
        var floorField = new TextField();
        var floorForm = new InputForm("Floor", " ", floorField);

        //
        var bellField = new TextField();
        var bellForm = new InputForm("Bell", " ", bellField);

        //
        var commentsField = new TextField();
        var commentsForm = new InputForm("Comments", " ", commentsField);

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
        panel.add(addressForm.getRef());
        panel.add(floorForm.getRef());
        panel.add(bellForm.getRef());
        panel.add(commentsForm.getRef());
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
