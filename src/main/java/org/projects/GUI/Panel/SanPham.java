package org.projects.GUI.Panel;
import org.projects.Action.SanPhamAction;
import org.projects.BUS.SanPhamBus;
import org.projects.GUI.Components.header.headerBar;
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
    private headerBar header;
    private List<SanPhamEntity> listSanPham;

    public SanPham() {
        initComponent();
    }

    private void initComponent() {
        String[][] listItemHeader = {
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"}
        };
        String[] quyen = new String[]{"add", "update", "delete", "detail"};
        this.table = new JTable();
        this.header = new headerBar(listItemHeader, quyen);
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
        table.setShowGrid(true);
        table.setGridColor(new Color(220, 220, 220));
        int totalWidth = table.getPreferredSize().width;
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(totalWidth * columnWidthPercentage[i] / 100);
        }
        this.add(new JScrollPane(table));
        for(String name : header.getHeaderFunc().getHm().keySet()) {
            header.getHeaderFunc().getHm().get(name).addMouseListener(new SanPhamAction(this, null));
        }
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public headerBar getHeader() {
        return header;
    }

    public void setHeader(headerBar header) {
        this.header = header;
    }

    public List<SanPhamEntity> getListSanPham() {
        return listSanPham;
    }

    public void setListSanPham(List<SanPhamEntity> listSanPham) {
        this.listSanPham = listSanPham;
    }
}