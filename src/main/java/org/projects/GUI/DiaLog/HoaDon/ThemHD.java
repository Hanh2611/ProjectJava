package org.projects.GUI.DiaLog.HoaDon;


import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.BUS.HoaDonBUS;
import org.projects.BUS.KhachHangBUS;
import org.projects.BUS.SanPhamBus;
import org.projects.GUI.Components.OnlyDigitFilter;
import org.projects.GUI.DiaLog.PhieuNhap.ThemPN;
import org.projects.GUI.Panel.HoaDon;
import org.projects.GUI.utils.Session;
import org.projects.entity.HoaDonEntity;
import org.projects.entity.KhachHangEntity;
import org.projects.entity.SanPhamEntity;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ThemHD extends JPanel {
    JTextField timKiem, hienthi_masp, hienthi_tensp, nhapsoluong, nhapgiaban,
            txtQuyCach, txtKhachHang, nhapMaPN, nhapNVNhap,txtNgayTao,tennvnhap,txtSoLuongTon;
    JLabel lblTitle,masp, tensp, soluong, giaban, lblQuyCach, lblKhachHang, maPN, nvNhap,
            ncc, lblTongTien, txtTongTien, lblNgayTao,lblSoLuongTon;
    JPanel panelLeft, panelright;
    JButton themSP, btnNhapHang, btnHuyBo, btnHuyBoSP, btnThemKH,btnSuaSP,btnThanhToan;
    JTable tableSanPham, danhSachSanPhamNhap;
    JTableHeader header, headerdanhSachSanPhamNhap;
    JScrollPane scrollPane, scrollPaneNhap;
    FlatSVGIcon icon_themsp,icon_bosp,icon_suasp,icon_dong,icon_tao;
    DefaultTableModel modelDanhSachNhap;
    private HoaDon hoaDon;
    private int maKhachHangDuocChon = -1; // trong class
    private final SanPhamBus sanPhamBus;


    public ThemHD(HoaDon hoaDon) {
        setPreferredSize(new Dimension(940, 650));
        setOpaque(false);
        setLayout(null);
        this.hoaDon = hoaDon;
        this.sanPhamBus = new SanPhamBus();
        init();
        loadDataToTableSanPham();
        loadMaHoaDon();
        loadNgayTao(); // thêm dòng này
    }

    public void init() {
        lblTitle = new JLabel("Hóa đơn / Tạo mới hóa đơn");
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
        String[] columnNames = {"Mã SP", "Tên SP","Giá bán","Tồn"};

            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

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
        icon_themsp = new FlatSVGIcon("icon/add.svg",15,15);
        themSP.setIcon(icon_themsp);
        themSP.setIconTextGap(6);
        themSP.setBounds(10, 355, 220, 30);
        themSP.setBackground(new Color(89, 168, 105, 255));
        themSP.setForeground(Color.WHITE);
        themSP.setCursor(new Cursor(Cursor.HAND_CURSOR));
        themSP.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));

        btnHuyBoSP = new JButton("Bỏ sản phẩm");
        btnHuyBoSP.setBounds(240, 355, 220, 30);
        icon_bosp = new FlatSVGIcon("icon/trash.svg",15,15);
        btnHuyBoSP.setIcon(icon_bosp);
        btnHuyBoSP.setIconTextGap(6);
        btnHuyBoSP.setBackground(Color.RED);
        btnHuyBoSP.setForeground(Color.WHITE);
        btnHuyBoSP.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHuyBoSP.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));

        btnSuaSP = new JButton("Sửa sản phẩm");
        btnSuaSP.setBounds(470, 355, 220, 30);
        icon_suasp = new FlatSVGIcon("icon/content-writing.svg",15,15);
        btnSuaSP.setIcon(icon_suasp);
        btnSuaSP.setIconTextGap(6);
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

        giaban = new JLabel("Giá bán");
        giaban.setBounds(450, 210, 100, 30);
        nhapgiaban = new JTextField();
        nhapgiaban.setBounds(450, 240, 130, 30);
        nhapgiaban.setEditable(false);
        panelLeft.add(giaban);
        panelLeft.add(nhapgiaban);

        lblSoLuongTon = new JLabel("Số lượng tồn");
        lblSoLuongTon.setBounds(590,210, 100, 30);
        txtSoLuongTon = new JTextField();
        txtSoLuongTon.setBounds(590,240, 130, 30);
        txtSoLuongTon.setEditable(false);
        panelLeft.add(lblSoLuongTon);
        panelLeft.add(txtSoLuongTon);

        soluong = new JLabel("Số lượng");
        soluong.setBounds(450, 275, 100, 30);
        nhapsoluong = new JTextField();
        nhapsoluong.setBounds(450, 305, 265, 30);
        panelLeft.add(soluong);
        panelLeft.add(nhapsoluong);
        ((AbstractDocument) nhapsoluong.getDocument()).setDocumentFilter(new OnlyDigitFilter());


        lblKhachHang = new JLabel("Khách hàng");
        lblKhachHang.setBounds(10, 330, 100, 30);
        txtKhachHang = new JTextField();
        txtKhachHang.setBounds(10, 360, 140, 30);
        txtKhachHang.setEditable(false);

        btnThemKH = new JButton("+");
        btnThemKH.setBounds(155, 360, 35, 30);


        panelright = new JPanel();
        panelright.setBounds(735, 5, 210, 655);
        panelright.setBackground(Color.WHITE);
        panelright.setLayout(null);

        panelright.add(btnThemKH);
        panelright.add(lblKhachHang);
        panelright.add(txtKhachHang);
        add(panelright);
        // Thông tin phiếu nhập
        // Thông tin phiếu nhập

        maPN = new JLabel("Mã hóa đơn");
        maPN.setBounds(10, 10, 180, 30);
        panelright.add(maPN);
        nhapMaPN = new JTextField();
        nhapMaPN.setBounds(10, 40, 180, 30);
        nhapMaPN.setEditable(false);
        panelright.add(nhapMaPN);

        nvNhap = new JLabel("Mã nhân viên nhập");
        nvNhap.setBounds(10, 90, 180, 30);
        panelright.add(nvNhap);
        nhapNVNhap = new JTextField();
        nhapNVNhap.setBounds(10, 120, 180, 30);
        nhapNVNhap.setEditable(false);
        panelright.add(nhapNVNhap);

        ncc = new JLabel("Tên nhân viên nhập");
        ncc.setBounds(10, 170, 180, 30);
        tennvnhap = new JTextField();
        tennvnhap.setEditable(false);
        tennvnhap.setBounds(10, 200, 180, 30);
        panelright.add(tennvnhap);
        panelright.add(ncc);

        lblNgayTao = new JLabel("Ngày tạo");
        lblNgayTao.setBounds(10, 250, 100, 30);
        txtNgayTao = new JTextField();
        txtNgayTao.setEditable(false);
        txtNgayTao.setBounds(10, 280, 180, 30);
        panelright.add(lblNgayTao);
        panelright.add(txtNgayTao);
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
        btnNhapHang = new JButton("Tạo");
        btnNhapHang.setBounds(100, 560, 100, 35);
        icon_tao = new FlatSVGIcon("icon/import.svg",15,15);
        btnNhapHang.setIcon(icon_tao);
        btnNhapHang.setIconTextGap(5);
        btnNhapHang.setFont(new Font("JETBRAINS MONO", Font.BOLD, 11));
        btnNhapHang.setBackground(Color.BLUE); // Xanh dương đậm
        btnNhapHang.setForeground(Color.WHITE);
        btnNhapHang.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnHuyBo = new JButton("Đóng");
        btnHuyBo.setBounds(10, 560, 90, 35);
        icon_dong = new FlatSVGIcon("icon/forbid.svg",15,15);
        btnHuyBo.setIcon(icon_dong);
        btnHuyBo.setIconTextGap(5);
        btnHuyBo.setBackground(new Color(103,116,132));
        btnHuyBo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHuyBo.setForeground(Color.WHITE);
        btnHuyBo.setFont(new Font("JETBRAINS MONO", Font.BOLD, 11));

        panelright.add(btnHuyBo);
        panelright.add(btnNhapHang);
        // Thêm vào sau khi tạo bảng sản phẩm
        tableSanPham.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tableSanPham.getSelectedRow();
            if (selectedRow != -1) { // Kiểm tra có chọn dòng nào không
                String maSP = tableModel.getValueAt(selectedRow, 0).toString();
                String tenSP = (String) tableModel.getValueAt(selectedRow, 1);
                String giaban = tableModel.getValueAt(selectedRow, 2).toString();
                String soluongton = tableModel.getValueAt(selectedRow, 3).toString();
                hienthi_masp.setText(maSP);
                hienthi_tensp.setText(tenSP);
                nhapgiaban.setText(giaban);
                txtSoLuongTon.setText(soluongton);

            }
        });
        // 🆕 Tạo bảng chứa danh sách sản phẩm đã chọn
        String[] columnNamesNhap = {"Mã SP", "Tên SP", "Số lượng", "Giá bán","Thành tiền"};
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
        btnThemKH.addActionListener(e -> {
            showChonKhachHangDialog();
        });
        timKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                timKiemSanPham();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                timKiemSanPham();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                timKiemSanPham();
            }

            private void timKiemSanPham() {
                String keyword = timKiem.getText().trim().toLowerCase();
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableSanPham.getModel());
                tableSanPham.setRowSorter(sorter);
                if (keyword.isEmpty() || keyword.equals("tìm kiếm mã sản phẩm, tên sản phẩm")) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(keyword)));
                }
            }
        });

