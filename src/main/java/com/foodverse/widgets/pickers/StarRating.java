package com.foodverse.widgets.pickers;

import java.awt.Component;
import java.util.function.IntConsumer;

import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.VectorImage;

public final class StarRating extends Widget {

    private final Row container = new Row();
    private final VectorImage[] enabledIcons;
    private final VectorImage[] disabledIcons;
    private final VectorImage[] currIcons;
    private int index;

    public StarRating(int numItems, IntConsumer onChange, int value) {
        index = value - 1;
        // Initializing the icon arrays...
        enabledIcons = new VectorImage[numItems];
        disabledIcons = new VectorImage[numItems];
        currIcons = new VectorImage[numItems];
        for (var i = 0; i < numItems; i++) {
            enabledIcons[i] = new VectorImage(IconAsset.STAR_MEDIUM_FILL);
            disabledIcons[i] = new VectorImage(IconAsset.STAR_MEDIUM_OUTLINE);
        }
        // Adding the icons to the container...
        for (var i = 0; i < numItems; i++) {
            var enabledIcon = enabledIcons[i];
            // Define i in an enclosing scope in order to use it in the lambda expression
            var k = i;
            enabledIcon.onPressed(e -> {
                if (k < index) {
                    for (var j = k + 1; j <= index; j++) {
                        container.replaceWidget(currIcons[j], disabledIcons[j]);
                        currIcons[j] = disabledIcons[j];
                    }
                    index = k;
                } else if (k == index) {
                    for (var j = 0; j <= index; j++) {
                        container.replaceWidget(currIcons[j], disabledIcons[j]);
                        currIcons[j] = disabledIcons[j];
                    }
                    index = -1;
                }
                container.refresh();
                onChange.accept(index + 1);
            });
            var disabledIcon = disabledIcons[i];
            disabledIcon.onPressed(e -> {
                if (k > index) {
                    for (var j = index + 1; j <= k; j++) {
                        container.replaceWidget(currIcons[j], enabledIcons[j]);
                        currIcons[j] = enabledIcons[j];
                    }
                    index = k;
                }
                container.refresh();
                onChange.accept(index + 1);
            });
            currIcons[i] = (value >= k + 1) ? enabledIcon : disabledIcon;
            container.addWidget(currIcons[i], new EdgeInsets.Builder()
                    .right(4)
                    .build(),
                Align.TOP_LEFT);
        }
    }

    @Override
    public Component getRef() {
        return container.getRef();
    }

}
