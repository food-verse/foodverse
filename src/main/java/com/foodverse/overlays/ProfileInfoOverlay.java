package com.foodverse.overlays;

import java.awt.Component;
import java.util.Optional;

import com.foodverse.models.Address;
import com.foodverse.models.User;
import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.input.InputValidation;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.utility.ui.Divider;
import com.foodverse.utility.ui.TextField;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;

public final class ProfileInfoOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    private User user;

    // Getting a reference to the input validator...
    private final InputValidation validator = InputValidation.getInstance();

    public ProfileInfoOverlay() {
        super(400, 400);
    }

    @Override
    public Component getRef() {

        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();
        if (signedUser.isPresent()) {
            user = signedUser.get();
        } else {
            Router.openOverlay(new Alert("Error", "Authenticated User is not found"));
            Router.closeOverlay();
        }

        // creating the page panel
        var panel = new Column();

        // creating overlay's heading widget
        var text = new Heading("Profile", HeadingSize.L);
        panel.addWidget(text, new EdgeInsets.Builder()
            .top(16)
            .left(8)
            .build(),
          Align.FIRST_LINE_START);
        
        // setting up fields to display the user's profile data

        // setting up view's name widget
        var nameWidget = new Column();
        var nameText = new Heading("Last-Name", HeadingSize.XS);
        var nameField = new TextField();
        nameField.setText(user.name());
        nameWidget.addWidget(nameText, new EdgeInsets.Builder()
                 .left(8)
                 .build(),
                 Align.LINE_START);
        nameWidget.addComponent(nameField, Align.CENTER);
        panel.addWidget(nameWidget, Align.LINE_START);

        // setting up view's phone widget
        var phoneNumberWidget = new Column();
        var phoneNumberText = new Heading("Phone Number", HeadingSize.XS);
        var phoneNumberField = new TextField();
        phoneNumberField.setText(user.phone());
        phoneNumberWidget.addWidget(phoneNumberText, new EdgeInsets.Builder()
                 .left(8)
                 .build(),
                 Align.LINE_START);
        phoneNumberWidget.addComponent(phoneNumberField, Align.CENTER);
        panel.addWidget(phoneNumberWidget, Align.LINE_START);

        // setting up view's email widget
        var emailWidget = new Column();
        var emailText = new Heading("Email", HeadingSize.XS);
        var emailField = new TextField();
        emailField.setText(user.email());
        emailWidget.addWidget(emailText, new EdgeInsets.Builder()
                 .left(8)
                 .build(),
                 Align.LINE_START);
        emailWidget.addComponent(emailField, Align.CENTER);
        panel.addWidget(emailWidget, Align.LINE_START);
       
        panel.addComponent(new Divider());

        // save updated data button
        var saveButton = new PillButton(
            "Save",
            ButtonSize.S,
            ButtonType.PRIMARY,
            e -> {
                boolean isValid = validator.isNameValid(nameField.getText()) && validator.isAddressValid(addressField.getText())
                && validator.isPhoneValid(phoneNumberField.getText()) && validator.isEmailValid(emailField.getText());
                if (!isValid){
                    Router.openOverlay(new Alert(
                        UIConstants.INVALID_CREDENTIALS_FORMAT_FOR_SIGNUP_TITLE,
                        UIConstants.INVALID_CREDENTIALS_FORMAT_FOR_SIGNUP_DESCRIPTION));
                } else {
                    // creating a new user with the updated data
                    User newUser = user.withName(nameField.getText())
                                       .withEmail(emailField.getText())
                                       .withPhone(phoneNumberField.getText());

                    // updating the user in the database
                    db.updateUser(newUser);
                }
            });
         panel.addWidget(saveButton, new EdgeInsets.Builder()
            .symmetric(14, 80)
            .build(),
           Align.LINE_START);

        return new ScrollView(panel.getRef()).getRef();
    }    
} 
