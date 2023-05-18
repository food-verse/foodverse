package com.foodverse.overlays;

import java.awt.Component;

import javax.swing.JPanel;

import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.input.InputValidation;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.utility.ui.Colors;
import com.foodverse.utility.ui.SecureTextField;
import com.foodverse.utility.ui.TextField;
import com.foodverse.views.ConsentPolicyView;
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
    //private final Database db = Database.getInstance();

    // Getting a reference to the input validator...
    private final InputValidation validator = InputValidation.getInstance();

    public SignUpOverlay() {
        super(600, 600);

        var panel = new JPanel();
        var signUpHeading = new Heading(UIConstants.REGISTRATION_PROMO_TITLE, HeadingSize.L);
        var explanationParagraph = new Paragraph(UIConstants.REGISTRATION_PROMO_MESSAGE, ParagraphSize.M, Colors.gray600);
        var textNameInput = new TextField();
        var nameInput = new InputForm("Name", textNameInput);
        var textAddressInput = new TextField();
        var addressInput = new InputForm("Address", textAddressInput);
        var textPhoneInput = new TextField();
        var phoneInput = new InputForm("Phone", textPhoneInput);
        var textEmailInput = new TextField();
        var emailInput = new InputForm("Email", textEmailInput);
        var textPasswordInput = new SecureTextField();
        var passwordInput = new InputForm("Password", UIConstants.REGISTRATION_PASSWORD_FIELD_HINT, textPasswordInput);
        var continueButton = new RectButton(
            "Continue",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                var name = textNameInput.getText();
                var address = textAddressInput.getText();
                var phone = textPhoneInput.getText();
                var email = textEmailInput.getText();
                var password = new String(textPasswordInput.getPassword());

                boolean isValid = checkValidityOfCredentials(name, address, phone, email, password);

                if (isValid) {
                    Router.closeOverlay();
                    Router.openOverlay(new RecoveryOptionsOverlay(name, address, phone, email, password));
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
        panel.add(new ConsentPolicyView().getRef());
        panel.add(continueButton.getRef());
        panel.setOpaque(false);
        component = panel;

    }

    private boolean checkValidityOfCredentials(String name, String address, String phone, String email, String password) {
        boolean isValid;

        isValid = validator.isNameValid(name) && validator.isAddressValid(address) && validator.isPhoneValid(phone) && validator.isEmailValid(email) && validator.isPasswordValid(password);

        return isValid;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
