package com.foodverse.overlays;

import com.foodverse.models.Address;
import com.foodverse.props.RecoveryOptionsProps;
import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.input.InputValidation;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
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
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

import java.awt.*;

public final class SignUpOverlay extends Overlay {

    private final Component component;

    // Getting a reference to the input validator...
    private final InputValidation validator = InputValidation.getInstance();

    public SignUpOverlay() {
        super(500, 700);
        var panel = new Column();
        var signUpHeading = new Heading(UIConstants.REGISTRATION_PROMO_TITLE, HeadingSize.L);
        var explanationParagraph = new Paragraph(
            UIConstants.REGISTRATION_PROMO_MESSAGE,
            ParagraphSize.M,
            Colors.gray600
        );
        var textNameInput = new TextField();
        var nameInput = new InputForm("Name", textNameInput);
        var textAddressInput = new TextField();
        var addressInput = new InputForm("Address", textAddressInput);
        var textPhoneInput = new TextField();
        var phoneInput = new InputForm(
            "Phone",
            UIConstants.REGISTRATION_PHONE_FIELD_HINT,
            textPhoneInput
        );
        var textEmailInput = new TextField();
        var emailInput = new InputForm("Email", textEmailInput);
        var textPasswordInput = new SecureTextField();
        var passwordInput = new InputForm(
            "Password",
            UIConstants.REGISTRATION_PASSWORD_FIELD_HINT,
            textPasswordInput
        );
        var consentPolicyParagraph = new ConsentPolicyView();
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
                if (checkValidityOfCredentials(name, address, phone, email, password)) {
                    Router.closeOverlay();
                    Router.openOverlay(
                        new RecoveryOptionsOverlay(new RecoveryOptionsProps(
                            name,
                            new Address(address, "", "", "", ""),
                            phone,
                            email,
                            password)
                        ));
                } else
                    Router.openOverlay(new Alert(
                        UIConstants.INVALID_CREDENTIALS_FORMAT_FOR_SIGNUP_TITLE,
                        UIConstants.INVALID_CREDENTIALS_FORMAT_FOR_SIGNUP_DESCRIPTION
                    ));
            });

        panel.addWidget(signUpHeading, Align.CENTER);
        panel.addWidget(explanationParagraph, new EdgeInsets.Builder()
                .symmetric(24, 0)
                .build(),
            Align.CENTER);
        panel.addWidget(nameInput, Align.CENTER);
        panel.addWidget(addressInput, Align.CENTER);
        panel.addWidget(phoneInput, Align.CENTER);
        panel.addWidget(emailInput, Align.CENTER);
        panel.addWidget(passwordInput, Align.CENTER);
        panel.addWidget(consentPolicyParagraph, Align.CENTER);
        panel.addWidget(continueButton, Align.CENTER);

        component = panel.getRef();

    }

    private boolean checkValidityOfCredentials(
        String name,
        String address,
        String phone,
        String email,
        String password
    ) {
        return validator.isNameValid(name) && validator.isAddressValid(address)
            && validator.isPhoneValid(phone) && validator.isEmailValid(email)
            && validator.isPasswordValid(password);
    }

    @Override
    public Component getRef() {
        return component;
    }

}
