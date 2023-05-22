package com.foodverse.utility.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.io.Serial;

import javax.swing.JPanel;

public class Divider extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    public Divider() {
        setPreferredSize(new Dimension(800, 4));
    }

    public Divider(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int height = getHeight();
        int width = getWidth();
        int y = height / 2;

        // Store the old stroke
        Stroke oldStroke = g2d.getStroke();

        // Set the desired stroke width
        float strokeWidth = 2.0f;
        g2d.setStroke(new BasicStroke(strokeWidth));

        // Set background color
        setBackground(Colors.white);

        // Set the color of the divider
        Color veryLightGray = Colors.gray100;
        g2d.setColor(veryLightGray);

        // Single horizontal line from left to right
        g2d.drawLine(0, y, width, y);
        g2d.setStroke(oldStroke);
    }

}
