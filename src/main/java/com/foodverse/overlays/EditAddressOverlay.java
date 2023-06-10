package com.foodverse.overlays;

import java.awt.Component;
import java.util.Optional;
import com.foodverse.models.Address;
import com.foodverse.models.User;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.TextField;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.input.InputForm;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public class EditAddressOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    private User user;

    private Address address;

    public EditAddressOverlay(Address address) {
        super(800,500);
        this.address = address;
    }

    //method to add a new address or edit an already existed one.
    //Case 1: the address parameter is null, then we create a new address
    //Case 2: address not null so we edit an already existed one
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

        // creating the page panel.
        var panel = new Column();

        // creating overlay's Heading Widget
        var text = new Heading("Add/Edit Address", HeadingSize.L);
        panel.addWidget(text, new EdgeInsets.Builder()
               .top(16)
               .left(8)
               .build(),
               Align.FIRST_LINE_START);

        //address Form view
        var addressField = new TextField();
        var addressForm = new InputForm("Address", " ", addressField);
        panel.addWidget(addressForm, new EdgeInsets.Builder()
               .symmetric(14, 40)
               .build(),
               Align.LINE_START);

        //Floor form view
        var floorField = new TextField();
        var floorForm = new InputForm("Floor", " ", floorField);
        panel.addWidget(floorForm, new EdgeInsets.Builder()
               .symmetric(14, 40)
               .build(),
               Align.LINE_START);


        //Doorbell form view
        var bellField = new TextField();
        var bellForm = new InputForm("Bell", " ", bellField); 
        panel.addWidget(bellForm, new EdgeInsets.Builder()
               .symmetric(14, 40)
               .build(),
               Align.LINE_START);

        //Comments form view
        var commentsField = new TextField();
        var commentsForm = new InputForm("Comments", "Please use this field to let us know about any specific delivery instructions", commentsField);
        panel.addWidget(commentsForm, new EdgeInsets.Builder()
               .symmetric(14, 40)
               .build(),
               Align.LINE_START);

        // display the addresse's previous elements, if we are in case 2
        if (address != null) {
            addressForm.setText(address.street() + address.number());
            floorForm.setText(address.floor());
            bellForm.setText(address.doorbell());
            commentsForm.setText(address.comments());
        }

        //button to save any changes. If we are in Case 2, we delete the previous
        //address from the list and add the updated one.
        var saveButton = new PillButton(
                "Save",
                ButtonSize.L,
                ButtonType.PRIMARY,
                e -> {
                    if (address != null) {
                        user.addresses().remove(address);
                    }
                    Address newAddress = new Address(addressForm.getText().replaceAll("\\d", ""), 
                    addressForm.getText().replaceAll("\\D", ""), floorForm.getText(), bellForm.getText(), 
                    commentsForm.getText());
                    user.addresses().add(newAddress);
                    db.updateUser(user);
                   
                    //If the address that will be changed is the current Address
                    //update the current address also
                    if (address.equals(user.currAddress())) {
                        User newUser = user.withCurrAddress(newAddress);
                        db.updateUser(newUser);
                    }
                   
                    Router.closeOverlay();
                });
        panel.addWidget(saveButton, new EdgeInsets.Builder()
                .symmetric(14, 40)
                .build(),
                Align.CENTER); 

        return new ScrollView(panel.getRef()).getRef();
    }

}
