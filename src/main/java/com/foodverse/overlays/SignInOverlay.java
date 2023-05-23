package com.foodverse.overlays;

import java.awt.Component;

import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.utility.ui.SecureTextField;
import com.foodverse.utility.ui.TextField;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.input.InputForm;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class SignInOverlay extends Overlay {

    private final Component component;
    private final int wrongAttemptsForRecovery = 5;

    // Counter for password recovery process
    private int recoveryCounter = wrongAttemptsForRecovery;

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    /*
     * private Row signInHeadingRow = new Row(); private Row emailInputRow = new Row(); private Row
     * passwordInputRow = new Row();
     */

    public SignInOverlay() {
        super(500, 400);

        // Heading
        var panel = new Column();

        var signInHeading = new Heading(UIConstants.SIGN_IN_TITLE, HeadingSize.L);
        var explanationParagraph =
            new Paragraph(UIConstants.SIGN_IN_PROMO_MESSAGE, ParagraphSize.M);
        var textEmailField = new TextField();
        var emailInput = new InputForm("Email", "", textEmailField);
        var textPasswordField = new SecureTextField();
        var passwordInput = new InputForm("Password", "", textPasswordField);
        var signInButton = new RectButton(
            "Sign In",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                var email = textEmailField.getText();
                var password = new String(textPasswordField.getPassword());
                var correctSign = db.signIn(email, password);

                if (correctSign) {
                    Router.pushPage(Pages.HOME);
                    Router.closeOverlay();
                } else {
                    recoveryCounter--;
                    if (recoveryCounter == 0) {
                        recoveryCounter = wrongAttemptsForRecovery; // Counter reset
                        var userExists = db.findUserByEmail(email);

                        // check if user exists and if yes then open password recovery overlay
                        if (userExists.isEmpty()) {
                            Router.openOverlay(new Alert(
                                UIConstants.USER_NOT_EXIST_TITLE,
                                UIConstants.USER_NOT_EXIST_DESCRIPTION));
                        } else {
                            // open password recovery overlay
                            Router.closeOverlay();
                            Router.openOverlay(new PasswordRecoveryOverlay(userExists.get()));
                        }
                    } else {
                        Router.openOverlay(new Alert(
                            UIConstants.INVALID_CREDENTIALS_TITLE,
                            UIConstants.INVALID_CREDENTIALS_DESCRIPTION));
                    }
                }
            });

        panel.addWidget(signInHeading, Align.CENTER);
        panel.addWidget(explanationParagraph, new EdgeInsets.Builder()
                .symmetric(24, 0)
                .build(),
            Align.CENTER);
        panel.addWidget(emailInput, Align.CENTER);
        panel.addWidget(passwordInput, Align.CENTER);
        panel.addWidget(signInButton, Align.CENTER);

        component = panel.getRef();

    }


    @Override
    public Component getRef() {
        return component;
    }

}
