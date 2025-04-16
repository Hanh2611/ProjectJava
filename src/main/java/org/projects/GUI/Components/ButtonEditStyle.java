package org.projects.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class ButtonEditStyle {
    private JButton button;
    private Color backgroundbutton;
    private Color foregroundbutton;

    public static JButton styleButton(JButton button, String buttontext, Color backgroundbutton, Color foregroundbutton) {
        button = new JButton(buttontext);
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
