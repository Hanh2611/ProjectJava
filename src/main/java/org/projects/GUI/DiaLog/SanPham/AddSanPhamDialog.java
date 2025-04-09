package org.projects.GUI.DiaLog.SanPham;

import org.projects.Action.SanPhamAction;
import org.projects.BUS.DanhMucSanPhamBus;
import org.projects.GUI.Components.labelText;
import org.projects.GUI.Panel.SanPham;
import org.projects.entity.DanhMucSanPhamEntity;
import org.projects.entity.Enum.QuyCach;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddSanPhamDialog extends JDialog {
    private SanPham sanPham;
    private JLabel imgPreview;
    private labelText tenSanPhamField;
    private labelText donViField;
    private labelText giaBanField;
    private JLabel hinhAnh;
    private JLabel quyCach;
    private JComboBox<String> quyCachField;
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

        JPanel firstPanel = new JPanel(new GridLayout(3, 1, 0, 0));
        firstPanel.add(tenSanPhamField);
        firstPanel.add(giaBanField);
        firstPanel.add(donViField);

        JPanel secondPanel = new JPanel(new GridLayout(4, 1, 0,0));
        DanhMucSanPhamBus danhMucSanPhamBus = new DanhMucSanPhamBus();
        List<String> listQuyCach = Arrays.stream(QuyCach.values())
                .map(QuyCach::getValue)
                .toList();
        List<String> listDanhMuc = danhMucSanPhamBus.getAllDanhMucSanPham()
                .stream()
                .map(DanhMucSanPhamEntity::getTenDanhMuc)
                .toList();
        quyCach = new JLabel("Quy cách");
        quyCachField = new JComboBox<>(listQuyCach.toArray(new String[0]));
        quyCachField.setSelectedIndex(0);
        quyCachField.setPreferredSize(new Dimension(50, 15));

        phanLoai = new JLabel("Phân loại");
        phanLoaiField = new JComboBox<>(listDanhMuc.toArray(new String[0]));
        phanLoaiField.setSelectedIndex(0);
        phanLoaiField.setPreferredSize(new Dimension(50, 15));

        secondPanel.add(quyCach);
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

        JButton confirmButton = new JButton("Thêm");
        confirmButton.addActionListener(sanPhamAction);
        add(confirmButton, BorderLayout.SOUTH);
        pack();
    }

    private void uploadImage() {
        fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
            Image scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            imgPreview.setIcon(new ImageIcon(scaled));
            imgPreview.setText(null);
        }
    }

    public JLabel getImgPreview() {
        return imgPreview;
    }

    public void setImgPreview(JLabel imgPreview) {
        this.imgPreview = imgPreview;
    }

    public labelText getTenSanPhamField() {
        return tenSanPhamField;
    }

    public void setTenSanPhamField(labelText tenSanPhamField) {
        this.tenSanPhamField = tenSanPhamField;
    }

    public labelText getDonViField() {
        return donViField;
    }

    public void setDonViField(labelText donViField) {
        this.donViField = donViField;
    }

    public labelText getGiaBanField() {
        return giaBanField;
    }

    public void setGiaBanField(labelText giaBanField) {
        this.giaBanField = giaBanField;
    }

    public JLabel getQuyCach() {
        return quyCach;
    }

    public void setQuyCach(JLabel quyCach) {
        this.quyCach = quyCach;
    }

    public JComboBox<String> getQuyCachField() {
        return quyCachField;
    }

    public void setQuyCachField(JComboBox<String> quyCachField) {
        this.quyCachField = quyCachField;
    }

    public JLabel getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(JLabel hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public JLabel getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(JLabel phanLoai) {
        this.phanLoai = phanLoai;
    }

    public JComboBox<String> getPhanLoaiField() {
        return phanLoaiField;
    }

    public void setPhanLoaiField(JComboBox<String> phanLoaiField) {
        this.phanLoaiField = phanLoaiField;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    public JButton getChucnangBTN() {
        return chucnangBTN;
    }

    public void setChucnangBTN(JButton chucnangBTN) {
        this.chucnangBTN = chucnangBTN;
    }

    public JButton getThoatBTN() {
        return thoatBTN;
    }

    public void setThoatBTN(JButton thoatBTN) {
        this.thoatBTN = thoatBTN;
    }

    public SanPhamAction getSanPhamAction() {
        return sanPhamAction;
    }

    public void setSanPhamAction(SanPhamAction sanPhamAction) {
        this.sanPhamAction = sanPhamAction;
    }
}
