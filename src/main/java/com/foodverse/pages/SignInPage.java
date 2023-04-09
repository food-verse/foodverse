package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.core.ui.Button.ButtonSize;
import com.foodverse.utility.core.ui.Button.ButtonType;
import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class SignInPage extends Page {

    @Override
    public Component getRef() {
        // Heading
        var panel = new JPanel();
        var text = new Heading("SignInPage", HeadingSize.L);
        var openOnboardingPage = new RectButton(
                "Open OnboardingPage ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.pushPage(Pages.ONBOARDING);
                });
        var openOverviewPage = new RectButton(
                "Open OverviewPage ->",
                ButtonSize.S,
                ButtonType.SECONDARY,
                e -> {
                    Router.pushPage(Pages.OVERVIEW);
                });
        panel.add(text.getRef());
        panel.add(openOnboardingPage.getRef());
        panel.add(openOverviewPage.getRef());
        panel.setOpaque(false);
        return panel;
    }

}
