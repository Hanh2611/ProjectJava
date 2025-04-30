package org.projects.GUI.Panel.ThongkePack;

import org.jfree.chart.ChartPanel;
import org.projects.Action.ThongKeDoanhThuTheoNgayAction;
import org.projects.Action.ThongKeDoanhThuTheoThangAction;
import org.projects.BUS.ThongKeDoanhThuBUS;
import org.projects.GUI.Chart.ColumnsChart;
import org.projects.GUI.Components.handleComponents;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class thongkedoanhthutheoThang extends JPanel {
    private JPanel header;
    private JLabel thang;
    private JComboBox<String> cbxthang;
    private JLabel nam;
    private JComboBox<String> cbxnam;
    private JButton thongke;
    private JButton reset;

    private JPanel center;
    private JPanel columnsChartPanel;
    private JPanel thangChartPanel;
    private ChartPanel columnsChart;
    private JPanel tablePanel;
    private JTable doanhthutheothangTable;
    private DefaultTableModel doanhthutheothangTableModel;
    private JScrollPane doanhthutheothangScrollPane;

    private ThongKeDoanhThuBUS tkdtBUS= new ThongKeDoanhThuBUS();
    private ThongKeDoanhThuTheoThangAction tkdtAction;

    public thongkedoanhthutheoThang() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        //header
        header = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
        thang = handleComponents.setLabelText("Tháng:");
        cbxthang = new JComboBox<>(new String[]{"Tất cả","1","2","3","4","5","6","7","8","9","10","11","12"});
        cbxthang.setSelectedItem("Tất cả");
        nam = handleComponents.setLabelText("Năm:");
        cbxnam = new JComboBox<>(new String[]{"2020","2021","2022","2023","2024","2025"});
        cbxnam.setSelectedItem("2025");
        thongke = handleComponents.createButton("Thống kê",100,30);
        thongke.setBackground(Color.decode("#2ed573"));
        thongke.setForeground(Color.WHITE);
        reset = handleComponents.createButton("Làm mới",100,30);
        reset.setBackground(Color.decode("#1e90ff"));
        reset.setForeground(Color.WHITE);
        header.add(thang);
        header.add(cbxthang);
        header.add(nam);
        header.add(cbxnam);
        header.add(thongke);
        header.add(reset);
        this.add(header, BorderLayout.NORTH);

        //center
        center = new JPanel(new GridLayout(2,1));
        thangChartPanel = new JPanel(new GridLayout(1,1));
        thangChartPanel.setBackground(Color.WHITE);
        String thang = String.valueOf(cbxthang.getSelectedItem());
        String nam = String.valueOf(cbxnam.getSelectedItem());
        columnsChartPanel = ColumnsChart.createColumnChart2("Doanh thu theo tháng",columnsChart,"Tháng-năm","Doanh thu",tkdtBUS.getthangvatongtien(thang,nam),900,300);
        thangChartPanel.add(columnsChartPanel);

        center.add(thangChartPanel);
//        center.add(tablePanel);
        this.add(center, BorderLayout.CENTER);
        //action
        tkdtAction = new ThongKeDoanhThuTheoThangAction(this,tkdtBUS);
        cbxthang.addItemListener(tkdtAction);
        cbxnam.addItemListener(tkdtAction);
        thongke.addActionListener(tkdtAction);
        reset.addActionListener(tkdtAction);
    }

    public JPanel getHeader() {
        return header;
    }

    public void setHeader(JPanel header) {
        this.header = header;
    }

    public JLabel getThang() {
        return thang;
    }

    public void setThang(JLabel thang) {
        this.thang = thang;
    }

    public JComboBox<String> getCbxthang() {
        return cbxthang;
    }

    public void setCbxthang(JComboBox<String> cbxthang) {
        this.cbxthang = cbxthang;
    }

    public JLabel getNam() {
        return nam;
    }

    public void setNam(JLabel nam) {
        this.nam = nam;
    }

    public JComboBox<String> getCbxnam() {
        return cbxnam;
    }

    public void setCbxnam(JComboBox<String> cbxnam) {
        this.cbxnam = cbxnam;
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

    public JPanel getColumnsChartPanel() {
        return columnsChartPanel;
    }

    public void setColumnsChartPanel(JPanel columnsChartPanel) {
        this.columnsChartPanel = columnsChartPanel;
    }

    public ChartPanel getColumnsChart() {
        return columnsChart;
    }

    public void setColumnsChart(ChartPanel columnsChart) {
        this.columnsChart = columnsChart;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public void setTablePanel(JPanel tablePanel) {
        this.tablePanel = tablePanel;
    }

    public JTable getDoanhthutheothangTable() {
        return doanhthutheothangTable;
    }

    public void setDoanhthutheothangTable(JTable doanhthutheothangTable) {
        this.doanhthutheothangTable = doanhthutheothangTable;
    }

    public DefaultTableModel getDoanhthutheothangTableModel() {
        return doanhthutheothangTableModel;
    }

    public void setDoanhthutheothangTableModel(DefaultTableModel doanhthutheothangTableModel) {
        this.doanhthutheothangTableModel = doanhthutheothangTableModel;
    }

    public JScrollPane getDoanhthutheothangScrollPane() {
        return doanhthutheothangScrollPane;
    }

    public void setDoanhthutheothangScrollPane(JScrollPane doanhthutheothangScrollPane) {
        this.doanhthutheothangScrollPane = doanhthutheothangScrollPane;
    }

    public JPanel getThangChartPanel() {
        return thangChartPanel;
    }

    public void setThangChartPanel(JPanel thangChartPanel) {
        this.thangChartPanel = thangChartPanel;
    }
}
