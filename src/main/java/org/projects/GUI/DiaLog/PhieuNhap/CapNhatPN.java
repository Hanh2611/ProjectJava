package org.projects.GUI.DiaLog.PhieuNhap;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.DAO.ChiTietPhieuNhapDAO;
import org.projects.DAO.NhaCungCapDAO;
import org.projects.DAO.PhieuNhapDAO;
import org.projects.DAO.SanPhamDAO;
import org.projects.GUI.Components.NumberOnlyFilter;
import org.projects.GUI.Components.OnlyDigitFilter;
import org.projects.GUI.Panel.PhieuNhap;
import org.projects.GUI.utils.Session;
import org.projects.entity.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CapNhatPN extends JPanel {
    JTextField timKiem, hienthi_masp, hienthi_tensp, nhapsoluong, nhapgiaban,
            txtQuyCach, txtDonViTinh, nhapMaPN, nhapNVNhap;
    JLabel lblTitle,masp, tensp, soluong, giaban, lblQuyCach, lblDonViTinh, maPN, nvNhap,
            ncc, lblTongTien, txtTongTien;
    JPanel panelLeft, panelright;
    JButton themSP, btnNhapHang, btnHuyBo, btnHuyBoSP,btnSuaSP;
    JTable tableSanPham, danhSachSanPhamNhap;
    JTableHeader header, headerdanhSachSanPhamNhap;
    JComboBox<String> nhapNCC;
    JScrollPane scrollPane, scrollPaneNhap;
    DefaultTableModel modelDanhSachNhap, tableModel;
    FlatSVGIcon icon_them, icon_sua, icon_xoa, icon_huybo;
    private PhieuNhap phieuNhap;
    private SanPhamEntity sanPhamEntity;
    private Map<String, NhaCungCapEntity> nccMap = new HashMap<>();


    public CapNhatPN(PhieuNhap phieuNhap) {
        setPreferredSize(new Dimension(940, 650));
        setOpaque(false);
        setLayout(null);
        this.phieuNhap = phieuNhap;
        init();
        loadDataToTableSanPham(); // Gọi ở cuối để load dữ liệu ban đầu

    }

    public void init() {
        lblTitle = new JLabel("Phiếu nhập / Sửa phiếu nhập");
        lblTitle.setFont(new Font("JETBRAINS MONO", Font.BOLD, 17));
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setBounds(30,10,500,30);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLUE); // Cùng màu với tiêu đề
        separator.setPreferredSize(new Dimension(690, 1)); // Chiều dài và độ dày
        separator.setBounds(20,40,690,10);
        // Ô tìm kiếm sản phẩm
        timKiem = new JTextField("Tìm kiếm mã sản phẩm, tên sản phẩm");
        timKiem.setBounds(15, 55, 430, 30);
        timKiem.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (timKiem.getText().equals("Tìm kiếm mã sản phẩm, tên sản phẩm")) {
                    timKiem.setText("");
                    timKiem.setForeground(Color.GRAY);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (timKiem.getText().isEmpty()) {
                    timKiem.setText("Tìm kiếm mã sản phẩm, tên sản phẩm");
                    timKiem.setForeground(Color.GRAY);
                }
            }
        });
        add(timKiem);
        add(lblTitle);
        add(separator);
        // Panel bên trái
        panelLeft = new JPanel();
        panelLeft.setLayout(null);
        panelLeft.setBounds(5, 5, 725, 655);
        panelLeft.setBackground(Color.WHITE);
        add(panelLeft);

        // Bảng dữ liệu sản phẩm
        String[] columnNames = {"Mã SP", "Tên SP"};
        tableModel = new DefaultTableModel(columnNames,0);
        tableSanPham = new JTable(tableModel);
        tableSanPham.setRowHeight(25);
        tableSanPham.getColumnModel().getColumn(0).setPreferredWidth(80);
        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(320);
        tableSanPham.setShowGrid(true);
        tableSanPham.setGridColor(Color.LIGHT_GRAY);
        tableSanPham.setDefaultEditor(Object.class, null);
        tableSanPham.setRowHeight(30);
        tableSanPham.setFocusable(false);
        tableSanPham.setSelectionBackground(new Color(204, 229, 255));
        tableSanPham.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 13));
        tableSanPham.setIntercellSpacing(new Dimension(0, 0));

        header = tableSanPham.getTableHeader();
        header.setBackground(new Color(245, 244, 245, 255));
        header.setForeground(new Color(105, 105, 105, 255));
        header.setFont(new Font("JETBRAINS MONO", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tableSanPham.getColumnCount(); i++) {
            tableSanPham.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        scrollPane = new JScrollPane(tableSanPham);
        scrollPane.setBounds(10, 90, 430, 250);
        panelLeft.add(scrollPane);

        themSP = new JButton("Thêm Sản Phẩm");
        icon_them = new FlatSVGIcon("icon/add.svg", 15,15);
        themSP.setIcon(icon_them);
        themSP.setIconTextGap(7);
        themSP.setBounds(10, 355, 220, 30);
        themSP.setBackground(new Color(89, 168, 105, 255));
        themSP.setForeground(Color.WHITE);
        themSP.setCursor(new Cursor(Cursor.HAND_CURSOR));
        themSP.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));

        btnHuyBoSP = new JButton("Bỏ sản phẩm");
        btnHuyBoSP.setBounds(240, 355, 220, 30);
        icon_xoa = new FlatSVGIcon("icon/trash.svg",15,15);
        btnHuyBoSP.setIcon(icon_xoa);
        btnHuyBoSP.setIconTextGap(7);
        btnHuyBoSP.setBackground(Color.RED);
        btnHuyBoSP.setForeground(Color.WHITE);
        btnHuyBoSP.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHuyBoSP.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));

        btnSuaSP = new JButton("Sửa sản phẩm");
        btnSuaSP.setBounds(470, 355, 220, 30);
        icon_sua = new FlatSVGIcon("icon/content-writing.svg",15,15);
        btnSuaSP.setIcon(icon_sua);
        btnSuaSP.setIconTextGap(7);
        btnSuaSP.setBackground(Color.BLUE);
        btnSuaSP.setForeground(Color.WHITE);
        btnSuaSP.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSuaSP.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
        panelLeft.add(btnSuaSP);
        panelLeft.add(btnHuyBoSP);
        panelLeft.add(themSP);


        masp = new JLabel("Mã Sản phẩm");
        masp.setBounds(450, 75, 100, 25);
        hienthi_masp = new JTextField();
        hienthi_masp.setBounds(450, 105, 265, 30);
        hienthi_masp.setEditable(false);
        panelLeft.add(masp);
        panelLeft.add(hienthi_masp);

        tensp = new JLabel("Tên sản phẩm");
        tensp.setBounds(450, 145, 100, 25);
        hienthi_tensp = new JTextField();
        hienthi_tensp.setBounds(450, 170, 265, 30);
        hienthi_tensp.setEditable(false);
        panelLeft.add(tensp);
        panelLeft.add(hienthi_tensp);

        soluong = new JLabel("Số lượng nhập");
        soluong.setBounds(450, 210, 100, 30);
        nhapsoluong = new JTextField();
        nhapsoluong.setBounds(450, 240, 265, 30);
        panelLeft.add(soluong);
        panelLeft.add(nhapsoluong);

        giaban = new JLabel("Giá nhập");
        giaban.setBounds(450, 275, 100, 30);
        nhapgiaban = new JTextField();
        nhapgiaban.setBounds(450, 305, 265, 30);
        panelLeft.add(giaban);
        panelLeft.add(nhapgiaban);

        ((AbstractDocument) nhapsoluong.getDocument()).setDocumentFilter(new OnlyDigitFilter());
        ((AbstractDocument) nhapgiaban.getDocument()).setDocumentFilter(new NumberOnlyFilter());
