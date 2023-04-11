package com.foodverse.utility.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.JPanel;
import com.foodverse.utility.core.Widget;

public final class ColoredBox extends JPanel {

    private final ColoredBoxStyle style;
    private final Consumer<MouseEvent> onPressed;

    public ColoredBox(Widget child) {
        this(child, null);
    }

    public ColoredBox(Widget child, Consumer<MouseEvent> onPressed) {
        this(child, onPressed, new ColoredBoxStyle.Builder().build());
    }

    public ColoredBox(Widget child, Consumer<MouseEvent> onPressed, ColoredBoxStyle style) {
        super(new GridBagLayout());
        this.style = style;
        this.onPressed = onPressed;
        add(child.getRef());
        setOpaque(false);
        setForeground(style.getBorderColor());
        setBackground(style.getDefaultBackgroundColor());
        if (onPressed != null) {
            addMouseListener(new ColoredBoxListener());
        }
        setMaximumSize(getMinimumSize());
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
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
    }

    private class ColoredBoxListener extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(style.getHoverBackgroundColor());
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(style.getDefaultBackgroundColor());
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            setBackground(style.getPressBackgroundColor());
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            onPressed.accept(e);
            mouseExited(e);
        }

    }

}
