package com.foodverse.overlays;

import java.awt.Component;

import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.views.SectionView;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public class InfoOverlay extends Overlay {

    private final Component component;

    public InfoOverlay() {

        // Creating text widgets...
        var infoTitle = new Heading(UIConstants.INFO_TITLE, HeadingSize.L);

        // Creating info's first step widget...
        var firstStepWidget = new SectionView(
            UIConstants.INFO_STEP_1_TITLE,
            UIConstants.INFO_STEP_1_DESCRIPTION
        );

        // Creating info's second step widget...
        var secondStepWidget = new SectionView(
            UIConstants.INFO_STEP_2_TITLE,
            UIConstants.INFO_STEP_2_DESCRIPTION
        );

        // Creating info's third step widget...
        var thirdStepWidget = new SectionView(
            UIConstants.INFO_STEP_3_TITLE,
            UIConstants.INFO_STEP_3_DESCRIPTION
        );

        // Creating the content column...
        var content = new Column();
        content.addWidget(firstStepWidget, Align.TOP_LEFT);
        content.addWidget(secondStepWidget, new EdgeInsets.Builder()
                .symmetric(24, 0)
                .build(),
            Align.TOP_LEFT);
        content.addWidget(thirdStepWidget, Align.TOP_LEFT);

        // Creating main panel...
        var panel = new Column();
        panel.addWidget(infoTitle, new EdgeInsets.Builder()
                .bottom(32)
                .build(),
            Align.TOP_LEFT);
        panel.addWidget(content, Align.TOP_LEFT);

        // Add padding to panel
        var paddedPanel = new Column();
        paddedPanel.addWidget(panel, new EdgeInsets.Builder()
                .all(32)
                .build(),
            Align.TOP_LEFT);

        component = paddedPanel.getRef();
    }

    @Override
    public Component getRef() {
        return component;
    }

    @Override
    public String getId() {
        return "How it works";
    }

}
