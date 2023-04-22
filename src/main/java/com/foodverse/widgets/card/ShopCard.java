package com.foodverse.widgets.card;

import java.awt.Component;

import com.foodverse.overlays.ShopOverlay;
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

public final class ShopCard extends Widget {

    private final ShopProps props;

    public ShopCard(ShopProps props) {
        this.props = props;
    }

    @Override
    public Component getRef() {

        var minOrder = String.format(
                "%d’ | Minimum %.2f€",
                props.prepTime(),
                props.minOrder());

        // Creating text widgets...
        var minOrderText = new Label(minOrder, LabelSize.XS, Colors.gray600);
        var shopTypeText = new Label(props.type().toString(), LabelSize.XS, Colors.gray600);
        var ratingText = new Label(
                String.valueOf(props.rating()),
                LabelSize.M,
                Colors.orange);
        var shopNameText = new Label(props.name(), LabelSize.L);

        // Creating image widgets...
        var starImage = new VectorImage(IconAsset.STAR);
        var thumbnailImage = new Image(props.thumbnail(), new ImageStyle.Builder()
                .width(240)
                .height(100)
                .build());

        // Creating card's rating widget...
        var ratingWidget = new Row();
        ratingWidget.addWidget(ratingText, new EdgeInsets.Builder()
                .right(2)
                .build(),
                Align.PAGE_START);
        ratingWidget.addWidget(starImage, new EdgeInsets.Builder()
                .build(),
                Align.LINE_END);

        // Creating card's heading widget...
        var headingWidget = new Row();
        headingWidget.addWidget(shopNameText, Align.FIRST_LINE_START);
        headingWidget.addWidget(ratingWidget, Align.LAST_LINE_END);

        // Creating card's information widget...
        var infoWidget = new Column();
        infoWidget.addWidget(shopTypeText, new EdgeInsets.Builder()
                .bottom(4)
                .build(),
                Align.FIRST_LINE_START);
        infoWidget.addWidget(minOrderText, Align.LAST_LINE_START);

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
        return new ColoredBox(imageCol, e -> {
            Router.openOverlay(new ShopOverlay(props.name()));
        });

    }

}
