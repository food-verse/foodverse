package com.foodverse.overlays;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class AddressesOverlay extends Overlay {

    // page's main panel
    private static JPanel mainPanel = new JPanel();

    // creating the main panel
    public AddressesOverlay() {
        var text = new Heading("Addresses", HeadingSize.L);

        // Button to add an address. It takes the user to a new add address page
        var addAddressButton = new RectButton(
                "Add new address",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.openOverlay(new EditAddressOverlay());
                });

        // Return to Settings Page
        var openSettingsPage = new RectButton(
                "<- Back",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.closeOverlay();
                });

        // add the components to the panel
        mainPanel.add(text.getRef());
        mainPanel.add(addAddressButton.getRef());
        mainPanel.add(openSettingsPage.getRef());
        mainPanel.setOpaque(false);
    }

    // static method that creates a subPanel of a new address, taken the data from the AddAddress
    // Page.
    // It then adds the subPanel to the main panel.
    public static void addAddress(String address, String floor, String bell, String comments) {
        var subPanel = new JPanel();
        var addressText = new Heading("Address: " + address, HeadingSize.M);
        var floorText = new Heading("Floor: " + floor, HeadingSize.M);
        var bellText = new Heading("Bell: " + bell, HeadingSize.M);
        var commentsText = new Heading("Comments: " + comments, HeadingSize.M);
        subPanel.add(addressText.getRef());
        subPanel.add(floorText.getRef());
        subPanel.add(bellText.getRef());
        subPanel.add(commentsText.getRef());
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
        subPanel.setBorder(BorderFactory.createEtchedBorder());
        subPanel.setOpaque(false);
        mainPanel.add(subPanel);
    }

    @Override
    public Component getRef() {
        return mainPanel;
    }

}
