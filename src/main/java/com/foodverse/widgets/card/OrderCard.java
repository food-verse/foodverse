package com.foodverse.widgets.card;

import java.awt.Component;
import java.util.Map;

import com.foodverse.overlays.OrderOverlay;
import com.foodverse.props.OrderCardProps;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.ui.ColoredBox;
import com.foodverse.utility.ui.Colors;
import com.foodverse.utility.ui.ImageStyle;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

public final class OrderCard extends Widget {

    private final OrderCardProps props;
    private final int numberOfLines = 3;

    public OrderCard(OrderCardProps props) {
        this.props = props;
    }

    @Override
    public Component getRef() {

        var price = String.format(
            "Price: %.2f€",
            props.price());

        // Creating text widgets...
        var priceText = new Label(price, LabelSize.XS, Colors.gray600);
        var shopNameText = new Label(props.name(), LabelSize.L);

        // Creating image widgets...
        var thumbnailImage = new Image(props.thumbnail(), new ImageStyle.Builder()
            .width(200)
            .height(100)
            .build());

        // Creating card's list of items widget...
        int widgetCount = 0;
        var itemListWidget = new Column();
        for (Map.Entry<String, Integer> entry : props.items().entrySet()) {
            var itemContent = String.format("• %d %s", entry.getValue(), entry.getKey());
            var itemText = new Label(itemContent, LabelSize.XS, Colors.gray600);
            itemListWidget.addWidget(itemText, Align.FIRST_LINE_START);
            widgetCount++;
            if (widgetCount == numberOfLines) {
                break;
            }
        }

        // Adding empty widgets to fill the available space, in case there aren't enough items...
        if (widgetCount < numberOfLines) {
            for (int i = widgetCount; i < numberOfLines; i++) {
                var itemText = new Label(" ", LabelSize.XS, Colors.gray600);
                itemListWidget.addWidget(itemText, Align.FIRST_LINE_START);
            }
        }

        // Creating card's main content widget...
        var mainContentWidget = new Column();
        mainContentWidget.addWidget(shopNameText, new EdgeInsets.Builder()
                .bottom(8)
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
            Router.openOverlay(new OrderOverlay(props.name(), props.items()));
        });

    }

}
