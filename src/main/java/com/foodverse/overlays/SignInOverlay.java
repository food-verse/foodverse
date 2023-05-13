package com.foodverse.overlays;

import java.awt.Component;

import javax.swing.JPanel;

import com.foodverse.utility.common.UIConstants;
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
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class SignInOverlay extends Overlay {

    private final Component component;

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    /*
     * private Row signInHeadingRow = new Row(); private Row emailInputRow = new Row(); private Row
     * passwordInputRow = new Row();
     */

    public SignInOverlay() {
        super(500, 400);

        // Heading
        var panel = new JPanel();
        var signInHeading = new Heading("Sign In", HeadingSize.XXL);
        var explanationParagraph = new Paragraph("Please sign to your account", ParagraphSize.M);
        var textEmailField = new TextField();
        var emailInput = new InputForm("Email", "", textEmailField);
        var textPasswordField = new SecureTextField();
        var passwordInput = new InputForm("Password", "", textPasswordField);
        var signInButton = new RectButton(
            "Sign In",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {

                // JOptionPane.showMessageDialog(null, "Email:" + textEmailField.getText() + "
                // Password: " + textPasswordField.getText());
                if (db.signIn(textEmailField.getText(), new String(textPasswordField.getPassword()))) {

                    Router.pushPage(Pages.HOME);
                    Router.closeOverlay();
                } else {
                    Router.openOverlay(new Alert(
                        UIConstants.INVALID_CREDENTIALS_TITLE,
                        UIConstants.INVALID_CREDENTIALS_DESCRIPTION));
                }
            });

        // signInHeadingRow.addComponent(signInHeading, Align.LINE_START);

        // panel.add(signInHeadingRow.getRef());
        panel.add(signInHeading.getRef());
        panel.add(explanationParagraph.getRef());
        panel.add(emailInput.getRef());
        panel.add(passwordInput.getRef());
        panel.add(signInButton.getRef());
        panel.setOpaque(false);
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
