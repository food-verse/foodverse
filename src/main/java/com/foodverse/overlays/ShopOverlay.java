package com.foodverse.overlays;

import java.awt.Component;
import java.util.Optional;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import com.foodverse.models.Shop;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.ImageStyle;
import com.foodverse.views.EmptyView;
import com.foodverse.widgets.media.Image;

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

        // Creating main panel...
        var panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //
        var props = ShopProps.from(shop.get());

        // Creating image widgets...
        // var starImage = new VectorImage(IconAsset.STAR);
        var thumbnailImage = new Image(props.thumbnail(), new ImageStyle.Builder()
                .width(800)
                .height(200)
                .build());

        panel.add(thumbnailImage.getRef());
        return panel;
    }

}
