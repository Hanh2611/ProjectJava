package org.projects.GUI.DiaLog;

import org.projects.Action.SanPhamAction;
import org.projects.BUS.DanhMucSanPhamBus;
import org.projects.BUS.SanPhamBus;
import org.projects.GUI.Components.labelText;
import org.projects.GUI.Panel.SanPham;
import org.projects.entity.DanhMucSanPhamEntity;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class AddSanPhamDialog extends JDialog {
    private SanPham sanPham;
    private JLabel imgPreview;
    private labelText tenSanPhamField;
    private labelText donViField;
    private labelText giaBanField;
    private labelText quyCachField;
    private JLabel hinhAnh;
    private JLabel phanLoai;
    private JComboBox<String> phanLoaiField;
    private JFileChooser fileChooser;
    private JButton chucnangBTN;
    private JButton thoatBTN;
    private SanPhamAction sanPhamAction;

    public AddSanPhamDialog(SanPham sanPham) {
        this.sanPham = sanPham;
        sanPhamAction = new SanPhamAction(sanPham, this);
        this.setTitle("Thêm sản phẩm");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.init();
        this.setVisible(true);
    }

    public void init() {
        setLayout(new BorderLayout());
        tenSanPhamField = new labelText("Tên sản phẩm", 30, 10);
        donViField = new labelText("Đơn vị", 30, 10);
        giaBanField = new labelText("Giá bán", 30, 10);
        quyCachField = new labelText("Quy cách", 30, 10);

        JPanel firstPanel = new JPanel(new GridLayout(3, 1, 0, 0));
        firstPanel.add(tenSanPhamField);
        firstPanel.add(giaBanField);
        firstPanel.add(donViField);

        JPanel secondPanel = new JPanel(new GridLayout(3, 1, 0,0));
        DanhMucSanPhamBus danhMucSanPhamBus = new DanhMucSanPhamBus();
        List<String> listDanhMuc = danhMucSanPhamBus.getAllDanhMucSanPham()
                .stream()
                .map(DanhMucSanPhamEntity::getTenDanhMuc)
                .toList();
        phanLoai = new JLabel("Phân loại");
        phanLoaiField = new JComboBox<>(listDanhMuc.toArray(new String[0]));
        phanLoaiField.setSelectedIndex(0);
        phanLoaiField.setPreferredSize(new Dimension(50, 20));
        hinhAnh = new JLabel("Hình ảnh");
        secondPanel.add(quyCachField);
        secondPanel.add(phanLoai);
        secondPanel.add(phanLoaiField);

        JButton uploadButton = new JButton("Tải ảnh");
        uploadButton.addActionListener(e -> uploadImage());

        JPanel imagePanel = new JPanel(new BorderLayout());
        imgPreview = new JLabel("Ảnh xem trước", SwingConstants.CENTER);
        imgPreview.setPreferredSize(new Dimension(50, 50));
        imgPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imagePanel.add(imgPreview, BorderLayout.CENTER);
        imagePanel.add(uploadButton, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setPreferredSize(new Dimension(600, 300));
        mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);
        mainPanel.add(imagePanel);

        add(mainPanel, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.addActionListener(e -> {
            dispose();
        });
        add(confirmButton, BorderLayout.SOUTH);
        pack();
    }

    private void uploadImage() {
        fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
            Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imgPreview.setIcon(new ImageIcon(scaled));
            imgPreview.setText(null);
        }
    }
}
