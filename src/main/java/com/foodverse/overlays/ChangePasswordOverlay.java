package com.foodverse.overlays;

import java.awt.Component;

import com.foodverse.models.User;
import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.input.InputValidation;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.utility.ui.SecureTextField;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.input.InputForm;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public class ChangePasswordOverlay extends Overlay {
    private final Component component;


    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    // Validation for inputs
    private final InputValidation validator = InputValidation.getInstance();


    /*
     * private Row signInHeadingRow = new Row(); private Row emailInputRow = new Row(); private Row
     * passwordInputRow = new Row();
     */

    public ChangePasswordOverlay(User user) {
        super(500, 450);

        // Heading
        var panel = new Column();
        var passwordChangeHeading = new Heading("Password Change", HeadingSize.L);
        var explanationParagraph = new Paragraph("Let's change password now", ParagraphSize.M);
        var textChangePassword = new SecureTextField();
        var newPasswordInput = new InputForm("Type your new password", textChangePassword);
        var textConfirmPassword = new SecureTextField();
        var confirmPasswordInput = new InputForm("Please write your new password again",
            UIConstants.REGISTRATION_PASSWORD_FIELD_HINT, textConfirmPassword);
        var changePasswordButton = new RectButton(
            "Change Password",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                var newPasswordAsAString = new String(textChangePassword.getPassword());
                var confirmPasswordAsAString = new String(textConfirmPassword.getPassword());

                var areValid = checkValidityOfNewPassword(newPasswordAsAString,
                    confirmPasswordAsAString);

                if (areValid) {
                    // change password and go to home page
                    var newCredentials = user.credentials().withPassword(newPasswordAsAString);
                    User updatedUser = user.withCredentials(newCredentials);
                    db.updateUser(updatedUser);
                    Router.closeOverlay();
                    Router.openOverlay(new Alert(UIConstants.SUCCESSFUL_PASSWORD_CHANGE_TITLE,
                        UIConstants.SUCCESSFUL_PASSWORD_CHANGE_DESCRIPTION));
                    Router.pushPage(Pages.HOME);
                } else {
                    // show message for wrong format
                    Router.openOverlay(new Alert(UIConstants.WRONG_NEW_PASSWORD_FORMAT_TITLE,
                        UIConstants.WRONG_NEW_PASSWORD_FORMAT_DESCRIPTION));
                }

            }

        );


        panel.addWidget(passwordChangeHeading, Align.CENTER);
        panel.addWidget(explanationParagraph, new EdgeInsets.Builder()
                .symmetric(24, 0)
                .build(),
            Align.CENTER);
        panel.addWidget(newPasswordInput, Align.CENTER);
        panel.addWidget(confirmPasswordInput, Align.CENTER);
        panel.addWidget(changePasswordButton, Align.CENTER);

        component = panel.getRef();
    }


    private boolean checkValidityOfNewPassword(String newPasswd, String confirmPasswd) {
        return validator.isPasswordValid(newPasswd) && validator.isPasswordValid(confirmPasswd)
            && newPasswd.equals(confirmPasswd);
    }

    @Override
    public Component getRef() {
        return component;
    }
}
