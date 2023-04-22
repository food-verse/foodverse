package com.foodverse.overlays;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.layout.SizedBox;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.utility.ui.TextField;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;

public final class ProfileInfoOverlay extends Overlay {

    public Component getRef() {

        // creating the page panel
        var panel = new JPanel();
        var text = new Heading("Profile", HeadingSize.L);

        // setting up fields to display the user's profile data
        var emailText = new Heading("Email", HeadingSize.M);
        var emailField = new TextField();
        emailField.setText("emilysmith123@gmail.com");
        var nameText = new Heading("Name", HeadingSize.M);
        var nameField = new TextField();
        nameField.setText("Emily");
        var lastNameText = new Heading("Last-Name", HeadingSize.M);
        var lastNameField = new TextField();
        lastNameField.setText("Smith");
        var phoneNumberText = new Heading("Phone Number", HeadingSize.M);
        var phoneNumberField = new TextField();
        phoneNumberField.setText("+1 (555) 555-1234");

        // return to settings button
        var openSettingsPage = new RectButton(
                "<- Back",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.closeOverlay();
                });


        // adding the components to the panel
        var box = new SizedBox(1, 1);
        panel.add(box.getRef());
        panel.add(text.getRef());
        panel.add(emailText.getRef());
        panel.add(emailField);
        panel.add(nameText.getRef());
        panel.add(nameField);
        panel.add(lastNameText.getRef());
        panel.add(lastNameField);
        panel.add(phoneNumberText.getRef());
        panel.add(phoneNumberField);
        panel.add(openSettingsPage.getRef());
        panel.setOpaque(false);
        return panel;
    }

}
