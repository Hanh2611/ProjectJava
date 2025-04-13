package org.projects.GUI.DiaLog.PhanQuyen;

import org.projects.BUS.PhanQuyenBUS;
import org.projects.DAO.DanhMucQuanLyDAO;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.LoginGUI;
import org.projects.GUI.Panel.PhanQuyenPack.PhanQuyen;
import org.projects.GUI.utils.FocusListenerUtils;
import org.projects.entity.DanhMucQuanLy;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.projects.Action.LoginAction.mainGUI;

public class addPhanQuyen extends JDialog {
    private JPanel titleBar, contentPanel, inputPanel;
    private JTextField textField;
    private JLabel nameLabel;
    private JTable mainTable;
    private JButton addButton;
    private List<DanhMucQuanLy> listDanhMuc;

    public addPhanQuyen(JFrame parent) {
        super(parent, "ADD PHAN QUYEN", true);
        this.getContentPane().setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setSize(900, 700);
        setLocationRelativeTo(parent);
        setUndecorated(true);
        titleBar = new JPanel();
        objectFactory.titleBar(titleBar, this, "Thêm nhóm quyền");
        this.add(titleBar);
        init();
        setVisible(true);
    }
    public void init() {
        listDanhMuc = new PhanQuyenBUS().getDanhMucQuanLy();
        inputPanel = new JPanel();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setOpaque(true);
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        inputPanel.setPreferredSize(new Dimension(800, 50));
        nameLabel = new JLabel("Tên nhóm quyền: ");
        nameLabel.setFont(new Font("JetBrains Mono",Font.BOLD,14));
        this.add(nameLabel);
        textField = handleComponents.createTextField("Nhập tên nhóm quyền.....", 60, 190, 500, 50);
        textField.setPreferredSize(new Dimension(500, 30));
        textField.addFocusListener(FocusListenerUtils.createPlaceholderTextField("Nhập tên nhóm quyền.....", textField));
        inputPanel.add(nameLabel);
        inputPanel.add(textField);
        this.add(inputPanel);
        //tạo bảng
        initTableManagement();
        addButton = new JButton("Thêm nhóm quyền");
        addButton.setPreferredSize(new Dimension(200, 40));
        addButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                pushData();
            }
        });
        this.add(contentPanel);
        this.add(addButton);
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
                return column != 0;
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
        mainTable.setOpaque(false);
        mainTable.setBackground(Color.WHITE);
        mainTable.setShowGrid(false);
        mainTable.setSelectionBackground(mainTable.getBackground());
        mainTable.setSelectionForeground(mainTable.getForeground());
        mainTable.setFocusable(false);
        JScrollPane scrollPane = new JScrollPane(mainTable);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setOpaque(true);
        scrollPane.setBackground(Color.WHITE);
        mainTable.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        mainTable.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentPanel.setBorder(BorderFactory.createEmptyBorder());
    }

    public void loadData(DefaultTableModel tableModel) {
        for (DanhMucQuanLy dm : listDanhMuc) {
            tableModel.addRow(new Object[] {dm.getTen_danh_muc_quan_ly(), Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE});
        }
    }

    public void pushData() {
        String nameNhomQuyen  = textField.getText();
        if (!checkNameNhomQuyen(nameNhomQuyen)) {
            return;
        }
//        String[] danhMuc = {"nhan_vien", "khach_hang", "san_pham", "don_hang", "thong_ke", "nha_cung_cap", "hoa_don", "tai_khoan", "phieu_nhap"};
        int row = mainTable.getRowCount();
        int col = mainTable.getColumnCount();
        HashMap<String, List<Boolean>> danhMucData = new HashMap<>();
        for (int i = 0; i < row; i++) {
            List<Boolean> rowData = new ArrayList<>();
            for (int j = 1; j < col; j++) {
                boolean isChecked = (Boolean) mainTable.getValueAt(i, j);
                rowData.add(isChecked);
            }
            danhMucData.put(listDanhMuc.get(i).getTen_danh_muc_quan_ly(), rowData);
        }
        int maNhomQuyen = PhanQuyenBUS.addNhomQuyen(nameNhomQuyen);
        PhanQuyenBUS.addCapQuyen(danhMucData, maNhomQuyen);
        JOptionPane.showMessageDialog(mainGUI, "Thêm nhóm quyền thành công!");
        dispose();
    }

    public boolean checkNameNhomQuyen(String nameNhomQuyen) {
        String regex = "^[a-zA-Z0-9_-]{3,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nameNhomQuyen);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(mainGUI, "Tên nhóm quyền không hợp lệ. Tên chỉ được chứa chữ cái, số, dấu gạch dưới (_) hoặc dấu gạch ngang (-), và phải có độ dài từ 3 đến 30 ký tự.");
            textField.requestFocusInWindow();
            return false;
        }
        if (nameNhomQuyen.equals("") || nameNhomQuyen == null || nameNhomQuyen.equals("Nhập tên nhóm quyền.....")) {
            JOptionPane.showMessageDialog(mainGUI, "Vui lòng nhập tên nhóm quyền!");
            textField.requestFocusInWindow();
            return false;
        }
        if (PhanQuyenBUS.checkExitsNameNhomQuyen(nameNhomQuyen)) {
            JOptionPane.showMessageDialog(mainGUI, "Tên nhóm quyền đã tồn tại!");
            textField.requestFocusInWindow();
            return false;
        }
        return true;
    }
}
