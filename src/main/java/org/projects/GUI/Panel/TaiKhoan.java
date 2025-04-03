package org.projects.GUI.Panel;

import org.projects.GUI.Components.layoutCompoment;

import javax.swing.JPanel;

public class TaiKhoan extends JPanel{
    public TaiKhoan() {
        String listItemHeader[][] = {
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"},
                {"icon/excel.svg", "Xuất excel", "export"}
        };
//        layoutCompoment.addHeader(this, listItemHeader);
    }
}
