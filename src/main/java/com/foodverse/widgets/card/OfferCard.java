package com.foodverse.widgets.card;

import java.awt.Component;
import java.util.Map;

import com.foodverse.models.Offer;
import com.foodverse.overlays.ShopOverlay;
import com.foodverse.props.OfferCardProps;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.utility.ui.ColoredBox;
import com.foodverse.utility.ui.Colors;
import com.foodverse.utility.ui.ImageStyle;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

public final class OfferCard extends Widget {

    private final OfferCardProps props;

    public OfferCard(OfferCardProps props) {
        this.props = props;
    }

    @Override
    public Component getRef() {

        // Creating button widgets...
        var openOrderButton = new PillButton("Order ->",
            ButtonSize.S,
            ButtonType.SECONDARY,
            e -> {
                Router.openOverlay(new ShopOverlay(props.name()));
            });

        // Creating text widgets...
        var shopNameText = new Label(props.name(), LabelSize.L);

        // Creating image widgets...
        var thumbnailImage = new Image(props.thumbnail(), new ImageStyle.Builder()
            .width(160)
            .height(180)
            .build());

        // Creating card's list of items widget...
        int widgetCount = 0;
        var builder = new StringBuilder();
        var itemListWidget = new Column();
        int numberOfLines = 3;
        for (Offer offer : props.offers()) {
            builder.append('•');
            for (Map.Entry<String, Integer> entry : offer.items().entrySet()) {
                builder.append(String.format(" %d %s +",
                    entry.getValue(), entry.getKey()));
            }
            builder.setLength(Math.max(builder.length() - 1, 0));
            builder.append(String.format("-> %.2f€", offer.total()));
            var itemText = new Label(builder.toString(), LabelSize.XS, Colors.gray600);
            itemListWidget.addWidget(itemText, Align.TOP_LEFT);
            builder.setLength(0);
            widgetCount++;
            if (widgetCount == numberOfLines) {
                break;
            }
        }

        // Adding empty widgets to fill the available space, in case there aren't enough items...
        if (widgetCount < numberOfLines) {
            for (int i = widgetCount; i < numberOfLines; i++) {
                var itemText = new Label(" ", LabelSize.XS, Colors.gray600);
                itemListWidget.addWidget(itemText, Align.TOP_LEFT);
            }
        }

        // Creating card's main content widget...
        var mainContentWidget = new Column();
        mainContentWidget.addWidget(shopNameText, new EdgeInsets.Builder()
                .bottom(16)
                .build(),
            Align.TOP_LEFT);
        mainContentWidget.addWidget(itemListWidget, Align.CENTER_LEFT);
        mainContentWidget.addWidget(openOrderButton, new EdgeInsets.Builder()
                .top(24)
                .build(),
            Align.BOTTOM_LEFT);

        // Adding the card's thumbnail widget to the main content...
        var imageCol = new Row();
        imageCol.addWidget(mainContentWidget, new EdgeInsets.Builder()
                .all(16)
                .left(20)
                .build(),
            Align.CENTER_LEFT);
        imageCol.addWidget(thumbnailImage, Align.CENTER_RIGHT);

        // Add border to card
        return new ColoredBox(imageCol);

    }

}
