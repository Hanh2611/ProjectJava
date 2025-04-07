package org.projects.GUI.DiaLog.PhieuNhap;


import org.projects.GUI.DiaLog.PhieuNhap.Components.NumberOnlyFilter;
import org.projects.GUI.DiaLog.PhieuNhap.Components.OnlyDigitFilter;
import org.projects.GUI.Panel.PhieuNhap;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

public class ThemPN extends JPanel {
    JTextField timKiem, hienthi_masp, hienthi_tensp, nhapsoluong, nhapgiaban,
            txtQuyCach, txtDonViTinh, nhapMaPN, nhapNVNhap;
    JLabel masp, tensp, soluong, giaban, lblQuyCach, lblDonViTinh, maPN, nvNhap,
            ncc, lblTongTien, txtTongTien;
    JPanel panelLeft, panelright;
    JButton themSP, btnNhapHang, btnHuyBo, btnHuyBoSP;
    JTable tableSanPham, danhSachSanPhamNhap;
    JTableHeader header, headerdanhSachSanPhamNhap;
    JComboBox<String> nhapNCC;
    JScrollPane scrollPane, scrollPaneNhap;
    private PhieuNhap phieuNhap;

    public ThemPN(PhieuNhap phieuNhap) {
        setPreferredSize(new Dimension(940, 650));
        setOpaque(false);
        setLayout(null);
        this.phieuNhap = phieuNhap;
        init();

    }

    public void init() {

        // Ô tìm kiếm sản phẩm
        timKiem = new JTextField("Tìm kiếm mã sản phẩm, tên sản phẩm");
        timKiem.setBounds(15, 15, 430, 30);
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

        // Panel bên trái
        panelLeft = new JPanel();
        panelLeft.setLayout(null);
        panelLeft.setBounds(5, 5, 725, 655);
        panelLeft.setBackground(Color.WHITE);
        add(panelLeft);

        // Bảng dữ liệu sản phẩm
        String[] columnNames = {"Mã SP", "Tên SP"};
        Object[][] data = {
                {"SP01", "Sản phẩm A"},
                {"SP02", "Sản phẩm B"},
                {"SP03", "Sản phẩm C"},
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
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
        scrollPane.setBounds(10, 50, 430, 300);
        panelLeft.add(scrollPane);

        themSP = new JButton("Thêm Sản Phẩm");
        ImageIcon icon = createIconFromSVG("/icon/add.svg"); // Đảm bảo rằng đường dẫn đúng và có file SVG
        themSP.setIcon(icon);
        themSP.setBounds(10, 355, 250, 30);
        themSP.setBackground(new Color(89, 168, 105, 255));
        themSP.setForeground(Color.WHITE);
        themSP.setCursor(new Cursor(Cursor.HAND_CURSOR));
        themSP.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));

