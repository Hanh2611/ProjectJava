package org.projects.GUI.Panel;

import org.projects.BUS.SanPhamBus;
import org.projects.DAO.SanPhamDao;
import org.projects.GUI.DiaLog.AddSanPhamDialog;
import org.projects.entity.SanPhamEntity;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class SanPham extends JPanel{

    private JTable table;
    private JPanel header;
    private List<SanPhamEntity> listSanPham;

    public SanPham() {
        initComponent();
        this.setBackground(Color.BLACK);
    }

    private void initComponent() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.table = new JTable();
        this.header = new JPanel();
        this.header.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        String[] quyen = new String[]{"Thêm", "Sửa", "Xóa", "Chi tiết"};
        for (String s : quyen) {
            JButton btn = new JButton(s);
            btn.setPreferredSize(new Dimension(50, 30));
            btn.setBackground(Color.WHITE);
            btn.setForeground(Color.BLACK);
            btn.setFont(new Font("Arial", Font.BOLD, 12));
            btn.setBorder(BorderFactory.createEmptyBorder());
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn.addActionListener(e -> {
                switch (s) {
                    case "Thêm":
                        SanPhamEntity sp = new SanPhamEntity();
                        new AddSanPhamDialog(s, sp);
                        break;
                    case "Sửa":
                        break;
                    case "Xóa":
                        break;
                    case "Chi tiết":
                        break;
                }
            });
            header.add(btn);
        }
        this.add(header);

        String[] columns = {"Id", "Hình ảnh", "Tên", "Phân loại", "Giá bán", "Số lượng tồn", "Quy cách", "Đơn vị"};
        int[] columnWidthPercentage = {5, 10, 35, 10, 10, 10, 10, 10};
        SanPhamBus sanPhamBus = new SanPhamBus();
        List<SanPhamEntity> list = sanPhamBus.getAllSanPham();

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 1) ? ImageIcon.class : Object.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(columns);
        for (SanPhamEntity sanPhamEntity : list) {
            String imgPath = "src/main/resources/img/product/" + sanPhamEntity.getHinhAnh();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imgPath)
                    .getImage().getScaledInstance(80, 55, Image.SCALE_SMOOTH));
            model.addRow(new Object[]{
                    sanPhamEntity.getId(),
                    imageIcon,
                    sanPhamEntity.getTenSanPham(),
                    sanPhamEntity.getPhanLoai().getTenDanhMuc(),
                    sanPhamEntity.getGiaBan(),
                    sanPhamEntity.getSoLuongTon(),
                    sanPhamEntity.getQuyCach(),
                    sanPhamEntity.getDonVi(),
                    });
        }

        table.setModel(model);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (i == 1){
                continue;
            }
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        table.setRowHeight(60);
        table.setDefaultEditor(Object.class, null);
        table.setPreferredScrollableViewportSize(new Dimension(960, 650));
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        int totalWidth = table.getPreferredSize().width;
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(totalWidth * columnWidthPercentage[i] / 100);
        }
        this.add(new JScrollPane(table));
    }}