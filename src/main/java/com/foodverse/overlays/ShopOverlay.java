package com.foodverse.overlays;

import java.awt.Component;
import javax.swing.JPanel;
import com.foodverse.utility.Button;
import com.foodverse.utility.Overlay;
import com.foodverse.utility.Router;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public class ShopOverlay extends Overlay {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        var text = new Heading("ShopOverlay", HeadingSize.L);
        var buttonText = new Paragraph("Close ShopOverlay", ParagraphSize.M);
        var button = new Button(buttonText, e -> {
            Router.closeOverlay();
        });
        panel.add(text.getRef());
        panel.add(button.getRef());
        return panel;
    }

}
