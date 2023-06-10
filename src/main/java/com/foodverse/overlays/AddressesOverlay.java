package com.foodverse.overlays;

import java.awt.Component;
import java.util.Optional;
import javax.swing.JPanel;

import com.foodverse.models.Address;
import com.foodverse.models.User;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.views.AddressItemView;
import com.foodverse.views.EmptyView;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class AddressesOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    private final Column overlayView = new Column();

    public AddressesOverlay() {
        super(800, 800);
    }

    @Override
    public Component getRef() {

        // page's main panel
        var panel = new JPanel();

        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();

        // If the shop or the user are not present, return an empty view...
        if (signedUser.isEmpty()) {
            return new EmptyView().getRef();
        }

        // Unwrapping shop and user...
        var user = signedUser.get();

        // creating overlay's Heading Widget
        var text = new Heading("Addresses", HeadingSize.L);
        overlayView.addWidget(text, new EdgeInsets.Builder()
            .top(16)
            .left(8)
            .build());

        // create views for the rest of the addresses list
        for (Address address : user.addresses()) {
            var subPanel = new AddressItemView(address);
            overlayView.addWidget(subPanel, Align.CENTER);
        }

        // Button to add an address. It takes the user to a new add address page
        var addAddressButton = new PillButton(
            "Add new address",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> Router.openOverlay(new ManageAddressOverlay(null)));
        overlayView.addWidget(addAddressButton, new EdgeInsets.Builder()
                .bottom(20)
                .symmetric(14, 40)
                .build(),
            Align.BOTTOM_CENTER);

        panel.add(overlayView.getRef());

        return new ScrollView(panel).getRef();
    }

}
