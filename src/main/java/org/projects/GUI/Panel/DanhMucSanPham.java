package org.projects.GUI.Panel;

import org.projects.GUI.Components.header.headerBar;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DanhMucSanPham extends JPanel{
    public DanhMucSanPham() {
        String listItemHeader[][] = {
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"},
                {"icon/excel.svg", "Xuất excel", "export"}
        };
        this.setPreferredSize(new Dimension(1100, 1000));
        this.setLayout(new FlowLayout(0, 0, 10));
        this.setBackground(Color.decode("#CAECF7"));
        this.add(new headerBar(listItemHeader));
    }
}