// 🆕 Thêm sự kiện cho nút "Thêm Sản Phẩm"
        themSP.addActionListener(e -> {
            String maSP = hienthi_masp.getText();
            String tenSP = hienthi_tensp.getText();
            String giaban = nhapgiaban.getText();
            String soLuong = nhapsoluong.getText();
            String soluongton = txtSoLuongTon.getText();
            if (maSP.isEmpty() || tenSP.isEmpty() || giaban.isEmpty() || soluongton.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm","Lỗi", JOptionPane.WARNING_MESSAGE);
                hienthi_masp.setBorder(BorderFactory.createLineBorder(Color.RED));
                hienthi_tensp.setBorder(BorderFactory.createLineBorder(Color.RED));
                nhapgiaban.setBorder(BorderFactory.createLineBorder(Color.RED));
                txtSoLuongTon.setBorder(BorderFactory.createLineBorder(Color.RED));
                return;
            }
            else{
                hienthi_masp.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                hienthi_tensp.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                nhapgiaban.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                txtSoLuongTon.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            }
            if(soLuong.isEmpty()){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng","Lỗi", JOptionPane.WARNING_MESSAGE);
                nhapsoluong.setBorder(BorderFactory.createLineBorder(Color.RED));
                return;
            }
            else{
                nhapsoluong.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            }

            int soLuongInt = Integer.parseInt(soLuong);
            double soLuongTonInt = Double.parseDouble(soluongton);;
            if(soLuongInt <= 0){
                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0","Lỗi", JOptionPane.WARNING_MESSAGE);
                nhapsoluong.setBorder(BorderFactory.createLineBorder(Color.RED));
                return;
            }else{
                nhapsoluong.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            }

            if(soLuongInt > soLuongTonInt){
                JOptionPane.showMessageDialog(null, "Số lượng bán vượt quá tồn trong kho","Lỗi", JOptionPane.WARNING_MESSAGE);
                nhapsoluong.setBorder(BorderFactory.createLineBorder(Color.RED));
                return;
            }
            else{
                nhapsoluong.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));

            }
            long giabanformatted = ThemPN.parseTien(giaban);
            long thanhtien = soLuongInt*giabanformatted;
            String thanhtienformatted = ThemPN.formatVND(thanhtien);
            for (int i = 0; i < modelDanhSachNhap.getRowCount(); i++) {
                String maSPTrongBang = modelDanhSachNhap.getValueAt(i, 0).toString();
                if (maSPTrongBang.equals(maSP)) {
                    JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại trong bảng", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            // Thêm vào bảng danh sách nhập hàng
            modelDanhSachNhap.addRow(new Object[]{maSP, tenSP, soLuongInt,giaban,thanhtienformatted});
            updateTotal(modelDanhSachNhap, txtTongTien);


            hienthi_masp.setText("");
            hienthi_tensp.setText("");
            nhapsoluong.setText("");
            nhapgiaban.setText("");
            txtSoLuongTon.setText("");
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
                hoaDon.showTrangChinh(); // Gọi hàm trong MainFrame
                resetForm();
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
            String giaban = modelDanhSachNhap.getValueAt(selectedRow, 3).toString();

            String tonKho = "0";
            for (int i = 0; i < tableSanPham.getRowCount(); i++) {
                String maSPGoc = tableSanPham.getValueAt(i, 0).toString(); // Cột 0 là mã sản phẩm
                if (maSP.equals(maSPGoc)) {
                    tonKho = (tableSanPham.getValueAt(i, 3).toString()); // Cột 2 là số lượng tồn
                    break;
                }
            }
            // Tạo JDialog
            JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(panelLeft), "Sửa sản phẩm", true);
            dialog.setSize(400, 350);
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
            JLabel lblSoLuong = new JLabel("Giá bán:");
            lblSoLuong.setFont(labelFont);
            lblSoLuong.setBounds(30, 90, 100, 25);
            JTextField txtSoLuong = new JTextField(giaban);
            txtSoLuong.setEditable(Boolean.FALSE);
            txtSoLuong.setFont(fieldFont);
            txtSoLuong.setBounds(150, 90, 200, 25);

            JLabel lblTonKho = new JLabel("Tồn kho:");
            lblTonKho.setFont(labelFont);
            lblTonKho.setBounds(30, 125, 100, 25);
            JTextField txtTonKho = new JTextField(tonKho);
            txtTonKho.setFont(fieldFont);
            txtTonKho.setBounds(150, 125, 200, 25);
            txtTonKho.setEditable(false);
            txtTonKho.setBackground(new Color(240, 240, 240));
            dialog.add(lblTonKho);
            dialog.add(txtTonKho);

            JLabel lblGiaNhap = new JLabel("Số lượng:");
            lblGiaNhap.setFont(labelFont);
            lblGiaNhap.setBounds(30, 160, 100, 25);
            JTextField txtGiaNhap = new JTextField(soLuong);
            ((AbstractDocument) txtGiaNhap.getDocument()).setDocumentFilter(new OnlyDigitFilter());
            txtGiaNhap.setFont(fieldFont);
            txtGiaNhap.setBounds(150, 160, 200, 25);

// Nút lưu
            JButton btnLuu = new JButton("Lưu");
            btnLuu.setBounds(100, 260, 90, 30);
            btnLuu.setBackground(new Color(89, 168, 105));
            btnLuu.setIcon(icon_themsp);
            btnLuu.setIconTextGap(5);
            btnLuu.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 12));
            btnLuu.setForeground(Color.WHITE);
            btnLuu.setFocusPainted(false);

