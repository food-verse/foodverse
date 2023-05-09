package com.foodverse.overlays;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Optional;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import com.foodverse.models.Shop;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.ImageStyle;
import com.foodverse.views.EmptyView;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.media.IconAsset;
import com.foodverse.widgets.media.Image;
import com.foodverse.widgets.media.VectorImage;

public final class ShopOverlay extends Overlay {

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    private final String merchant;

    public ShopOverlay(String merchant) {
        super(800, 680);
        this.merchant = merchant;
    }

    @Override
    public Component getRef() {

        // Searching for the shop in the database...
        Optional<Shop> shop = db.findShopByName(merchant);
        if (!shop.isPresent()) {
            return new EmptyView().getRef();
        }

        var column = new Column();

        // Remove the default padding of the shop with a temporary panel
        var panelWithoutPad = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelWithoutPad.setOpaque(false);

        // Creating main panel...
        var panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //
        var props = ShopProps.from(shop.get());

        // Creating image widgets...
        var thumbnailImage = new Image(props.thumbnail(), new ImageStyle.Builder()
                .width(800)
                .height(200)
                .build());
        var heartImage = new VectorImage(IconAsset.HEART);
        var heartFillImage = new VectorImage(IconAsset.HEART_FILL);

        // Add padding to heart
        var paddedImage = new Column();
        paddedImage.addWidget(thumbnailImage, new EdgeInsets.Builder()
                .all(0)
                .build(),
                Align.FIRST_LINE_START);

        var paddedHeartImage = new Column();
        paddedHeartImage.addWidget(heartImage, new EdgeInsets.Builder()
                .all(4)
                .build(),
                Align.FIRST_LINE_START);

        var paddedHeartFillImage = new Column();
        paddedHeartFillImage.addWidget(heartFillImage, new EdgeInsets.Builder()
                .all(4)
                .build(),
                Align.FIRST_LINE_START);

        heartImage.onPressed(e -> {
            panel.remove(heartImage.getRef());
            panel.add(heartFillImage.getRef());
            panel.revalidate();
            panel.repaint();
        }, "Add to favorites");

        heartFillImage.onPressed(e -> {
            panel.remove(heartFillImage.getRef());
            panel.add(heartImage.getRef());
            panel.revalidate();
            panel.repaint();
        }, "Remove from favorites");

        panel.add(heartImage.getRef());

        column.addWidget(thumbnailImage, new EdgeInsets.Builder()
                .all(0)
                .build(),
                Align.FIRST_LINE_START);
        column.addComponent(panel, new EdgeInsets.Builder()
                .all(0)
                .build(),
                Align.LAST_LINE_START);
        return column.getRef();
    }

}
