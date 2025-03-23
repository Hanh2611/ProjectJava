package org.projects.GUI;

import javax.swing.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.Panel.HeaderTrangChu;

import java.awt.*;

public class TrangChuDemo extends JFrame {
    //header chua icon + ten cua hang + button dang nhap/dang ki + nut thoat
    //center de chua cac san pham show ra
    private HeaderTrangChu header;
    public TrangChuDemo() {
        this.setSize(900,700);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        header = new HeaderTrangChu();
        init();
        this.setVisible(true);
    }
    private void init() {
        header.setPreferredSize(new Dimension(100,60));
        this.add(header, BorderLayout.NORTH);
    }
}
