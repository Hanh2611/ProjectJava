package org.projects.GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonEditStyle {
    private JButton button;
    private Color backgroundbutton;
    private Color foregroundbutton;

    public static JButton styleButton(String buttontext, Color backgroundbutton, Color foregroundbutton) {
        JButton button = new JButton(buttontext);
        button.setFont(new Font("Jetbrains Mono", Font.BOLD, 14));
        button.setBackground(backgroundbutton);
        button.setForeground(foregroundbutton);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150)),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    public JButton getButton() {
        return button;
    }

    public Color getBackgroundbutton() {
        return backgroundbutton;
    }

    public Color getForegroundbutton() {
        return foregroundbutton;
    }
}
