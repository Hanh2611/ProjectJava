package org.projects.GUI.Panel;
import org.projects.Action.SanPhamAction;
import org.projects.BUS.PhanQuyenBUS;
import org.projects.BUS.SanPhamBus;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.utils.Helper;
import org.projects.GUI.utils.Session;
import org.projects.GUI.utils.UIUtils;
import org.projects.entity.Enum.QuyCach;
import org.projects.entity.SanPhamEntity;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

public class SanPham extends JPanel{

    private headerBar header;
    private DefaultTableModel model;
    private JTable table;
    private final SanPhamAction sanPhamAction = new SanPhamAction(this);

    private final SanPhamBus sanPhamBus = new SanPhamBus(this);

    public SanPham() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(940,650));
        initComponent();
        this.add(header);
        centerPanel.add(new JScrollPane(table), BorderLayout.CENTER);
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
        String[] listCbBox = new String[]{"---", "Mã", "Tên", "Phân loại"};
        this.table = new JTable();
        header = new headerBar(listItemHeader,  Session.quyenTaiKhoan.get(PhanQuyenBUS.getMaDanhMuc("SanPham") - 1), listCbBox);
//        header = new headerBar(listItemHeader,new ArrayList<>(Arrays.asList("add", "update", "delete", "detail")),listCbBox);

        String[] columns = {"Mã", "Hình ảnh", "Tên", "Phân loại", "Giá bán", "Trạng thái"};
        int[] columnWidthPercentage = {2, 8, 35, 20, 15, 20};

        model = new DefaultTableModel() {
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
        table.setModel(model);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (i == 1){
                continue;
            }
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        sorter.setComparator(2, Comparator.comparing(String::toString));
        sorter.setComparator(4, Comparator.comparingDouble(o -> Double.parseDouble(o.toString())));
        table.setRowSorter(sorter);
        table.setRowHeight(60);
        table.setDefaultEditor(Object.class, null);
        table.setPreferredScrollableViewportSize(new Dimension(900, 650));
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
        header.getSearch().getSearchField().addKeyListener(sanPhamAction);
        header.getSearch().getSearchButton().addActionListener(sanPhamAction);

        UIUtils.refreshComponent(this);
    }

    public void loadList(List<SanPhamEntity> list) {
        model.setRowCount(0);
        if (list != null) {
            for (SanPhamEntity sanPhamEntity : list) {
                String imgPath = Helper.getProductImagePath(sanPhamEntity.getHinhAnh());
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imgPath)
                        .getImage().getScaledInstance(80, 55, Image.SCALE_SMOOTH));
                model.addRow(new Object[]{
                    sanPhamEntity.getId(),
                    imageIcon,
                    sanPhamEntity.getTenSanPham(),
                    sanPhamEntity.getPhanLoai().getTenDanhMuc(),
                    (sanPhamEntity.getQuyCach().equals(QuyCach.KG) || sanPhamEntity.getQuyCach().equals(QuyCach.G))?
                            Helper.formatPrice(sanPhamEntity.getGiaBan()) + "/" + sanPhamEntity.getQuyCach().getValue():
                            Helper.formatPrice(sanPhamEntity.getGiaBan()) + "/" + sanPhamEntity.getQuyCach().getValue() + "/" + sanPhamEntity.getDonVi(),
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

    public SanPhamEntity getSelectedRow() {
        int row = table.getSelectedRow();
        if(row == -1) return null;
        int ma = (int) table.getValueAt(row,0);
        return sanPhamBus.getSanPhamById(ma);
    }

    public headerBar getHeader() {
        return header;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public SanPhamAction getSanPhamAction() {
        return sanPhamAction;
    }

    public SanPhamBus getSanPhamBus() {
        return sanPhamBus;
    }
}