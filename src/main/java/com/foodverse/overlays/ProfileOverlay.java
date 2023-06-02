package com.foodverse.overlays;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Optional;

import com.foodverse.models.User;
import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.utility.ui.Divider;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.modal.Dialog;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public class ProfileOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    private User user;

    public ProfileOverlay() {
        super(800, 540);
    }

    @Override
    public Component getRef() {

        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();
        if (signedUser.isPresent()) {
            user = signedUser.get();
        } else {
            Router.openOverlay(new Alert("Error", "Authenticated User is not found"));
            Router.closeOverlay();
        }

        // Creating the page panel
        var panel = new Column();

        // Creating overlays heading widget
        var text = new Heading("Settings", HeadingSize.L);
        panel.addWidget(text, new EdgeInsets.Builder()
                .top(16)
                .build(),
            Align.CENTER);


        // Buttons that lead to the various settings we can use
        var openProfilePage = new PillButton(
            "Profile Info",
            ButtonSize.L,
            ButtonType.SECONDARY,
            e -> {
                Router.openOverlay(new ProfileInfoOverlay());
            });
        panel.addWidget(openProfilePage, new EdgeInsets.Builder()
                .symmetric(14, 40)
                .build(),
            Align.CENTER);

        var openHistoryPage = new PillButton(
            "History",
            ButtonSize.L,
            ButtonType.SECONDARY,
            e -> {
                Router.openOverlay(new HistoryOverlay());
            });
        panel.addWidget(openHistoryPage, new EdgeInsets.Builder()
                .symmetric(14, 40)
                .build(),
            Align.CENTER);


        var openAddressesPage = new PillButton(
            "Addresses",
            ButtonSize.L,
            ButtonType.SECONDARY,
            e -> {
                Router.openOverlay(new AddressesOverlay());
            });
        panel.addWidget(openAddressesPage, new EdgeInsets.Builder()
                .symmetric(14, 40)
                .build(),
            Align.CENTER);

        var openFavoritesPage = new PillButton(
            "Favorites",
            ButtonSize.L,
            ButtonType.SECONDARY,
            e -> {
                Router.openOverlay(new FavoritesOverlay());
            });
        panel.addWidget(openFavoritesPage, new EdgeInsets.Builder()
                .symmetric(14, 40)
                .build(),
            Align.CENTER);

        panel.addComponent(new Divider());

        // Signing out...
        var signOutButton = new PillButton(
            "Sign Out",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                signOutAction(e);
            });
        panel.addWidget(signOutButton, new EdgeInsets.Builder()
                .symmetric(14, 40)
                .build(),
            Align.CENTER);

        return new ScrollView(panel.getRef()).getRef();

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

