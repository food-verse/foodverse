package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;

import com.foodverse.utility.input.InputValidation;
import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class SignUpPage extends Page {

    private final Component component;

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    // Getting a reference to the input validator...
    private final InputValidation validator = InputValidation.getInstance();

    public SignUpPage() {

        //
        validator.isIdValid("emilysmith123");

        var panel = new JPanel();
        var text = new Heading("Sign Up", HeadingSize.L);
        var openOnboardingPage = new RectButton(
            "<- Back",
            ButtonSize.S,
            ButtonType.SECONDARY,
            e -> {
                Router.pushPage(Pages.ONBOARDING);
            });
        var signUpButton = new RectButton(
            String.format("%s ->", getId()),
            ButtonSize.S,
            ButtonType.PRIMARY,
            e -> {
                if (db.signIn("emilysmith123", "XyZ987!")) {
                    Router.pushPage(Pages.HOME);
                }
            });
        panel.add(text.getRef());
        panel.add(openOnboardingPage.getRef());
        panel.add(signUpButton.getRef());
        panel.setOpaque(false);
        component = panel;

    }

    @Override
    public Component getRef() {
        return component;
    }

}
