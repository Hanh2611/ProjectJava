package org.projects.GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonEditStyle {
    private JButton button;
    private Color backgroundbutton;
    private Color foregroundbutton;

//    public static JButton styleButton(String buttontext, Color backgroundbutton, Color foregroundbutton) {
//        JButton button = new JButton(buttontext);
//        button.setFont(new Font("Jetbrains Mono", Font.BOLD, 14));
//        button.setBackground(backgroundbutton);
//        button.setForeground(foregroundbutton);
//        button.setFocusPainted(false);
//        button.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(new Color(150, 150, 150)),
//                BorderFactory.createEmptyBorder(8, 15, 8, 15)
//        ));
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//        return button;
//    }
    public static JButton styleButton(String buttontext, Color backgroundbutton, Color foregroundbutton) {
        JButton button = new JButton(buttontext) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);

                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(backgroundbutton); // Màu viền
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                g2.dispose();
            }
        };

        button.setFont(new Font("Jetbrains Mono", Font.BOLD, 14));
        button.setBackground(backgroundbutton);
        button.setForeground(foregroundbutton);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setContentAreaFilled(false);
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
