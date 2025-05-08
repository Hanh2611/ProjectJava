package org.projects.GUI.DiaLog.SanPham;

import org.projects.Action.SanPhamAction;
import org.projects.BUS.DanhMucSanPhamBus;
import org.projects.GUI.Components.NumberOnlyFilter;
import org.projects.GUI.Components.labelText;
import org.projects.GUI.Panel.SanPham;
import org.projects.GUI.utils.Helper;
import org.projects.entity.DanhMucSanPhamEntity;
import org.projects.entity.Enum.QuyCach;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class AddSanPhamDialog extends JDialog {
    private final SanPham sanPham;
    private final SanPhamAction sanPhamAction;

    private JLabel imgPreview;

    private labelText tenSanPhamField, donViField, giaBanField;

    private JComboBox<String> quyCachField, phanLoaiField;
    private JButton uploadBtn, lamMoiBtn, huyBtn, luuBtn, btnAddPhanLoai;
    private JFileChooser fileChooser;
    private File selectedFile;

    public AddSanPhamDialog(SanPham sanPham) {
        this.sanPham = sanPham;
        this.sanPhamAction = new SanPhamAction(sanPham, this);
        setTitle("Thêm sản phẩm");
        setSize(450, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponent();
        setVisible(true);
    }

    public void initComponent() {
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
        uploadBtn = new JButton("Thêm hình ảnh");
        uploadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadBtn.addActionListener(e -> uploadImage());
        content.add(uploadBtn);

        // Image preview
        imgPreview = new JLabel();
        imgPreview.setIcon(new ImageIcon(new ImageIcon(Helper.getProductImagePath("product.jpg")).getImage().getScaledInstance(190, 140, Image.SCALE_SMOOTH))); // Set default if needed
        imgPreview.setPreferredSize(new Dimension(200, 150));
        imgPreview.setHorizontalAlignment(SwingConstants.CENTER);
        imgPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JPanel imgPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imgPanel.setOpaque(false);
        imgPanel.add(imgPreview);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(imgPanel);

        // Fields
        tenSanPhamField = new labelText("Nhập tên sản phẩm", 30, 10);
        giaBanField = new labelText("Nhập giá bán", 30, 10);
        donViField = new labelText("Nhập đơn vị", 30, 10);

        ((AbstractDocument) giaBanField.getTextField().getDocument()).setDocumentFilter(new NumberOnlyFilter());

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
        JPanel phanLoaiContainer= new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        phanLoaiLabel.setPreferredSize(new Dimension(450, 20));
        phanLoaiPanel.add(phanLoaiLabel);
        DanhMucSanPhamBus danhMucSanPhamBus = new DanhMucSanPhamBus();
        List<String> listDanhMuc = danhMucSanPhamBus.getAllDanhMucSanPham()
                .stream()
                .map(DanhMucSanPhamEntity::getTenDanhMuc)
                .toList();
        btnAddPhanLoai = new JButton("+");
        btnAddPhanLoai.setPreferredSize(new Dimension(30, 30));
        btnAddPhanLoai.addActionListener(e -> {
            new AddDanhMucDialog(this, this.sanPham).showDialog();
        });
        phanLoaiField = new JComboBox<>(listDanhMuc.toArray(new String[0]));
        phanLoaiField.setPreferredSize(new Dimension(350, 30));
        phanLoaiContainer.add(phanLoaiField);
        phanLoaiContainer.add(btnAddPhanLoai);
        phanLoaiPanel.add(phanLoaiContainer);
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
        fileChooser.setFileFilter(new FileNameExtensionFilter("Ảnh (JPG, PNG, GIF)", "jpg", "png", "gif"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            setSelectedFile(fileChooser.getSelectedFile());
            ImageIcon icon = new ImageIcon(getSelectedFile().getAbsolutePath());
            imgPreview.setIcon(new ImageIcon(icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH)));
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

    public SanPham getSanPham() {
        return sanPham;
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

    public SanPhamAction getSanPhamAction() {
        return sanPhamAction;
    }

    public JButton getUploadBtn() {
        return uploadBtn;
    }

    public void setUploadBtn(JButton uploadBtn) {
        this.uploadBtn = uploadBtn;
    }

    public JButton getBtnAddPhanLoai() {
        return btnAddPhanLoai;
    }

    public void setBtnAddPhanLoai(JButton btnAddPhanLoai) {
        this.btnAddPhanLoai = btnAddPhanLoai;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }
}
