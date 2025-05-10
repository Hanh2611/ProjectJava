package org.projects.GUI.Panel.ThongkePack;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.projects.Action.ThongKeSanPhamAction;
import org.projects.BUS.ThongKeSanPhamBUS;
import org.projects.GUI.Chart.PieChart;
import org.projects.GUI.Components.ButtonEditStyle;
import org.projects.GUI.Components.handleComponents;
import org.projects.entity.ThongKeSanPhamEntity;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class thongkeSanpham extends JPanel {
    private JPanel centerPanel;
    private JPanel sanphamPanel;
    private ChartPanel sanphamChart;
    private HashMap<String,Integer> sanphamhm;
    private JPanel notePanel;
    private JLabel tongquanLabel;
    private JLabel tongtonLabel;

    private JPanel bottomPanel;
    private JPanel headerBottom;
    private JComboBox<String> danhmucsanphamComboBox;
    private List<String> listtendanhmuc;
    private JButton btnExcel;
    private JTable sanphamTable;
    private JScrollPane sanphamScroll;
    private DefaultTableModel tableModel;
    private ThongKeSanPhamBUS tkspBUS = new ThongKeSanPhamBUS();
    private ThongKeSanPhamAction tkspAction;
    public thongkeSanpham() {
        this.setLayout(new BorderLayout(10,10));
        this.setPreferredSize(new Dimension(940, 1000));
        //center
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        sanphamPanel = PieChart.createPieChart("Tỉ lệ sản phẩm theo danh mục sản phẩm",sanphamChart,tkspBUS.getDanhmucvasoluongsp(),650,400);
        centerPanel.add(sanphamPanel);

        notePanel = new JPanel();
        notePanel.setPreferredSize(new Dimension(150, 150));
        tongquanLabel = new JLabel("Tổng quan tồn kho:");
        tongtonLabel = new JLabel("Tổng tồn: " + String.valueOf(tkspBUS.getSoluongtonsp()) + "Sản phẩm");
        tongquanLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 14));
        tongtonLabel.setFont(new Font("Jetbrains Mono", Font.PLAIN, 12));
        tongtonLabel.setForeground(new Color(50, 150, 50));
        notePanel.add(tongquanLabel);
        notePanel.add(Box.createVerticalStrut(10));  // Thêm khoảng cách giữa các label
        notePanel.add(tongtonLabel);
        centerPanel.add(notePanel);
        this.add(centerPanel, BorderLayout.CENTER);

        //bottom
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(940,300));
        headerBottom = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        danhmucsanphamComboBox = new JComboBox<>();
        danhmucsanphamComboBox.addItem("Tất cả");
        listtendanhmuc = tkspBUS.getTendanhmuc();
        for(String i : listtendanhmuc){
            danhmucsanphamComboBox.addItem(i);
        }
        danhmucsanphamComboBox.setSelectedItem("Tất cả");
        btnExcel = new ButtonEditStyle("Xuất Excel",Color.decode("#2ecc71"),Color.WHITE,100,30);
        headerBottom.add(danhmucsanphamComboBox);
        headerBottom.add(btnExcel);
        bottomPanel.add(headerBottom, BorderLayout.NORTH);
        String[] cols = new String[]{"Mã SP","Tên SP","Danh mục","Số lượng tồn","Giá bán","Trạng thái tồn kho"};
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(cols);
        sanphamTable = new JTable();
        sanphamTable.setSelectionBackground(new Color(184, 207, 255));
        sanphamTable.setSelectionForeground(Color.BLACK);
        sanphamTable.setRowHeight(40);
        sanphamTable.setFont(new Font("Jetbrains Mono", Font.PLAIN, 10));
        sanphamTable.setGridColor(new Color(200,200,200));
        sanphamTable.setShowGrid(true);
        sanphamTable.setIntercellSpacing(new Dimension(5, 5)); //padding của mỗi ô
        sanphamTable.setModel(tableModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        for(int i = 0 ; i < tableModel.getRowCount(); i++){
            sanphamTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JTableHeader titleTableheader = sanphamTable.getTableHeader();
        titleTableheader.setFont(new Font("Jetbrains Mono", Font.BOLD, 10));
        titleTableheader.setBackground(new Color(100, 149, 237));
        titleTableheader.setForeground(Color.WHITE);
        titleTableheader.setPreferredSize(new Dimension(titleTableheader.getWidth(), 40));
        titleTableheader.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        sanphamScroll = new JScrollPane(sanphamTable);
        sanphamScroll.setBorder(new EmptyBorder(10,10,10,10));
        bottomPanel.add(sanphamScroll,BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.SOUTH);
        loadData();

        //action
        tkspAction = new ThongKeSanPhamAction(this,tkspBUS);
        danhmucsanphamComboBox.addItemListener(tkspAction);
        btnExcel.addActionListener(tkspAction);
    }

    public void loadList(List<ThongKeSanPhamEntity> list) {
        tableModel.setRowCount(0);
        for(ThongKeSanPhamEntity tkspE : list) {
            tableModel.addRow(new Object[]{
                    tkspE.getMaSanPham(),
                    tkspE.getTenSanPham(),
                    tkspE.getTenDanhMuc(),
                    tkspE.getSoLuongTon(),
                    tkspE.getGiaBan(),
                    tkspE.getTrangThai()
            });
        }
    }
    public void loadData() {
        loadList(tkspBUS.showlist());
    }

    public JTable getSanphamTable() {
        return sanphamTable;
    }
}
