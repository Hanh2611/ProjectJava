package org.projects.GUI.DiaLog.SanPham;

import org.projects.BUS.DanhMucSanPhamBus;
import org.projects.BUS.SanPhamBus;
import org.projects.GUI.Panel.SanPham;

import javax.swing.*;
import java.awt.*;

public class AddDanhMucDialog extends JDialog {

    private final DanhMucSanPhamBus danhMucSanPhamBus;
    private AddSanPhamDialog addSanPhamDialog;
    private final SanPham sanPham;

    public AddDanhMucDialog(AddSanPhamDialog addSanPhamDialog, SanPham sanPham) {
        this.addSanPhamDialog = addSanPhamDialog;
        this.sanPham = sanPham;
        this.danhMucSanPhamBus = new DanhMucSanPhamBus();
    }

    public void showDialog() {
        setTitle("Thêm Danh Mục");
        setSize(300, 140);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Nhập tên danh mục:");
        JTextField textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(200, 30));
        JButton addButton = new JButton("Thêm");
        addButton.setBackground(Color.GREEN);
        JButton cancelButton = new JButton("Hủy");
        cancelButton.setBackground(Color.PINK);

        panel.add(label);
        panel.add(textField);
        panel.add(cancelButton);
        panel.add(addButton);

        add(panel);

        addButton.addActionListener(e -> {
            String danhMuc = textField.getText();
            if (!danhMuc.isEmpty()) {
                if (danhMucSanPhamBus.createDanhMuc(danhMuc) > 0) {
                    JOptionPane.showMessageDialog(this, "Đã thêm danh mục: " + danhMuc);
                    addSanPhamDialog.dispose();
                    addSanPhamDialog = new AddSanPhamDialog(sanPham);
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm danh mục thất bại.");
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên danh mục.");
            }
        });

        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }
}
