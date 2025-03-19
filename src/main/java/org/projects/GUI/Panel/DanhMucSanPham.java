package org.projects.GUI.Panel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DanhMucSanPham extends JPanel{
    public DanhMucSanPham() {
        this.setBackground(Color.BLUE);
        this.add(new JLabel("Danh muc san pham"));
    }
}
