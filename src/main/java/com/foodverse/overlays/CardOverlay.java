package com.foodverse.overlays;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.ui.Colors;

public class CardOverlay extends Overlay {

    private final Component component;

    public CardOverlay() {

        var payment = new JPanel();

        Border boarder = BorderFactory.createLineBorder(Colors.black);

        var cardNumber = new JTextArea("Card Number ");
        cardNumber.setBorder(boarder);
        var Name = new JTextArea("Name ");
        Name.setBorder(boarder);
        var secCode = new JTextArea("Secret Code ");
        secCode.setBorder(boarder);
        var Date = new JTextArea("Day/Year ");
        Date.setBorder(boarder);

        payment.add(cardNumber);
        payment.add(Name);
        payment.add(secCode);
        payment.add(Date);
        component = payment;

    }

    @Override
    public Component getRef() {
        return component;
    }
}
