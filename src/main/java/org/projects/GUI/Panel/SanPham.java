package org.projects.GUI.Panel;
import org.projects.Action.SanPhamAction;
import org.projects.BUS.SanPhamBus;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.utils.PriceRenderer;
import org.projects.GUI.utils.UIUtils;
import org.projects.entity.SanPhamEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.swing.*;
import javax.swing.table.*;

public class SanPham extends JPanel{

    private headerBar header;
    private DefaultTableModel model;
    private JTable table;
    private final SanPhamAction sanPhamAction = new SanPhamAction(this,null);

    private final SanPhamBus sanPhamBus = new SanPhamBus(this);
    private SanPhamEntity sanPhamEntity;

    public SanPham() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(940,1000));
        initComponent();
        centerPanel.add(new JScrollPane(table));
        this.add(header);
        this.add(centerPanel);
        reloadDAO();
    }

    private void initComponent() {
        String[][] listItemHeader = {
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"}
        };
        ArrayList<String> listAction = new ArrayList<>(List.of(new String[]{"them", "sua", "xoa", "chi tiet"}));
        String[] listCbBox = new String[]{"---", "Tên"};
        this.table = new JTable();
        this.header = new headerBar(listItemHeader, listAction, listCbBox);

        String[] columns = {"Hình ảnh", "Tên", "Phân loại", "Giá bán", "Trạng thái"};
        int[] columnWidthPercentage = {10, 30, 20, 20, 20};

        model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 0) ? ImageIcon.class : Object.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (i == 0){
                continue;
            }
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        renderer = new PriceRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(3).setCellRenderer(renderer);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        sorter.setComparator(1, Comparator.comparing(String::toString));
        sorter.setComparator(3, Comparator.comparingDouble(o -> Double.parseDouble(o.toString())));
        table.setRowSorter(sorter);
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

        for(String name : header.getHeaderFunc().getHm().keySet()) {
            header.getHeaderFunc().getHm().get(name).addMouseListener(sanPhamAction);
        }

        //su kien tim kiem
        header.getSearch().getSearchComboBox().addItemListener(sanPhamAction);
        header.getSearch().getSearchField().getDocument().addDocumentListener(sanPhamAction);
        header.getSearch().getSearchButton().addActionListener(sanPhamAction);

        UIUtils.refreshComponent(this);
    }

    public void loadList(List<SanPhamEntity> list) {
        model.setRowCount(0);
        if (list != null) {
            for (SanPhamEntity sanPhamEntity : list) {
                String imgPath = "src/main/resources/img/product/" + sanPhamEntity.getHinhAnh();
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imgPath)
                        .getImage().getScaledInstance(80, 55, Image.SCALE_SMOOTH));
                model.addRow(new Object[]{
                    imageIcon,
                    sanPhamEntity.getTenSanPham(),
                    sanPhamEntity.getPhanLoai().getTenDanhMuc(),
                    sanPhamEntity.getGiaBan()   ,
                    sanPhamEntity.isTrangThai() ? "Đang kinh doanh" : "Ngừng kinh doanh",
                });
            }
        }
    }

    public void reloadDAO() {
        List<SanPhamEntity> listSanPham = sanPhamBus.getAllSanPham();
        loadList(listSanPham);
    }

    private SanPhamEntity getSanPhamEntity() {
        int row = table.getSelectedRow();
        if (row == -1) return null;
        int id = (int) table.getValueAt(row, 0);
        return sanPhamBus.getSanPhamById(id);
    };

    public void searchFunction(String keyword, String textField) {
        if(!keyword.equals("---") && !textField.trim().isEmpty()) {
            List<SanPhamEntity> list = sanPhamBus.searchSanPham(keyword, textField);
            loadList(list);
        } else {
            reloadDAO();
        }
    }

    public headerBar getHeader() {
        return header;
    }
}