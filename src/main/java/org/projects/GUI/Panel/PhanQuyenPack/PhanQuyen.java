package org.projects.GUI.Panel.PhanQuyenPack;

import org.projects.BUS.PhanQuyenBUS;
import org.projects.DAO.PhanQuyenDAO;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.Components.layoutCompoment;
import org.projects.entity.NhomQuyen;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
//        layoutCompoment.addHeader(this, listItemHeader);
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
        loadData(tableModel);
        mainTable = new JTable(tableModel);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < mainTable.getColumnCount(); i++) {
            mainTable.getColumnModel().getColumn(i).setPreferredWidth(200);
            mainTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        JTableHeader header = mainTable.getTableHeader();
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(new Color(240, 240, 240));
        header.setFont(new Font("JETBRAINS MONO", Font.BOLD, 16));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        mainTable.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 14));
        mainTable.setRowHeight(40);
        mainTable.getTableHeader().setReorderingAllowed(false);
        mainTable.setShowGrid(true);
        mainTable.setGridColor(Color.decode("#CAECF7"));
        JScrollPane scrollPane = new JScrollPane(mainTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }
    public void loadData(DefaultTableModel tableModel) {
        List<NhomQuyen> list = new PhanQuyenBUS().getNhomQuyen();
        for (NhomQuyen nhomQuyen : list) {
            tableModel.addRow(new Object[]{nhomQuyen.getMaNhomQuyen(), nhomQuyen.getTenNomQuyen()});
        }
    }
}
