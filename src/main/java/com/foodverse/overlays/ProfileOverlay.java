package com.foodverse.overlays;

import java.awt.Component;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.foodverse.models.User;
import com.foodverse.pages.HomePage;
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
    
    private User user;

    @Override
    public Component getRef() {

        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();
        if (signedUser.isPresent()) {
            user = signedUser.get();
        } else {
            JOptionPane.showMessageDialog(null, "Authenticated User is not found", "Error", JOptionPane.ERROR_MESSAGE);
            close();
            Router.pushPage(Pages.HOME);
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
                Router.openOverlay(new AddressesOverlay(user, db));
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
                Router.openOverlay(new HistoryOverlay(user, db));
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
                    UIConstants.SIGN_OUT_CONFIRM_MESSAGE,
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

