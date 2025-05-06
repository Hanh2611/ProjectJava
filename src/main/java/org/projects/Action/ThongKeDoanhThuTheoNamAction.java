package org.projects.Action;

import org.jfree.chart.ChartPanel;
import org.projects.BUS.ThongKeDoanhThuBUS;
import org.projects.GUI.Chart.ColumnsChart;
import org.projects.GUI.Panel.ThongkePack.thongkedoanhthutheoNam;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ThongKeDoanhThuTheoNamAction implements ActionListener, ItemListener {
    private thongkedoanhthutheoNam tkdtTheoNam;
    private ThongKeDoanhThuBUS tkdtBUS;
    public ThongKeDoanhThuTheoNamAction(thongkedoanhthutheoNam tkdtTheoNam,ThongKeDoanhThuBUS tkdtBUS) {
        this.tkdtTheoNam = tkdtTheoNam;
        this.tkdtBUS = tkdtBUS;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tkdtTheoNam.getThongke()) {
            updateChartandTable();
        } else if(e.getSource() == tkdtTheoNam.getReset()) {
            tkdtTheoNam.getCbxnam().setSelectedItem("Tất cả");
            updateChartandTable();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        tkdtTheoNam.getCbxnam().setSelectedItem(e.getItem().toString());
    }

    public void updateChartandTable() {
        String nam = String.valueOf(tkdtTheoNam.getCbxnam().getSelectedItem());
        tkdtTheoNam.getNamChartPanel().removeAll();
        JPanel panel = ColumnsChart.createColumnChart2("Doanh thu theo năm",tkdtTheoNam.getColumnsChart(),"Năm","Doanh thu",tkdtBUS.getnamvatongtien(nam),900,300);
        tkdtTheoNam.getNamChartPanel().add(panel);
        tkdtTheoNam.loadData(nam);
        tkdtTheoNam.revalidate();
        tkdtTheoNam.repaint();
    }
}
