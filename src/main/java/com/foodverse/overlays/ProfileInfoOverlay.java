package com.foodverse.overlays;

import java.awt.Component;
import java.awt.Dimension;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.foodverse.models.User;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.utility.ui.TextField;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;

public final class ProfileInfoOverlay extends Overlay {

    private Component component;

    public ProfileInfoOverlay(User user, Database db) {
        
        super(400, 400);

        // creating the page panel
        var panel = new JPanel();
        var text = new Heading("Profile", HeadingSize.L);

        // setting up fields to display the user's profile data
        var emailText = new Heading("Email", HeadingSize.M);
        var emailField = new TextField();
        emailField.setText(user.email());
        
        var idText = new Heading("ID", HeadingSize.M);
        var idField = new TextField();
        idField.setText(user.id());
       
        var nameText = new Heading("Last-Name", HeadingSize.M);
        var nameField = new TextField();
        nameField.setText(user.name());
      
        var phoneNumberText = new Heading("Phone Number", HeadingSize.M);
        var phoneNumberField = new TextField();
        phoneNumberField.setText(user.phone());

        // confirm changes to the profile data button
        var confirmChangesButton = new RectButton(
                "Confirm Changes",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    // check if the data given by the user are valid. If yes it updates the user, if not it shows an error message
                    if ((!emailField.getText().contains("@")) || (!emailField.getText().endsWith(".com"))) {
                        JOptionPane.showMessageDialog(null, "Email address is not valid", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (!isValidNumber(phoneNumberField.getText())) {
                        JOptionPane.showMessageDialog(null, "Phone number is not valid", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        // creating a new user with the updated data
                        User updatedUser = user.withEmail(emailField.getText())
                                               .withName(nameField.getText())
                                               .withPhone(phoneNumberField.getText());

                        // updating the user in the database
                        db.updateUser(updatedUser);
                    }
                });

        // return to settings button
        var openSettingsPage = new RectButton(
                "<- Back",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.closeOverlay();
                });


        // adding the components to the panel
        panel.add(text.getRef());
        panel.add(emailText.getRef());
        panel.add(emailField);
        panel.add(idText.getRef());
        panel.add(idField);
        panel.add(nameText.getRef());
        panel.add(nameField);
        panel.add(phoneNumberText.getRef());
        panel.add(phoneNumberField);
        panel.add(confirmChangesButton.getRef());
        panel.add(openSettingsPage.getRef());
        panel.setPreferredSize(new Dimension(1200, 400));
        panel.setOpaque(false);
        component = panel;
    }

    public Component getRef() {
        return component;
    }
    
    // method to check if a number submitted by a user is valid
    public boolean isValidNumber(String phoneNumber) {
        // Regular expression pattern for a valid US phone number
        String pattern = "^(\\+1)?[\\-\\s]?(\\(?\\d{3}\\)?[\\-\\s]?){2}\\d{4}$";
    
        // Compile the pattern into a regex object
        Pattern regex = Pattern.compile(pattern);
    
        // Match the regex against the phone number
        Matcher matcher = regex.matcher(phoneNumber);
    
        // Return whether the phone number matches the pattern
        return matcher.matches();
    }

}
