package com.foodverse.overlays;

import java.awt.Component;

import javax.swing.JPanel;

import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.input.InputValidation;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.utility.ui.TextField;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.input.InputForm;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class SignUpOverlay extends Overlay {

    private final Component component;

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    // Getting a reference to the input validator...
    private final InputValidation validator = InputValidation.getInstance();

    public SignUpOverlay() {
        super(600, 840);

        //
        // validator.isIdValid("emilysmith123");

        var panel = new JPanel();
        var signUpHeading = new Heading("Sign Up", HeadingSize.XXL);
        var explanationParagraph =
            new Paragraph("Sign up now and start enjoying delicious meals.", ParagraphSize.S);
        var textNameInput = new TextField();
        var nameInput = new InputForm("Name", "", textNameInput);
        var textAddressInput = new TextField();
        var addressInput = new InputForm("Address", "", textAddressInput);
        var textPhoneInput = new TextField();
        var phoneInput = new InputForm("Phone", "", textPhoneInput);
        var textEmailInput = new TextField();
        var emailInput = new InputForm("Email", "", textEmailInput);
        var textPasswordInput = new TextField();
        var passwordInput = new InputForm("Password", "Your password must be at least 8 characters",
            textPasswordInput);
        var privacyPolicyParagraph = new Paragraph(
            "By signing up you agree to our Terms of Use and Privacy Policy.", ParagraphSize.S);
        var continueButton = new RectButton(
            "Sign Up",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                boolean isValid = checkCredentials(textNameInput, textAddressInput,
                    textPhoneInput, textEmailInput, textPasswordInput);

                if (isValid) {
                    System.out.println("OK");// something
                    Router.closeOverlay();
                    Router.openOverlay(new RecoveryOptionsOverlay(
                        textNameInput.getText(), textAddressInput.getText(),
                        textPhoneInput.getText(), textEmailInput.getText(),
                        textPasswordInput.getText())); // just to check if its display is
                    // alright
                } else
                    Router.openOverlay(new Alert(
                        UIConstants.INVALID_CREDENTIALS_FORMAT_FOR_SIGNUP_TITLE,
                        UIConstants.INVALID_CREDENTIALS_FORMAT_FOR_SIGNUP_DESCRIPTION));

            });


        panel.add(signUpHeading.getRef());
        panel.add(explanationParagraph.getRef());
        panel.add(nameInput.getRef());
        panel.add(addressInput.getRef());
        panel.add(phoneInput.getRef());
        panel.add(emailInput.getRef());
        panel.add(passwordInput.getRef());
        panel.add(privacyPolicyParagraph.getRef());
        panel.add(continueButton.getRef());
        panel.setOpaque(false);
        component = panel;

    }

    private boolean checkCredentials(TextField name, TextField address, TextField phone,
                                     TextField email, TextField password) {
        boolean isValid;

        isValid = validator.isNameValid(name.getText())
            && validator.isAddressValid(address.getText()) &&
            validator.isPhoneValid(phone.getText()) && validator.isEmailValid(email.getText())
            &&
            validator.isPasswordValid(password.getText());

        return isValid;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
