package org.projects.GUI.DiaLog.HoaDon;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.DiaLog.PhieuNhap.ThemPN;
import org.projects.GUI.Panel.HoaDon;
import org.projects.entity.ChiTietHoaDonFullEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ChiTietHD extends JPanel {
    private JLabel lblchitietpn, lblngaylap, lblmanguoilap, lbltennguoilap,
            lblmahd,lblmakh,lbltenkh,lblTongTien;
    private JTextField txtngaylap, txtmanguoilap, txttennguoilap,
            txtmahd,txtmakh,txttenkh,txtTongTien;
    private Font fontHeader = new Font("JetBrains Mono", Font.BOLD, 20); // Font Header
    private Font fontChung = new Font("JetBrains Mono", Font.PLAIN, 14);
    private JTable table;
    private JScrollPane scrollPane;
    private JTableHeader header;
    private DefaultTableModel tableModel;
    private DefaultTableCellRenderer tableCellRenderer;
    private JButton btndong;
    private HoaDon hoaDon;
    FlatSVGIcon icon_close;
    JPanel panelcon;
    public ChiTietHD(HoaDon hoaDon) {

        this.hoaDon = hoaDon;
        setOpaque(false);
        setLayout(null);
        init();
    }

    public void init() {
        panelcon = new JPanel();
        panelcon.setLayout(null);
        panelcon.setBounds(1, 0, 940, 650);
        panelcon.setBackground(Color.WHITE);

        lblchitietpn = new JLabel("CHI TIẾT HÓA ĐƠN");
        lblchitietpn.setFont(fontHeader);
        lblchitietpn.setBounds(350, 0, 300, 50);
        panelcon.add(lblchitietpn);

        // Dòng 1
        lblngaylap = new JLabel("Ngày lập:");
        lblngaylap.setFont(fontChung);
        lblngaylap.setBounds(30, 50, 100, 30);
        panelcon.add(lblngaylap);

        txtngaylap = new JTextField();
        txtngaylap.setFont(fontChung);
        txtngaylap.setBounds(30, 80, 250, 30); // Điều chỉnh vị trí để nằm dưới label
        txtngaylap.setEditable(false);
        txtngaylap.setEditable(false);
        panelcon.add(txtngaylap);

        lblmanguoilap = new JLabel("Mã người lập:");
        lblmanguoilap.setFont(fontChung);
        lblmanguoilap.setBounds(340, 50, 120, 30);
        panelcon.add(lblmanguoilap);

        txtmanguoilap = new JTextField();
        txtmanguoilap.setFont(fontChung);
        txtmanguoilap.setBounds(340, 80, 250, 30); // Điều chỉnh vị trí
        txtmanguoilap.setEditable(false);
        panelcon.add(txtmanguoilap);

        lbltennguoilap = new JLabel("Tên người lập:");
        lbltennguoilap.setFont(fontChung);
        lbltennguoilap.setBounds(640, 50, 120, 30);
        panelcon.add(lbltennguoilap);

        txttennguoilap = new JTextField();
        txttennguoilap.setFont(fontChung);
        txttennguoilap.setBounds(640, 80, 250, 30); // Điều chỉnh vị trí
        txttennguoilap.setEditable(false);
        panelcon.add(txttennguoilap);

        // Dòng 2
        lblmahd = new JLabel("Mã Hóa Đơn:");
        lblmahd.setFont(fontChung);
        lblmahd.setBounds(30, 130, 120, 30);
        panelcon.add(lblmahd);

        txtmahd = new JTextField();
        txtmahd.setFont(fontChung);
        txtmahd.setBounds(30, 160, 250, 30); // Điều chỉnh vị trí
        txtmahd.setEditable(false);
        panelcon.add(txtmahd);

        lblmakh = new JLabel("Mã KH:");
        lblmakh.setFont(fontChung);
        lblmakh.setBounds(340, 130, 100, 30);
        panelcon.add(lblmakh);

        txtmakh = new JTextField();
        txtmakh.setFont(fontChung);
        txtmakh.setBounds(340, 160, 250, 30); // Điều chỉnh vị trí
        txtmakh.setEditable(false);
        panelcon.add(txtmakh);

        lbltenkh = new JLabel("Tên KH:");
        lbltenkh.setFont(fontChung);
        lbltenkh.setBounds(640, 130, 100, 30);
        panelcon.add(lbltenkh);

        txttenkh = new JTextField();
        txttenkh.setFont(fontChung);
        txttenkh.setBounds(640, 160, 250, 30); // Điều chỉnh vị trí
        txttenkh.setEditable(false);
        panelcon.add(txttenkh);

        String [] colums = {"Mã SP", "Tên SP","Giá ","Số lượng","Thành tiền" };

        tableModel = new DefaultTableModel(colums,0){
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        table = new JTable(tableModel);
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setDefaultEditor(Object.class, null);
        table.setRowHeight(20);
        table.setFocusable(false);
        table.setSelectionBackground(new Color(204, 229, 255));
        table.setFont(fontChung);
        table.setDefaultEditor(Object.class,null);
        header = table.getTableHeader();
        header.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
        header.setBackground(new Color(245,244,245,255));

        tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(tableCellRenderer);
        }
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30,220,860,330);
        btndong = new JButton("Đóng");
        icon_close = new FlatSVGIcon("icon/forbid.svg",15,15);
        btndong.setIcon(icon_close);
        btndong.setIconTextGap(6);
        btndong.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btndong.setForeground(Color.WHITE);
        btndong.setBackground(new Color(103,116,132));
        btndong.setBounds(400,605,110,35);
        btndong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hoaDon.showTrangChinh();
            }
        });

        lblTongTien = new JLabel("Tổng tiền hàng : ");
        lblTongTien.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
        lblTongTien.setBounds(590, 560, 150, 30); // vị trí gần cuối

        txtTongTien = new JTextField();
        txtTongTien.setFont(fontChung);
        txtTongTien.setBounds(740, 560, 150, 30);
        txtTongTien.setEditable(false);
        panelcon.add(lblTongTien);
        panelcon.add(txtTongTien);
        panelcon.add(btndong);
        panelcon.add(scrollPane);


        // Thêm panelcon vào giao diện
        add(panelcon);
    }
    public void setData(List<ChiTietHoaDonFullEntity> list) {

        if (list == null || list.isEmpty()) return;
        ChiTietHoaDonFullEntity first = list.get(0);
        txtngaylap.setText(first.getNgayTao().toString());
        txtmanguoilap.setText(String.valueOf(first.getMaNguoiLap()));
        txttennguoilap.setText(first.getTenNguoiLap());
        txtmahd.setText(String.valueOf(first.getMaHD()));
        txtmakh.setText(String.valueOf(first.getMaKH()));
        txttenkh.setText(String.valueOf(first.getTenKH()));
        String tongGiaTri = ThemPN.formatVND((long) first.getTongGiaTri());
        txtTongTien.setText(String.valueOf(tongGiaTri));
        tableModel.setRowCount(0); // Clear bảng

        for (ChiTietHoaDonFullEntity hd : list) {
            String giaBan = ThemPN.formatVND((long) hd.getGiaBan());
            String thanhTien = ThemPN.formatVND( (long) hd.getThanhTien());
            Object[] row = {
                    hd.getMaSP(),
                    hd.getTenSP(),
                    giaBan,
                    hd.getSoLuong(),
                    thanhTien
            };
            tableModel.addRow(row);
        }
    }
}

