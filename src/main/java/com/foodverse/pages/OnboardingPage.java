package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.Page;
import com.foodverse.utility.Pages;
import com.foodverse.utility.Router;
import com.foodverse.utility.core.Button.ButtonSize;
import com.foodverse.utility.core.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class OnboardingPage extends Page {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        var text = new Heading("OnboardingPage", HeadingSize.L);
        var homePageButton = new RectButton(
                "HomePage ->",
                ButtonSize.XS,
                ButtonType.PRIMARY,
                e -> Router.pushPage(Pages.HOME));
        var signInPageButton = new RectButton(
                "SignInPage ->",
                ButtonSize.XS,
                ButtonType.PRIMARY,
                e -> Router.pushPage(Pages.HOME));
        var signUpPageButton = new RectButton(
                "SignUpPage ->",
                ButtonSize.XS,
                ButtonType.PRIMARY,
                e -> Router.pushPage(Pages.HOME));
        panel.add(text.getRef());
        panel.add(homePageButton.getRef());
        panel.add(signInPageButton.getRef());
        panel.add(signUpPageButton.getRef());
        panel.setOpaque(false);
        return panel;
    }

}
