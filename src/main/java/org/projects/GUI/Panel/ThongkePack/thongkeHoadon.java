package org.projects.GUI.Panel.ThongkePack;

import com.toedter.calendar.JDateChooser;
import org.jfree.chart.ChartPanel;
import org.projects.Action.ThongKeHoaDonAction;
import org.projects.BUS.ThongKeHoaDonBUS;
import org.projects.GUI.Chart.ColumnsChart;
import org.projects.GUI.Components.ButtonEditStyle;
import org.projects.GUI.Components.PanelBorderRadius;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.utils.ChangeDateToString;
import org.projects.GUI.utils.UIUtils;
import org.projects.entity.ThongkeHoaDonEntity;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class thongkeHoadon extends JPanel {
    private JPanel header;
    private JLabel ngayLabel;
    private JDateChooser dateFrom;
    private JLabel denLabel;
    private JDateChooser dateTo;
    private JLabel trangthailLabel;
    private JComboBox<String> cbxtrangthai;
    private JButton loctrangthai;
    private JButton reset;

    private JPanel center;
    private JPanel center1;
    private ChartPanel hoadonChart;
    private HashMap<String,Integer> soluonghoadontheongay;

    private JPanel bottom;
    private JTable hoadonTable;
    private DefaultTableModel hoadonTableModel;
    private JScrollPane hoadonScrollPane;

    private ThongKeHoaDonBUS tkhdBUS;
    private ThongKeHoaDonAction tkhdAction;
    public thongkeHoadon() {
        this.setLayout(new BorderLayout(10,10));
        this.setPreferredSize(new Dimension(940, 1000));
        //header
        header = new PanelBorderRadius();
        header.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        header.setBackground(Color.WHITE);

        tkhdBUS = new ThongKeHoaDonBUS();
        initComponent();

        //center
        center = new JPanel(new BorderLayout());

        bottom = new JPanel(new BorderLayout());

        this.add(header, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);
        init();
        //action
        tkhdAction = new ThongKeHoaDonAction(this,tkhdBUS);
        cbxtrangthai.addItemListener(tkhdAction);
        loctrangthai.addActionListener(tkhdAction);
        reset.addActionListener(tkhdAction);
    }

    public void initComponent() {
        ngayLabel = new JLabel("Ngày: ");
        ngayLabel.setFont(new Font("Jetbrains Mono ", Font.BOLD, 14));
        dateFrom = new JDateChooser();
        dateFrom.setDateFormatString("yyyy-MM-dd");
        dateFrom.setFont(new Font("Jetbrains Mono", Font.PLAIN, 13));
        dateFrom.setPreferredSize(new Dimension(150, 30));

        denLabel = new JLabel("Đến: ");
        denLabel.setFont(new Font("Jetbrains Mono ", Font.BOLD, 14));
        dateTo = new JDateChooser();
        dateTo.setDateFormatString("yyyy-MM-dd");
        dateTo.setFont(new Font("Jetbrains Mono", Font.PLAIN, 13));
        dateTo.setPreferredSize(new Dimension(150, 30));

        trangthailLabel = new JLabel("Trạng thái: ");
        trangthailLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 14));
        cbxtrangthai = new JComboBox<>(new String[]{"Tất cả","da_thanh_toan","chua_thanh_toan"});

        loctrangthai = new ButtonEditStyle("Lọc",Color.decode("#55efc4"),Color.WHITE,100,30);

        reset = new ButtonEditStyle("Làm mới",Color.decode("#00a8ff"),Color.WHITE,100,30);

        String from = ChangeDateToString.changeDate(dateFrom.getDate());
        String to = ChangeDateToString.changeDate(dateTo.getDate());
        String trangthai = cbxtrangthai.getSelectedItem().toString();
        soluonghoadontheongay  = tkhdBUS.getSLHDtheongay(from,to,trangthai);
    }

    public void init() {

        header.removeAll();
        center.removeAll();
        bottom.removeAll();

        header.add(ngayLabel);
        header.add(dateFrom);
        header.add(denLabel);
        header.add(dateTo);
        header.add(trangthailLabel);
        header.add(cbxtrangthai);
        header.add(loctrangthai);
        header.add(reset);

        center1 = ColumnsChart.createColumnsChart("Số lượng hóa đơn theo ngày",hoadonChart,"Ngày-tháng-năm","Số lượng",soluonghoadontheongay,800,500);
        center.add(center1,BorderLayout.CENTER);

        String[] cols = new String[]{"Ngày","Số lượng hóa đơn","Trạng thái"};
        hoadonTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        hoadonTableModel.setColumnIdentifiers(cols);
        hoadonTable = new JTable();
        hoadonTable.setSelectionBackground(new Color(184, 207, 255));
        hoadonTable.setSelectionForeground(Color.BLACK);
        hoadonTable.setRowHeight(30);
        hoadonTable.setFont(new Font("Jetbrains Mono", Font.PLAIN, 10));
        hoadonTable.setGridColor(new Color(200,200,200));
        hoadonTable.setShowGrid(true);
        hoadonTable.setIntercellSpacing(new Dimension(5, 5)); //padding của mỗi ô
        hoadonTable.setModel(hoadonTableModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        for(int i = 0 ; i < hoadonTableModel.getRowCount(); i++){
            hoadonTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JTableHeader titleTableheader = hoadonTable.getTableHeader();
        titleTableheader.setFont(new Font("Jetbrains Mono", Font.BOLD, 14));
        titleTableheader.setBackground(new Color(100, 149, 237));
        titleTableheader.setForeground(Color.WHITE);
        titleTableheader.setPreferredSize(new Dimension(titleTableheader.getWidth(), 40));
        titleTableheader.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        hoadonScrollPane = new JScrollPane(hoadonTable);
        hoadonScrollPane.setBorder(new EmptyBorder(10,10,10,10));
        hoadonScrollPane.setPreferredSize(new Dimension(940, 250));
        bottom.add(hoadonScrollPane,BorderLayout.CENTER);
        loadData();
        UIUtils.refreshComponent(this);

    }

    public void loadlist(List<ThongkeHoaDonEntity> list) {
        hoadonTableModel.setRowCount(0);
        for(ThongkeHoaDonEntity tkhdE :list){
            hoadonTableModel.addRow(new Object[]{
               tkhdE.getNgaytao(),
               tkhdE.getSoluong(),
               tkhdE.getTrangthai()
            });
        }
    }
    public void loadData() {
        loadlist(tkhdBUS.getList());
    }

    public JDateChooser getDateFrom() {
        return dateFrom;
    }

    public JDateChooser getDateTo() {
        return dateTo;
    }

    public HashMap<String, Integer> getSoluonghoadontheongay() {
        return soluonghoadontheongay;
    }

    public void setSoluonghoadontheongay(HashMap<String, Integer> soluonghoadontheongay) {
        this.soluonghoadontheongay = soluonghoadontheongay;
    }

    public JButton getLoctrangthai() {
        return loctrangthai;
    }

    public JPanel getCenter() {
        return center;
    }

    public void setCenter(JPanel center) {
        this.center = center;
    }

    public ChartPanel getHoadonChart() {
        return hoadonChart;
    }

    public JComboBox<String> getCbxtrangthai() {
        return cbxtrangthai;
    }

    public JButton getReset() {
        return reset;
    }

    public JPanel getCenter1() {
        return center1;
    }

    public void setCenter1(JPanel center1) {
        this.center1 = center1;
    }
}
