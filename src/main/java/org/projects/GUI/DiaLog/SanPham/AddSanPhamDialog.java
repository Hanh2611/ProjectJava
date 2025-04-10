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

public class AddSanPhamDialog extends JDialog {

    private SanPham sanPham;
    private SanPhamAction sanPhamAction;

    private JLabel imgPreview;
    private labelText tenSanPhamField;
    private labelText donViField;
    private labelText giaBanField;
    private JComboBox<String> quyCachField;
    private JComboBox<String> phanLoaiField;
    private JButton lamMoiBtn, huyBtn, luuBtn;
    private JFileChooser fileChooser;
    private File selectedFile;

    public AddSanPhamDialog(SanPham sanPham) {
        this.sanPham = sanPham;
        this.sanPhamAction = new SanPhamAction(sanPham, this);
        setTitle("Thêm sản phẩm");
        setSize(450, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
        setVisible(true);
    }

    public void init() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        content.setBackground(Color.WHITE);

        // Title
        JLabel title = new JLabel("Thêm sản phẩm", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.BLUE);
        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 10)));

        // Upload Button
        JButton uploadBtn = new JButton("ADD IMAGE");
        uploadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadBtn.addActionListener(e -> uploadImage());

        // Image preview
        imgPreview = new JLabel();
        imgPreview.setIcon(new ImageIcon(new ImageIcon("src/main/resources/img/user.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))); // Set default if needed
        imgPreview.setPreferredSize(new Dimension(180, 150));
        imgPreview.setHorizontalAlignment(SwingConstants.CENTER);
        imgPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JPanel imgPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imgPanel.setOpaque(false);
        imgPanel.add(imgPreview);

        content.add(uploadBtn);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(imgPanel);

        // Fields
        tenSanPhamField = new labelText("Nhập tên sản phẩm", 30, 10);
        giaBanField = new labelText("Nhập giá bán", 30, 10);
        donViField = new labelText("Nhập đơn vị", 30, 10);

        content.add(tenSanPhamField);
        content.add(giaBanField);
        content.add(donViField);

        // ComboBox - Quy cách
        JPanel quyCachPanel = new JPanel();
        quyCachPanel.setLayout(new GridLayout(2, 1));
        JLabel quyCachLabel = new JLabel("Chọn quy cách:");
        quyCachLabel.setPreferredSize(new Dimension(450, 20));
        quyCachPanel.add(quyCachLabel);
        List<String> listQuyCach = Arrays.stream(QuyCach.values())
                .map(QuyCach::getValue)
                .toList();
        quyCachField = new JComboBox<>(listQuyCach.toArray(new String[0]));
        quyCachPanel.add(quyCachField);
        content.add(quyCachPanel);

        // ComboBox - Phân loại
        JPanel phanLoaiPanel = new JPanel();
        phanLoaiPanel.setLayout(new GridLayout(2, 1));
        JLabel phanLoaiLabel = new JLabel("Chọn phân loại:");
        phanLoaiLabel.setPreferredSize(new Dimension(450, 20));
        phanLoaiPanel.add(phanLoaiLabel);
        DanhMucSanPhamBus danhMucSanPhamBus = new DanhMucSanPhamBus();
        List<String> listDanhMuc = danhMucSanPhamBus.getAllDanhMucSanPham()
                .stream()
                .map(DanhMucSanPhamEntity::getTenDanhMuc)
                .toList();
        phanLoaiField = new JComboBox<>(listDanhMuc.toArray(new String[0]));
        phanLoaiPanel.add(phanLoaiField);
        content.add(phanLoaiPanel);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        lamMoiBtn = new JButton("Làm mới");
        lamMoiBtn.setBackground(Color.CYAN);
        lamMoiBtn.addActionListener(e -> resetForm());

        huyBtn = new JButton("Hủy");
        huyBtn.setBackground(Color.PINK);
        huyBtn.addActionListener(e -> dispose());

        luuBtn = new JButton("Thêm");
        luuBtn.setBackground(Color.GREEN);
        luuBtn.addActionListener(sanPhamAction);

        buttonPanel.add(lamMoiBtn);
        buttonPanel.add(huyBtn);
        buttonPanel.add(luuBtn);

        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(buttonPanel);

        add(content);
    }

    private void uploadImage() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Ảnh (JPG, PNG, GIF)", "jpg", "png", "gif"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            setSelectedFile(fileChooser.getSelectedFile());
            ImageIcon icon = new ImageIcon(getSelectedFile().getAbsolutePath());
            imgPreview.setIcon(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        }
    }

    private void resetForm() {
        tenSanPhamField.getTextField().setText("");
        donViField.getTextField().setText("");
        giaBanField.getTextField().setText("");
        quyCachField.setSelectedIndex(0);
        phanLoaiField.setSelectedIndex(0);
        imgPreview.setIcon(null);
    }

//    public AddSanPhamDialog(SanPham sanPham) {
//        this.sanPham = sanPham;
//        sanPhamAction = new SanPhamAction(sanPham, this);
//        this.setTitle("Thêm sản phẩm");
//        this.setSize(600, 400);
//        this.setLocationRelativeTo(null);
//        this.init();
//        this.setVisible(true);
//    }
//
//    public void init() {
//        setLayout(new BorderLayout());
//        tenSanPhamField = new labelText("Tên sản phẩm", 30, 10);
//        donViField = new labelText("Đơn vị", 30, 10);
//        giaBanField = new labelText("Giá bán", 30, 10);
//
//        JPanel firstPanel = new JPanel(new GridLayout(3, 1, 0, 0));
//        firstPanel.add(tenSanPhamField);
//        firstPanel.add(giaBanField);
//        firstPanel.add(donViField);
//
//        JPanel secondPanel = new JPanel(new GridLayout(4, 1, 0,0));
//        DanhMucSanPhamBus danhMucSanPhamBus = new DanhMucSanPhamBus();
//        List<String> listQuyCach = Arrays.stream(QuyCach.values())
//                .map(QuyCach::getValue)
//                .toList();
//        List<String> listDanhMuc = danhMucSanPhamBus.getAllDanhMucSanPham()
//                .stream()
//                .map(DanhMucSanPhamEntity::getTenDanhMuc)
//                .toList();
//        quyCach = new JLabel("Quy cách");
//        quyCachField = new JComboBox<>(listQuyCach.toArray(new String[0]));
//        quyCachField.setSelectedIndex(0);
//        quyCachField.setPreferredSize(new Dimension(50, 15));
//
//        phanLoai = new JLabel("Phân loại");
//        phanLoaiField = new JComboBox<>(listDanhMuc.toArray(new String[0]));
//        phanLoaiField.setSelectedIndex(0);
//        phanLoaiField.setPreferredSize(new Dimension(50, 15));
//
//        secondPanel.add(quyCach);
//        secondPanel.add(quyCachField);
//        secondPanel.add(phanLoai);
//        secondPanel.add(phanLoaiField);
//
//        JButton uploadButton = new JButton("Tải ảnh");
//        uploadButton.addActionListener(e -> uploadImage());
//
//        JPanel imagePanel = new JPanel(new BorderLayout());
//        imgPreview = new JLabel("Ảnh xem trước", SwingConstants.CENTER);
//        imgPreview.setPreferredSize(new Dimension(50, 50));
//        imgPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//        imagePanel.add(imgPreview, BorderLayout.CENTER);
//        imagePanel.add(uploadButton, BorderLayout.SOUTH);
//
//        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 10, 10));
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        mainPanel.setPreferredSize(new Dimension(600, 300));
//        mainPanel.add(firstPanel);
//        mainPanel.add(secondPanel);
//        mainPanel.add(imagePanel);
//
//        add(mainPanel, BorderLayout.CENTER);
//
//        JButton confirmButton = new JButton("Thêm");
//        confirmButton.addActionListener(sanPhamAction);
//        add(confirmButton, BorderLayout.SOUTH);
//        pack();
//    }

//    private void uploadImage() {
//        fileChooser = new JFileChooser();
//        int option = fileChooser.showOpenDialog(this);
//        if (option == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
//            Image scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
//            imgPreview.setIcon(new ImageIcon(scaled));
//            imgPreview.setText(null);
//        }
//    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
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

    public JComboBox<String> getQuyCachField() {
        return quyCachField;
    }

    public void setQuyCachField(JComboBox<String> quyCachField) {
        this.quyCachField = quyCachField;
    }

    public JComboBox<String> getPhanLoaiField() {
        return phanLoaiField;
    }

    public void setPhanLoaiField(JComboBox<String> phanLoaiField) {
        this.phanLoaiField = phanLoaiField;
    }

    public JButton getLamMoiBtn() {
        return lamMoiBtn;
    }

    public void setLamMoiBtn(JButton lamMoiBtn) {
        this.lamMoiBtn = lamMoiBtn;
    }

    public JButton getHuyBtn() {
        return huyBtn;
    }

    public void setHuyBtn(JButton huyBtn) {
        this.huyBtn = huyBtn;
    }

    public JButton getLuuBtn() {
        return luuBtn;
    }

    public void setLuuBtn(JButton luuBtn) {
        this.luuBtn = luuBtn;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }
}
