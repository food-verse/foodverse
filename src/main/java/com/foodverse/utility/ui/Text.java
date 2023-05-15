package com.foodverse.utility.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.JLabel;

import com.foodverse.utility.core.Widget;

public abstract class Text extends Widget {

    private final JLabel component = new JLabel();
    private Consumer<MouseEvent> onPressed;

    protected Text(String data, FontStyle fontStyle, TextStyle textStyle) {
        component.setText(data);
        component.setForeground(textStyle.getColor());
        component.setFont(new Font(fontStyle.getAttributes()));
        component.setHorizontalAlignment(textStyle.getTextAlign().getAlignment());
    }

    public void setText(String data) {
        component.setText(data);
    }

    public void onPressed(Consumer<MouseEvent> onPressed, String ariaLabel) {
        this.onPressed = onPressed;
        if (onPressed != null) {
            component.setToolTipText(ariaLabel);
            component.addMouseListener(new TextListener());
            component.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public Component getRef() {
        return component;
    }

    private class TextListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            onPressed.accept(e);
        }

    }

}
