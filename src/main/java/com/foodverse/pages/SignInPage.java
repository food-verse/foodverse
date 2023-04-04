package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.Page;
import com.foodverse.utility.Pages;
import com.foodverse.utility.Router;
import com.foodverse.utility.core.Button.ButtonSize;
import com.foodverse.utility.core.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class SignInPage extends Page {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        var text = new Heading("SignInPage", HeadingSize.L);
        var openHomePage = new RectButton(
                "Open HomePage ->",
                ButtonSize.S,
                ButtonType.SECONDARY,
                e -> {
                    Router.pushPage(Pages.HOME);
                });
        panel.add(text.getRef());
        panel.add(openHomePage.getRef());
        return panel;
    }

}
