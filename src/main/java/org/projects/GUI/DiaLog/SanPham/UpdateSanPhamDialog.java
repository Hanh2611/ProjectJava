package org.projects.GUI.DiaLog.SanPham;

import org.projects.Action.SanPhamAction;
import org.projects.BUS.DanhMucSanPhamBus;
import org.projects.GUI.Components.labelText;
import org.projects.GUI.Panel.SanPham;
import org.projects.GUI.utils.Helper;
import org.projects.entity.DanhMucSanPhamEntity;
import org.projects.entity.Enum.QuyCach;
import org.projects.entity.SanPhamEntity;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class UpdateSanPhamDialog extends JDialog {
    private final SanPham sanPham;
    private final SanPhamAction sanPhamAction;
    private SanPhamEntity sanPhamEntity;

    private JLabel imgPreview, trangThai;
    private labelText tenSanPhamField, donViField, giaBanField, soLuongTonField;
    private JComboBox<String> quyCachField, phanLoaiField;
    private JButton uploadBtn, lamMoiBtn, huyBtn, luuBtn;
    private JFileChooser fileChooser;
    private File selectedFile;
    private JRadioButton isAvailable, isNotAvailable;

    public UpdateSanPhamDialog(SanPham sanPham, SanPhamEntity sanPhamEntity) {
        this.sanPham = sanPham;
        this.sanPhamEntity = sanPhamEntity;
        this.sanPhamAction = new SanPhamAction(sanPham, this);
        setTitle("Cập nhật sản phẩm");
        setSize(450, 700);
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
        JLabel title = new JLabel("Cập nhật sản phẩm", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.BLUE);
        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 10)));

        // Upload Button
        uploadBtn = new JButton("Hình ảnh");
        uploadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadBtn.addActionListener(e -> uploadImage());
        content.add(uploadBtn);

        // Image preview
        fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(Helper.getProductImagePath(sanPhamEntity.getHinhAnh())));
        setSelectedFile(fileChooser.getSelectedFile());
        imgPreview = new JLabel();
        imgPreview.setIcon(new ImageIcon(new ImageIcon(getSelectedFile().getAbsolutePath()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))); // Set default if needed
        imgPreview.setPreferredSize(new Dimension(200, 150));
        imgPreview.setHorizontalAlignment(SwingConstants.CENTER);
        imgPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JPanel imgPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imgPanel.setOpaque(false);
        imgPanel.add(imgPreview);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(imgPanel);

        // Fields
        tenSanPhamField = new labelText("Tên sản phẩm", 30, 10);
        giaBanField = new labelText("Giá bán", 30, 10);
        donViField = new labelText("Đơn vị", 30, 10);
        soLuongTonField = new labelText("Số lượng tồn", 30, 10);
        tenSanPhamField.getTextField().setText(sanPhamEntity.getTenSanPham());
        giaBanField.getTextField().setText(String.valueOf(sanPhamEntity.getGiaBan()));
        donViField.getTextField().setText(sanPhamEntity.getDonVi());
        soLuongTonField.getTextField().setText(String.valueOf(sanPhamEntity.getSoLuongTon()));

        content.add(tenSanPhamField);
        content.add(giaBanField);
        content.add(donViField);
        content.add(soLuongTonField);

        // ComboBox - Quy cách
        JPanel quyCachPanel = new JPanel();
        quyCachPanel.setLayout(new GridLayout(2, 1));
        JLabel quyCachLabel = new JLabel("Quy cách");
        quyCachLabel.setPreferredSize(new Dimension(450, 20));
        quyCachPanel.add(quyCachLabel);
        java.util.List<String> listQuyCach = Arrays.stream(QuyCach.values())
                .map(QuyCach::getValue)
                .toList();
        quyCachField = new JComboBox<>(listQuyCach.toArray(new String[0]));
        quyCachField.setSelectedIndex(sanPhamEntity.getQuyCach().ordinal());
        quyCachPanel.add(quyCachField);
        content.add(quyCachPanel);

        // ComboBox - Phân loại
        JPanel phanLoaiPanel = new JPanel();
        phanLoaiPanel.setLayout(new GridLayout(2, 1));
        JLabel phanLoaiLabel = new JLabel("Phân loại");
        phanLoaiLabel.setPreferredSize(new Dimension(450, 20));
        phanLoaiPanel.add(phanLoaiLabel);
        DanhMucSanPhamBus danhMucSanPhamBus = new DanhMucSanPhamBus();
        List<String> listDanhMuc = danhMucSanPhamBus.getAllDanhMucSanPham()
                .stream()
                .map(DanhMucSanPhamEntity::getTenDanhMuc)
                .toList();
        phanLoaiField = new JComboBox<>(listDanhMuc.toArray(new String[0]));
        phanLoaiField.setSelectedItem(sanPhamEntity.getPhanLoai().getTenDanhMuc());
        phanLoaiPanel.add(phanLoaiField);
        content.add(phanLoaiPanel);

        // Radio - Trạng thái
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        isAvailable = new JRadioButton("Kích hoạt");
        isNotAvailable = new JRadioButton("Ngừng kinh doanh");
        isAvailable.setSelected(sanPhamEntity.isTrangThai());
        isNotAvailable.setSelected(!sanPhamEntity.isTrangThai());
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(isAvailable);
        buttonGroup.add(isNotAvailable);
        statusPanel.add(isAvailable);
        statusPanel.add(isNotAvailable);
        content.add(statusPanel);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        lamMoiBtn = new JButton("Làm mới");
        lamMoiBtn.setBackground(Color.CYAN);
        lamMoiBtn.addActionListener(e -> resetForm());

        huyBtn = new JButton("Hủy");
        huyBtn.setBackground(Color.PINK);
        huyBtn.addActionListener(e -> dispose());

        luuBtn = new JButton("Lưu");
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
        tenSanPhamField.getTextField().setText(sanPhamEntity.getTenSanPham());
        giaBanField.getTextField().setText(String.valueOf(sanPhamEntity.getGiaBan()));
        donViField.getTextField().setText(sanPhamEntity.getDonVi());
        soLuongTonField.getTextField().setText(String.valueOf(sanPhamEntity.getSoLuongTon()));
        quyCachField.setSelectedIndex(sanPhamEntity.getQuyCach().ordinal());
        phanLoaiField.setSelectedItem(sanPhamEntity.getPhanLoai().getTenDanhMuc());
        imgPreview.setIcon(new ImageIcon(new ImageIcon(Helper.getProductImagePath(sanPhamEntity.getHinhAnh())).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        isAvailable.setSelected(sanPhamEntity.isTrangThai());
        isNotAvailable.setSelected(!sanPhamEntity.isTrangThai());
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public SanPhamAction getSanPhamAction() {
        return sanPhamAction;
    }

    public SanPhamEntity getSanPhamEntity() {
        return sanPhamEntity;
    }

    public void setSanPhamEntity(SanPhamEntity sanPhamEntity) {
        this.sanPhamEntity = sanPhamEntity;
    }

    public JLabel getImgPreview() {
        return imgPreview;
    }

    public void setImgPreview(JLabel imgPreview) {
        this.imgPreview = imgPreview;
    }

    public JLabel getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(JLabel trangThai) {
        this.trangThai = trangThai;
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

    public labelText getSoLuongTonField() {
        return soLuongTonField;
    }

    public void setSoLuongTonField(labelText soLuongTonField) {
        this.soLuongTonField = soLuongTonField;
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

    public JButton getUploadBtn() {
        return uploadBtn;
    }

    public void setUploadBtn(JButton uploadBtn) {
        this.uploadBtn = uploadBtn;
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

    public JRadioButton getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(JRadioButton isAvailable) {
        this.isAvailable = isAvailable;
    }

    public JRadioButton getIsNotAvailable() {
        return isNotAvailable;
    }

    public void setIsNotAvailable(JRadioButton isNotAvailable) {
        this.isNotAvailable = isNotAvailable;
    }
}
