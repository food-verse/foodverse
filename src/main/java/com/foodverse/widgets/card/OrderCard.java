package com.foodverse.widgets.card;

import java.awt.Component;
import java.util.Map;
import com.foodverse.models.Item;
import com.foodverse.utility.EdgeInsets;
import com.foodverse.utility.Widget;
import com.foodverse.utility.core.Align;
import com.foodverse.utility.core.ColoredBox;
import com.foodverse.utility.core.Colors;
import com.foodverse.utility.core.ImageAsset;
import com.foodverse.utility.core.ImageStyle;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

public final class OrderCard extends Widget {

    private final OrderProps props;

    public OrderCard(OrderProps props) {
        this.props = props;
    }

    @Override
    public Component getRef() {
        var price = String.format(
                "Price: %.2f€",
                props.getPrice());
        // Creating text widgets...
        var priceText = new Label(price, LabelSize.XS, Colors.gray600);
        var ratingText = new Label(
                String.valueOf(props.getRating()),
                LabelSize.M,
                Colors.orange);
        var shopNameText = new Label(props.getShopName(), LabelSize.L);
        // Creating image widgets...
        var starImage = new Image(ImageAsset.STAR, new ImageStyle.Builder()
                .width(16)
                .height(16)
                .build());
        var thumbnailImage = new Image(props.getThumbnail(), new ImageStyle.Builder()
                .width(240)
                .height(100)
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
                .right(57)
                .build(),
                Align.FIRST_LINE_START);
        headingWidget.addWidget(ratingWidget, Align.LAST_LINE_END);
        // Creating card's list of items widget...
        var itemListWidget = new Column();
        for (Map.Entry<Item, Integer> entry : props.getItems().entrySet()) {
            var itemContent = String.format("• %d %s", entry.getValue(),
                    entry.getKey().getName());
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
        mainContentWidget.addWidget(priceText, new EdgeInsets.Builder()
                .top(16)
                .build(),
                Align.LAST_LINE_START);
        // Adding the card's thumbnail widget to the main content...
        var imageCol = new Column();
        imageCol.addWidget(thumbnailImage, Align.FIRST_LINE_START);
        imageCol.addWidget(mainContentWidget, new EdgeInsets.Builder()
                .left(16)
                .top(8)
                .right(12)
                .bottom(16)
                .build(),
                Align.LAST_LINE_START);
        // Add border to
        return new ColoredBox(imageCol);
    }

}
