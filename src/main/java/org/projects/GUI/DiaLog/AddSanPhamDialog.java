package org.projects.GUI.DiaLog;

import org.projects.GUI.Panel.SanPham;
import org.projects.entity.SanPhamEntity;

import javax.swing.*;
import java.awt.*;

public class AddSanPhamDialog extends JDialog {
    private SanPhamEntity sanPham;
    private String actionType; // them , xoa, sua , chi tiet
    private JLabel maSP;
    private JLabel tenSP;
    private JLabel giaSP;
    private JLabel maNCC;
    private JLabel maLoaiSP;
    private JLabel maNSX;
    private JLabel moTa;
    private JTextField maSPField;
    private JTextField tenSPField;
    private JTextField giaSPField;
    private JTextField maNCCField;
    private JTextField maLoaiSPField;
    private JTextField maNSXField;
    private JTextField moTaField;
    private JButton chucnangBTN;
    private JButton thoatBTN;
    public AddSanPhamDialog(String actionType, SanPhamEntity sanPham) {
        this.sanPham = sanPham;
        this.actionType = actionType;
        this.init();
        this.setTitle(this.getActionType());
        this.setSize(1230, 820);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(7, 2));
        this.setVisible(true);
    }
    public void init() {
        maSP = new JLabel("Mã sản phẩm: ");
        maSPField = new JTextField(10);
        this.add(maSP);
        this.add(maSPField);
        tenSP = new JLabel("Tên sản phẩm: ");
        tenSPField = new JTextField(10);
        this.add(tenSP);
        this.add(tenSPField);
        giaSP = new JLabel("Giá sản phẩm: ");
        giaSPField = new JTextField(10);
        this.add(giaSP);
        this.add(giaSPField);
        maNCC = new JLabel("Mã nhà cung cấp: ");
        maNCCField = new JTextField(10);
        this.add(maNCC);
        this.add(maNCCField);
        maLoaiSP = new JLabel("Mã loại sản phẩm: ");
        maLoaiSPField = new JTextField(10);
        this.add(maLoaiSP);
        this.add(maLoaiSPField);
        maNSX = new JLabel("Mã nhà sản xuất: ");
        maNSXField = new JTextField(10);
        this.add(maNSX);
        this.add(maNSXField);
        moTa = new JLabel("Mô tả: ");
        moTaField = new JTextField(10);
        this.add(moTa);
        this.add(moTaField);
        chucnangBTN = new JButton("Chức năng");
        chucnangBTN.setBackground(Color.WHITE);

    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    public String getActionType() {
        return actionType;
    }
}
