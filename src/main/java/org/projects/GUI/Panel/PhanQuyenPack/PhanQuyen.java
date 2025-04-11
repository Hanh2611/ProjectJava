package org.projects.GUI.Panel.PhanQuyenPack;

import org.projects.Action.PhanQuyenAction;
import org.projects.BUS.PhanQuyenBUS;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.Components.layoutCompoment;
import org.projects.GUI.DiaLog.PhanQuyen.addPhanQuyen;
import org.projects.GUI.utils.Session;
import org.projects.entity.NhomQuyen;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import static org.projects.Action.LoginAction.mainGUI;

public class PhanQuyen extends JPanel{
    private JTable mainTable;
    private JPanel contentPanel;
    private headerBar header;
    private DefaultTableModel tableModel;
    private String[][] listItemHeader;
    public PhanQuyen() {
        listItemHeader = new String[][]{
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"},
                {"icon/excel.svg", "Xuất excel", "export"}
        };
//        header = new headerBar(listItemHeader, Session.quyenTaiKhoan.get(PhanQuyenBUS.getMaDanhMuc("PhanQuyen") - 1),new String[]{"--"});
        header = new headerBar(listItemHeader,new ArrayList<>(Arrays.asList("add","update","delete","detail","export")),new String[]{"---"});

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.add(header);
        init();
        initTable();
    }
    public void init() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        layoutCompoment.setupMainPanel(contentPanel);
        this.setOpaque(true);
        this.setBackground(Color.decode("#CAECF7"));
        this.add(contentPanel);
    }
    public void initTable() {
        String [] col = {"Mã nhóm quyền", "Tên nhóm quyền"};
        tableModel = new DefaultTableModel(col, 0) {
            public boolean isCellEditable(int row, int column) {return false;}
        };
        loadData();
        mainTable = new JTable(tableModel);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < mainTable.getColumnCount(); i++) {
            mainTable.getColumnModel().getColumn(i).setPreferredWidth(200);
            mainTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        JTableHeader headerTable = mainTable.getTableHeader();
        headerTable.setBackground(new Color(0, 102, 204));
        headerTable.setForeground(new Color(240, 240, 240));
        headerTable.setFont(new Font("JETBRAINS MONO", Font.BOLD, 16));
        headerTable.setPreferredSize(new Dimension(header.getWidth(), 35));
        mainTable.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 14));
        mainTable.setRowHeight(40);
        mainTable.getTableHeader().setReorderingAllowed(false);
        mainTable.setShowGrid(false);
        mainTable.setGridColor(Color.decode("#CAECF7"));
        JScrollPane scrollPane = new JScrollPane(mainTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        mainTable.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentPanel.setBorder(BorderFactory.createEmptyBorder());
        for(String name : header.getHeaderFunc().getHm().keySet()) {
            header.getHeaderFunc().getHm().get(name).addMouseListener(new PhanQuyenAction(this, mainTable));
        }
    }
    public void loadData() {
        List<NhomQuyen> list = new PhanQuyenBUS().getNhomQuyen();
        for (NhomQuyen nhomQuyen : list) {
            tableModel.addRow(new Object[]{nhomQuyen.getMaNhomQuyen(), nhomQuyen.getTenNomQuyen()});
        }
    }

    public headerBar getHeader() {
        return header;
    }
    public void setHeader(headerBar header) {
        this.header = header;
    }
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public String[][] getListItemHeader() {
        return listItemHeader;
    }
}
