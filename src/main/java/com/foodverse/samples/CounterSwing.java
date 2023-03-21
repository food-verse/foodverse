package com.foodverse.samples;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CounterSwing extends JPanel {

    private final JLabel label = new JLabel();
    private final JButton button = new JButton();
    private Integer count = 0;

    public CounterSwing() {
        label.setText(count.toString());
        label.setFont(new Font(Map.of(
                TextAttribute.FAMILY, "Inter",
                TextAttribute.WEIGHT, 1.0f,
                TextAttribute.SIZE, 18)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                label.setText(count.toString());
            }
        });
        add(button);
    }

}
