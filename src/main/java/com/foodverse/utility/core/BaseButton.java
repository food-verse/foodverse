package com.foodverse.utility.core;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;

final class BaseButton extends JButton {

    private ButtonStyle buttonStyle;
    private boolean isEnabled;

    public void setStyle(ButtonStyle buttonStyle) {
        this.buttonStyle = buttonStyle;
        isEnabled = buttonStyle.isEnabled();
    }

    public void init() {
        setFont(new Font(buttonStyle.getTextStyle().getAttributes()));
        setPreferredSize(getButtonSize());
        setMinimumSize(getButtonSize());
        setMaximumSize(getButtonSize());
        setBorder(BorderFactory.createEmptyBorder());
        setFocusPainted(false);
        setContentAreaFilled(false);
        addMouseListener(new BaseButtonListener());
        setEnabled(isEnabled);
        applyStyle();
    }

    public void applyStyle() {
        if (isEnabled) {
            setForeground(buttonStyle.getDefaultTextColor());
            setBackground(buttonStyle.getDefaultBackgroundColor());
        } else {
            setBackground(buttonStyle.getDisabledBackgroundColor());
            setForeground(buttonStyle.getDisabledTextColor());
        }
    }

    public void toggle() {
        isEnabled = !isEnabled;
        setEnabled(isEnabled);
    }

    private Dimension getButtonSize() {
        Font font = getFont();
        FontMetrics fontMetrics = getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(getText()) + buttonStyle.getPadding().getLeft()
                + buttonStyle.getPadding().getRight();
        int textHeight = fontMetrics.getHeight() + buttonStyle.getPadding().getTop()
                + buttonStyle.getPadding().getBottom();
        if (buttonStyle.getMinimumWidth() > textWidth) {
            textWidth = buttonStyle.getMinimumWidth();
        }
        return new Dimension(textWidth, textHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(),
                buttonStyle.getBorderRadius(),
                buttonStyle.getBorderRadius());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Skip painting border
    }

    private class BaseButtonListener extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            if (isEnabled) {
                setBackground(buttonStyle.getHoverBackgroundColor());
                setForeground(buttonStyle.getHoverTextColor());
            } else {
                setBackground(buttonStyle.getDisabledBackgroundColor());
                setForeground(buttonStyle.getDisabledTextColor());
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (isEnabled) {
                setBackground(buttonStyle.getDefaultBackgroundColor());
                setForeground(buttonStyle.getDefaultTextColor());
            } else {
                setBackground(buttonStyle.getDisabledBackgroundColor());
                setForeground(buttonStyle.getDisabledTextColor());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (isEnabled) {
                setBackground(buttonStyle.getPressBackgroundColor());
                setForeground(buttonStyle.getPressTextColor());
            } else {
                setBackground(buttonStyle.getDisabledBackgroundColor());
                setForeground(buttonStyle.getDisabledTextColor());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouseExited(e);
        }

    }

}