        btnHuyBoSP = new JButton("Bỏ sản phẩm");
        btnHuyBoSP.setBounds(270, 355, 250, 30);
        btnHuyBoSP.setBackground(Color.RED);
        btnHuyBoSP.setForeground(Color.WHITE);
        btnHuyBoSP.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHuyBoSP.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));

        panelLeft.add(btnHuyBoSP);
        panelLeft.add(themSP);


        masp = new JLabel("Mã Sản phẩm");
        masp.setBounds(450, 20, 100, 25);
        hienthi_masp = new JTextField();
        hienthi_masp.setBounds(450, 50, 265, 30);
        hienthi_masp.setEditable(false);
        panelLeft.add(masp);
        panelLeft.add(hienthi_masp);

        tensp = new JLabel("Tên sản phẩm");
        tensp.setBounds(450, 80, 100, 25);
        hienthi_tensp = new JTextField();
        hienthi_tensp.setBounds(450, 110, 265, 30);
        hienthi_tensp.setEditable(false);
        panelLeft.add(tensp);
        panelLeft.add(hienthi_tensp);

        soluong = new JLabel("Số lượng nhập");
        soluong.setBounds(450, 150, 100, 30);
        nhapsoluong = new JTextField();
        nhapsoluong.setBounds(450, 180, 115, 30);
        panelLeft.add(soluong);
        panelLeft.add(nhapsoluong);

        giaban = new JLabel("Giá nhập");
        giaban.setBounds(580, 150, 100, 30);
        nhapgiaban = new JTextField();
        nhapgiaban.setBounds(580, 180, 135, 30);
        panelLeft.add(giaban);
        panelLeft.add(nhapgiaban);

        ((AbstractDocument) nhapsoluong.getDocument()).setDocumentFilter(new OnlyDigitFilter());
        ((AbstractDocument) nhapgiaban.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        lblQuyCach = new JLabel("Quy cách");
        lblQuyCach.setBounds(450, 215, 100, 30);
        txtQuyCach = new JTextField();
        txtQuyCach.setBounds(450, 245, 265, 30);
        panelLeft.add(lblQuyCach);
        panelLeft.add(txtQuyCach);

        lblDonViTinh = new JLabel("Đơn vị tính");
        lblDonViTinh.setBounds(450, 280, 100, 30);
        txtDonViTinh = new JTextField();
        txtDonViTinh.setBounds(450, 310, 265, 30);
        panelLeft.add(lblDonViTinh);
        panelLeft.add(txtDonViTinh);

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
        nhapNCC = new JComboBox<>(new String[]{"Cty TGDD", "Cty FPT", "Cty Viettel"});
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
        btnNhapHang = new JButton("Nhập hàng");
        btnNhapHang.setBounds(100, 560, 100, 35);
        btnNhapHang.setFont(new Font("JETBRAINS MONO", Font.BOLD, 11));
        btnNhapHang.setBackground(Color.BLUE); // Xanh dương đậm
        btnNhapHang.setForeground(Color.WHITE);
        btnNhapHang.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnHuyBo = new JButton("Hủy bỏ");
        btnHuyBo.setBounds(10, 560, 90, 35);
        btnHuyBo.setBackground(Color.RED);
        btnHuyBo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHuyBo.setForeground(Color.WHITE);
        btnHuyBo.setFont(new Font("JETBRAINS MONO", Font.BOLD, 11));


        panelright.add(btnHuyBo);
        panelright.add(btnNhapHang);
        // Thêm vào sau khi tạo bảng sản phẩm
        tableSanPham.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tableSanPham.getSelectedRow();
            if (selectedRow != -1) { // Kiểm tra có chọn dòng nào không
                String maSP = (String) tableModel.getValueAt(selectedRow, 0);
                String tenSP = (String) tableModel.getValueAt(selectedRow, 1);

                hienthi_masp.setText(maSP);
                hienthi_tensp.setText(tenSP);
            }
        });
        // 🆕 Tạo bảng chứa danh sách sản phẩm đã chọn
        String[] columnNamesNhap = {"Mã SP", "Tên SP", "Số lượng", "Giá nhập", "Quy cách", "Đơn vị"};
        DefaultTableModel modelDanhSachNhap = new DefaultTableModel(columnNamesNhap, 0) {
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
            String quyCach = txtQuyCach.getText();
            String donViTinh = txtDonViTinh.getText();

            // Kiểm tra nếu các ô không được để trống
            if (maSP.isEmpty() || tenSP.isEmpty() || soLuong.isEmpty() || giaNhap.isEmpty() || quyCach.isEmpty() || donViTinh.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Thêm vào bảng danh sách nhập hàng
            modelDanhSachNhap.addRow(new Object[]{maSP, tenSP, soLuong, giaNhap, quyCach, donViTinh});
            updateTotal(modelDanhSachNhap, txtTongTien);

            hienthi_masp.setText("");
            hienthi_tensp.setText("");
            nhapsoluong.setText("");
            nhapgiaban.setText("");
            txtQuyCach.setText("");
            txtDonViTinh.setText("");
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
                phieuNhap.showTrangChinh(); // Gọi hàm trong MainFrame
                modelDanhSachNhap.setRowCount(0);
                updateTotal(modelDanhSachNhap, txtTongTien);
            }
        });

        panelright.add(nhapNCC);
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

    private ImageIcon createIconFromSVG(String svgFilePath) {
        // Tạo icon từ file SVG
        ImageIcon icon = new ImageIcon(getClass().getResource(svgFilePath));
        if (icon != null) {
            return icon;  // Trả về icon hợp lệ
        } else {
            System.out.println("Không thể tạo icon từ file SVG.");
            return null;  // Nếu không thành công thì trả về null
        }
    }
}