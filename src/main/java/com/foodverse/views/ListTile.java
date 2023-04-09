package com.foodverse.views;

import java.awt.Component;
import com.foodverse.utility.Widget;
import com.foodverse.utility.core.layout.Align;
import com.foodverse.utility.core.layout.EdgeInsets;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class ListTile extends Widget {

    private final Row component = new Row();

    public ListTile(String data) {
        var widgetText = new Heading("Nearby", HeadingSize.L);
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
