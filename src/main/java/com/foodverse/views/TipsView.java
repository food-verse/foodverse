package com.foodverse.views;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.ui.Colors;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.PillButton;

public final class TipsView extends Widget {

        @Override
        public Component getRef() {
                var panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Colors.black));
                panel.setPreferredSize(new Dimension(1250, 100));
                panel.setBackground(Colors.white);

                var tips = new JPanel();
                tips.setPreferredSize(new Dimension(1200, 80));
                tips.setBackground(Colors.white);
                var tipLabel = new Label("Tips:", LabelSize.L);
                tips.add(tipLabel.getRef());

                var tip1 = new PillButton("0.5$",
                                ButtonSize.XS,
                                ButtonType.SECONDARY,
                                e -> {
                                });
                var tip2 = new PillButton("1$",
                                ButtonSize.XS,
                                ButtonType.SECONDARY,
                                e -> {
                                });
                var tip3 = new PillButton("2$",
                                ButtonSize.XS,
                                ButtonType.SECONDARY,
                                e -> {
                                });
                var tip4 = new PillButton("5$",
                                ButtonSize.XS,
                                ButtonType.SECONDARY,
                                e -> {
                                });

                tips.add(tip1.getRef());
                tips.add(tip2.getRef());
                tips.add(tip3.getRef());
                tips.add(tip4.getRef());
                panel.add(tips);

                return panel;

        }
}
