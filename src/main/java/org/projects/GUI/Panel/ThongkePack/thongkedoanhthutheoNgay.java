package org.projects.GUI.Panel.ThongkePack;

import com.toedter.calendar.JDateChooser;
import org.jfree.chart.ChartPanel;
import org.projects.Action.ThongKeDoanhThuTheoNgayAction;
import org.projects.BUS.ThongKeDoanhThuBUS;
import org.projects.GUI.Chart.ColumnsChart;
import org.projects.GUI.Components.ButtonEditStyle;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.utils.ChangeDateToString;
import org.projects.entity.ThongkeDoanhThuEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class thongkedoanhthutheoNgay extends JPanel {
    private JPanel header;
    private JLabel ngay;
    private JDateChooser datefrom;
    private JLabel den;
    private JDateChooser dateto;
    private JButton thongke;
    private JButton reset;

    private JPanel center;
    private JPanel center1;
    private JPanel columnsPanel;
    private ChartPanel doanhthuChart;

    private JPanel center2;
    private JTable doanhthuTheongayTable;
    private DefaultTableModel doanhthuTheongayTableModel;
    private JScrollPane doanhthuTheongayScrollPane;

    private ThongKeDoanhThuBUS tkdtBUS = new ThongKeDoanhThuBUS();
    private ThongKeDoanhThuTheoNgayAction tkdtAction;

    public thongkedoanhthutheoNgay() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        //header
        header = new JPanel(new FlowLayout(FlowLayout.LEFT,20,5));
        ngay = handleComponents.setLabelText("Ngày:");
        datefrom = handleComponents.createDate(120,25);
        den = handleComponents.setLabelText("Đến:");
        dateto = handleComponents.createDate(120,25);
        thongke = new ButtonEditStyle("Thống kê",Color.decode("#2ed573"),Color.WHITE,100,30);
        reset = new ButtonEditStyle("Làm mới",Color.decode("#1e90ff"),Color.WHITE,100,30);
        header.add(ngay);
        header.add(datefrom);
        header.add(den);
        header.add(dateto);
        header.add(thongke);
        header.add(reset);
        this.add(header, BorderLayout.NORTH);

        //center
        center = new JPanel(new GridLayout(2,1));
        center1 = new JPanel(new GridLayout(1,1));
        center2 = new JPanel(new GridLayout(1,1));
        columnsPanel = ColumnsChart.createColumnChart2("Doanh thu",doanhthuChart,"Ngày","Doanh thu",tkdtBUS.getngayvatongtien(ChangeDateToString.changeDate(datefrom),ChangeDateToString.changeDate(dateto)),900,300);
        center1.add(columnsPanel);
        center.add(center1);


        String[] cols = new String[]{"Mã hóa đơn","Khách hàng","Ngày","Tổng tiền"};
        doanhthuTheongayTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        doanhthuTheongayTableModel.setColumnIdentifiers(cols);
        doanhthuTheongayTable = new JTable();
        doanhthuTheongayTable.setSelectionBackground(new Color(184, 207, 255));
        doanhthuTheongayTable.setSelectionForeground(Color.BLACK);
        doanhthuTheongayTable.setRowHeight(30);
        doanhthuTheongayTable.setFont(new Font("Jetbrains Mono", Font.PLAIN, 10));
        doanhthuTheongayTable.setGridColor(new Color(200,200,200));
        doanhthuTheongayTable.setShowGrid(true);
        doanhthuTheongayTable.setIntercellSpacing(new Dimension(5, 5)); //padding của mỗi ô
        doanhthuTheongayTable.setModel(doanhthuTheongayTableModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        for(int i = 0 ; i < doanhthuTheongayTableModel.getRowCount(); i++){
            doanhthuTheongayTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JTableHeader titleTableheader = doanhthuTheongayTable.getTableHeader();
        titleTableheader.setFont(new Font("Jetbrains Mono", Font.BOLD, 14));
        titleTableheader.setBackground(new Color(100, 149, 237));
        titleTableheader.setForeground(Color.WHITE);
        titleTableheader.setPreferredSize(new Dimension(titleTableheader.getWidth(), 40));
        titleTableheader.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        doanhthuTheongayScrollPane = new JScrollPane(doanhthuTheongayTable);
        doanhthuTheongayScrollPane.setPreferredSize(new Dimension(940, 250));
        center2.add(doanhthuTheongayScrollPane);
        center.add(center2);
        //action
        tkdtAction = new ThongKeDoanhThuTheoNgayAction(this,tkdtBUS);
        thongke.addActionListener(tkdtAction);
        reset.addActionListener(tkdtAction);
        this.add(center, BorderLayout.CENTER);
        loadData();
    }
    public void loadList(List<ThongkeDoanhThuEntity> lst) {
        doanhthuTheongayTableModel.setRowCount(0);
        for(ThongkeDoanhThuEntity tkdtE : lst){
            doanhthuTheongayTableModel.addRow(new Object[]{
                    tkdtE.getMahoadon(),
                    tkdtE.getTenkhachhang(),
                    tkdtE.getNgaytaodon(),
                    tkdtE.getTongtienhoadonformat()
            });
        }
    }
    public void loadData() {
        loadList(tkdtBUS.getdanhsach(ChangeDateToString.changeDate(datefrom),ChangeDateToString.changeDate(dateto)));
    }
    public JPanel getCenter1() {
        return center1;
    }

    public void setCenter1(JPanel center1) {
        this.center1 = center1;
    }

    public JPanel getCenter2() {
        return center2;
    }

    public void setCenter2(JPanel center2) {
        this.center2 = center2;
    }
    public JPanel getHeader() {
        return header;
    }

    public void setHeader(JPanel header) {
        this.header = header;
    }

    public JLabel getNgay() {
        return ngay;
    }

    public void setNgay(JLabel ngay) {
        this.ngay = ngay;
    }

    public JDateChooser getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(JDateChooser datefrom) {
        this.datefrom = datefrom;
    }

    public JLabel getDen() {
        return den;
    }

    public void setDen(JLabel den) {
        this.den = den;
    }

    public JDateChooser getDateto() {
        return dateto;
    }

    public void setDateto(JDateChooser dateto) {
        this.dateto = dateto;
    }

    public JButton getThongke() {
        return thongke;
    }

    public void setThongke(JButton thongke) {
        this.thongke = thongke;
    }

    public JButton getReset() {
        return reset;
    }

    public void setReset(JButton reset) {
        this.reset = reset;
    }

    public JPanel getCenter() {
        return center;
    }

    public void setCenter(JPanel center) {
        this.center = center;
    }

    public JPanel getColumnsPanel() {
        return columnsPanel;
    }

    public void setColumnsPanel(JPanel columnsPanel) {
        this.columnsPanel = columnsPanel;
    }

    public ChartPanel getDoanhthuChart() {
        return doanhthuChart;
    }

    public void setDoanhthuChart(ChartPanel doanhthuChart) {
        this.doanhthuChart = doanhthuChart;
    }
}
