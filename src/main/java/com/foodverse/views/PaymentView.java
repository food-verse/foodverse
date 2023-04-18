package com.foodverse.views;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Colors;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.ButtonGroup;
import javax.swing.*;

public final class PaymentView extends Widget {

    @Override
    public Component getRef() {
        var panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Colors.black));
        panel.setPreferredSize(new Dimension(1250, 100));
        panel.setBackground(Colors.white);

        // Border
        Border boarder = BorderFactory.createLineBorder(Colors.black);

        var payment = new JPanel();
        payment.setBorder(boarder);
        payment.setPreferredSize(new Dimension(600, 100));
        payment.setBackground(Colors.white);

        // var CardButton = new RectButton("Card",
        // ButtonSize.S,
        // ButtonType.PRIMARY,
        // e -> {
        // Carddetails(e);
        // });

        // payment.add(CardButton.getRef());

        // Card Details

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

        // Buttons for payment

        var CardDetails = new JRadioButton("Card ");
        var Cash = new JRadioButton("Cash ");
        var Take = new JRadioButton("Take away ");
        var group = new ButtonGroup();
        group.add(CardDetails);
        group.add(Take);
        group.add(Cash);

        payment.add(CardDetails);
        payment.add(Cash);
        payment.add(Take);

        panel.add(payment);

        return panel;
    }

    // private JPanel Carddetails(ActionEvent e) {
    // var payment = new JPanel();
    // Border boarder = BorderFactory.createLineBorder(Colors.black);

    // var cardNumber = new JTextArea("Card Number ");
    // cardNumber.setBorder(boarder);
    // var Name = new JTextArea("Name ");
    // Name.setBorder(boarder);
    // var secCode = new JTextArea("Secret Code ");
    // secCode.setBorder(boarder);
    // var Date = new JTextArea("Day/Year ");
    // Date.setBorder(boarder);

    // payment.add(cardNumber);
    // payment.add(Name);
    // payment.add(secCode);
    // payment.add(Date);
    // return payment;
    // }

}
