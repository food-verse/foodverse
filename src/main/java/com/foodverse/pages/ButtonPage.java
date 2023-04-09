package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.core.ui.Button.ButtonSize;
import com.foodverse.utility.core.ui.Button.ButtonType;
import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.widgets.button.CircleButton;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.button.SquareButton;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class ButtonPage extends Page {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        var text = new Heading("ButtonPage", HeadingSize.L);
        var openOverviewPage = new RectButton(
                "Open OverviewPage ->",
                ButtonSize.S,
                ButtonType.SECONDARY,
                e -> {
                    Router.pushPage(Pages.OVERVIEW);
                });
        panel.add(text.getRef());
        panel.add(openOverviewPage.getRef());
        for (ButtonType type : ButtonType.values()) {
            for (ButtonSize size : ButtonSize.values()) {
                var button = new RectButton("Label", size, type, e -> {
                });
                panel.add(button.getRef());
            }
        }
        for (ButtonType type : ButtonType.values()) {
            for (ButtonSize size : ButtonSize.values()) {
                var button = new PillButton("Label", size, type, e -> {
                });
                panel.add(button.getRef());
            }
        }
        for (ButtonType type : ButtonType.values()) {
            for (ButtonSize size : ButtonSize.values()) {
                var button = new SquareButton("01", size, type, e -> {
                });
                panel.add(button.getRef());
            }
        }
        for (ButtonType type : ButtonType.values()) {
            for (ButtonSize size : ButtonSize.values()) {
                var button = new CircleButton("01", size, type, e -> {
                });
                panel.add(button.getRef());
            }
        }
        for (ButtonType type : ButtonType.values()) {
            for (ButtonSize size : ButtonSize.values()) {
                var button = new RectButton("Label", false, size, type, e -> {
                });
                panel.add(button.getRef());
            }
        }
        for (ButtonType type : ButtonType.values()) {
            for (ButtonSize size : ButtonSize.values()) {
                var button = new PillButton("Label", false, size, type, e -> {
                });
                panel.add(button.getRef());
            }
        }
        for (ButtonType type : ButtonType.values()) {
            for (ButtonSize size : ButtonSize.values()) {
                var button = new SquareButton("01", false, size, type, e -> {
                });
                panel.add(button.getRef());
            }
        }
        for (ButtonType type : ButtonType.values()) {
            for (ButtonSize size : ButtonSize.values()) {
                var button = new CircleButton("01", false, size, type, e -> {
                });
                panel.add(button.getRef());
            }
        }
        panel.setOpaque(false);
        return panel;
    }

}
