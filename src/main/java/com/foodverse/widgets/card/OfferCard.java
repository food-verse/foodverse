package com.foodverse.widgets.card;

import java.awt.Component;
import java.util.Map;
import com.foodverse.models.Item;
import com.foodverse.utility.Widget;
import com.foodverse.utility.core.layout.Align;
import com.foodverse.utility.core.layout.EdgeInsets;
import com.foodverse.utility.core.ui.ColoredBox;
import com.foodverse.utility.core.ui.Colors;
import com.foodverse.utility.core.ui.ImageStyle;
import com.foodverse.utility.core.ui.Button.ButtonSize;
import com.foodverse.utility.core.ui.Button.ButtonType;
import com.foodverse.utility.system.ImageAsset;
import com.foodverse.widgets.button.PillButton;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

public final class OfferCard extends Widget {

    private final OfferProps props;

    public OfferCard(OfferProps props) {
        this.props = props;
    }

    @Override
    public Component getRef() {
        // Creating button widgets...
        var openOrderButton = new PillButton("Order ->",
                ButtonSize.S,
                ButtonType.SECONDARY,
                e -> {
                });
        // Creating text widgets...
        var ratingText = new Label(String.valueOf(props.getRating()), LabelSize.M, Colors.orange);
        var shopNameText = new Label(props.getShopName(), LabelSize.L);
        // Creating image widgets...
        var starImage = new Image(ImageAsset.STAR, new ImageStyle.Builder()
                .width(16)
                .height(16)
                .build());
        var thumbnailImage = new Image(props.getThumbnail(), new ImageStyle.Builder()
                .width(160)
                .height(180)
                .build());
        // Creating card's rating widget...
        var ratingWidget = new Row();
        ratingWidget.addWidget(ratingText, Align.LINE_START);
        ratingWidget.addWidget(starImage, new EdgeInsets.Builder()
                .left(2)
                .build(),
                Align.LINE_END);
        // Creating card's heading widget...
        var headingWidget = new Row();
        headingWidget.addWidget(shopNameText, new EdgeInsets.Builder()
                .right(111)
                .build(),
                Align.FIRST_LINE_START);
        headingWidget.addWidget(ratingWidget, Align.LAST_LINE_END);
        // Creating card's list of items widget...
        var itemListWidget = new Column();
        for (Map.Entry<Item, Integer> entry : props.getItems().entrySet()) {
            var itemContent = String.format("• %d %s", entry.getValue(), entry.getKey().getName());
            var itemText = new Label(itemContent, LabelSize.XS, Colors.gray600);
            itemListWidget.addWidget(itemText, Align.FIRST_LINE_START);
        }
        // Creating card's main content widget...
        var mainContentWidget = new Column();
        mainContentWidget.addWidget(headingWidget, new EdgeInsets.Builder()
                .bottom(16)
                .build(),
                Align.FIRST_LINE_START);
        mainContentWidget.addWidget(itemListWidget, Align.LINE_START);
        mainContentWidget.addWidget(openOrderButton, new EdgeInsets.Builder()
                .top(24)
                .build(),
                Align.LAST_LINE_START);
        // Adding the card's thumbnail widget to the main content...
        var imageCol = new Row();
        imageCol.addWidget(mainContentWidget, new EdgeInsets.Builder()
                .all(16)
                .left(20)
                .build(),
                Align.LINE_START);
        imageCol.addWidget(thumbnailImage, Align.LINE_END);
        // Add border to
        return new ColoredBox(imageCol);
    }

}
