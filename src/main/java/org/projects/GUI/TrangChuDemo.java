package org.projects.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import org.projects.BUS.DanhMucSanPhamBus;
import org.projects.BUS.SanPhamBus;
import org.projects.GUI.Panel.HeaderTrangChu;
import org.projects.GUI.utils.Helper;
import org.projects.entity.DanhMucSanPhamEntity;
import org.projects.entity.SanPhamEntity;

public class TrangChuDemo extends JFrame {
    private final SanPhamBus sanPhamBus;
    private final DanhMucSanPhamBus danhMucSanPhamBus;
    private final HeaderTrangChu header;
    private JPanel center;
    private JPanel searchBarContainer;
    private JPanel productContainer;
    private List<SanPhamEntity> sanPhamList;
    private List<DanhMucSanPhamEntity> danhMucList;

    public TrangChuDemo() {
        this.sanPhamBus = new SanPhamBus();
        this.danhMucSanPhamBus = new DanhMucSanPhamBus();
        this.setSize(900, 700);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        header = new HeaderTrangChu();
        init();
        this.setVisible(true);
    }

    private void init() {
        this.center = new JPanel(new BorderLayout());
        this.searchBarContainer = new JPanel(new BorderLayout());
        this.productContainer = new JPanel(new BorderLayout());

        header.setPreferredSize(new Dimension(100, 60));
        this.add(header, BorderLayout.NORTH);

        searchBarContainer.setPreferredSize(new Dimension(0, 45));

        JTextField searchField = new JTextField("Nhập tên sản phẩm...");
        searchField.setForeground(Color.GRAY);
        searchField.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
        searchField.setMargin(new Insets(10, 10, 5, 5));

        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Nhập tên sản phẩm...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Nhập tên sản phẩm...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        this.danhMucList = danhMucSanPhamBus.getAllDanhMucSanPham();

        String[] danhMucNames = new String[danhMucList.size() + 1];
        danhMucNames[0] = "Tất cả";
        for (int i = 0; i < danhMucList.size(); i++) {
            danhMucNames[i + 1] = danhMucList.get(i).getTenDanhMuc();
        }

        JComboBox<String> danhMucComboBox = new JComboBox<>(danhMucNames);
        danhMucComboBox.setPreferredSize(new Dimension(180, 35));
        danhMucComboBox.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
        danhMucComboBox.setFocusable(false);

        danhMucComboBox.addActionListener(e -> {
            int selectedIndex = danhMucComboBox.getSelectedIndex();
            System.out.println(selectedIndex);
            if (selectedIndex == 0) {
                sanPhamList = sanPhamBus.getAllSanPham();
            } else {
                int selectedId = danhMucList.get(selectedIndex - 1).getId();
                sanPhamList = sanPhamBus.getSanPhamByDanhMuc(selectedId);
                searchField.setText("");
            }
            renderListSanPham(sanPhamList);
        });

        searchBarContainer.add(danhMucComboBox, BorderLayout.WEST);
        searchBarContainer.add(searchField, BorderLayout.CENTER);

        searchField.addKeyListener(
            new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    String value = searchField.getText();
                    if (!value.equals("Nhập tên sản phẩm...") && !value.isEmpty()) {
                        List<SanPhamEntity> filteredList = sanPhamList.stream().filter(sp -> sp.getTenSanPham().toLowerCase().contains(value.toLowerCase())).toList();
                        renderListSanPham(filteredList);
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            }
        );

        sanPhamList = sanPhamBus.getAllSanPham();

        center.add(searchBarContainer, BorderLayout.NORTH);
        center.add(productContainer, BorderLayout.CENTER);
        this.add(center, BorderLayout.CENTER);

        renderListSanPham(sanPhamList);
    }

