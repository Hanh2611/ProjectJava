package org.projects.GUI.Panel;

import org.projects.Action.PhieuNhapAction;
import org.projects.BUS.PhanQuyenBUS;
import org.projects.BUS.PhieuNhapBUS;
import org.projects.DAO.PhieuNhapDAO;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.DiaLog.PhieuNhap.CapNhatPN;
import org.projects.GUI.DiaLog.PhieuNhap.ChiTietPN;
import org.projects.GUI.DiaLog.PhieuNhap.ThemPN;
import org.projects.GUI.utils.Session;
import org.projects.GUI.utils.UIUtils;
import org.projects.entity.ChiTietPhieuNhapFullEntity;
import org.projects.entity.PhieuNhapEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhieuNhap extends JPanel {
    private headerBar header;
    private JPanel themPN;
    private CapNhatPN capNhatPN;
    private ChiTietPN chiTietPN;
    private JPanel contentpanel;
    private CardLayout cardLayout;
    private JTable table;
    private DefaultTableModel tableModel;
    private PhieuNhapAction actionHandler = new PhieuNhapAction(this);
    private PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS(this); //  Khai báo BUS
    private PhieuNhapEntity phieuNhapEntity;
    private PhieuNhapDAO phieuNhapDAO;
    private int currentPanel = 0;

    public PhieuNhap() {
        String[][] listItemHeader = {
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"}
        };
        header = new headerBar(listItemHeader, Session.quyenTaiKhoan.get(PhanQuyenBUS.getMaDanhMuc("PhanQuyen") - 1),new String[]{"---","mã","tên","địa chỉ"});
//        header = new headerBar(listItemHeader,new ArrayList<>(Arrays.asList("add", "update", "delete", "detail")),new String[]{"Tất cả","Mã","Tên nhân viên","Tên NCC"});
        this.add(header);
        init();
        reloadDAO();
    }

    private void init() {
        JPanel right = createTablePanel();
        themPN = new ThemPN(this);
        chiTietPN = new ChiTietPN(this);
        capNhatPN = new CapNhatPN(this);
        contentpanel = new JPanel();
        contentpanel.setOpaque(false);
        cardLayout = new CardLayout();
        contentpanel.setLayout(cardLayout);
        contentpanel.setPreferredSize(new Dimension(940, 650));
        contentpanel.add(right, "trangchinh");
        contentpanel.add(themPN, "Them PN");
        contentpanel.add(chiTietPN, "ChiTiet PN");
        contentpanel.add(capNhatPN, "CapNhat PN");

        add(contentpanel);
        cardLayout.show(contentpanel, "trangchinh");

        for (String name : header.getHeaderFunc().getHm().keySet()) {
            header.getHeaderFunc().getHm().get(name).addMouseListener(actionHandler);
        }

        UIUtils.refreshComponent(this);
        header.getSearch().getSearchComboBox().addItemListener(actionHandler);
        header.getSearch().getSearchField().getDocument().addDocumentListener(actionHandler);
        header.getSearch().getSearchButton().addActionListener(actionHandler);
    }
    public int getCurrentPanel() {
        return currentPanel;
    }
    public headerBar getHeader() {
        return header;
    }
    public void searchfunction(String keyword,String textfield) {
        keyword = this.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        textfield = this.getHeader().getSearch().getSearchField().getText();
        if(!keyword.equals("---") && !textfield.trim().isEmpty()) {
            List<PhieuNhapEntity> lst = PhieuNhapBUS.search(keyword,textfield);
            loadList(lst);
        } else reloadDAO();
    }

    public void reloadDAO() {
        List<PhieuNhapEntity> lst = new PhieuNhapDAO().showlist();
        loadList(lst);
    }
    public static String formatCurrency(long value) {
        DecimalFormat format = new DecimalFormat("#,###");
        return format.format(value).replace(",", ".") + " ₫";
    }
    public void loadList(List<PhieuNhapEntity> list) {
        tableModel.setRowCount(0);
        if (list != null) {
            for (PhieuNhapEntity pn : list) {
                String tongFormatted = formatCurrency((long) pn.getTongGiaTri());
                tableModel.addRow(new Object[]{
                        pn.getMaPN(),
                        pn.getTenNV(),
                        pn.getTenNCC(),
                        pn.getNgayNhap(),
                        tongFormatted
                });
            }
        }
    }


    private JPanel createTablePanel() {
        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.WHITE);
        right.setOpaque(false);

        String[] columnNames = {"Mã Phiếu Nhập", "Tên Nhân Viên", "Tên Nhà Cung Cấp", "Ngày Nhập", "Tổng tiền"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        customizeTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(940, 640));

        right.add(scrollPane, BorderLayout.CENTER);

        return right;
    }


    private void customizeTable() {
        table.setDefaultEditor(Object.class, null);
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setFocusable(false);
        table.setBackground(new Color(245, 245, 245));
        table.setGridColor(new Color(220, 220, 220));
        table.setSelectionBackground(new Color(204, 229, 255));
        table.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 13));
        table.setIntercellSpacing(new Dimension(0, 0));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(new Color(240, 240, 240));
        header.setFont(new Font("JETBRAINS MONO", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
    }

    public JTable getTable() {
        return table;
    }

    public void showTrangChinh() {
        cardLayout.show(contentpanel, "trangchinh");
        currentPanel =0;
    }

    public void showThemPN() {
        cardLayout.show(contentpanel, "Them PN");
        currentPanel = 1;
    }

    public void showChiTietPN(List<ChiTietPhieuNhapFullEntity> list) {
        chiTietPN.setData(list); // gọi setData trước khi hiển thị
        cardLayout.show(contentpanel, "ChiTiet PN");
        currentPanel = 1;

    }
    public void showCapNhatPN(List<ChiTietPhieuNhapFullEntity> list) {
        capNhatPN.loadDatatoTablePhieuNhap(list);
        cardLayout.show(contentpanel, "CapNhat PN");
        currentPanel = 1;

    }
}