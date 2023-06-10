package com.foodverse.views;

import java.awt.Component;
import java.util.Optional;
import com.foodverse.models.Address;
import com.foodverse.models.User;
import com.foodverse.overlays.EditAddressOverlay;
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
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public class AddressView extends Widget {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    private User user;

    private final Component component;

    public AddressView(Address address) {

        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();
        if (signedUser.isPresent()) {
            user = signedUser.get();
        } else {
            Router.openOverlay(new Alert("Error", "Authenticated User is not found"));
            Router.closeOverlay();
        }

        //Adding a location Icon
        var pinIcon = new VectorImage(IconAsset.LOCATION);

        //Elements of an address displayed 
        var addressText = new Paragraph(address.street() + address.number(), ParagraphSize.S);
        var addressView = new Row();
        addressView.addWidget(pinIcon, Align.LINE_START);
        var textView = new Column();
        textView.addWidget(addressText);
        var floorText = new Paragraph("Floor: " + address.floor() + " ,", ParagraphSize.XS);
        var bellText = new Paragraph("Doorbell: " + address.doorbell() + " ,", ParagraphSize.XS);
        var commentsText = new Paragraph("Comments: " + address.comments(), ParagraphSize.XS);
        var othersView = new Row();
        othersView.addWidget(floorText);
        othersView.addWidget(bellText);
        othersView.addWidget(commentsText);
        textView.addWidget(othersView);
        addressView.addWidget(textView);

        var buttonView = new Column();

        //Set an address saved in the overlay as current
        var setCurrentButton = new PillButton(
            "Set as Current Address",
            ButtonSize.XS,
            ButtonType.PRIMARY,
            e -> {
                User newUser = user.withCurrAddress(address);
                db.updateUser(newUser);
            });
        buttonView.addWidget(setCurrentButton, new EdgeInsets.Builder()
                  .top(10)
                  .right(8)
                  .build(),
                  Align.LINE_END);
        
        //edit the address displayed in the view, taking the user 
        //to the EditAddressOverlay
        var editAddressButton = new PillButton(
            "Edit address",
            ButtonSize.XS,
            ButtonType.PRIMARY,
            e -> {
                Router.openOverlay(new EditAddressOverlay(address));
            });
        buttonView.addWidget(editAddressButton, new EdgeInsets.Builder()
                  .right(8)
                  .build(),
                  Align.LINE_END);
        
        //delete the address 
        var deleteAddressButton = new PillButton(
            "Delete address",
            ButtonSize.XS,
            ButtonType.PRIMARY,
            e -> {
                user.addresses().remove(address);
                db.updateUser(user);
                if (address.equals(user.currAddress())) {
                    User newUser = user.withCurrAddress(null);
                    db.updateUser(newUser);
                }
            });
        buttonView.addWidget(deleteAddressButton, new EdgeInsets.Builder()
                  .right(8)
                  .build(),
                  Align.LINE_END);
        
        addressView.addWidget(buttonView, new EdgeInsets.Builder()
                   .right(10)
                   .build(),
                   Align.LINE_END);

        component = addressView.getRef();
    }

    @Override
    public Component getRef() {
        return component;
    }

}