    public void renderListSanPham(List<SanPhamEntity> list) {
        productContainer.removeAll();

        JPanel productsGridPanel = new JPanel();

        productsGridPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 10));
        productsGridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (list.isEmpty()) {
            JLabel emptyLabel = new JLabel("Không tìm thấy sản phẩm nào");
            emptyLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
            emptyLabel.setHorizontalAlignment(JLabel.CENTER);
            productsGridPanel.add(emptyLabel);
        }

        productsGridPanel.setMinimumSize(new Dimension(productContainer.getWidth(), 100));

        for (SanPhamEntity sp : list) {
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
            productPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            productPanel.setBackground(Color.WHITE);
            productPanel.setPreferredSize(new Dimension(200, 235));
            productPanel.setMaximumSize(new Dimension(200, 235));
            productPanel.setMinimumSize(new Dimension(200, 235));
            productPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            productPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            String imgPath = Helper.getProductImagePath(sp.getHinhAnh());
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH));

            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            imageLabel.setPreferredSize(new Dimension(200, 120));

            JLabel nameLabel = new JLabel("<html><div style='text-align: center; width: 160px; font-size: 11px; font-weight:bold'>" + sp.getTenSanPham() + "</div></html>");
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            nameLabel.setPreferredSize(new Dimension(150, 60));

            JLabel priceLabel = new JLabel(Helper.formatPrice(sp.getGiaBan()));
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            priceLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
            priceLabel.setForeground(Color.RED);

            productPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            productPanel.add(imageLabel);
            productPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            productPanel.add(nameLabel);
            productPanel.add(Box.createVerticalGlue());
            productPanel.add(priceLabel);
            productPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            productsGridPanel.add(productPanel);
        }

        JScrollPane scrollPane = new JScrollPane(productsGridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        productContainer.add(scrollPane, BorderLayout.CENTER);

        scrollPane.revalidate();
        productContainer.revalidate();
        productContainer.repaint();
    }

    public static class WrapLayout extends FlowLayout {
        private Dimension preferredLayoutSize;

        public WrapLayout() {
            super();
        }

        public WrapLayout(int align) {
            super(align);
        }

        public WrapLayout(int align, int hgap, int vgap) {
            super(align, hgap, vgap);
        }

        @Override
        public Dimension preferredLayoutSize(Container target) {
            return layoutSize(target, true);
        }

        @Override
        public Dimension minimumLayoutSize(Container target) {
            Dimension minimum = layoutSize(target, false);
            minimum.width -= (getHgap() + 1);
            return minimum;
        }

        private Dimension layoutSize(Container target, boolean preferred) {
            synchronized (target.getTreeLock()) {
                // Tính toán chiều cao dựa trên giá trị chiều rộng cha hiện tại
                int targetWidth = target.getWidth();

                // Nếu chiều rộng bằng 0, sử dụng container cha
                if (targetWidth == 0)
                    targetWidth = ((JScrollPane)target.getParent().getParent()).getWidth();

                // Trừ đi padding và scrollbar (nếu cần)
                targetWidth = targetWidth - (target.getInsets().left + target.getInsets().right + 20);

                int hgap = getHgap();
                int vgap = getVgap();
                Insets insets = target.getInsets();
                int horizontalInsetsAndGap = insets.left + insets.right + (hgap * 2);
                int maxWidth = targetWidth - horizontalInsetsAndGap;

                // Tính chiều cao cần thiết
                Dimension dim = new Dimension(0, 0);
                int rowWidth = 0;
                int rowHeight = 0;

                int nmembers = target.getComponentCount();

                for (int i = 0; i < nmembers; i++) {
                    Component m = target.getComponent(i);

                    if (m.isVisible()) {
                        Dimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();

                        if (rowWidth + d.width > maxWidth) {
                            addRow(dim, rowWidth, rowHeight);
                            rowWidth = 0;
                            rowHeight = 0;
                        }

                        if (rowWidth > 0) {
                            rowWidth += hgap;
                        }

                        rowWidth += d.width;
                        rowHeight = Math.max(rowHeight, d.height);
                    }
                }

                addRow(dim, rowWidth, rowHeight);

                dim.width += horizontalInsetsAndGap;
                dim.height += insets.top + insets.bottom + vgap * 2;

                preferredLayoutSize = dim;
                return dim;
            }
        }

        // Thêm hàng mới vào tổng chiều cao
        private void addRow(Dimension dim, int rowWidth, int rowHeight) {
            dim.width = Math.max(dim.width, rowWidth);

            if (dim.height > 0) {
                dim.height += getVgap();
            }

            dim.height += rowHeight;
        }
    }
}