package com.foodverse.widgets.card;

import java.awt.Component;
import java.util.Map;
import com.foodverse.overlays.OrderOverlay;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.ColoredBox;
import com.foodverse.utility.ui.Colors;
import com.foodverse.utility.ui.ImageStyle;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.media.VectorImage;
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
        var shopNameText = new Label(props.getName(), LabelSize.L);

        // Creating image widgets...
        var starImage = new VectorImage(IconAsset.STAR);
        var thumbnailImage = new Image(props.getThumbnail(), new ImageStyle.Builder()
                .width(240)
                .height(100)
                .build());

        // Creating card's rating widget...
        var ratingWidget = new Row();
        ratingWidget.addWidget(ratingText, new EdgeInsets.Builder()
                .right(2)
                .build(),
                Align.LINE_START);
        ratingWidget.addWidget(starImage, Align.LINE_END);

        // Creating card's heading widget...
        var headingWidget = new Row();
        headingWidget.addWidget(shopNameText, Align.FIRST_LINE_START);
        headingWidget.addWidget(ratingWidget, Align.LAST_LINE_END);

        // Creating card's list of items widget...
        var itemListWidget = new Column();
        for (Map.Entry<String, Integer> entry : props.getItems().entrySet()) {
            var itemContent = String.format("• %d %s", entry.getValue(), entry.getKey());
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

        // Add border to card
        return new ColoredBox(imageCol, e -> {
            Router.openOverlay(new OrderOverlay());
        });

    }

}
