package com.foodverse.widgets.layout;

import java.awt.Component;

import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class ListTile extends Widget {

    private final Row component = new Row();

    public ListTile(String data, ListTileSize size) {
        var widgetText = new Heading(data, size.getSize());
        component.addWidget(widgetText, new EdgeInsets.Builder()
                .left(size == ListTileSize.M ? 48 : 40)
                .top(size == ListTileSize.M ? 0 : 24)
                .build(),
            Align.CENTER);
    }

    public ListTile(String data) {
        this(data, ListTileSize.M);
    }

    @Override
    public Component getRef() {
        return component.getRef();
    }

    public enum ListTileSize {
        S(HeadingSize.M), M(HeadingSize.L);

        private final HeadingSize size;

        ListTileSize(HeadingSize size) {
            this.size = size;
        }

        public HeadingSize getSize() {
            return size;
        }

    }

}
