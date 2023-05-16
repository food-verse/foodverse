package com.foodverse.views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

public class AddLine extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int height = getHeight();
        int width = getWidth();

        int y = height / 2;
        Stroke oldStroke = g2d.getStroke(); // Store the old stroke
        float strokeWidth = 2.0f; // Set the desired stroke width
        g2d.setStroke(new BasicStroke(strokeWidth));

        setBackground(Color.WHITE); // Set background color

        Color veryLightGray = new Color(240, 240, 240);
        g2d.setColor(veryLightGray);

        g2d.drawLine(0, y, width, y); // Single horizontal line from left to right
        g2d.setStroke(oldStroke);

    }

}
