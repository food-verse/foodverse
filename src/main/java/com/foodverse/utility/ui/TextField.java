package com.foodverse.utility.ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.widgets.button.RectButton;

public final class TextField extends JTextField {

    private Shape shape;
    private ButtonTheme theme = new ButtonTheme.Builder()
            .defaultBackgroundColor(Colors.buttonSecondaryFill)
            .defaultTextColor(Colors.buttonSecondaryText)
            .hoverBackgroundColor(Colors.buttonSecondaryHover)
            .hoverTextColor(Colors.buttonSecondaryText)
            .pressBackgroundColor(Colors.buttonSecondaryActive)
            .pressTextColor(Colors.buttonSecondaryText)
            .build();
    private boolean isEnabled = true;
    private ButtonStyle buttonStyle = RectButton.getButtonStyle(
            isEnabled,
            ButtonSize.S,
            theme,
            false);

    public TextField() {
        setFont(new Font(buttonStyle.getTextStyle().getAttributes()));
        setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        setOpaque(false);
        setEnabled(isEnabled);
        setSelectionColor(Colors.buttonSecondaryActive);
        setColumns(16);
        applyStyle();
    }

    public void setTheme(ButtonStyle buttonStyle) {
        this.buttonStyle = buttonStyle;
    }

    public void toggle() {
        isEnabled = !isEnabled;
        setFocusable(isEnabled);
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

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
        super.paintComponent(g);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
        }
        return shape.contains(x, y);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Skip painting border
    }

}