// Nút hủy
            JButton btnHuy = new JButton("Hủy");
            btnHuy.setBounds(200, 260, 90, 30);
            btnHuy.setIcon(icon_dong);
            btnHuy.setIconTextGap(5);
            btnHuy.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 12));
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
                    String newSoLuong = txtGiaNhap.getText();
                    if(newSoLuong.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng","Lỗi", JOptionPane.WARNING_MESSAGE);
                        txtGiaNhap.setBorder(BorderFactory.createLineBorder(Color.RED));
                        txtGiaNhap.requestFocus();
                    }
                    else{
                        txtGiaNhap.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                    }
                    int sl = Integer.parseInt(txtGiaNhap.getText());
                    if (sl <= 0) {
                        JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                        txtGiaNhap.setBorder(BorderFactory.createLineBorder(Color.RED));
                        txtGiaNhap.requestFocus();
                        return;
                    }
                    else{
                        txtGiaNhap.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                    }
                    double soluongton = Double.parseDouble(txtTonKho.getText());
                    if(sl > soluongton){
                        JOptionPane.showMessageDialog(null, "Số lượng bán vượt tồn trong kho", "Lỗi", JOptionPane.WARNING_MESSAGE);
                        txtGiaNhap.setBorder(BorderFactory.createLineBorder(Color.RED));
                        txtGiaNhap.requestFocus();
                        return;
                    }
                    else{
                        txtGiaNhap.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));

                    }
                    modelDanhSachNhap.setValueAt(sl, selectedRow, 2);
                    // Nếu cần cập nhật tổng tiền:
                    updateTotal(modelDanhSachNhap,txtTongTien);

                    dialog.dispose();
            });

            dialog.setVisible(true);
        });
            btnNhapHang.addActionListener(ev -> {
                if (maKhachHangDuocChon == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng trước khi tạo hóa đơn!");
                    txtKhachHang.setBorder(BorderFactory.createLineBorder(Color.RED));
                    return;
                }
                else{
                    txtKhachHang.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));

                }
                if (modelDanhSachNhap.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất một sản phẩm!");
                    return;
                }
                int maNV = Session.curUser.getMaNguoiDung();
                long tongTien = ThemPN.parseTien(txtTongTien.getText());
                LocalDateTime nowVN = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
                HoaDonEntity hoaDonEntity = new HoaDonEntity();
                hoaDonEntity.setNgayTao(Timestamp.valueOf(nowVN));
                hoaDonEntity.setMaNV(maNV);
                hoaDonEntity.setMaKh(maKhachHangDuocChon);
                hoaDonEntity.setTongGiaTri(tongTien);
                hoaDonEntity.setTrangThai("chua_thanh_toan");

                HoaDonBUS hoaDonBUS = new HoaDonBUS();
                boolean success = hoaDonBUS.themHoaDon(hoaDonEntity, modelDanhSachNhap);

                if (!success) {
                    JOptionPane.showMessageDialog(null, "Tạo hóa đơn thất bại!");
                    return;
                }

                JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công!");
                modelDanhSachNhap.setRowCount(0);
                txtKhachHang.setText("");
                txtTongTien.setText("0");
                maKhachHangDuocChon = -1;

                hoaDon.reloadDAO();      // nếu cần cập nhật lại danh sách
                hoaDon.showTrangChinh(); // trở lại giao diện chính
            });

            panelright.add(nvNhap);
        }
    private void loadMaHoaDon() {
        int manv = Session.curUser.getMaNguoiDung();
        String tennv = Session.curUser.getTenDangNhap();
        nhapNVNhap.setText(String.valueOf(manv)); // Hiển thị mã nhân viên
        tennvnhap.setText(tennv);                 // Hiển thị tên nhân viên

        HoaDonBUS hoaDonBUS = new HoaDonBUS();
        int nextMaHD = hoaDonBUS.getNextMaHoaDon(); // Gọi BUS thay vì DAO
        nhapMaPN.setText(String.valueOf(nextMaHD)); // Gán vào ô nhập mã
    }
    private void loadNgayTao() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        txtNgayTao.setText(now.format(formatter));
    }
    private void showChonKhachHangDialog() {
        JDialog dialog = new JDialog((Frame) null, "Chọn Khách Hàng", true);
        dialog.setSize(500, 350);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getContentPane().setBackground(new Color(250, 250, 250));


        Font font = new Font("JetBrains Mono", Font.PLAIN, 13);
        Font headerFont = new Font("JetBrains Mono", Font.BOLD, 13);

        JTextField txtTimKiem = new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(460, 28));
        txtTimKiem.setFont(font);
        txtTimKiem.setText("Tìm theo tên hoặc số điện thoại");
        txtTimKiem.setForeground(Color.GRAY);

        txtTimKiem.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (txtTimKiem.getText().equals("Tìm theo tên hoặc số điện thoại")) {
                    txtTimKiem.setText("");
                    txtTimKiem.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (txtTimKiem.getText().isEmpty()) {
                    txtTimKiem.setText("Tìm theo tên hoặc số điện thoại");
                    txtTimKiem.setForeground(Color.GRAY);
                }
            }
        });


        // Bảng khách hàng
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Tên khách hàng", "Số điện thoại"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false; // không cho sửa bảng
            }
        };
        JTable table = new JTable(model);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        table.setFont(font);
        table.getTableHeader().setFont(headerFont);
        table.setRowHeight(24);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filter(); }
            public void removeUpdate(DocumentEvent e) { filter(); }
            public void changedUpdate(DocumentEvent e) { filter(); }

            private void filter() {
                String text = txtTimKiem.getText().trim();
                if (text.isEmpty() || text.equals("Tìm theo tên hoặc số điện thoại")) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text)));
                }
            }
        });
