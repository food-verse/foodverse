package com.foodverse.overlays;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Optional;
import javax.swing.JPanel;

import com.foodverse.models.User;
import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.modal.Dialog;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public class ProfileOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    private User user;

    private final Component component;

    public ProfileOverlay() {

        super(400, 400);

        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();
        if (signedUser.isPresent()) {
            user = signedUser.get();
        } else {
            Router.openOverlay(new Alert("Error", "Authenticated User is not found"));
            Router.closeOverlay();
        }

        // Creating the page panel
        var panel = new JPanel();
        var text = new Heading("Profile", HeadingSize.L);

        // Buttons that lead to the various settings we can use
        var openProfilePage = new RectButton(
            "Profile Info ->",
            ButtonSize.S,
            ButtonType.SECONDARY,
            e -> {
                Router.openOverlay(new ProfileInfoOverlay(user, db));
            });
        var openAddressesPage = new RectButton(
            "Addresses ->",
            ButtonSize.S,
            ButtonType.SECONDARY,
            e -> {
                Router.openOverlay(new AddressesOverlay());
            });
        var openFavoritesPage = new RectButton(
            "Favorites ->",
            ButtonSize.S,
            ButtonType.SECONDARY,
            e -> {
                Router.openOverlay(new FavoritesOverlay());
            });
        var openHistoryPage = new RectButton(
            "History ->",
            ButtonSize.S,
            ButtonType.SECONDARY,
            e -> {
                Router.openOverlay(new HistoryOverlay());
            });

        // Signing out...
        var signOutButton = new RectButton(
            "Sign Out ->",
            ButtonSize.S,
            ButtonType.PRIMARY,
            e -> {
                signOutAction(e);
            });

        // adding all the buttons to the panel
        panel.add(text.getRef());
        panel.add(openProfilePage.getRef());
        panel.add(openAddressesPage.getRef());
        panel.add(openFavoritesPage.getRef());
        panel.add(openHistoryPage.getRef());
        panel.add(signOutButton.getRef());
        panel.setOpaque(false);
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

    private void signOutAction(ActionEvent e) {
        Router.openOverlay(new Dialog(
            "Sign Out",
            UIConstants.SIGN_OUT_CONFIRM_MESSAGE,
            "Sign Out",
            event -> {
                db.signOut();
                Router.closeOverlay();
                Router.pushPage(Pages.ONBOARDING);
            }));
    }

}

