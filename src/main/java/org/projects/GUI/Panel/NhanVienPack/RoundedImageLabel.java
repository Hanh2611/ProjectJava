package org.projects.GUI.Panel.NhanVienPack;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedImageLabel extends JLabel {
    private Image image;

    public RoundedImageLabel(ImageIcon icon, int arcWidth, int arcHeight) {
        this.image = icon.getImage();
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setClip(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30));
            g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            g2.dispose();
        }
    }
}