//        lblQuyCach = new JLabel("Quy cách");
//        lblQuyCach.setBounds(450, 215, 100, 30);
//        txtQuyCach = new JTextField();
//        txtQuyCach.setBounds(450, 245, 265, 30);
//        panelLeft.add(lblQuyCach);
//        panelLeft.add(txtQuyCach);
//
//        lblDonViTinh = new JLabel("Đơn vị tính");
//        lblDonViTinh.setBounds(450, 280, 100, 30);
//        txtDonViTinh = new JTextField();
//        txtDonViTinh.setBounds(450, 310, 265, 30);
//        panelLeft.add(lblDonViTinh);
//        panelLeft.add(txtDonViTinh);

        panelright = new JPanel();
        panelright.setBounds(735, 5, 210, 655);
        panelright.setBackground(Color.WHITE);
        panelright.setLayout(null);
        add(panelright);
        // Thông tin phiếu nhập
        // Thông tin phiếu nhập

        maPN = new JLabel("Mã phiếu nhập");
        maPN.setBounds(10, 10, 180, 30);
        panelright.add(maPN);
        nhapMaPN = new JTextField();
        nhapMaPN.setBounds(10, 40, 180, 30);
        nhapMaPN.setEditable(false);
        panelright.add(nhapMaPN);

        nvNhap = new JLabel("Nhân viên nhập");
        nvNhap.setBounds(10, 90, 180, 30);
        panelright.add(nvNhap);
        nhapNVNhap = new JTextField();
        nhapNVNhap.setBounds(10, 120, 180, 30);
        nhapNVNhap.setEditable(false);
        panelright.add(nhapNVNhap);

        ncc = new JLabel("Nhà cung cấp");
        ncc.setBounds(10, 170, 180, 30);
        panelright.add(ncc);
        nhapNCC = new JComboBox<>();
        nhapNCC.setBounds(10, 200, 180, 30);
        // Tổng tiền
        // Tổng tiền (sát mép dưới)
        // Tổng tiền (sát mép dưới)
        lblTongTien = new JLabel("Tổng tiền:");
        lblTongTien.setBounds(10, 480, 180, 30);
        lblTongTien.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
        lblTongTien.setForeground(Color.BLACK);
        panelright.add(lblTongTien);

