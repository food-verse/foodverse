package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.overlays.OrderOverlay;
import com.foodverse.overlays.ProfileOverlay;
import com.foodverse.overlays.ShopOverlay;
import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.input.InputForm;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class OverviewPage extends Page {

    private final Component component;

    public OverviewPage() {
        var panel = new JPanel();
        var text = new Heading("OverviewPage", HeadingSize.L);
        var openOnboardingPage = new RectButton(
                "Open OnboardingPage ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.pushPage(Pages.ONBOARDING);
                });
        var openSignInPage = new RectButton(
                "Open SignInPage ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.pushPage(Pages.LOGIN);
                });
        var openSignUpPage = new RectButton(
                "Open SignUpPage ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.pushPage(Pages.REGISTER);
                });
        var openHomePage = new RectButton(
                "Open HomePage ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.pushPage(Pages.HOME);
                });
        var openShopOverlay = new RectButton(
                "Open ShopOverlay ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.openOverlay(new ShopOverlay());
                });
        var openOrderOverlay = new RectButton(
                "Open OrderOverlay ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.openOverlay(new OrderOverlay());
                });
        var openProfileOverlay = new RectButton(
                "Open ProfileOverlay ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.openOverlay(new ProfileOverlay());
                });
        var openTextPage = new RectButton(
                "Open TextPage ->",
                ButtonSize.S,
                ButtonType.SECONDARY,
                e -> {
                    Router.pushPage(Pages.TEXTS);
                });
        var openButtonPage = new RectButton(
                "Open ButtonPage ->",
                ButtonSize.S,
                ButtonType.SECONDARY,
                e -> {
                    Router.pushPage(Pages.BUTTONS);
                });
        var inputForm = new InputForm("Label", "Hint");
        var toggleButton = new PillButton("Toggle Field",
                ButtonSize.S,
                ButtonType.TERTIARY,
                e -> {
                    inputForm.toggle();
                });
        panel.add(text.getRef());
        panel.add(openOnboardingPage.getRef());
        panel.add(openSignInPage.getRef());
        panel.add(openSignUpPage.getRef());
        panel.add(openHomePage.getRef());
        panel.add(openShopOverlay.getRef());
        panel.add(openOrderOverlay.getRef());
        panel.add(openProfileOverlay.getRef());
        panel.add(openTextPage.getRef());
        panel.add(openButtonPage.getRef());
        panel.add(inputForm.getRef());
        panel.add(toggleButton.getRef());
        panel.setOpaque(false);
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
