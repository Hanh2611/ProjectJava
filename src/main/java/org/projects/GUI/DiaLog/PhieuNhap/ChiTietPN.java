package org.projects.GUI.DiaLog.PhieuNhap;


import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.Action.PhieuNhapAction;
import org.projects.DAO.ChiTietPhieuNhapDAO;
import org.projects.DAO.ChiTietPhieuNhapFullEntityDAO;
import org.projects.DAO.PhieuNhapDAO;
import org.projects.GUI.Panel.PhieuNhap;
import org.projects.GUI.utils.VotePDF;
import org.projects.entity.ChiTietPhieuNhapFullEntity;
import org.projects.entity.PhieuNhapEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChiTietPN extends JPanel {
    private JLabel lblchitietpn, lblngaylap, lblmanguoilap, lbltennguoilap, lblmapn, lblmancc, lbltenncc,lblTongGiaTri;
    private JTextField txtngaylap, txtmanguoilap, txttennguoilap, txtmapn, txtmancc, txttenncc,txtTongGiaTri;
    private Font fontHeader = new Font("JetBrains Mono", Font.BOLD, 20); // Font Header
    private Font fontChung = new Font("JetBrains Mono", Font.PLAIN, 14);
    private JTable table;
    private JScrollPane scrollPane;
    private JTableHeader header;
    private DefaultTableModel tableModel;
    private DefaultTableCellRenderer tableCellRenderer;
    private JButton btndong;
    private JButton xuatphieu;
    private PhieuNhap phieuNhap;
    FlatSVGIcon icon_close;
    JPanel panelcon;
    public ChiTietPN(PhieuNhap phieuNhap) {

        this.phieuNhap = phieuNhap;
        setOpaque(false);
        setLayout(null);
        init();
    }

    public void init() {
        panelcon = new JPanel();
        panelcon.setLayout(null);
        panelcon.setBounds(1, 0, 940, 650);
        panelcon.setBackground(Color.WHITE);

        lblchitietpn = new JLabel("CHI TIẾT PHIẾU NHẬP");
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
        lblmapn = new JLabel("Mã Phiếu Nhập:");
        lblmapn.setFont(fontChung);
        lblmapn.setBounds(30, 130, 120, 30);
        panelcon.add(lblmapn);

        txtmapn = new JTextField();
        txtmapn.setFont(fontChung);
        txtmapn.setBounds(30, 160, 250, 30); // Điều chỉnh vị trí
        txtmapn.setEditable(false);
        panelcon.add(txtmapn);

        lblmancc = new JLabel("Mã NCC:");
        lblmancc.setFont(fontChung);
        lblmancc.setBounds(340, 130, 100, 30);
        panelcon.add(lblmancc);

        txtmancc = new JTextField();
        txtmancc.setFont(fontChung);
        txtmancc.setBounds(340, 160, 250, 30); // Điều chỉnh vị trí
        txtmancc.setEditable(false);
        panelcon.add(txtmancc);

        lbltenncc = new JLabel("Tên NCC:");
        lbltenncc.setFont(fontChung);
        lbltenncc.setBounds(640, 130, 100, 30);
        panelcon.add(lbltenncc);

        txttenncc = new JTextField();
        txttenncc.setFont(fontChung);
        txttenncc.setBounds(640, 160, 250, 30); // Điều chỉnh vị trí
        txttenncc.setEditable(false);
        panelcon.add(txttenncc);

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
        btndong.setForeground(Color.WHITE);
        btndong.setBackground(new Color(103,116,132));
        btndong.setBounds(400,605,110,35);
        btndong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phieuNhap.showTrangChinh();
            }
        });
        xuatphieu = new JButton("Xuất phiếu");
        xuatphieu.setBackground(new Color(103,116,132));
        xuatphieu.setForeground(Color.WHITE);
        xuatphieu.setBounds(520,605,110,35);
        xuatphieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inPhieuNhap();
            }
        });
        panelcon.add(btndong);
        panelcon.add(xuatphieu);
        panelcon.add(scrollPane);

        lblTongGiaTri = new JLabel("Tổng giá trị:");
        lblTongGiaTri.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTongGiaTri.setBounds(600, 560, 100, 30); // Vị trí bên phải dưới bảng

// TextField tổng tiền
        txtTongGiaTri = new JTextField();
        txtTongGiaTri.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTongGiaTri.setEditable(false); // Không cho người dùng sửa
        txtTongGiaTri.setHorizontalAlignment(JTextField.RIGHT); // Căn phải
        txtTongGiaTri.setBounds(700, 560, 190, 30);

// Thêm vào panel
        panelcon.add(lblTongGiaTri);
        panelcon.add(txtTongGiaTri);

        // Thêm panelcon vào giao diện
        add(panelcon);
    }
    public void setData(List<ChiTietPhieuNhapFullEntity> list) {
        if (list == null || list.isEmpty()) return;

        ChiTietPhieuNhapFullEntity first = list.get(0);

        txtmapn.setText(String.valueOf(first.getMaPN()));
        txtngaylap.setText(first.getNgaylap().toString());
        txtmanguoilap.setText(String.valueOf(first.getMaNguoiLap())); // Nếu có mã người lập thì thêm vào entity và set
        txttennguoilap.setText(first.getTenNguoiLap());
        txtmancc.setText(String.valueOf(first.getMaNCC())); // Nếu có mã nhà cung cấp thì thêm vào entity và set
        txttenncc.setText(first.getTenNCC());
        String tonggiatri = PhieuNhap.formatCurrency((long) first.getTongGiaTriNhap());
        txtTongGiaTri.setText(tonggiatri);

        tableModel.setRowCount(0); // Clear bảng

        for (ChiTietPhieuNhapFullEntity ct : list) {
            String giaBan = PhieuNhap.formatCurrency((long) ct.getGia());
            String thanhTien = PhieuNhap.formatCurrency((long) ct.getThanhtien());
            Object[] row = {
                    ct.getMasp(),
                    ct.getTenSP(),
                    giaBan,
                    ct.getSoLuong(),
                    thanhTien
            };
            tableModel.addRow(row);
        }
    }

    //ham in phieu
    public void inPhieuNhap() {
        String maPNstr = txtmapn.getText().trim();
        if (maPNstr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int maphieunhap;
        try {
            maphieunhap = Integer.parseInt(maPNstr);
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã phiếu nhập không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        PhieuNhapEntity pnEntity = new PhieuNhapDAO().getPhieuNhapById(maphieunhap);
        if (pnEntity == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<ChiTietPhieuNhapFullEntity> chiTietList = new ChiTietPhieuNhapFullEntityDAO().showlist(maphieunhap);

        if (chiTietList == null || chiTietList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phiếu nhập không có sản phẩm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        VotePDF.taoPhieuNhap(pnEntity,chiTietList);

    }
}
