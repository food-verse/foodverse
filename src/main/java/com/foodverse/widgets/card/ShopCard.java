package com.foodverse.widgets.card;

import java.awt.Component;
import com.foodverse.utility.Widget;
import com.foodverse.utility.core.layout.Align;
import com.foodverse.utility.core.layout.EdgeInsets;
import com.foodverse.utility.core.ui.ColoredBox;
import com.foodverse.utility.core.ui.Colors;
import com.foodverse.utility.core.ui.ImageAsset;
import com.foodverse.utility.core.ui.ImageStyle;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

public final class ShopCard extends Widget {

    private final ShopProps props;

    public ShopCard(ShopProps props) {
        this.props = props;
    }

    @Override
    public Component getRef() {
        var minimumOrder = String.format(
                "%d’ | Ελάχιστη %.2f€",
                props.getTime(),
                props.getMinimumOrder());
        // Creating text widgets...
        var minimumOrderText = new Label(minimumOrder, LabelSize.XS, Colors.gray600);
        var shopTypeText = new Label(props.getType().toString(), LabelSize.XS, Colors.gray600);
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
        ratingWidget.addWidget(ratingText, new EdgeInsets.Builder()
                .build(),
                Align.LINE_START);
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
        // Creating card's information widget...
        var infoWidget = new Column();
        infoWidget.addWidget(shopTypeText, new EdgeInsets.Builder()
                .bottom(4)
                .build(),
                Align.FIRST_LINE_START);
        infoWidget.addWidget(minimumOrderText, Align.LAST_LINE_START);
        // Creating card's main content widget...
        var mainContentWidget = new Column();
        mainContentWidget.addWidget(headingWidget, new EdgeInsets.Builder()
                .bottom(16)
                .build(),
                Align.FIRST_LINE_START);
        mainContentWidget.addWidget(infoWidget, Align.LAST_LINE_START);
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
        return new ColoredBox(imageCol);
    }

}
