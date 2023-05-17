package com.foodverse.overlays;

import java.awt.Component;

import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.ui.Colors;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class SectionView extends Widget {

    private final Component component;

    public SectionView(String title, String description) {

        // Creating text widgets...
        var titleText = new Heading(title, HeadingSize.S);
        var descriptionText = new Paragraph(
            description,
            ParagraphSize.M,
            Colors.gray600
        );

        // Creating the column of the section...
        var column = new Column();
        column.addWidget(titleText, new EdgeInsets.Builder()
                .bottom(16)
                .build(),
            Align.FIRST_LINE_START);
        column.addWidget(descriptionText, Align.FIRST_LINE_START);

        component = column.getRef();
    }

    @Override
    public Component getRef() {
        return component;
    }

}
