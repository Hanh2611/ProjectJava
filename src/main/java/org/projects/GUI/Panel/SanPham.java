package org.projects.GUI.Panel;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SanPham extends JPanel{

    private JTable table;

    public SanPham() {
        initComponent();
        this.setBackground(Color.BLUE);
        this.add(new JLabel("SanPham"));
    }

    private void initComponent() {

        String[] columns = {"Hình ảnh", "Tên", "Phân loại", "Giá bán", "Số lượng tồn", "Quy cách", "Đơn vị"};
        Object[][] data ={
                {"src/main/resources/img/product/tra-xanh-c2.jpg", "Trà xanh C2", "Nước giải khát", 10000, "100", "Chai", "330ml"},
                {"src/main/resources/img/product/suon-bo-my.jpg", "Sườn bò Mỹ", "Thịt", 200000, "50", "Hộp", "300g"}
        };

        for (Object[] it : data){
            it[0] = new ImageIcon(new ImageIcon(it[0].toString())
                    .getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH));
        }

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 0) ? ImageIcon.class : Object.class;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(60);
        table.setDefaultEditor(Object.class, null);
        table.setPreferredScrollableViewportSize(new Dimension(950, 650));
        table.setFillsViewportHeight(true);
        this.add(new JScrollPane(table));
    }
}
