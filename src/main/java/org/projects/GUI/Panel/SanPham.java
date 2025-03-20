package org.projects.GUI.Panel;

import java.awt.Color;

import javax.swing.*;

public class SanPham extends JPanel{

    private JTable table;

    public SanPham() {
        initComponent();
        this.setBackground(Color.BLUE);
        this.add(new JLabel("SanPham"));
    }

    private void initComponent() {
        String[] columns = {"Hình ảnh", "Tên", "Phân loại", "Giá bán", "Số lượng tồn", "Quy cách", "Đơn vị"};
        String[][] data = {};
        table = new JTable();
    }
}
