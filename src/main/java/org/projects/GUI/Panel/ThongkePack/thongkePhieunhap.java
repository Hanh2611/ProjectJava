package org.projects.GUI.Panel.ThongkePack;

import com.toedter.calendar.JDateChooser;
import org.jfree.chart.ChartPanel;
import org.projects.BUS.NhaCungCapBUS;
import org.projects.BUS.NhanVienBus;
import org.projects.BUS.ThongKePhieuNhapBUS;
import org.projects.GUI.Chart.ColumnsChart;
import org.projects.GUI.Chart.PieChart;
import org.projects.GUI.Components.handleComponents;
import org.projects.entity.NhaCungCapEntity;
import org.projects.entity.NhanVienEntity;
import org.projects.entity.ThongKePhieuNhapEntity;
import org.projects.entity.ThongKeSanPhamEntity;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class thongkePhieunhap extends JPanel {
    private JPanel west;
    private JPanel headerWest;
    private JLabel fromLabel;
    private JDateChooser dateFrom;
    private JLabel toLabel;
    private JDateChooser dateTo;
    private JLabel tenNcc;
    private JComboBox<String> nccBox;
    private JLabel tenNv;
    private JComboBox<String> nvBox;
    private JButton timkiembtn;
    private JButton resetbtn;
    private JPanel bottomWest;
    private ChartPanel pie;

    private JPanel center;
    private JPanel contentChart;
    private ChartPanel columns;
    private JPanel tableData;
    private JTable thongkeTable;
    private DefaultTableModel thongkeTableModel;
    private JScrollPane thongkeScroll;

    private ThongKePhieuNhapBUS tkpnBUS = new ThongKePhieuNhapBUS();
    public thongkePhieunhap() {
        this.setLayout(new BorderLayout(10,10));
        this.setPreferredSize(new Dimension(940, 1000));
        this.setBackground(Color.WHITE);

        //west
        west = new JPanel(new GridLayout(2,1,5,5));
        west.setPreferredSize(new Dimension(350,1000));
        west.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        west.setBackground(Color.WHITE);
        headerWest = new JPanel();
        headerWest.setLayout(new BoxLayout(headerWest,BoxLayout.Y_AXIS));
        JPanel datePanel = new JPanel(new GridLayout(2,2));
        fromLabel = handleComponents.setLabelText("Từ ngày: ");
        dateFrom = handleComponents.createDate(100,25);
        toLabel = handleComponents.setLabelText("Đến ngày: ");
        dateTo = handleComponents.createDate(100,25);
        datePanel.add(fromLabel);
        datePanel.add(dateFrom);
        datePanel.add(toLabel);
        datePanel.add(dateTo);
        JPanel tenNccPanel = new JPanel(new GridLayout(2,1));
        tenNcc = handleComponents.setLabelText("Nhà cung cấp: ");
        nccBox = new JComboBox<>();
        nccBox.addItem("Tất cả");
        for(NhaCungCapEntity ncce: NhaCungCapBUS.getList()) {
            nccBox.addItem(ncce.getTenNCC());
        }
        nccBox.setSelectedItem("Tất cả");
        tenNccPanel.add(tenNcc);
        tenNccPanel.add(nccBox);
        JPanel tenNvPanel = new JPanel(new GridLayout(2,1));
        tenNv = handleComponents.setLabelText("Nhân viên: ");
        nvBox = new JComboBox<>();
        nvBox.addItem("Tất cả");
        for(NhanVienEntity nve : NhanVienBus.getList()) {
            nvBox.addItem(nve.getTenNhanVien());
        }
        nvBox.setSelectedItem("Tất cả");
        tenNvPanel.add(tenNv);
        tenNvPanel.add(nvBox);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        timkiembtn = handleComponents.createButton("Tìm",70,25);
        timkiembtn.setBackground(Color.decode("#55efc4"));
        resetbtn = handleComponents.createButton("Reset",70,25);
        resetbtn.setBackground(Color.decode("#0984e3"));
        buttonPanel.add(timkiembtn);
        buttonPanel.add(resetbtn);

        headerWest.add(datePanel);
        headerWest.add(tenNccPanel);
        headerWest.add(tenNvPanel);
        headerWest.add(buttonPanel);

        bottomWest = PieChart.createPieChart("Tổng lượng hàng nhập theo nhà cung cấp",pie,tkpnBUS.getnhacungcapvasoluong(),350,1000);

        west.add(headerWest);
        west.add(bottomWest);

        center = new JPanel(new GridLayout(2,1));
        contentChart = ColumnsChart.createColumnChart2("Tổng giá trị nhập theo ngày",columns,"Ngày","Tổng giá trị",tkpnBUS.gettonggiatriphieunhap(),550,300);
        center.add(contentChart);
        tableData = new JPanel(new BorderLayout());
        String[] cols = new String[]{"Ngày nhập","số lượng","Tổng giá trị"};
        thongkeTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        thongkeTableModel.setColumnIdentifiers(cols);
        thongkeTable = new JTable();
        thongkeTable.setSelectionBackground(new Color(184, 207, 255));
        thongkeTable.setSelectionForeground(Color.BLACK);
        thongkeTable.setRowHeight(40);
        thongkeTable.setFont(new Font("Jetbrains Mono", Font.PLAIN, 10));
        thongkeTable.setGridColor(new Color(200,200,200));
        thongkeTable.setShowGrid(true);
        thongkeTable.setIntercellSpacing(new Dimension(5, 5)); //padding của mỗi ô
        thongkeTable.setModel(thongkeTableModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        for(int i = 0 ; i < thongkeTableModel.getRowCount(); i++){
            thongkeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JTableHeader titleTableheader = thongkeTable.getTableHeader();
        titleTableheader.setFont(new Font("Jetbrains Mono", Font.BOLD, 10));
        titleTableheader.setBackground(new Color(100, 149, 237));
        titleTableheader.setForeground(Color.WHITE);
        titleTableheader.setPreferredSize(new Dimension(titleTableheader.getWidth(), 25));
        titleTableheader.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        thongkeScroll = new JScrollPane(thongkeTable);
        tableData.add(thongkeScroll,BorderLayout.CENTER);
        center.add(tableData);

        this.add(west, BorderLayout.WEST);
        this.add(center, BorderLayout.CENTER);
        loadData();
    }

    public void loadList(List<ThongKePhieuNhapEntity> list) {
        thongkeTableModel.setRowCount(0);
        for(ThongKePhieuNhapEntity tkpnE : list) {
            thongkeTableModel.addRow(new Object[]{
                    tkpnE.getNgaynhap(),
                    tkpnE.getSoluong(),
                    tkpnE.getTonggiatri()
            });
        }
    }
    public void loadData() {
        loadList(tkpnBUS.showlist());
    }

}