// Hiển thị số tiền bằng JLabel (nền trắng, bo góc, căn phải)
        txtTongTien = new JLabel("0 ₫", SwingConstants.RIGHT);
        txtTongTien.setBounds(10, 510, 180, 40);
        txtTongTien.setOpaque(true);
        txtTongTien.setBackground(new Color(240, 240, 240)); // Màu nền nhẹ
        txtTongTien.setFont(new Font("JETBRAINS MONO", Font.BOLD, 16));
        txtTongTien.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        txtTongTien.setForeground(Color.RED);
        txtTongTien.setHorizontalAlignment(SwingConstants.CENTER);
        panelright.add(txtTongTien);

// Nút Nhập hàng (màu xanh, bo góc, hover đổi màu)
        btnNhapHang = new JButton("Sửa ");
        btnNhapHang.setBounds(100, 560, 100, 35);
        btnNhapHang.setFont(new Font("JETBRAINS MONO", Font.BOLD, 11));
        btnNhapHang.setIcon(icon_sua);
        btnNhapHang.setIconTextGap(5);
        btnNhapHang.setBackground(Color.BLUE); // Xanh dương đậm
        btnNhapHang.setForeground(Color.WHITE);
        btnNhapHang.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnHuyBo = new JButton("Hủy bỏ");
        btnHuyBo.setBounds(10, 560, 90, 35);
        icon_huybo = new FlatSVGIcon("icon/forbid.svg",15,15);
        btnHuyBo.setIcon(icon_huybo);
        btnHuyBo.setIconTextGap(5);
        btnHuyBo.setBackground(new Color(103,116,132));
        btnHuyBo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHuyBo.setForeground(Color.WHITE);
        btnHuyBo.setFont(new Font("JETBRAINS MONO", Font.BOLD, 11));


        panelright.add(btnHuyBo);
        panelright.add(btnNhapHang);
        // Thêm vào sau khi tạo bảng sản phẩm
        tableSanPham.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tableSanPham. getSelectedRow();
            if (selectedRow != -1) { // Kiểm tra có chọn dòng nào không
                String maSP = String.valueOf(tableModel.getValueAt(selectedRow, 0));
                String tenSP = (String) tableModel.getValueAt(selectedRow, 1);

                hienthi_masp.setText(maSP);
                hienthi_tensp.setText(tenSP);
            }
        });
        // 🆕 Tạo bảng chứa danh sách sản phẩm đã chọn
        String[] columnNamesNhap = {"Mã SP", "Tên SP", "Số lượng", "Giá nhập","Thành Tiền"};
        modelDanhSachNhap = new DefaultTableModel(columnNamesNhap, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable danhSachSanPhamNhap = new JTable(modelDanhSachNhap);
        JScrollPane scrollPaneNhap = new JScrollPane(danhSachSanPhamNhap);
        scrollPaneNhap.setBounds(10, 400, 705, 240);
        panelLeft.add(scrollPaneNhap);
        danhSachSanPhamNhap.setShowGrid(true);
        danhSachSanPhamNhap.setGridColor(Color.LIGHT_GRAY);
        danhSachSanPhamNhap.setDefaultEditor(Object.class, null);
        danhSachSanPhamNhap.setRowHeight(30);
        danhSachSanPhamNhap.setFocusable(false);
        danhSachSanPhamNhap.setSelectionBackground(new Color(204, 229, 255));
        danhSachSanPhamNhap.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 13));
        danhSachSanPhamNhap.setIntercellSpacing(new Dimension(0, 0));
        for (int i = 0; i < danhSachSanPhamNhap.getColumnCount(); i++) {
            danhSachSanPhamNhap.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        headerdanhSachSanPhamNhap = danhSachSanPhamNhap.getTableHeader();
        headerdanhSachSanPhamNhap.setForeground(new Color(105, 105, 105, 255));
        headerdanhSachSanPhamNhap.setBackground(new Color(245, 244, 245, 255));
        headerdanhSachSanPhamNhap.setFont(new Font("JETBRAINS MONO", Font.BOLD, 13));
        headerdanhSachSanPhamNhap.setPreferredSize(new Dimension(headerdanhSachSanPhamNhap.getWidth(), 35));
// 🆕 Thêm sự kiện cho nút "Thêm Sản Phẩm"
        themSP.addActionListener(e -> {
            String maSP = hienthi_masp.getText();
            String tenSP = hienthi_tensp.getText();
            String soLuong = nhapsoluong.getText();
            String giaNhap = nhapgiaban.getText();
            // Kiểm tra nếu các ô không được để trống
            if (maSP.isEmpty() && tenSP.isEmpty()){
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm","Lỗi", JOptionPane.WARNING_MESSAGE);
                hienthi_masp.setBorder(BorderFactory.createLineBorder(Color.RED));
                hienthi_tensp.setBorder(BorderFactory.createLineBorder(Color.RED));
                return;
            }
            else{
                hienthi_masp.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                hienthi_tensp.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            }
            if(soLuong.isEmpty()){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng","Lỗi", JOptionPane.WARNING_MESSAGE);
                nhapsoluong.setBorder(BorderFactory.createLineBorder(Color.RED));
                nhapsoluong.requestFocus();
                return;
            }
            else{
                nhapsoluong.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            }
            if(giaNhap.isEmpty()){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập giá nhập","Lỗi", JOptionPane.WARNING_MESSAGE);
                nhapgiaban.setBorder(BorderFactory.createLineBorder(Color.RED));
                nhapgiaban.requestFocus();
                return;
            }
            else{
                nhapgiaban.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            }
            int sl = Integer.parseInt(soLuong);
            long gia = ThemPN.parseTien(giaNhap); // Bỏ định dạng ₫, .
            if (sl <= 0) {
                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                nhapsoluong.setBorder(BorderFactory.createLineBorder(Color.RED));
                nhapsoluong.requestFocus();
                return;
            }
            else{
                nhapsoluong.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));

            }
            if (gia <= 0) {
                JOptionPane.showMessageDialog(null, "Giá bán phải lớn hơn 0!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                nhapgiaban.setBorder(BorderFactory.createLineBorder(Color.RED));
                nhapgiaban.requestFocus();
                return;
            }
            else{
                nhapgiaban.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            }
            long thanhTien = sl * gia;
            for (int i = 0; i < modelDanhSachNhap.getRowCount(); i++) {
                String maSPTrongBang = modelDanhSachNhap.getValueAt(i, 0).toString();
                if (maSPTrongBang.equals(maSP)) {
                    JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại trong bảng", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            // Thêm vào bảng danh sách nhập hàng
            modelDanhSachNhap.addRow(new Object[]{maSP, tenSP, sl, giaNhap,ThemPN.formatVND(thanhTien)});
            updateTotal(modelDanhSachNhap, txtTongTien);

            hienthi_masp.setText("");
            hienthi_tensp.setText("");
            nhapsoluong.setText("");
            nhapgiaban.setText("");
        });

        btnHuyBoSP.addActionListener( e ->{
            int selectRow = danhSachSanPhamNhap.getSelectedRow();
            if(selectRow == -1){
                JOptionPane.showMessageDialog(
                        null,
                        "Vui lòng chọn sản phẩm cần hủy bỏ!",
                        "Thông báo",
                        JOptionPane.WARNING_MESSAGE
                );
            }
            else{
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Bạn có chắc muốn hủy bỏ sản phẩm đã thêm?",
                        "Xác nhận hủy bỏ",
                        JOptionPane.YES_NO_OPTION
                );
                if(confirm == JOptionPane.YES_OPTION){
                    modelDanhSachNhap.removeRow(selectRow);
                    updateTotal(modelDanhSachNhap, txtTongTien);

                }
            }
        });
        btnHuyBo.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc muốn hủy bỏ?",
                    "Xác nhận hủy",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                resetForm();
                phieuNhap.showTrangChinh(); // Gọi hàm trong MainFrame
                modelDanhSachNhap.setRowCount(0);
                updateTotal(modelDanhSachNhap, txtTongTien);
            }
        });
        btnSuaSP.addActionListener(e -> {
            int selectedRow = danhSachSanPhamNhap.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lấy dữ liệu cũ từ dòng đã chọn
            String maSP = modelDanhSachNhap.getValueAt(selectedRow,0).toString();
            String tenSP = (String) modelDanhSachNhap.getValueAt(selectedRow, 1);
            String soLuong = modelDanhSachNhap.getValueAt(selectedRow, 2).toString();
            String giaNhap = modelDanhSachNhap.getValueAt(selectedRow, 3).toString();

            // Tạo JDialog
            JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(panelLeft), "Sửa sản phẩm", true);
            dialog.setSize(400, 300);
            dialog.setLocationRelativeTo(null);
            dialog.setLayout(null);

            // Label + text field
            // Font dùng chung
            Font labelFont = new Font("JETBRAINS MONO", Font.BOLD, 11);
            Font fieldFont = new Font("JETBRAINS MONO", Font.PLAIN, 11);

// Mã SP (không chỉnh sửa)
            JLabel lblMaSP = new JLabel("Mã sản phẩm:");
            lblMaSP.setFont(labelFont);
            lblMaSP.setBounds(30, 20, 100, 25);
            JTextField txtMaSP = new JTextField(maSP);
            txtMaSP.setFont(fieldFont);
            txtMaSP.setBounds(150, 20, 200, 25);
            txtMaSP.setEditable(false);
            txtMaSP.setBackground(new Color(240, 240, 240)); // Màu xám nhẹ

// Tên SP (không chỉnh sửa)
            JLabel lblTenSP = new JLabel("Tên sản phẩm:");
            lblTenSP.setFont(labelFont);
            lblTenSP.setBounds(30, 55, 100, 25);
            JTextField txtTenSP = new JTextField(tenSP);
            txtTenSP.setFont(fieldFont);
            txtTenSP.setBounds(150, 55, 200, 25);
            txtTenSP.setEditable(false);
            txtTenSP.setBackground(new Color(240, 240, 240));

// Các field có thể chỉnh sửa
            JLabel lblSoLuong = new JLabel("Số lượng:");
            lblSoLuong.setFont(labelFont);
            lblSoLuong.setBounds(30, 90, 100, 25);
            JTextField txtSoLuong = new JTextField(soLuong);
            ((AbstractDocument) txtSoLuong.getDocument()).setDocumentFilter(new OnlyDigitFilter());
            txtSoLuong.setFont(fieldFont);
            txtSoLuong.setBounds(150, 90, 200, 25);

            JLabel lblGiaNhap = new JLabel("Giá nhập:");
            lblGiaNhap.setFont(labelFont);
            lblGiaNhap.setBounds(30, 125, 100, 25);
            JTextField txtGiaNhap = new JTextField(giaNhap);
            ((AbstractDocument) txtGiaNhap.getDocument()).setDocumentFilter(new NumberOnlyFilter());
            txtGiaNhap.setFont(fieldFont);
            txtGiaNhap.setBounds(150, 125, 200, 25);

// Nút lưu
            JButton btnLuu = new JButton("Lưu");
            btnLuu.setBounds(100, 210, 90, 30);
            btnLuu.setBackground(new Color(89, 168, 105));
            btnLuu.setIcon(icon_them);
            btnLuu.setIconTextGap(7);
            btnLuu.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 12));
            btnLuu.setForeground(Color.WHITE);
            btnLuu.setFocusPainted(false);

