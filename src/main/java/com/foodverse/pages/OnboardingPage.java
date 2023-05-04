package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;

import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class OnboardingPage extends Page {

    private final Component component;

    public OnboardingPage() {
        var panel = new JPanel();
        var text = new Heading("Onboarding", HeadingSize.L);
        var signInPageButton = new RectButton(
            "Sign In ->",
            ButtonSize.S,
            ButtonType.PRIMARY,
            e -> Router.pushPage(Pages.LOGIN));
        var signUpPageButton = new RectButton(
            "Sign Up ->",
            ButtonSize.S,
            ButtonType.SECONDARY,
            e -> Router.pushPage(Pages.REGISTER));
        panel.add(text.getRef());
        panel.add(signInPageButton.getRef());
        panel.add(signUpPageButton.getRef());
        panel.setOpaque(false);
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
