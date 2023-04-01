package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.overlays.ShopOverlay;
import com.foodverse.utility.Page;
import com.foodverse.utility.Router;
import com.foodverse.utility.core.Button.ButtonSize;
import com.foodverse.utility.core.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class HomePage extends Page {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        var text = new Heading("HomePage", HeadingSize.L);
        var button = new RectButton(
                "Open ShopOverlay ->", ButtonSize.S, ButtonType.PRIMARY, e -> {
                    Router.openOverlay(new ShopOverlay());
                });
        panel.add(text.getRef());
        panel.add(button.getRef());
        return panel;
    }

}
