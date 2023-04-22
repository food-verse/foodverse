package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class SignInPage extends Page {

    private final Component component;

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    public SignInPage() {

        // Heading
        var panel = new JPanel();
        var text = new Heading("Sign In", HeadingSize.L);
        var openOnboardingPage = new RectButton(
                "<- Back",
                ButtonSize.S,
                ButtonType.SECONDARY,
                e -> {
                    Router.pushPage(Pages.ONBOARDING);
                });
        var signInButton = new RectButton(
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
        panel.add(signInButton.getRef());
        panel.setOpaque(false);
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
