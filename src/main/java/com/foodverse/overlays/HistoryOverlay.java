package com.foodverse.overlays;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.media.Image;
import com.foodverse.utility.ui.ImageStyle;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;

public final class HistoryOverlay extends Overlay {

    // number of orders the user made
    private int orderCount = 2;

    // different data one order has
    private String pictureAsset;
    private String storeName;
    private String date;
    private ArrayList<String> items = new ArrayList<>();
    private double totalPrice;

    public Component getRef() {

        // creating the main panel of the page
        var mainPanel = new JPanel();
        var text = new Heading("History", HeadingSize.L);
        mainPanel.add(text.getRef());

        pictureAsset = "R.png";
        storeName = "Burgerlicious";
        date = "11/4/2023 10:13:09 PM";
        items.add("Classic burger: 1");
        items.add("Veggie burger: 2");
        items.add("Coca-Cola: 3");
        totalPrice = 31.47;

        // creating subPanels for every order using the method createSubPanel. Then we add the
        // subPanel to the main panel.
        for (int i = 0; i < orderCount; i++) {
            var subPanel = createSubPanel(i);
            mainPanel.add(subPanel);

            pictureAsset = "pizza.jpg";
            storeName = "Pizzantastic";
            date = "11/4/2023 10:13:09 PM";
            items.clear();
            items.add("Coca-Cola: 2");
            items.add("Margherita Pizza: 1");
            items.add("Hawaiian Pizza: 1");
            totalPrice = 33.98;
        }

        // return to Settings Page
        var openSettingsPage = new RectButton(
                "<- Back",
                ButtonSize.S,
                ButtonType.PRIMARY,
                e -> {
                    Router.closeOverlay();
                });

        mainPanel.add(openSettingsPage.getRef());
        mainPanel.setOpaque(false);
        return mainPanel;
    }

    // method that creates and returns a subPanel that displays one order
    public JPanel createSubPanel(int i) {
        var subPanel = new JPanel();

        // Shows the user which is his first order and which is his latest
        if (i == 0) {
            var subtext = new Heading("(first order)", HeadingSize.XS);
            subPanel.add(subtext.getRef());
        }
        if (orderCount - i == 1) {
            var subtext = new Heading("(earliest order)", HeadingSize.XS);
            subPanel.add(subtext.getRef());
        }

        // show order's data and adding the components to the subPanel
        var picture = new Image(pictureAsset, new ImageStyle.Builder()
                .width(300)
                .height(200)
                .build());
        var storeText = new Heading("Store's name:" + storeName, HeadingSize.M);
        var dateText = new Heading("(Date):" + date, HeadingSize.M);
        var itemsText = new Heading("Items:", HeadingSize.M);

        subPanel.add(picture.getRef());
        subPanel.add(storeText.getRef());
        subPanel.add(dateText.getRef());
        subPanel.add(itemsText.getRef());

        for (String item : items) {
            itemsText = new Heading(item, HeadingSize.M);
            subPanel.add(itemsText.getRef());
        }

        var totalPriceText =
                new Heading("Total: " + Double.toString(totalPrice) + " $", HeadingSize.M);
        subPanel.add(totalPriceText.getRef());

        subPanel.setOpaque(false);
        subPanel.setBorder(BorderFactory.createEtchedBorder());
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
        return subPanel;
    }

}
