package com.foodverse.views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.ui.Colors;
import com.foodverse.widgets.layout.Column;

public final class AmountView extends Widget {

    private final Component component;

    public AmountView(float total) {
        var panel = new JPanel();

        panel.setPreferredSize(new Dimension(1250, 50));
        panel.setBackground(Colors.white);

        // // Border
        // Border boarder = BorderFactory.createLineBorder(Colors.black);

        var amount = new JPanel();

        DecimalFormat df = new DecimalFormat("#0.00");
        String towDigitTotal = df.format(total);

        amount.setPreferredSize(new Dimension(200, 100));
        var amountLabel = new JLabel("Total: " + towDigitTotal + " | ");
        changeFont(amountLabel, "Arial", Font.PLAIN, 14);
        amount.add(amountLabel);
        amount.setBackground(Colors.white);
        panel.add(amount);

        var paddedPanel = new Column();
        paddedPanel.addComponent(panel, Align.FIRST_LINE_START);

        component = panel;

    }

    @Override
    public Component getRef() {
        return component;
    }

    public static void changeFont(JLabel label, String fontName, int fontStyle, int fontSize) {
        Font font = new Font(fontName, fontStyle, fontSize);
        label.setFont(font);
    }

}
