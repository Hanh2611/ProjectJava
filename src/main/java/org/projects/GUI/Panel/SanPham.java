package org.projects.GUI.Panel;

import org.projects.entity.Enum.QuyCach;
import org.projects.entity.SanPhamEntity;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class SanPham extends JPanel{

    private JTable table;
    private JPanel header;
    private ArrayList<SanPhamEntity> listSanPham;

    public SanPham() {
        initComponent();
        this.setBackground(Color.BLACK);
    }

    private void initComponent() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.table = new JTable();
        this.header = new JPanel();
        this.header.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JButton btnAdd = new JButton("Thêm");
        JButton btnUpdate = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnDetail = new JButton("Chi tiết");
        header.add(btnAdd);
        header.add(btnUpdate);
        header.add(btnDelete);
        header.add(btnDetail);

        String[] columns = {"Id", "Hình ảnh", "Tên", "Phân loại", "Giá bán", "Số lượng tồn", "Quy cách", "Đơn vị"};
        int[] columnWidthPercentage = {5, 10, 35, 10, 10, 10, 10, 10};
        ArrayList<SanPhamEntity> list = new ArrayList<>();
        list.add(new SanPhamEntity(1, "Trà xanh C2 hương chanh 360ml", "Nước giải khát", "360ml", 8000.0, 100.0, QuyCach.CHAI, "traxanhc2hngchanh360ml.jpg"));
        list.add(new SanPhamEntity(2, "Trà xanh C2 hương chanh 500ml",  "Nước giải khát", "500ml", 10000, 100, QuyCach.CHAI, "traxanhc2hngchanh500ml.jpg"));

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
                    sanPhamEntity.getPhanLoai(),
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
        table.setPreferredScrollableViewportSize(new Dimension(950, 630));
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        int totalWidth = table.getPreferredSize().width;
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(totalWidth * columnWidthPercentage[i] / 100);
        }
        this.add(header);
        this.add(new JScrollPane(table));
    }}