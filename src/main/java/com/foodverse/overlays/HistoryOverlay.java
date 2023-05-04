package com.foodverse.overlays;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.views.OrderView;

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

        // creating subPanels for every order using the OrderView class. Then we add the
        // subPanel to the main panel.
        for (int i = 0; i < orderCount; i++) {
            var subPanel =
                    new OrderView(i, orderCount, pictureAsset, storeName, date, items, totalPrice);
            mainPanel.add(subPanel.getRef());

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

}
