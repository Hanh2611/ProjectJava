package org.projects.GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class ButtonEditStyle extends JButton{
    private final int bankinh = 30;

    public ButtonEditStyle(String buttontext, Color backgroundbutton, Color foregroundbutton,int width,int height) {
        super(buttontext);
        setFont(new Font("Jetbrains Mono", Font.BOLD, 14));
        setBackground(backgroundbutton);
        setForeground(foregroundbutton);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setMargin(new Insets(5, 5, 5, 5));
        setPreferredSize(new Dimension(width,height));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), bankinh, bankinh);

        g2d.setColor(getBackground());
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, bankinh, bankinh);

        g2d.dispose();
        super.paintComponent(g);
    }

    @Override
    public boolean contains(int x, int y) {
        Shape s = new RoundRectangle2D.Float(0,0,getWidth(),getHeight(),bankinh,bankinh);
        return s.contains(x, y);
    }

}
