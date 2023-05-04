package com.foodverse.widgets.layout;

import java.awt.Component;

import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class ListTile extends Widget {

    private final Row component = new Row();

    public ListTile(String data) {
        var widgetText = new Heading(data, HeadingSize.L);
        component.addWidget(widgetText, new EdgeInsets.Builder()
                .left(48)
                .build(),
            Align.CENTER);
    }

    @Override
    public Component getRef() {
        return component.getRef();
    }

}