// Hiển thị đường kẻ
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));

        // Load dữ liệu khách hàng
        Map<String, KhachHangEntity> khMap = new HashMap<>();
        List<KhachHangEntity> list = KhachHangBUS.getList();

        for (KhachHangEntity kh : list) {
            String displayName = kh.getTen() + " - " + kh.getSdt();
            model.addRow(new Object[]{kh.getTen(), kh.getSdt()});
            khMap.put(displayName, kh);
        }

        // Panel nút
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        btnPanel.setBackground(new Color(250, 250, 250));

        JButton btnChon = new JButton("Chọn");
        btnChon.setBackground(new Color(76, 175, 80));
        btnChon.setForeground(Color.WHITE);
        btnChon.setFocusPainted(false);
        btnChon.setFont(font);

        JButton btnHuy = new JButton("Hủy");
        btnHuy.setBackground(new Color(244, 67, 54));
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setFocusPainted(false);
        btnHuy.setFont(font);

        btnPanel.add(btnChon);
        btnPanel.add(btnHuy);

        // Sự kiện chọn
        btnChon.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String tenKH = model.getValueAt(selectedRow, 0).toString();
                String sdt = model.getValueAt(selectedRow, 1).toString();
                String key = tenKH + " - " + sdt;
                KhachHangEntity selectedKH = khMap.get(key);

                if (selectedKH != null) {
                    maKhachHangDuocChon = selectedKH.getMa();
                    txtKhachHang.setText(sdt);
                    // Có thể lưu selectedKH.getMaKH() nếu cần
                }
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Vui lòng chọn khách hàng!");
            }
        });

        // Sự kiện hủy
        btnHuy.addActionListener(e -> dialog.dispose());
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(new Color(250, 250, 250));
        searchPanel.add(txtTimKiem);
        dialog.add(searchPanel, BorderLayout.NORTH);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(btnPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public void resetForm(){
        hienthi_masp.setText("");
        hienthi_tensp.setText("");
        nhapsoluong.setText("");
        nhapgiaban.setText("");
        txtSoLuongTon.setText("");
        txtKhachHang.setText("");
        hienthi_masp.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        hienthi_tensp.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        nhapgiaban.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        nhapsoluong.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        txtSoLuongTon.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        modelDanhSachNhap.setRowCount(0);
        updateTotal(modelDanhSachNhap, txtTongTien);
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
    public String formatCurrency(long value) {
        DecimalFormat format = new DecimalFormat("#,###");
        return format.format(value).replace(",", ".") + " ₫";
    }
    public void loadDataToTableSanPham() {
        List<SanPhamEntity> list = sanPhamBus.getAllSanPham();

        DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
        model.setRowCount(0); // clear dữ liệu cũ

        for (SanPhamEntity sp : list) {
            if (sp.getSoLuongTon() > 0 && sp.isTrangThai() ) {
                String giaFormatted = formatCurrency((long) sp.getGiaBan());
                model.addRow(new Object[]{
                        sp.getId(),
                        sp.getTenSanPham(),
                        giaFormatted,
                        sp.getSoLuongTon(),
                });
            }
        }
    }

}