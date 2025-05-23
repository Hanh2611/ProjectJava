package org.projects.GUI.Panel.KhachHangPack;

import org.projects.Action.KhachHangAction;
import org.projects.BUS.KhachHangBUS;
import org.projects.BUS.NhanVienBus;
import org.projects.BUS.PhanQuyenBUS;
import org.projects.DAO.KhachHangDAO;
import org.projects.DAO.NhanVienDao;
import org.projects.GUI.Components.Transition.mainTransition;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.utils.Session;
import org.projects.config.DatabasesConfig;
import org.projects.entity.KhachHangEntity;
import org.projects.entity.NhanVienEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.CompoundBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class KhachHang extends JPanel {
    private JTable table;
    private headerBar header;
    private DefaultTableModel tableModel;
    private static mainTransition transition = new mainTransition();
    private KhachHangAction khachHangAction = new KhachHangAction(this);
    public KhachHang() {
        init();
        setupHeader();
        setupLayout();
    }

    private void init(){
        String [] col = {"Mã KH", "Tên khách hàng", "SĐT", "Địa chỉ"};
        tableModel = new DefaultTableModel(col, 0){
            // không cho chính sửa trực tiếp trong bảng
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        reloadDAO();
        table = new JTable(tableModel);
        CustomTable();
    }

    public void loadList(List<KhachHangEntity> list) {
        tableModel.setRowCount(0);
        if(list != null) {
            for(KhachHangEntity kh : list) {
                tableModel.addRow(new Object[]{
                        kh.getMa(),
                        kh.getTen(),
                        kh.getSdt(),
                        kh.getDiaChi(),
                });
            }
        }
    }

    public void reloadDAO() {
        List<KhachHangEntity> showlist = new KhachHangDAO().showlist();
        loadList(showlist);
    }
    public static String convertTrangThai(String s){
        if(s.equals("da_thanh_toan")) return "Đã thanh toán";
        return "Chưa thanh toán";
    }

    private void CustomTable(){
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(new Color(240, 240, 240));
        header.setFont(new Font("JETBRAINS MONO", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        table.setBackground(new Color(245, 245, 245));
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setGridColor(new Color(220, 220, 220));
        table.setSelectionBackground(new Color(204, 229, 255));
        table.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 13));
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setOpaque(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void setupHeader(){
        String[][] listItemHeader = {
                {"icon/add.svg", "Thêm", "Them"},
                {"icon/content-writing.svg", "Sửa", "Sua"},
                {"icon/trash.svg", "Xóa", "Xoa"},
                {"icon/details.svg", "Chi tiết", "ChiTiet"},
                {"icon/excel.svg", "Xuất excel", "Excel"}
        };
        String[] quyen = new String[]{"add", "update", "delete", "detail"};
//        addHeader(this, listItemHeader, quyen);
//        add(new headerBar(listItemHeader, new ArrayList<>(Arrays.asList("add", "update", "delete", "detail")),new String[]{"---" , "mã" , "tên" , "địa chỉ"}));
        add(new headerBar(listItemHeader , Session.quyenTaiKhoan.get(PhanQuyenBUS.getMaDanhMuc("KhachHang") - 1) , new String[]{"---" , "mã" , "tên" , "địa chỉ"}));
        header = (headerBar) this.getComponent(0);
        for(String key : header.getHeaderFunc().getHm().keySet()){
            header.getHeaderFunc().getHm().get(key).addMouseListener(khachHangAction);
        }

        header.getSearch().getSearchComboBox().addItemListener(khachHangAction);
//        header.getSearch().getSearchField().addKeyListener(nhanVienAction);
        header.getSearch().getSearchButton().addActionListener(khachHangAction);
        header.getSearch().getSearchField().getDocument().addDocumentListener(khachHangAction);
    }
    private void setupLayout() {
        setBackground(new Color(240, 240, 240));
        // Scroll
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.WHITE, 2),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                )
        );
        //scrollPane.setBorder(border);
        // Panel trung tâm
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        centerPanel.setPreferredSize(new Dimension(940, 650));
        centerPanel.add(scrollPane , gbc);
        add(centerPanel);
    }

    public headerBar getHeader() {
        return header;
    }

    public KhachHangEntity getRow() {
        int row = table.getSelectedRow();
        if(row == -1) return null;
        int ma = (int)table.getValueAt(row,0);
        String ten = table.getValueAt(row,1).toString();
        String sdt = table.getValueAt(row,2).toString();
        String diachi = table.getValueAt(row,3).toString();
        String avatar = null;
        String sql = "Select avatar from khach_hang where ma_khach_hang = ?";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, ma);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    avatar = rs.getString("avatar");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new KhachHangEntity(ma , ten ,sdt, diachi , avatar);
    }

    public void searchfunction(String keyword,String textfield) {
        keyword = this.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        textfield = this.getHeader().getSearch().getSearchField().getText();
        if(!keyword.equals("---") && !textfield.trim().isEmpty()) {
            List<KhachHangEntity> lst = KhachHangBUS.search(keyword,textfield);
            loadList(lst);
        } else reloadDAO();
    }
    public JTable getTable(){
        return table;
    }
}

