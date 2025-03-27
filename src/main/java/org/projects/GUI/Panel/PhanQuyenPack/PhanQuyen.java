package org.projects.GUI.Panel.PhanQuyenPack;

import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.Components.layoutCompoment;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

public class PhanQuyen extends JPanel{
    private JTable mainTable;
    public PhanQuyen() {
        String listItemHeader[][] = {
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"},
                {"icon/excel.svg", "Xuất excel", "export"}
        };
        layoutCompoment.addHeader(this, listItemHeader);
        init();
    }
    public void init() {

    }
    public void initTable() {

    }
}
