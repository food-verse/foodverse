package com.foodverse.overlays;

import java.awt.Component;
import javax.swing.JPanel;

import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class SignInOverlay extends Overlay {

    private final Component component;

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    public SignInOverlay() {
        super(600, 524);

        // Heading
        var panel = new JPanel();
        var text = new Heading("Sign In", HeadingSize.XXL);
        var signInButton = new RectButton(
            "Sign In",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                if (db.signIn("emilysmith123", "XyZ987!")) {
                    Router.pushPage(Pages.HOME);
                    Router.closeOverlay();
                }
            });
        panel.add(text.getRef());
        panel.add(signInButton.getRef());
        panel.setOpaque(false);
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }

}
