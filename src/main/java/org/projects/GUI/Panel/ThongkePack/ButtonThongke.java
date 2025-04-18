package org.projects.GUI.Panel.ThongkePack;

import javax.swing.*;
import java.awt.*;

public class ButtonThongke extends JButton {
    private String namebtn;
    public ButtonThongke(String name) {
        this.namebtn = name;
        this.setText(namebtn);
        this.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
        this.setForeground(Color.decode("#485460"));
        this.setFocusPainted(false);
        this.setBorderPainted(true);
        this.setContentAreaFilled(true); // Bật vẽ nền
        this.setOpaque(true); // Đảm bảo nền được vẽ
        this.setBackground(null); // Không có nền mặc định
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public String getNamebtn() {
        return namebtn;
    }
}
