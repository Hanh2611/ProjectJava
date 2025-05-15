package org.projects.GUI.Panel.ThongkePack;

import org.jfree.chart.ChartPanel;
import org.projects.Action.ThongKeDoanhThuTheoNamAction;
import org.projects.BUS.ThongKeDoanhThuBUS;
import org.projects.GUI.Chart.ColumnsChart;
import org.projects.GUI.Components.ButtonEditStyle;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.utils.UIUtils;
import org.projects.entity.ThongkeDoanhThuEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class thongkedoanhthutheoNam extends JPanel {
    private JPanel header;
    private JLabel nam;
    private JComboBox<String> cbxnam;
    private JButton thongke;
    private JButton reset;

    private JPanel center;
    private JPanel columnsChartPanel;
    private JPanel namChartPanel;
    private ChartPanel columnsChart;
    private JPanel tablePanel;
    private JTable doanhthutheonamTable;
    private DefaultTableModel doanhthutheonamTableModel;
    private JScrollPane doanhthutheonamScrollPane;

    private final ThongKeDoanhThuBUS tkdtBUS;
    private final ThongKeDoanhThuTheoNamAction tkdtAction;
    public thongkedoanhthutheoNam() {
        tkdtBUS = new ThongKeDoanhThuBUS();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        //header
        header = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
        this.add(header, BorderLayout.NORTH);

        center = new JPanel(new GridLayout(2,1));
        this.add(center, BorderLayout.CENTER);
        init();
        //action
        tkdtAction = new ThongKeDoanhThuTheoNamAction(this,tkdtBUS);
        cbxnam.addItemListener(tkdtAction);
        thongke.addActionListener(tkdtAction);
        reset.addActionListener(tkdtAction);

    }

    public void init() {
        header.removeAll();
        center.removeAll();

        nam = handleComponents.setLabelText("Năm:");
        cbxnam = new JComboBox<>(new String[]{"Tất cả","2020","2021","2022","2023","2024","2025","2026"});
        cbxnam.setSelectedItem("Tất cả");
        thongke = new ButtonEditStyle("Thống kê",Color.decode("#2ed573"),Color.WHITE,100,30);
        reset = new ButtonEditStyle("Làm mới",Color.decode("#1e90ff"),Color.WHITE,100,30);
        header.add(nam);
        header.add(cbxnam);
        header.add(thongke);
        header.add(reset);

        namChartPanel  = new JPanel(new GridLayout(1,1));
        namChartPanel.setBackground(Color.WHITE);
        String nam = String.valueOf(cbxnam.getSelectedItem());
        columnsChartPanel = ColumnsChart.createColumnChart2("Doanh thu theo năm",columnsChart,"Năm","Doanh thu",tkdtBUS.getnamvatongtien(nam),900,300);
        namChartPanel.add(columnsChartPanel);


        tablePanel = new JPanel(new GridLayout(1,1));
        String[] cols = new String[]{"Năm","Tổng tiền","Chi phí nhập","lợi nhuận"};
        doanhthutheonamTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        doanhthutheonamTableModel.setColumnIdentifiers(cols);
        doanhthutheonamTable = new JTable();
        doanhthutheonamTable.setSelectionBackground(new Color(184, 207, 255));
        doanhthutheonamTable.setSelectionForeground(Color.BLACK);
        doanhthutheonamTable.setRowHeight(30);
        doanhthutheonamTable.setFont(new Font("Jetbrains Mono", Font.PLAIN, 10));
        doanhthutheonamTable.setGridColor(new Color(200,200,200));
        doanhthutheonamTable.setShowGrid(true);
        doanhthutheonamTable.setIntercellSpacing(new Dimension(5, 5)); //padding của mỗi ô
        doanhthutheonamTable.setModel(doanhthutheonamTableModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        for(int i = 0 ; i < doanhthutheonamTableModel.getRowCount(); i++){
            doanhthutheonamTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JTableHeader titleTableheader = doanhthutheonamTable.getTableHeader();
        titleTableheader.setFont(new Font("Jetbrains Mono", Font.BOLD, 14));
        titleTableheader.setBackground(new Color(100, 149, 237));
        titleTableheader.setForeground(Color.WHITE);
        titleTableheader.setPreferredSize(new Dimension(titleTableheader.getWidth(), 40));
        titleTableheader.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        doanhthutheonamScrollPane = new JScrollPane(doanhthutheonamTable);
        doanhthutheonamScrollPane.setPreferredSize(new Dimension(940, 250));
        tablePanel.add(doanhthutheonamScrollPane);

        center.add(namChartPanel);
        center.add(tablePanel);
        loadData(nam);
        UIUtils.refreshComponent(this);
    }

    public void loadList(List<ThongkeDoanhThuEntity> lst) {
        doanhthutheonamTableModel.setRowCount(0);
        for(ThongkeDoanhThuEntity tkdtE : lst){
            doanhthutheonamTableModel.addRow(new Object[]{
                    tkdtE.getNam(),
                    tkdtE.getTongtienhoadonformat(),
                    tkdtE.getTongchiphinhaptrongthangformat(),
                    tkdtE.getLoinhuanformat()
            });
        }
    }

    public void loadData(String nam) {
        loadList(tkdtBUS.laydanhsachtheonam(nam));
    }

    public JPanel getHeader() {
        return header;
    }

    public void setHeader(JPanel header) {
        this.header = header;
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

    public JPanel getNamChartPanel() {
        return namChartPanel;
    }

    public void setNamChartPanel(JPanel namChartPanel) {
        this.namChartPanel = namChartPanel;
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

    public JTable getDoanhthutheonamTable() {
        return doanhthutheonamTable;
    }

    public void setDoanhthutheonamTable(JTable doanhthutheonamTable) {
        this.doanhthutheonamTable = doanhthutheonamTable;
    }

    public DefaultTableModel getDoanhthutheonamTableModel() {
        return doanhthutheonamTableModel;
    }

    public void setDoanhthutheonamTableModel(DefaultTableModel doanhthutheonamTableModel) {
        this.doanhthutheonamTableModel = doanhthutheonamTableModel;
    }

    public JScrollPane getDoanhthutheonamScrollPane() {
        return doanhthutheonamScrollPane;
    }

    public void setDoanhthutheonamScrollPane(JScrollPane doanhthutheonamScrollPane) {
        this.doanhthutheonamScrollPane = doanhthutheonamScrollPane;
    }
}
