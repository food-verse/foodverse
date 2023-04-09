package com.foodverse.pages;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.core.ui.Button.ButtonSize;
import com.foodverse.utility.core.ui.Button.ButtonType;
import com.foodverse.utility.navigation.Page;
import com.foodverse.utility.navigation.Pages;
import com.foodverse.utility.navigation.Router;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.text.Display;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Display.DisplaySize;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Label.LabelSize;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class TextPage extends Page {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        var text = new Heading("TextPage", HeadingSize.L);
        var openOverviewPage = new RectButton(
                "Open OverviewPage ->",
                ButtonSize.S,
                ButtonType.SECONDARY,
                e -> {
                    Router.pushPage(Pages.OVERVIEW);
                });
        panel.add(text.getRef());
        panel.add(openOverviewPage.getRef());
        for (DisplaySize size : DisplaySize.values()) {
            var textWidget = new Display("Display", size);
            panel.add(textWidget.getRef());
        }
        for (HeadingSize size : HeadingSize.values()) {
            var textWidget = new Heading("Heading", size);
            panel.add(textWidget.getRef());
        }
        for (LabelSize size : LabelSize.values()) {
            var textWidget = new Label("Label", size);
            panel.add(textWidget.getRef());
        }
        for (ParagraphSize size : ParagraphSize.values()) {
            var textWidget = new Paragraph("Paragraph", size);
            panel.add(textWidget.getRef());
        }
        for (DisplaySize size : DisplaySize.values()) {
            var textWidget = new Display("Display", size, true);
            panel.add(textWidget.getRef());
        }
        for (HeadingSize size : HeadingSize.values()) {
            var textWidget = new Heading("Heading", size, true);
            panel.add(textWidget.getRef());
        }
        for (LabelSize size : LabelSize.values()) {
            var textWidget = new Label("Label", size, true);
            panel.add(textWidget.getRef());
        }
        for (ParagraphSize size : ParagraphSize.values()) {
            var textWidget = new Paragraph("Paragraph", size, true);
            panel.add(textWidget.getRef());
        }
        panel.setOpaque(false);
        return panel;
    }

}
