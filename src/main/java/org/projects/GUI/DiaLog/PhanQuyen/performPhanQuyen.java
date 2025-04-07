package org.projects.GUI.DiaLog.PhanQuyen;

import org.projects.BUS.PhanQuyenBUS;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.utils.FocusListenerUtils;
import org.projects.entity.CapQuyen;
import org.projects.entity.DanhMucQuanLy;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.projects.Action.LoginAction.mainGUI;

public class performPhanQuyen extends JDialog {
    private JPanel titleBar, contentPanel, inputPanel;
    private JTextField textField;
    private JLabel nameLabel;
    private JTable mainTable;
    private List<DanhMucQuanLy> listDanhMuc;
    private String nameNhomQuyen;
    private int maNhomQuyen;

    public performPhanQuyen(JFrame parent, int maNhomQuyen, String nameNhomQuyen) {
        super(parent, "ADD PHAN QUYEN", true);
        this.nameNhomQuyen = nameNhomQuyen;
        this.maNhomQuyen = maNhomQuyen;
        this.setBackground(Color.decode("#FFFFFF"));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setSize(900, 700);
        setLocationRelativeTo(parent);
        setUndecorated(true);
        titleBar = new JPanel();
        objectFactory.titleBar(titleBar, this, "Chi tiết nhóm quyền");
        this.add(titleBar);
        init();
        setVisible(true);
    }
    public void init() {
        listDanhMuc = new PhanQuyenBUS().getDanhMucQuanLy();
        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        inputPanel.setPreferredSize(new Dimension(800, 50));
        nameLabel = new JLabel("Tên nhóm quyền: ");
        nameLabel.setFont(new Font("JetBrains Mono",Font.BOLD,14));
        this.add(nameLabel);
        textField = new JTextField(nameNhomQuyen);
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(500, 30));
        inputPanel.add(nameLabel);
        inputPanel.add(textField);
        this.add(inputPanel);
        //tạo bảng
        initTableManagement();
        this.add(contentPanel);
    }

    public void initTableManagement() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(900, 560));
        String[] col = {"", "Tạo mới", "Sửa", "Xoá", "Xem chi tiết", "Xuất Excel"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return String.class;
                return Boolean.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        loadData(tableModel);
        mainTable = new JTable(tableModel);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < mainTable.getColumnCount(); i++) {
            mainTable.getColumnModel().getColumn(i).setPreferredWidth(200);
            if (i == 0)
                mainTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
            else
                mainTable.getColumnModel().getColumn(i).setCellRenderer(mainTable.getDefaultRenderer(Boolean.class));
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
        mainTable.setShowGrid(false);
        mainTable.setSelectionBackground(mainTable.getBackground());
        mainTable.setSelectionForeground(mainTable.getForeground());
        mainTable.setFocusable(false);
        JScrollPane scrollPane = new JScrollPane(mainTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    public void loadData(DefaultTableModel tableModel) {
        String[] hanhDong = {"them", "sua", "xoa", "xem", "excel"};
        for (DanhMucQuanLy dm : listDanhMuc) {
            tableModel.addRow(new Object[] {dm.getTen_danh_muc_quan_ly(), PhanQuyenBUS.checkCapQuyen(new CapQuyen(maNhomQuyen, dm.getMa_danh_muc_quan_ly(), hanhDong[0])),
                    PhanQuyenBUS.checkCapQuyen(new CapQuyen(maNhomQuyen, dm.getMa_danh_muc_quan_ly(), hanhDong[1])),
                    PhanQuyenBUS.checkCapQuyen(new CapQuyen(maNhomQuyen, dm.getMa_danh_muc_quan_ly(), hanhDong[2])),
                    PhanQuyenBUS.checkCapQuyen(new CapQuyen(maNhomQuyen, dm.getMa_danh_muc_quan_ly(), hanhDong[3])),
                    PhanQuyenBUS.checkCapQuyen(new CapQuyen(maNhomQuyen, dm.getMa_danh_muc_quan_ly(), hanhDong[4]))});
        }
    }
}
