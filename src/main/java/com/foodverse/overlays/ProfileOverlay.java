package com.foodverse.overlays;

import java.awt.Component;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.foodverse.models.User;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public class ProfileOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    @Override
    public Component getRef() {

        // Getting the authenticated user...
        db.getAuthenticatedUser().ifPresentOrElse(signedUser -> {
            // System.out.println(signedUser);
        }, () -> {
            // System.out.println("Authenticated user not found");
        });

        // Alternative
        Optional<User> signedUser = db.getAuthenticatedUser();
        if (signedUser.isPresent()) {
            // System.out.println(signedUser.get());
        } else {
            // System.out.println("Authenticated user not found");
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
                Router.openOverlay(new ProfileInfoOverlay());
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
                Object[] options = {"Cancel", "Sign Out"};
                var option = JOptionPane.showOptionDialog(
                    null,
                    "Are you sure you want to sign out?",
                    "Sign Out",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
                if (option == JOptionPane.NO_OPTION) {
                    db.signOut();
                    Router.closeOverlay();
                    Router.pushPage(Pages.LOGIN);
                }
            });

        // adding all the buttons to the panel
        panel.add(text.getRef());
        panel.add(openProfilePage.getRef());
        panel.add(openAddressesPage.getRef());
        panel.add(openFavoritesPage.getRef());
        panel.add(openHistoryPage.getRef());
        panel.add(signOutButton.getRef());
        panel.setOpaque(false);
        return panel;

    }

}

