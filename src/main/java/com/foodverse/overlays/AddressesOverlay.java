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
import com.foodverse.views.AddressView;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class AddressesOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    private User user;

    private final Column overlayView = new Column();

    public AddressesOverlay() {
        super(800,800);
    }

    // creating the main panel
    @Override
    public Component getRef() {

        //page's main panel
        var panel = new JPanel();

        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();
        if (signedUser.isPresent()) {
            user = signedUser.get();
        } else {
            Router.openOverlay(new Alert("Error", "Authenticated User is not found"));
            Router.closeOverlay();
        }

        // creating overlay's Heading Widget
        var text = new Heading("Addresses", HeadingSize.L);
        overlayView.addWidget(text, new EdgeInsets.Builder()
               .top(16)
               .left(8)
               .build(),
               Align.FIRST_LINE_START);
        
        //create a view for the current address first
        var subPanel = new AddressView(user.currAddress());
        overlayView.addWidget(subPanel, Align.CENTER);

        // create views for the rest of the addresses list
        for (Address address: user.addresses()) {
            if (!address.equals(user.currAddress())) {
                subPanel = new AddressView(address);
                overlayView.addWidget(subPanel, Align.CENTER);
            }
        }

        // Button to add an address. It takes the user to a new add address page
        var addAddressButton = new PillButton(
                "Add new address",
                ButtonSize.L,
                ButtonType.PRIMARY,
                e -> {
                    Router.openOverlay(new EditAddressOverlay(null));
                });
        overlayView.addWidget(addAddressButton, new EdgeInsets.Builder()
                .bottom(20)
                .symmetric(14, 40)
                .build(),
                Align.PAGE_END);

        panel.add(overlayView.getRef());

        return new ScrollView(panel).getRef();
    }

}
