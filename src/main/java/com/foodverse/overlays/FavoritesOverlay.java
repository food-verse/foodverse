package com.foodverse.overlays;

import java.awt.Component;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.foodverse.models.Shop;
import com.foodverse.models.User;
import com.foodverse.props.ShopCardProps;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.widgets.layout.Carousel;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.layout.ScrollView;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Heading.HeadingSize;

public final class FavoritesOverlay extends Overlay {

     // Getting a reference to the database...
     private final Database db = Database.getInstance();

     private User user;

    public FavoritesOverlay() {
        super(700, 700);
    }

    @Override
    public Component getRef() {
 
        // Getting the authenticated user...
        Optional<User> signedUser = db.getAuthenticatedUser();
        if (signedUser.isPresent()) {
            user = signedUser.get();
        } else {
            Router.openOverlay(new Alert("Error", "Authenticated User is not found"));
            Router.closeOverlay();
        }
        
        // Creating the page panel
        var panel = new Column();

        // Creating overlays heading widget
        var text = new Heading("Favourites", HeadingSize.L);
        panel.addWidget(text, new EdgeInsets.Builder()
                .top(16)
                .left(8)
                .build(),
            Align.FIRST_LINE_START);

        // Getting the list of shops...
        List<Shop> shops = db.getShops();

        // Create new favourites Set so the objects can be Shop objects not String.
        Set<Shop> favouritesSet = new HashSet<>();
        for(String merchant: user.favorites()) {
            for (Shop shop: shops) {
                if (merchant.equals(shop.name())) {
                    favouritesSet.add(shop);
                    break;
                }
            }
        }

        // counter of shops displayed in one row
        int count = 0;

        // displaying the favourite shops in the overlay. The max shops
        // that can be displayed in one row are 3
        Set<Shop> shopsInRow = new HashSet<>();
        for (Shop shop: favouritesSet) {
            if (count == 3) {
                List<ShopCardProps> shopProps = shopsInRow.stream()
                  .map(ShopCardProps::from)
                  .sorted(Comparator.comparingDouble(ShopCardProps::rating).reversed())
                  .collect(Collectors.toList());
                
                // Create a carousel for the available shops
                var shopCarousel = new Carousel(shopProps);

                // Add the carousel for the available offers to the main panel
                panel.addComponent(shopCarousel.getRef());

                //clear the shops from the favouritesSet that got displayed in the overlay
                for (Shop shopDisplayed: shopsInRow) {
                    favouritesSet.remove(shopDisplayed);
                }

                 // clear the shops that were showed in the previous row and reset the counter
                 shopsInRow.clear();
                 count = 1;
            } else {
                count++;
            }
            shopsInRow.add(shop);
        }

        // check if a shop in the favorites Set didn't get displayed. If there is one display it
        if (!favouritesSet.isEmpty()) {
            List<ShopCardProps> shopProps = shopsInRow.stream()
                  .map(ShopCardProps::from)
                  .sorted(Comparator.comparingDouble(ShopCardProps::rating).reversed())
                  .collect(Collectors.toList());
            var shopCarousel = new Carousel(shopProps);
            panel.addComponent(shopCarousel.getRef());
        }

        
        return new ScrollView(panel.getRef()).getRef();

    }

}
