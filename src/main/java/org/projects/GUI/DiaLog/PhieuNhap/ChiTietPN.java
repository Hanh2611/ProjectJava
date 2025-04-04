package org.projects.GUI.DiaLog.PhieuNhap;

import org.projects.GUI.Panel.PhieuNhap;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ChiTietPN extends JPanel {
    private JLabel lblchitietpn, lblngaylap, lblmanguoilap, lbltennguoilap, lblmapn, lblmancc, lbltenncc;
    private JTextField txtngaylap, txtmanguoilap, txttennguoilap, txtmapn, txtmancc, txttenncc;
    private Font fontHeader = new Font("JetBrains Mono", Font.BOLD, 20); // Font Header
    private Font fontChung = new Font("JetBrains Mono", Font.PLAIN, 14);
    private JTable table;
    private JScrollPane scrollPane;
    private JTableHeader header;
    private DefaultTableModel tableModel;
    private DefaultTableCellRenderer tableCellRenderer;
    private JButton btndong;
    private PhieuNhap mainPanel;
    JPanel panelcon;

    public ChiTietPN(PhieuNhap mainPanel) {
        this.mainPanel = mainPanel;
        setOpaque(false);
        setLayout(null);
        init();
    }

    public void init() {
        panelcon = new JPanel();
        panelcon.setLayout(null);
        panelcon.setBounds(5, 0, 930, 650);
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

        String [] colums = {"Mã SP", "Tên SP","Giá ","Đơn vị","Phân loại","Quy cách","Số lượng" };
        Object[][] data = {
                {"SP001", "Paracetamol", 2000, "Viên", "Thuốc giảm đau", "Hộp 10 viên", 100},
                {"SP002", "Ibuprofen", 1500, "Viên", "Thuốc giảm đau", "Hộp 10 viên", 200},
                {"SP003", "Amoxicillin", 5000, "Viên", "Kháng sinh", "Hộp 10 viên", 50}
        };
        tableModel = new DefaultTableModel(data,colums){
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


        header = table.getTableHeader();
        header.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
        header.setBackground(new Color(245,244,245,255));

        tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(tableCellRenderer);
        }
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30,220,860,380);
        btndong = new JButton("Đóng");
        btndong.setBackground(new Color(89,168,105,255));
        btndong.setBounds(400,605,110,35);
        btndong.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btndong.addActionListener(e ->{
            int result = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc chắn muốn đóng ?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (result == JOptionPane.YES_OPTION) {
                mainPanel.showTrangChinh();
            }
        });
        panelcon.add(btndong);
        panelcon.add(scrollPane);



        // Thêm panelcon vào giao diện
        add(panelcon);
    }
}
