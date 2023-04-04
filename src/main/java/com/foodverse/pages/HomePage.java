package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.overlays.OrderOverlay;
import com.foodverse.overlays.ProfileOverlay;
import com.foodverse.overlays.ShopOverlay;
import com.foodverse.utility.Page;
import com.foodverse.utility.Pages;
import com.foodverse.utility.Router;
import com.foodverse.utility.core.Button.ButtonSize;
import com.foodverse.utility.core.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class HomePage extends Page {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        var text = new Heading("HomePage", HeadingSize.L);
        var openOnboardingPage = new RectButton(
                "Open OnboardingPage ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.pushPage(Pages.ONBOARDING);
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
        panel.add(text.getRef());
        panel.add(openOnboardingPage.getRef());
        panel.add(openShopOverlay.getRef());
        panel.add(openOrderOverlay.getRef());
        panel.add(openProfileOverlay.getRef());
        panel.add(openTextPage.getRef());
        panel.add(openButtonPage.getRef());
        panel.setOpaque(false);
        return panel;
    }

}
