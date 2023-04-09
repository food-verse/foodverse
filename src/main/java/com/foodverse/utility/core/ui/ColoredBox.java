package com.foodverse.utility.core.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import com.foodverse.utility.Widget;
import com.foodverse.utility.core.ui.ColoredBoxStyle.Builder;

public final class ColoredBox extends JPanel {

    private final ColoredBoxStyle style;

    public ColoredBox(Widget child) {
        this(new ColoredBoxStyle.Builder().build(), child);
    }

    public ColoredBox(ColoredBoxStyle style, Widget child) {
        super(new GridBagLayout());
        setForeground(style.getBorderColor());
        setBackground(style.getFillColor());
        setOpaque(false);
        add(child.getRef());
        setMaximumSize(getMinimumSize());
        this.style = style;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(style.getBorderRadius(), style.getBorderRadius());
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(style.getFillColor());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        graphics.setColor(style.getBorderColor());
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
    }

}