// Nút hủy
            JButton btnHuy = new JButton("Hủy");
            btnHuy.setBounds(200, 210, 90, 30);
            btnHuy.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 12));
            btnHuy.setIcon(icon_huybo);
            btnHuy.setIconTextGap(7);
            btnHuy.setBackground(new Color(103,116,132));
            btnHuy.setForeground(Color.WHITE);
            btnHuy.setFocusPainted(false);

            // Thêm vào dialog
            dialog.add(lblMaSP); dialog.add(txtMaSP);
            dialog.add(lblTenSP); dialog.add(txtTenSP);
            dialog.add(lblSoLuong); dialog.add(txtSoLuong);
            dialog.add(lblGiaNhap); dialog.add(txtGiaNhap);
            dialog.add(btnLuu); dialog.add(btnHuy);

            // Sự kiện nút Hủy
            btnHuy.addActionListener(ev -> dialog.dispose());

            // Sự kiện nút Lưu
            btnLuu.addActionListener(ev -> {
                    String newSoLuong = txtSoLuong.getText();
                    String newGiaNhap = txtGiaNhap.getText();
                    if(newSoLuong.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng","Lỗi", JOptionPane.WARNING_MESSAGE);
                        txtSoLuong.setBorder(BorderFactory.createLineBorder(Color.RED));
                        txtSoLuong.requestFocus();
                        return;
                    }
                    else{
                        txtSoLuong.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                    }
                    if(newGiaNhap.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập giá nhập","Lỗi", JOptionPane.WARNING_MESSAGE);
                        txtGiaNhap.setBorder(BorderFactory.createLineBorder(Color.RED));
                        txtGiaNhap.requestFocus();
                        return;
                    }
                    else{
                        txtGiaNhap.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                    }

                    int sl = Integer.parseInt(txtSoLuong.getText());
                    long gia = ThemPN.parseTien(newGiaNhap);
                    if (sl <= 0) {
                        JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                        txtSoLuong.setBorder(BorderFactory.createLineBorder(Color.RED));
                        txtSoLuong.requestFocus();
                        return;
                    }
                    else{
                        txtSoLuong.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                    }
                    if (gia <= 0) {
                        JOptionPane.showMessageDialog(null, "Giá bán phải lớn hơn 0!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                        txtGiaNhap.setBorder(BorderFactory.createLineBorder(Color.RED));
                        txtGiaNhap.requestFocus();
                        return;
                    }
                    else{
                        txtGiaNhap.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                    }
                    long thanhtien = sl* gia;
                    String thanhTienFormatted = ThemPN.formatVND(thanhtien);
                    modelDanhSachNhap.setValueAt(sl, selectedRow, 2);
                    modelDanhSachNhap.setValueAt(newGiaNhap, selectedRow, 3);
                    modelDanhSachNhap.setValueAt(thanhTienFormatted, selectedRow, 4);
                    // Nếu cần cập nhật tổng tiền:
                    updateTotal(modelDanhSachNhap,txtTongTien);

                    dialog.dispose();
            });

            dialog.setVisible(true);
        });
            btnNhapHang.addActionListener(e -> {
               int maPN = Integer.parseInt(nhapMaPN.getText());
                String selectedName = (String) nhapNCC.getSelectedItem();
                if (selectedName == null || !nccMap.containsKey(selectedName)) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp!");
                    return;
                }
                if (modelDanhSachNhap.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất một sản phẩm!");
                    return;
                }
                NhaCungCapEntity selectedNCC = nccMap.get(selectedName);
                int maNCC = selectedNCC.getMaNCC();
                long tongTien = ThemPN.parseTien(txtTongTien.getText()); // parse ngược lại số
                int maNV = Session.curUser.getMaNguoiDung(); // Lấy nhân viên hiện tại (nếu có biến lưu)
                PhieuNhapEntity pn = new PhieuNhapEntity();
                pn.setMaPN(maPN);
                pn.setMaNCC(maNCC);
                pn.setMaNV(maNV);
                pn.setTongGiaTri(tongTien);

                int result = new PhieuNhapDAO().sua(pn);

                if (result > 0) {
                    ChiTietPhieuNhapDAO ctDAO = new ChiTietPhieuNhapDAO();
                    ctDAO.xoaTheoMaPhieuNhap(maPN);
                    for (int i = 0; i < modelDanhSachNhap.getRowCount(); i++) {
                        int maSP = Integer.parseInt(modelDanhSachNhap.getValueAt(i, 0).toString());
                        int soLuong = Integer.parseInt(modelDanhSachNhap.getValueAt(i, 2).toString());
                        String giaStr = modelDanhSachNhap.getValueAt(i, 3).toString();
                        long giaNhap = ThemPN.parseTien(giaStr); // parse định dạng tiền tệ
                        long thanhTien = soLuong * giaNhap;

                        ChiTietPhieuNhapEntity ct = new ChiTietPhieuNhapEntity();
                        ct.setMaPN(maPN);
                        ct.setMaSP(maSP);
                        ct.setSoLuong(soLuong);
                        ct.setGiaNhap(giaNhap);
                        ct.setThanhTien(thanhTien);

                        ctDAO.them(ct); // thêm vào db
                    }
                    JOptionPane.showMessageDialog(null, "Cập nhật phiếu nhập thành công!");
                    phieuNhap.reloadDAO();
                    phieuNhap.showTrangChinh();
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
                }


            });
            panelright.add(nhapNCC);
        }
    public void resetForm() {
        // Xóa nội dung các ô nhập
        hienthi_masp.setText("");
        hienthi_tensp.setText("");
        nhapsoluong.setText("");
        nhapgiaban.setText("");

        // Reset viền về mặc định
        Border defaultBorder = UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border");
        hienthi_masp.setBorder(defaultBorder);
        hienthi_tensp.setBorder(defaultBorder);
        nhapgiaban.setBorder(defaultBorder);
        nhapsoluong.setBorder(defaultBorder);
        nhapNCC.setBorder(defaultBorder);

        // Reset combobox và dữ liệu bảng
        updateTotal(modelDanhSachNhap, txtTongTien);

    }
    public void loadDataToTableSanPham() {
        SanPhamDAO dao = new SanPhamDAO();
        java.util.List<SanPhamEntity> list = dao.showlist();

        DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
        model.setRowCount(0); // clear dữ liệu cũ

        for (SanPhamEntity sp : list) {
            if (sp.isTrangThai() ) {
                model.addRow(new Object[]{
                        sp.getId(),
                        sp.getTenSanPham(),
                });
            }
        }
    }
    private void loadNhaCungCapCombobox(int maNCCCu) {
        NhaCungCapDAO dao = new NhaCungCapDAO();
        java.util.List<NhaCungCapEntity> list = dao.showlist();
        nhapNCC.removeAllItems();
        nccMap.clear();

        nhapNCC.addItem("— Chọn nhà cung cấp —");

        String tenHienThi = "";

        for (NhaCungCapEntity ncc : list) {
            String displayName = ncc.getTenNCC() + " (ID: " + ncc.getMaNCC() + ")";
            nhapNCC.addItem(displayName);
            nccMap.put(displayName, ncc);

            if (ncc.getMaNCC() == maNCCCu) {
                tenHienThi = displayName;
            }
        }

        if (!tenHienThi.isEmpty()) {
            nhapNCC.setSelectedItem(tenHienThi);
        }
    }

    public void loadDatatoTablePhieuNhap(List <ChiTietPhieuNhapFullEntity> list){
        if (list == null || list.isEmpty()) return;
        ChiTietPhieuNhapFullEntity first = list.get(0);
        int maNCC = first.getMaNCC();
        int maPN = first.getMaPN();
        loadNhaCungCapCombobox(maNCC);
        nhapMaPN.setText(String.valueOf(maPN));
        nhapNVNhap.setText(first.getTenNguoiLap());
        nhapNCC.setSelectedItem(first.getTenNCC());
        modelDanhSachNhap.setRowCount(0);
        for(ChiTietPhieuNhapFullEntity sp : list){
            String giaFormatted = phieuNhap.formatCurrency((long) sp.getGia());
            String thanhTienFormatted = phieuNhap.formatCurrency((long) sp.getThanhtien());
            modelDanhSachNhap.addRow(new Object[]{
                    sp.getMasp(),
                    sp.getTenSP(),
                    sp.getSoLuong(),
                    giaFormatted,
                    thanhTienFormatted,


            });
        }
        updateTotal(modelDanhSachNhap,txtTongTien);

    }

    private void updateTotal(DefaultTableModel model, JLabel txtTongTien) {
        long total = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            int soLuong = 0;
            long giaNhap = 0;

            try {
                soLuong = Integer.parseInt(model.getValueAt(i, 2).toString().replaceAll("[^0-9]", ""));
                String giaNhapStr = model.getValueAt(i, 3).toString().replaceAll("[^0-9]", ""); // Xóa ký tự không phải số
                giaNhap = giaNhapStr.isEmpty() ? 0 : Long.parseLong(giaNhapStr);
            } catch (Exception e) {
                // Nếu có lỗi parse, bỏ qua dòng đó
                continue;
            }

            total += soLuong * giaNhap;
        }

        // Format: dấu chấm ngăn cách + thêm " ₫"
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formatted = formatter.format(total).replace(",", ".") + " ₫";
        txtTongTien.setText(formatted);
    }

}