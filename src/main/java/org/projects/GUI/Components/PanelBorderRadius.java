package org.projects.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class PanelBorderRadius extends JPanel{

    int shadowSize = 3;
    Color HowerBackgroundColor = new Color(255, 255, 255);

    public PanelBorderRadius () {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        createShadow(grphcs);
    }

    private void createShadow(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Vẽ nền với bo góc
        g2.setColor(HowerBackgroundColor); // ← Dùng color từ biến
        g2.fillRoundRect(0, 0, width, height, 15, 15);
    }

    public void setBackgroundColor(Color color) {
        this.HowerBackgroundColor = color;
        repaint(); // ← Gọi lại vẽ để áp dụng màu mới
    }
}
