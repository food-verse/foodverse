package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.kitfox.svg.Text;
import com.foodverse.utility.ui.TextField;

public final class ProfilePage extends Page {
    
    private final Component component;

    public ProfilePage() {
        var panel = new JPanel();
        var text = new Heading("Profile", HeadingSize.L);
        var emailText = new Heading("Email", HeadingSize.M);
        var emailField = new TextField();
        emailField.setText("emilysmith123@gmail.com");
        var nameText = new Heading("Name", HeadingSize.M);
        var nameField = new TextField();
        nameField.setText("Emily");
        var lastNameText = new Heading("Last-Name", HeadingSize.M);
        var lastNameField = new TextField();
        lastNameField.setText("Smith");
        var phoneNumberText = new Heading("Phone Number", HeadingSize.M);
        var phoneNumberField = new TextField();
        phoneNumberField.setText("+1 (555) 555-1234");
       
        panel.add(text.getRef());
        panel.add(emailText.getRef());
        panel.add(emailField);
        panel.add(nameText.getRef());
        panel.add(nameField);
        panel.add(lastNameText.getRef());
        panel.add(lastNameField);
        panel.add(phoneNumberText.getRef());
        panel.add(phoneNumberField);
        panel.setOpaque(false);
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }
}
