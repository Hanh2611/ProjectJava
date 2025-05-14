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
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class DetailSanPhamDialog extends JDialog {
    private final SanPham sanPham;
    private final SanPhamAction sanPhamAction;
    private SanPhamEntity sanPhamEntity;

    private JLabel imgPreview, trangThai;
    private labelText tenSanPhamField, donViField, giaBanField, soLuongTonField;
    private JComboBox<String> quyCachField, phanLoaiField;
    private JButton dongBtn;
    private JRadioButton isAvailable, isNotAvailable, isInStock, isOutOfStock;

    public DetailSanPhamDialog(SanPham sanPham, SanPhamEntity sanPhamEntity) {
        this.sanPham = sanPham;
        this.sanPhamEntity = sanPhamEntity;
        this.sanPhamAction = new SanPhamAction(sanPham, this);
        setTitle("Chi tiết sản phẩm");
        setSize(450, 780);
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
        JLabel title = new JLabel("Chi tiết sản phẩm", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.BLUE);
        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 10)));

        // Image preview
        imgPreview = new JLabel();
        imgPreview.setIcon(new ImageIcon(new ImageIcon(Helper.getProductImagePath(sanPhamEntity.getHinhAnh())).getImage().getScaledInstance(190, 140, Image.SCALE_SMOOTH))); // Set default if needed
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

        tenSanPhamField.getTextField().setEnabled(false);
        giaBanField.getTextField().setEditable(false);
        donViField.getTextField().setEditable(false);
        soLuongTonField.getTextField().setEditable(false);

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
        List<String> listQuyCach = Arrays.stream(QuyCach.values())
                .map(QuyCach::getValue)
                .toList();
        quyCachField = new JComboBox<>(listQuyCach.toArray(new String[0]));
        quyCachField.setSelectedIndex(sanPhamEntity.getQuyCach().ordinal());
        quyCachField.setEnabled(false);
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
        phanLoaiField.setEnabled(false);
        phanLoaiPanel.add(phanLoaiField);
        content.add(phanLoaiPanel);

        //Radio - Hết hàng
        JPanel stockStatusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        isOutOfStock = new JRadioButton("Hết hàng");
        isInStock = new JRadioButton("Còn hàng");
        isOutOfStock.setSelected(sanPhamEntity.isHetHang());
        isInStock.setSelected(!sanPhamEntity.isHetHang());
        isOutOfStock.setEnabled(false);
        isInStock.setEnabled(false);
        ButtonGroup stockButtonGroup = new ButtonGroup();
        stockButtonGroup.add(isInStock);
        stockButtonGroup.add(isOutOfStock);
        stockStatusPanel.add(isInStock);
        stockStatusPanel.add(isOutOfStock);
        content.add(stockStatusPanel);

        // Radio - Trạng thái
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        isAvailable = new JRadioButton("Kích hoạt");
        isNotAvailable = new JRadioButton("Ngừng kinh doanh");
        isAvailable.setSelected(sanPhamEntity.isTrangThai());
        isNotAvailable.setSelected(!sanPhamEntity.isTrangThai());
        isAvailable.setEnabled(false);
        isNotAvailable.setEnabled(false);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(isAvailable);
        buttonGroup.add(isNotAvailable);
        statusPanel.add(isAvailable);
        statusPanel.add(isNotAvailable);

        content.add(statusPanel);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        dongBtn = new JButton("Đóng");
        dongBtn.setBackground(Color.GRAY);
        dongBtn.addActionListener(e -> dispose());

        buttonPanel.add(dongBtn);

        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(buttonPanel);

        add(content);
    }
}
