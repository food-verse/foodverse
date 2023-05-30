package com.foodverse.widgets.modal;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.Button;
import com.foodverse.utility.ui.Colors;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public class Dialog extends Overlay {

    private final Component component;

    public Dialog(
        String title,
        String description,
        String confirmLabel,
        Consumer<ActionEvent> onPressed
    ) {

        // Creating main container...
        var content = new Column();

        // Creating text widgets...
        var titleText = new Heading(title, HeadingSize.S);
        var descriptionText = new Paragraph(description, ParagraphSize.M, Colors.gray600);

        // Creating button widgets...
        var confirmButton = new PillButton(
            confirmLabel,
            Button.ButtonSize.S,
            Button.ButtonType.PRIMARY,
            e -> {
                onPressed.accept(e);
                Router.closeOverlay();
            });
        var cancelButton = new PillButton(
            "Cancel",
            Button.ButtonSize.S,
            Button.ButtonType.SECONDARY,
            e -> {
                Router.closeOverlay();
            });

        // Creating card's main content widget...
        content.addWidget(titleText, new EdgeInsets.Builder()
                .bottom(16)
                .build(),
            Align.PAGE_START);
        content.addWidget(descriptionText, Align.PAGE_END);

        // Creating the row of buttons...
        var actions = new Row();
        actions.addWidget(confirmButton, new EdgeInsets.Builder()
                .right(8)
                .build(),
            Align.LAST_LINE_END);
        actions.addWidget(cancelButton, Align.LAST_LINE_END);

        // Add the buttons row to the bottom of the alert...
        var paddedContent = new Column();
        paddedContent.addWidget(content, new EdgeInsets.Builder()
                .bottom(24)
                .build(),
            Align.PAGE_START);
        paddedContent.addWidget(actions, Align.PAGE_END);

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
