package com.foodverse.widgets.button;

import java.awt.Component;
import java.util.function.Consumer;

import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.widgets.layout.Row;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.VectorImage;

public final class IconButton extends Widget {

    private final Row container = new Row();
    private boolean isEnabled;

    public IconButton(
        IconAsset enabledIconAsset,
        String enabledAriaLabel,
        IconAsset disabledIconAsset,
        String disabledAriaLabel,
        Consumer<Boolean> onChange,
        boolean isIconEnabled
    ) {
        this.isEnabled = isIconEnabled;
        // Creating the icons...
        var enabledIcon = new VectorImage(enabledIconAsset);
        var disabledIcon = new VectorImage(disabledIconAsset);
        // Adding the icon to the container...
        if (isEnabled) {
            container.addWidget(enabledIcon, Align.FIRST_LINE_START);
        } else {
            container.addWidget(disabledIcon, Align.FIRST_LINE_START);
        }
        // Adding the event listeners to the icons...
        enabledIcon.onPressed(e -> {
            container.replaceWidget(enabledIcon, disabledIcon);
            container.refresh();
            isEnabled = !isEnabled;
            onChange.accept(isEnabled);
        }, enabledAriaLabel);
        disabledIcon.onPressed(e -> {
            container.replaceWidget(disabledIcon, enabledIcon);
            container.refresh();
            isEnabled = !isEnabled;
            onChange.accept(isEnabled);
        }, disabledAriaLabel);
    }

    @Override
    public Component getRef() {
        return container.getRef();
    }

}
