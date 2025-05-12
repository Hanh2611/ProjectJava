package org.projects.Action;

import org.projects.BUS.ThongKeDoanhThuBUS;
import org.projects.GUI.Chart.ColumnsChart;
import org.projects.GUI.Panel.ThongkePack.thongkedoanhthutheoThang;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

public class ThongKeDoanhThuTheoThangAction implements ActionListener, ItemListener {
    private final thongkedoanhthutheoThang tkdtTheoThang;
    private final ThongKeDoanhThuBUS tkdtBUS;
    public ThongKeDoanhThuTheoThangAction(thongkedoanhthutheoThang tkdtTheoThang,ThongKeDoanhThuBUS tkdtBUS) {
        this.tkdtTheoThang = tkdtTheoThang;
        this.tkdtBUS = tkdtBUS;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tkdtTheoThang.getThongke()) {
            updateChartAndTableMonth();
        } else if (e.getSource() == tkdtTheoThang.getReset()) {
            tkdtTheoThang.getCbxthang().setSelectedItem("Tất cả");
            tkdtTheoThang.getCbxnam().setSelectedItem("Tất cả");
            updateChartAndTableMonth();
        } 
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            String thang =String.valueOf(tkdtTheoThang.getCbxthang().getSelectedItem());
            String nam = String.valueOf(tkdtTheoThang.getCbxnam().getSelectedItem());
            tkdtTheoThang.getCbxthang().setSelectedItem(thang);
            tkdtTheoThang.getCbxnam().setSelectedItem(nam);
        }
    }

    public void updateChartAndTableMonth() {
        String thang = String.valueOf(tkdtTheoThang.getCbxthang().getSelectedItem());
        String nam = String.valueOf(tkdtTheoThang.getCbxnam().getSelectedItem());
        tkdtTheoThang.getThangChartPanel().removeAll();
        JPanel panel = ColumnsChart.createColumnChart2("Doanh thu theo tháng",tkdtTheoThang.getColumnsChart(),"Tháng-năm","Doanh thu",tkdtBUS.getthangvatongtien(thang,nam),900,300);
        tkdtTheoThang.getThangChartPanel().add(panel);
        tkdtTheoThang.loadData(thang,nam);
        tkdtTheoThang.revalidate();
        tkdtTheoThang.repaint();
    }
}
