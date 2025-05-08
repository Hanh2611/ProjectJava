package org.projects.GUI.Panel;

import org.projects.Action.TaiKhoanAction;
import org.projects.BUS.PhanQuyenBUS;
import org.projects.BUS.TaiKhoanBUS;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.Components.layoutCompoment;
import org.projects.GUI.utils.Session;
import org.projects.entity.TaiKhoanEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//todo: mình sẽ cho tạo tài khoản mặc định sẽ là khách hàng, sau đó mình sẽ set tài khoản vào với nhân viên
//todo: mã người dùng sẽ được tạo tự động rồi lấy cái mã đó để có thể set cho nhân viên
//todo: trong phần chi tiết ta có tên người dùng(Lấy từ mã người dùng), tên đăng nhập, loại tài khoản, các quyền, trạng thái;
//todo: (Nhớ dùng regex) trong phần thêm ta sẽ nhập tên đăng nhập, mật khẩu, mã nhân viên (vì trước đó cần có nhân viên nên ta sẽ tạo 1 người dùng, rồi sau đó tạo tài khoản với mã người dùng đó)
//todo: (Nhớ dùng regex) trong phần sửa thì ta sẽ được quyền sửa mật khẩu, quyền, trạng thái
//todo: Trong phần quyền mình có thể dùng combobox kết hợp với tìm kiếm để có thể chọn nhóm quyền 1 cách dễ dàng
//todo: Phần trạng thái cũng sẽ là combobox
//todo: Xóa thì có thể xóa tài khoản, sau đó xóa người dùng
public class TaiKhoan extends JPanel{
    private headerBar header;
    private JTable mainTable;
    private JScrollPane scrollPane;
    private JPanel contentPanel;
    private DefaultTableModel tableModel;
    private String[][] listItemHeader;
    private TaiKhoanAction tkAction;
    public TaiKhoan() {
        listItemHeader = new String[][]{
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"},
                {"icon/excel.svg", "Xuất excel", "export"}
        };
        tkAction = new TaiKhoanAction(this,null);
        this.setBackground(Color.decode("#CAECF7"));
        this.setOpaque(true);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        initHeader();
        initContent();
    }

    public void initHeader() {
        header = new headerBar(listItemHeader, Session.quyenTaiKhoan.get(PhanQuyenBUS.getMaDanhMuc("TaiKhoan") - 1), new String[]{"--"});
//        header = new headerBar(listItemHeader,new ArrayList<>(Arrays.asList("add", "update", "delete", "detail")),new String[]{"---","tên đăng nhập","mã người dùng"});

        this.add(header);
    }

    public void initContent() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        layoutCompoment.setupMainPanel(contentPanel);
        initTable();
        this.add(contentPanel);
    }

    public void initTable() {
        String[] col = {"Tên người dùng", "Mã người dùng", "Tên đăng nhập", "Loại người dùng", "Trạng thái"};
        tableModel = new DefaultTableModel(col, 0) {
            public boolean isCellEditable(int row, int column) {return false;}
        };
        loadDataIntoTable();
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
        scrollPane = new JScrollPane(mainTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        mainTable.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentPanel.setBorder(BorderFactory.createEmptyBorder());
        //action
        for(String name : this.getHeader().getHeaderFunc().getHm().keySet()) {
            this.getHeader().getHeaderFunc().getHm().get(name).addMouseListener(tkAction);
        }
        header.getSearch().getSearchComboBox().addItemListener(tkAction);
        header.getSearch().getSearchField().getDocument().addDocumentListener(tkAction);
        header.getSearch().getSearchButton().addActionListener(tkAction);

    }
    public void loadDataIntoTable() {
        tableModel.setRowCount(0);
        List<TaiKhoanEntity> listTaiKhoanEntity = TaiKhoanBUS.getListTaiKhoan();
        for (TaiKhoanEntity t : listTaiKhoanEntity) {
            tableModel.addRow(new Object[]{TaiKhoanBUS.getTenNguoiDung(t.getMaNguoiDung()), t.getMaNguoiDung(), t.getTenDangNhap(), TaiKhoanBUS.getLoaiNguoiDung(t.getMaNguoiDung()), t.getTrangThai()});
        }
    }

    public TaiKhoanEntity getSelectedTaiKhoanEntity() {
        int row = mainTable.getSelectedRow();
        if(row >= 0) {
            String tendangnhap = mainTable.getModel().getValueAt(row, 2).toString();
            List<TaiKhoanEntity> lst = TaiKhoanBUS.getListTaiKhoan();
            for(TaiKhoanEntity tkEntity : lst) {
                if(tkEntity.getTenDangNhap().equals(tendangnhap)) return tkEntity;
            }
        }
        return null;
    }

    public void searchFunction(String key, String word) {
        key = this.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        word = this.getHeader().getSearch().getSearchField().getText().trim();
        if (!key.equals("---") && !word.isEmpty()) {
            List<TaiKhoanEntity> tks = TaiKhoanBUS.search(key, word);
            tableModel.setRowCount(0); // Xóa bảng
            for (TaiKhoanEntity t : tks) {
                tableModel.addRow(new Object[]{
                        TaiKhoanBUS.getTenNguoiDung(t.getMaNguoiDung()),
                        t.getMaNguoiDung(),
                        t.getTenDangNhap(),
                        TaiKhoanBUS.getLoaiNguoiDung(t.getMaNguoiDung()),
                        t.getTrangThai()
                });
            }
        } else {
            loadDataIntoTable();
        }
    }


    public headerBar getHeader() {
        return header;
    }

    public void setHeader(headerBar header) {
        this.header = header;
    }

    public JTable getMainTable() {
        return mainTable;
    }

    public void setMainTable(JTable mainTable) {
        this.mainTable = mainTable;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
}