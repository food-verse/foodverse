package com.foodverse.widgets.modal;

import java.awt.Component;

import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.Button;
import com.foodverse.utility.ui.Colors;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public class Alert extends Overlay {

    private final Component component;

    public Alert(String title, String description) {
        this(title, description, null);
    }

    public Alert(String title, String description, Runnable onPressed) {

        // Creating main container...
        var content = new Column();

        // Creating text widgets...
        var titleText = new Heading(title, HeadingSize.S);
        var descriptionText = new Paragraph(description, ParagraphSize.M, Colors.gray600);

        // Creating button widgets...
        var actionButton = new PillButton(
            "OK",
            Button.ButtonSize.S,
            Button.ButtonType.SECONDARY,
            e -> {
                Router.closeOverlay();
                if (onPressed != null) {
                    onPressed.run();
                }
            });

        // Creating card's main content widget...
        content.addWidget(titleText, new EdgeInsets.Builder()
                .bottom(16)
                .build(),
            Align.TOP_CENTER);
        content.addWidget(descriptionText, Align.BOTTOM_CENTER);

        // Add the action button to the bottom of the alert...
        var paddedContent = new Column();
        paddedContent.addWidget(content, new EdgeInsets.Builder()
                .bottom(24)
                .build(),
            Align.TOP_CENTER);
        paddedContent.addWidget(actionButton, Align.BOTTOM_CENTER);

        // Add padding to the alert...
        var paddedWidget = new Column();
        paddedWidget.addWidget(paddedContent, new EdgeInsets.Builder()
                .all(24)
                .build(),
            Align.CENTER);

        component = paddedWidget.getRef();
    }

    @Override
    public Component getRef() {
        return component;
    }

}
