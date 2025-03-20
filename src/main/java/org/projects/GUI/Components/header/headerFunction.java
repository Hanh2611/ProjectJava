package org.projects.GUI.Components.header;

import javax.swing.*;
import java.awt.*;

public class headerFunction extends JPanel {
    String arr[] = {"Thêm", "Sửa", "Xóa", "Chi Tiết"};
    public headerFunction(Dimension parentSize) {
        this.setPreferredSize(new Dimension((int) (parentSize.width*0.5), parentSize.height));
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        System.out.println(parentSize);
    }
}
