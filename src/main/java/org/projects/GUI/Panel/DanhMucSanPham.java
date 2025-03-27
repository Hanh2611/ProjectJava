package org.projects.GUI.Panel;

import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.Components.layoutCompoment;

import java.awt.*;

import javax.swing.*;

public class DanhMucSanPham extends JPanel{
    public DanhMucSanPham() {
        String listItemHeader[][] = {
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"},
                {"icon/excel.svg", "Xuất excel", "export"}
        };
        layoutCompoment.addHeader(this, listItemHeader);
    }
}
