package org.projects.GUI.Panel.PhanQuyenPack;

import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.Components.layoutCompoment;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;

public class PhanQuyen extends JPanel{
    private JTable mainTable;
    private JPanel contentPanel;
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
        initTable();
    }
    public void init() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        layoutCompoment.setupMainPanel(contentPanel);
        this.add(contentPanel);
    }
    public void initTable() {
        String [] col = {"Mã nhóm quyền", "Tên nhóm quyền"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0) {
            public boolean isCellEditable(int row, int column) {return false;}
        };
        tableModel.addRow(new Object[] {"111", "Bố"});
        mainTable = new JTable(tableModel);
        for (int i = 0; i < mainTable.getColumnCount(); i++) {
            mainTable.getColumnModel().getColumn(i).setPreferredWidth(200);
        }
        contentPanel.add(mainTable, BorderLayout.CENTER);
    }
}
