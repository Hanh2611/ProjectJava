package org.projects.GUI.DiaLog.SanPham;

import org.projects.Action.SanPhamAction;
import org.projects.BUS.DanhMucSanPhamBus;
import org.projects.GUI.Components.NumberOnlyFilter;
import org.projects.GUI.Components.OnlyDigitFilter;
import org.projects.GUI.Components.Transition.mainTransition;
import org.projects.GUI.Components.labelText;
import org.projects.GUI.Panel.SanPham;
import org.projects.GUI.utils.Helper;
import org.projects.entity.DanhMucSanPhamEntity;
import org.projects.entity.Enum.QuyCach;
import org.projects.entity.SanPhamEntity;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateSanPhamDialog extends JDialog {
    private final SanPham sanPham;
    private final SanPhamAction sanPhamAction;
    private SanPhamEntity sanPhamEntity;

    private JLabel imgPreview, trangThai;
    private labelText tenSanPhamField, donViField, giaBanField, soLuongTonField;
    private labelText quyCachField, phanLoaiField;
    private JButton uploadBtn, lamMoiBtn, huyBtn, luuBtn;
    private JFileChooser fileChooser;
    private File selectedFile;
    private JRadioButton isAvailable, isNotAvailable, isOutOfStock, isInStock;
    mainTransition mainTransition = new mainTransition();
    public UpdateSanPhamDialog(SanPham sanPham, SanPhamEntity sanPhamEntity) {
        this.sanPham = sanPham;
        this.sanPhamEntity = sanPhamEntity;
        this.sanPhamAction = new SanPhamAction(sanPham, this);
        setTitle("Cập nhật sản phẩm");

        //setSize(450, 750);
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponent();
        mainTransition.showSlideIn(this , 450  , 770);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                e.getWindow().setVisible(false);
                mainTransition.closeSlideOut(UpdateSanPhamDialog.this);
            }
        });
//        setVisible(true);
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
        imgPreview.setIcon(new ImageIcon(new ImageIcon(getSelectedFile().getAbsolutePath()).getImage().getScaledInstance(190, 140, Image.SCALE_SMOOTH))); // Set default if needed
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

        ArrayList<String> listQuyCach = new ArrayList<>(Arrays.stream(QuyCach.values())
                .map(QuyCach::getValue)
                .toList());
        DanhMucSanPhamBus danhMucSanPhamBus = new DanhMucSanPhamBus();
        ArrayList<String> listDanhMuc = new ArrayList<>(danhMucSanPhamBus.getAllDanhMucSanPham()
                .stream()
                .map(DanhMucSanPhamEntity::getTenDanhMuc)
                .toList());
        quyCachField = new labelText("Quy cách", listQuyCach);
        phanLoaiField = new labelText("Phân loại", listDanhMuc);
        quyCachField.getCbx().setSelectedIndex(sanPhamEntity.getQuyCach().ordinal());
        phanLoaiField.getCbx().setSelectedItem(sanPhamEntity.getPhanLoai().getTenDanhMuc());

        tenSanPhamField.getTextField().setText(sanPhamEntity.getTenSanPham());
        giaBanField.getTextField().setText(String.valueOf(sanPhamEntity.getGiaBan()));
        donViField.getTextField().setText(sanPhamEntity.getDonVi());
        soLuongTonField.getTextField().setText(String.valueOf(sanPhamEntity.getSoLuongTon()));

        ((AbstractDocument) giaBanField.getTextField().getDocument()).setDocumentFilter(new OnlyDigitFilter());
        ((AbstractDocument) soLuongTonField.getTextField().getDocument()).setDocumentFilter(new OnlyDigitFilter());

        content.add(tenSanPhamField);
        content.add(giaBanField);
        content.add(donViField);
        content.add(soLuongTonField);
        content.add(quyCachField);
        content.add(phanLoaiField);

        // ComboBox - Quy cách
