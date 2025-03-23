package org.projects.GUI.Panel;

import org.projects.GUI.Components.header.headerBar;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DanhMucSanPham extends JPanel{
    public DanhMucSanPham() {
        String listItemHeader[][] = {
                {"icon/add.svg", "Thêm", "Them"},
                {"icon/content-writing.svg", "Sửa", "Sua"},
                {"icon/trash.svg", "Xóa", "Xoa"},
                {"icon/details.svg", "Chi tiết", "ChiTiet"},
                {"icon/excel.svg", "Xuất excel", "Excel"}
        };
        this.setPreferredSize(new Dimension(1100, 1000));
        this.setLayout(new FlowLayout(0, 0, 0));
        this.add(new headerBar(listItemHeader));
    }
}
