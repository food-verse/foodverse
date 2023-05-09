package com.foodverse.widgets.layout;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JPanel;

import com.foodverse.utility.core.Props;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.views.EmptyView;
import com.foodverse.widgets.card.OfferCard;
import com.foodverse.widgets.card.OfferCardProps;
import com.foodverse.widgets.card.OrderCard;
import com.foodverse.widgets.card.OrderCardProps;
import com.foodverse.widgets.card.ShopCard;
import com.foodverse.widgets.card.ShopCardProps;

public final class Carousel extends Widget {

    private final List<? extends Props> propsList;

    public Carousel(List<? extends Props> propsList) {
        this.propsList = propsList;
    }

    @Override
    public Component getRef() {
        // Remove the default padding of the carousel with a temporary panel
        var panelWithoutPad = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelWithoutPad.setOpaque(false);
        // Creating carousel...
        var carousel = new Row();
        // If list of props is empty return an empty JPanel
        if (propsList.isEmpty()) {
            return new EmptyView().getRef();
        }
        // Adding list of widgets to the carousel...
        for (Props props : propsList) {
            if (props instanceof OfferCardProps p) {
                carousel.addWidget(new OfferCard(p),
                    new EdgeInsets.Builder()
                        .right(8)
                        .build(),
                    Align.FIRST_LINE_END);
            } else if (props instanceof OrderCardProps p) {
                carousel.addWidget(new OrderCard(p),
                    new EdgeInsets.Builder()
                        .right(8)
                        .build(),
                    Align.FIRST_LINE_END);
            } else if (props instanceof ShopCardProps p) {
                carousel.addWidget(new ShopCard(p),
                    new EdgeInsets.Builder()
                        .right(8)
                        .build(),
                    Align.FIRST_LINE_END);
            }
        }
        panelWithoutPad.add(carousel.getRef());
        // Add padding to the carousel
        var paddedCarousel = new Row();
        paddedCarousel.addComponent(panelWithoutPad, new EdgeInsets.Builder()
                .symmetric(16, 48)
                .bottom(24)
                .build(),
            Align.FIRST_LINE_START);
        return paddedCarousel.getRef();
    }

}