//        JPanel quyCachPanel = new JPanel();
//        quyCachPanel.setLayout(new GridLayout(2, 1));
//        JLabel quyCachLabel = new JLabel("Quy cách");
//        quyCachLabel.setPreferredSize(new Dimension(450, 0));
//        quyCachPanel.add(quyCachLabel);
//        List<String> listQuyCach = Arrays.stream(QuyCach.values())
//                .map(QuyCach::getValue)
//                .toList();
//        quyCachField = new JComboBox<>(listQuyCach.toArray(new String[0]));
//        quyCachField.setSelectedIndex(sanPhamEntity.getQuyCach().ordinal());
//        quyCachField.setPreferredSize(new Dimension(450, 30));
//        quyCachPanel.add(quyCachField);
//        content.add(quyCachPanel);

        // ComboBox - Phân loại
//        JPanel phanLoaiPanel = new JPanel();
//        phanLoaiPanel.setLayout(new GridLayout(2, 1));
//        JLabel phanLoaiLabel = new JLabel("Phân loại");
//        phanLoaiLabel.setPreferredSize(new Dimension(450, 0));
//        phanLoaiPanel.add(phanLoaiLabel);
//        DanhMucSanPhamBus danhMucSanPhamBus = new DanhMucSanPhamBus();
//        List<String> listDanhMuc = danhMucSanPhamBus.getAllDanhMucSanPham()
//                .stream()
//                .map(DanhMucSanPhamEntity::getTenDanhMuc)
//                .toList();
//        phanLoaiField = new JComboBox<>(listDanhMuc.toArray(new String[0]));
//        phanLoaiField.setSelectedItem(sanPhamEntity.getPhanLoai().getTenDanhMuc());
//        phanLoaiField.setPreferredSize(new Dimension(450, 30));
//        phanLoaiPanel.add(phanLoaiField);
//        content.add(phanLoaiPanel);

        //Radio - Hết hàng
//        JPanel stockStatusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
//        isOutOfStock = new JRadioButton("Hết hàng");
//        isInStock = new JRadioButton("Còn hàng");
//        isOutOfStock.setSelected(sanPhamEntity.isHetHang());
//        isInStock.setSelected(!sanPhamEntity.isHetHang());
//        ButtonGroup stockButtonGroup = new ButtonGroup();
//        stockButtonGroup.add(isInStock);
//        stockButtonGroup.add(isOutOfStock);
//        stockStatusPanel.add(isInStock);
//        stockStatusPanel.add(isOutOfStock);
//        content.add(stockStatusPanel);

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
        huyBtn.addActionListener(e -> mainTransition.closeSlideOutSP(this));

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
        giaBanField.getTextField().setText("");
        giaBanField.getTextField().setText(String.valueOf(sanPhamEntity.getGiaBan()));
        donViField.getTextField().setText(sanPhamEntity.getDonVi());
        soLuongTonField.getTextField().setText(String.valueOf(sanPhamEntity.getSoLuongTon()));
        quyCachField.getCbx().setSelectedItem(sanPhamEntity.getQuyCach().ordinal());
        phanLoaiField.getCbx().setSelectedItem(sanPhamEntity.getPhanLoai().getTenDanhMuc());
        imgPreview.setIcon(new ImageIcon(new ImageIcon(Helper.getProductImagePath(sanPhamEntity.getHinhAnh())).getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH)));
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

//    public JComboBox<String> getQuyCachField() {
//        return quyCachField;
//    }
//
//    public void setQuyCachField(JComboBox<String> quyCachField) {
//        this.quyCachField = quyCachField;
//    }
//
//    public JComboBox<String> getPhanLoaiField() {
//        return phanLoaiField;
//    }
//
//    public void setPhanLoaiField(JComboBox<String> phanLoaiField) {
//        this.phanLoaiField = phanLoaiField;
//    }


    public labelText getQuyCachField() {
        return quyCachField;
    }

    public void setQuyCachField(labelText quyCachField) {
        this.quyCachField = quyCachField;
    }

    public labelText getPhanLoaiField() {
        return phanLoaiField;
    }

    public void setPhanLoaiField(labelText phanLoaiField) {
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

    public void setIsInStock(JRadioButton isInStock) {
        this.isInStock = isInStock;
    }

    public JRadioButton getIsOutOfStock() {
        return isOutOfStock;
    }

    public void setIsOutOfStock(JRadioButton isOutOfStock) {
        this.isOutOfStock = isOutOfStock;
    }

    public JRadioButton getIsInStock() {
        return isInStock;
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
