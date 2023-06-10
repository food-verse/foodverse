package com.foodverse.views;

import java.awt.Component;
import java.util.Optional;

import com.foodverse.models.Address;
import com.foodverse.models.User;
import com.foodverse.overlays.AddressesOverlay;
import com.foodverse.overlays.ManageAddressOverlay;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.VectorImage;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public class AddressItemView extends Widget {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    private final Component component;

    public AddressItemView(Address address) {

        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();

        // If the user are not present, return an empty view...
        if (signedUser.isEmpty()) {
            component = new EmptyView().getRef();
            return;
        }

        // Unwrapping user...
        var user = signedUser.get();

        // Adding a location Icon
        var pinIcon = new VectorImage(IconAsset.LOCATION);

        // Elements of an address displayed
        var addressText = new Paragraph(
            String.format("%s %s", address.street(), address.number()),
            ParagraphSize.S
        );
        var addressView = new Row();
        addressView.addWidget(pinIcon, Align.CENTER_LEFT);
        var textView = new Column();
        textView.addWidget(addressText);
        var floorText = new Paragraph("Floor: " + address.floor() + ", ", ParagraphSize.XS);
        var bellText = new Paragraph("Doorbell: " + address.doorbell() + ", ", ParagraphSize.XS);
        var commentsText = new Paragraph("Comments: " + address.comments(), ParagraphSize.XS);
        var othersView = new Row();
        othersView.addWidget(floorText);
        othersView.addWidget(bellText);
        othersView.addWidget(commentsText);
        textView.addWidget(othersView);
        addressView.addWidget(textView);

        var buttonView = new Column();

        // Set an address saved in the overlay as current
        var setCurrentButton = new PillButton(
            "Set as Current Address",
            ButtonSize.XS,
            ButtonType.PRIMARY,
            e -> {
                if (!address.equals(user.addresses().get(0))) {
                    user.addresses().remove(address);
                    user.addresses().add(0, address);
                    db.updateUser(user);
                    Router.closeOverlay();
                    Router.openOverlay(new AddressesOverlay());
                }
            });
        buttonView.addWidget(setCurrentButton, new EdgeInsets.Builder()
                .top(10)
                .right(8)
                .build(),
            Align.CENTER_RIGHT);

        // edit the address displayed in the view, taking the user
        // to the EditAddressOverlay
        var editAddressButton = new PillButton(
            "Edit address",
            ButtonSize.XS,
            ButtonType.PRIMARY,
            e -> {
                Router.openOverlay(new ManageAddressOverlay(address));
            });
        buttonView.addWidget(editAddressButton, new EdgeInsets.Builder()
                .right(8)
                .build(),
            Align.CENTER_RIGHT);

        // delete the address
        var deleteAddressButton = new PillButton(
            "Delete address",
            ButtonSize.XS,
            ButtonType.PRIMARY,
            e -> {
                user.addresses().remove(address);
                db.updateUser(user);
                if (address.equals(user.addresses().get(0))) {
                    user.addresses().remove(address);
                    db.updateUser(user);
                }
                Router.closeOverlay();
                Router.openOverlay(new AddressesOverlay());
            });
        buttonView.addWidget(deleteAddressButton, new EdgeInsets.Builder()
                .right(8)
                .build(),
            Align.CENTER_RIGHT);

        addressView.addWidget(buttonView, new EdgeInsets.Builder()
                .right(10)
                .build(),
            Align.CENTER_RIGHT);

        component = addressView.getRef();
    }

    @Override
    public Component getRef() {
        return component;
    }

}
