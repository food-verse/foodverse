package com.foodverse.views;

import java.awt.Component;
import java.util.ArrayList;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.ImageStyle;

public final class OrderView extends Widget {

    public final Component component;
   
    public OrderView(int i, int count, String pictureAsset, String storeName, String date, ArrayList<String> items, double totalPrice) {
        var panel = new JPanel();

        // Shows the user which is his first order and which is his latest
        if (i == 0) {
            var subtext = new Heading("(first order)", HeadingSize.XS);
            panel.add(subtext.getRef());
        }
        if (count - i == 1) {
            var subtext = new Heading("(earliest order)", HeadingSize.XS);
            panel.add(subtext.getRef());
        }

        // show order's data and adding the components to the subPanel
        var picture = new Image(pictureAsset, new ImageStyle.Builder()
                .width(300)
                .height(200)
                .build());
        var storeText = new Heading("Store's name:" + storeName, HeadingSize.M);
        var dateText = new Heading("(Date):" + date, HeadingSize.M);
        var itemsText = new Heading("Items:", HeadingSize.M);

        panel.add(picture.getRef());
        panel.add(storeText.getRef());
        panel.add(dateText.getRef());
        panel.add(itemsText.getRef());

        for (String item : items) {
            itemsText = new Heading(item, HeadingSize.M);
            panel.add(itemsText.getRef());
        }

        var totalPriceText =  new Heading("Total: " + Double.toString(totalPrice) + " $", HeadingSize.M);
        panel.add(totalPriceText.getRef());

        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        component = panel;
    }

    @Override
    public Component getRef() {
        return component;
    }
    
}
