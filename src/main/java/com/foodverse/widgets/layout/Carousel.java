package com.foodverse.widgets.layout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JPanel;
import com.foodverse.utility.EdgeInsets;
import com.foodverse.utility.Props;
import com.foodverse.utility.Widget;
import com.foodverse.utility.core.Align;
import com.foodverse.widgets.card.OfferCard;
import com.foodverse.widgets.card.OfferProps;
import com.foodverse.widgets.card.OrderCard;
import com.foodverse.widgets.card.OrderProps;
import com.foodverse.widgets.card.ShopCard;
import com.foodverse.widgets.card.ShopProps;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

public final class Carousel extends Widget {

    private final List<? extends Props> propList;

    public Carousel(List<? extends Props> propList) {
        this.propList = propList;
    }

    @Override
    public Component getRef() {
        // Remove the default padding of the carousel with a temporary panel
        var panelWithoutPad = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelWithoutPad.setOpaque(false);
        // Creating carousel...
        var carousel = new Row();
        // If list of props is empty return an empty JPanel
        if (propList.isEmpty()) {
            var warningText = new Label("Unfortunately, there is nothing to display.", LabelSize.L);
            var emptyView = new JPanel();
            emptyView.add(warningText.getRef());
            emptyView.setPreferredSize(new Dimension(100, 100));
            return emptyView;
        }
        // Adding list of widgets to the carousel...
        var list = (List<?>) propList;
        for (Object props : list) {
            if (props instanceof OfferProps) {
                carousel.addWidget(new OfferCard((OfferProps) props),
                        new EdgeInsets.Builder()
                                .right(8)
                                .build(),
                        Align.FIRST_LINE_END);
            } else if (props instanceof OrderProps) {
                carousel.addWidget(new OrderCard((OrderProps) props),
                        new EdgeInsets.Builder()
                                .right(8)
                                .build(),
                        Align.FIRST_LINE_END);
            } else if (props instanceof ShopProps) {
                carousel.addWidget(new ShopCard((ShopProps) props),
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
