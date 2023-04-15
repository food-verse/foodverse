package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class SettingsPage extends Page {
    
    private final Component component;

    public SettingsPage() {
         
        var panel = new JPanel();
        var text = new Heading("Settings", HeadingSize.L);
        var openProfilePage = new RectButton(
                "Open Profile Page ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.pushPage(Pages.PROFILE);
                });
        var openHistoryPage = new RectButton(
                "Open History Page ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.pushPage(Pages.HISTORY);
                });
        var openAddressesPage = new RectButton(
                "Open Addresses Page ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.pushPage(Pages.ADDRESSES);
                });
        var openFavouritesPage = new RectButton(
                "Open Favourites Page ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.pushPage(Pages.FAVOURITES);
                });
        var openInfoPage = new RectButton(
                "Open Info page ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.pushPage(Pages.INFO);
                });
        var openOnboardingPage = new RectButton(
                "Return to Overview Page ->",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.pushPage(Pages.OVERVIEW);
                });
        panel.add(text.getRef());
        panel.add(openProfilePage.getRef());
        panel.add(openHistoryPage.getRef());
        panel.add(openAddressesPage.getRef());
        panel.add(openFavouritesPage.getRef());
        panel.add(openInfoPage.getRef());
        panel.add(openOnboardingPage.getRef());
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }
}
