package com.foodverse.views;

import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.VectorImage;
import com.foodverse.widgets.text.Paragraph;

import java.awt.*;

public class AddressView extends Widget {

    private final Component component;

    public AddressView(String address) {

        //
        var pinIcon = new VectorImage(IconAsset.LOCATION);
        var addressText = new Paragraph(address, Paragraph.ParagraphSize.S);

        //
        var addressView = new Row();
        addressView.addWidget(pinIcon, Align.LINE_START);
        addressView.addWidget(addressText, new EdgeInsets.Builder()
                .left(8)
                .build(),
            Align.LINE_START);

        component = addressView.getRef();
    }

    @Override
    public Component getRef() {
        return component;
    }

}
