package com.foodverse.overlays;

import java.awt.Component;
import java.util.Optional;

import com.foodverse.models.Address;
import com.foodverse.models.User;
import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.utility.ui.TextField;
import com.foodverse.views.EmptyView;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.input.InputForm;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public class ManageAddressOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    private final Address address;

    public ManageAddressOverlay(Address address) {
        super(800, 500);
        this.address = address;
    }

    @Override
    public Component getRef() {

        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();

        // If the shop or the user are not present, return an empty view...
        if (signedUser.isEmpty()) {
            return new EmptyView().getRef();
        }

        // Unwrapping shop and user...
        var user = signedUser.get();

        // creating the page panel.
        var panel = new Column();

        // creating overlay's Heading Widget
        var text = new Heading("Manage Address", HeadingSize.L);
        panel.addWidget(text, new EdgeInsets.Builder()
            .symmetric(24, 40)
            .build());

        // address Form view
        var addressField = new TextField();
        var addressForm = new InputForm("Address", "", addressField);
        panel.addWidget(addressForm, new EdgeInsets.Builder()
                .symmetric(0, 40)
                .build(),
            Align.CENTER_LEFT);

        // address Form view
        var numberField = new TextField();
        var numberForm = new InputForm("Number", "", numberField);
        panel.addWidget(numberForm, new EdgeInsets.Builder()
                .symmetric(0, 40)
                .build(),
            Align.CENTER_LEFT);

        // Floor form view
        var floorField = new TextField();
        var floorForm = new InputForm("Floor", "", floorField);
        panel.addWidget(floorForm, new EdgeInsets.Builder()
                .symmetric(0, 40)
                .build(),
            Align.CENTER_LEFT);

        // Doorbell form view
        var bellField = new TextField();
        var bellForm = new InputForm("Bell", "", bellField);
        panel.addWidget(bellForm, new EdgeInsets.Builder()
                .symmetric(0, 40)
                .build(),
            Align.CENTER_LEFT);

        // Comments form view
        var commentsField = new TextField();
        var commentsForm = new InputForm(
            "Comments",
            UIConstants.REGISTRATION_COMMENTS_CAPTION,
            commentsField);
        panel.addWidget(commentsForm, new EdgeInsets.Builder()
                .symmetric(0, 40)
                .build(),
            Align.CENTER_LEFT);

        // display the address's previous elements, if we are in case 2
        if (address != null) {
            addressField.setText(address.street());
            numberField.setText(address.number());
            floorField.setText(address.floor());
            bellField.setText(address.doorbell());
            commentsField.setText(address.comments());
        }

        // button to save any changes. If we are in Case 2, we delete the previous
        // address from the list and add the updated one.
        var saveButton = new PillButton(
            "Save",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                Address newAddress = new Address(
                    addressField.getText(),
                    numberField.getText(),
                    floorField.getText(),
                    bellField.getText(),
                    commentsField.getText());
                if (address != null) {
                    if (address.equals(newAddress)) {
                        Router.closeOverlay();
                        return;
                    } else {
                        if (user.addresses().indexOf(address) == 0) {
                            user.addresses().remove(address);
                            user.addresses().add(0, newAddress);
                        } else {
                            user.addresses().remove(address);
                            user.addresses().add(newAddress);
                        }
                    }
                } else {
                    user.addresses().add(newAddress);
                }
                db.updateUser(user);
                Router.closeOverlay();
                Router.closeOverlay();
                Router.openOverlay(new AddressesOverlay());
            });
        panel.addWidget(saveButton, new EdgeInsets.Builder()
                .symmetric(10, 40)
                .bottom(24)
                .build(),
            Align.CENTER);

        return new ScrollView(panel.getRef()).getRef();
    }

}
