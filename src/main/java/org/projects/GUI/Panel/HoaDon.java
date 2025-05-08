package org.projects.GUI.Panel;

import org.projects.Action.HoaDonAction;
import org.projects.BUS.HoaDonBUS;
import org.projects.BUS.PhanQuyenBUS;
import org.projects.DAO.HoaDonDAO;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.DiaLog.HoaDon.CapNhatHD;
import org.projects.GUI.DiaLog.HoaDon.ChiTietHD;
import org.projects.GUI.DiaLog.HoaDon.ThemHD;
import org.projects.GUI.utils.Session;
import org.projects.GUI.utils.UIUtils;
import org.projects.entity.ChiTietHoaDonFullEntity;
import org.projects.entity.HoaDonEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HoaDon extends JPanel{
    private headerBar header;
    private JPanel contentPanel,main,themHD;
    private DefaultTableModel tableModel;
    private CardLayout cardLayout;
    private JTable table;
    private HoaDonAction actionHandler;
    private HoaDonEntity hoaDonEntity;
    private HoaDonBUS hoaDonBUS = new HoaDonBUS(this);
    private CapNhatHD capNhatHD;
    private ChiTietHD chiTietHD;

    public HoaDon() {
        String[][] listItemHeader = {
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"}
        };
        header = new headerBar(listItemHeader, Session.quyenTaiKhoan.get(PhanQuyenBUS.getMaDanhMuc("HoaDon") - 1),new String[]{"---","mã","tên","địa chỉ"});
//        header = new headerBar(listItemHeader,new ArrayList<>(Arrays.asList("add", "update", "delete", "detail")),new String[]{"Tất cả","Mã","Tên nhân viên","Tên khách hàng"});
        this.add(header);
        init();
        reloadDAO();
        showTrangChinh();
    }
    private void init() {
        JPanel main = createTablePanel();
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);
        contentPanel.setPreferredSize(new Dimension(    940, 650));
        add(contentPanel);
        cardLayout.show(contentPanel, "trangchinh");
        themHD = new ThemHD(this);
        chiTietHD = new ChiTietHD(this);
        capNhatHD = new CapNhatHD(this);
        contentPanel.add(capNhatHD, "Capnhat HD");
        contentPanel.add(main, "trangchinh");
        contentPanel.add(themHD, "themHD");
        contentPanel.add(chiTietHD, "chiTietHD");
        actionHandler = new HoaDonAction(this);
        for (String name : header.getHeaderFunc().getHm().keySet()) {
            header.getHeaderFunc().getHm().get(name).addMouseListener(actionHandler);
        }
        UIUtils.refreshComponent(this);
        header.getSearch().getSearchComboBox().addItemListener(actionHandler);
        header.getSearch().getSearchField().getDocument().addDocumentListener(actionHandler);
        header.getSearch().getSearchButton().addActionListener(actionHandler);
    }
    private JPanel createTablePanel() {
        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.WHITE);
        right.setOpaque(false);

        // Dữ liệu bảng
        String[] columnNames = {"Mã hóa đơn", "Tên nhân viên", "Tên khách hàng", "Ngày tạo", "Tổng giá trị","Trạng thái"};

        tableModel = new DefaultTableModel(columnNames, 0);

        table = new JTable(tableModel);
        customizeTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(940, 640));


        right.add(scrollPane, BorderLayout.CENTER);
        return right;
    }
    public void reloadDAO(){
        List<HoaDonEntity> lst = new HoaDonDAO().showlist();
        loadList(lst);
    }
    public static String formatCurrency(long value) {
        DecimalFormat format = new DecimalFormat("#,###");
        return format.format(value).replace(",", ".") + " ₫";
    }
    public void loadList(List<HoaDonEntity> list){
        tableModel.setRowCount(0);
        if(list != null){
            for(HoaDonEntity hd : list){
                String giaFormatted = formatCurrency((long) hd.getTongGiaTri());
                tableModel.addRow(new Object[]{
                        hd.getMaHoaDon(),
                        hd.getTenNV(),
                        hd.getTenKh(),
                        hd.getNgayTao(),
                        giaFormatted,
                        hd.getTrangThai(),
                });
            }
        }
    }
    public headerBar getHeader() {
        return header;
    }
    public void searchfunction(String keyword,String textfield) {
        keyword = this.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        textfield = this.getHeader().getSearch().getSearchField().getText();
        if(!keyword.equals("Tất cả") && !textfield.trim().isEmpty()) {
            List<HoaDonEntity> lst = HoaDonBUS.search(keyword,textfield);
            loadList(lst);
        } else reloadDAO();
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

    // Căn giữa nội dung bảng
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    table.setDefaultRenderer(Object.class, centerRenderer);

    // Cấu hình header
    JTableHeader header = table.getTableHeader();
    header.setBackground(new Color(0, 102, 204));
    header.setForeground(new Color(240, 240, 240));
    header.setFont(new Font("JETBRAINS MONO", Font.BOLD, 13));
    header.setPreferredSize(new Dimension(header.getWidth(), 35));
    }

    public void showThemHD(){
        cardLayout.show(contentPanel, "themHD");
    }
    public void showSuaHD(List <ChiTietHoaDonFullEntity> list){
        capNhatHD.loadDatatoTableHoaDon(list);
        cardLayout.show(contentPanel, "Capnhat HD");
    }
    public void showChiTietHD(List<ChiTietHoaDonFullEntity> list){
        chiTietHD.setData(list);
        cardLayout.show(contentPanel, "chiTietHD");
    }
    public JTable getTable() {
        return table;
    }
    public void showTrangChinh(){
        cardLayout.show(contentPanel, "trangchinh");
    }
}
