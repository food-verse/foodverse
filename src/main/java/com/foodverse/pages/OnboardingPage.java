package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;

import com.foodverse.overlays.InfoOverlay;
import com.foodverse.overlays.SignInOverlay;
import com.foodverse.overlays.SignUpOverlay;
import com.foodverse.utility.common.URLHandler;
import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.VectorImage;

public final class OnboardingPage extends Page {

    private final Component component;

    public OnboardingPage() {

        //
        var panel = new JPanel();
        panel.setOpaque(false);

        // Creating image widgets...
        var repositoryUrl = "https://github.com/food-verse/foodverse";
        var brandImage = new VectorImage(IconAsset.BRAND, e -> {
            URLHandler.open(repositoryUrl);
        }, repositoryUrl);

        // Creating button widgets...
        var infoButton = new PillButton(
            "How it works",
            ButtonSize.M,
            ButtonType.TERTIARY,
            e -> Router.openOverlay(new InfoOverlay()));
        var signInButton = new PillButton(
            "Sign In",
            ButtonSize.M,
            ButtonType.SECONDARY,
            e -> Router.openOverlay(new SignInOverlay()));
        var signUpButton = new PillButton(
            "Sign Up",
            ButtonSize.M,
            ButtonType.PRIMARY,
            e -> Router.openOverlay(new SignUpOverlay()));

        panel.add(brandImage.getRef());
        panel.add(infoButton.getRef());
        panel.add(signInButton.getRef());
        panel.add(signUpButton.getRef());
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
