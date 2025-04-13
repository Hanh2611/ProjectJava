package org.projects.GUI.DiaLog.PhieuNhap;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;

public class TestSVGIcon {
    public static void main(String[] args) {
        // Set giao diện FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Tạo frame cơ bản
        JFrame frame = new JFrame("Hiển thị SVG Icon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        // Load SVG icon
        FlatSVGIcon icon = new FlatSVGIcon("icon/add.svg", 50, 50);

        // Gắn vào JLabel
        JLabel label = new JLabel("Thêm", icon, JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);

        // Thêm vào frame
        frame.add(label);

        // Hiển thị giao diện
        frame.setLocationRelativeTo(null); // căn giữa màn hình
        frame.setVisible(true);
    }
}
