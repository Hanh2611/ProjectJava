package org.projects.Action;

import org.projects.BUS.ThongKeDoanhThuBUS;
import org.projects.GUI.Chart.ColumnsChart;
import org.projects.GUI.Panel.ThongkePack.thongkedoanhthutheoNam;
import org.projects.GUI.Panel.ThongkePack.thongkedoanhthutheoNgay;
import org.projects.GUI.Panel.ThongkePack.thongkedoanhthutheoThang;
import org.projects.GUI.utils.ChangeDateToString;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ThongKeDoanhThuTheoNgayAction implements ActionListener {
    private thongkedoanhthutheoNgay tkdtTheoNgay;
    private ThongKeDoanhThuBUS tkdtBUS;
    public ThongKeDoanhThuTheoNgayAction(thongkedoanhthutheoNgay tkdtTheoNgay,ThongKeDoanhThuBUS tkdtBUS) {
        this.tkdtTheoNgay = tkdtTheoNgay;
        this.tkdtBUS = tkdtBUS;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //thống kê theo ngày
        if(e.getSource() == tkdtTheoNgay.getThongke()) {
            updateChartAndTableDay();
        } else if (e.getSource() == tkdtTheoNgay.getReset()) {
            tkdtTheoNgay.getDatefrom().setDate(null);
            tkdtTheoNgay.getDateto().setDate(null);
            updateChartAndTableDay();
        }
    }

    public void updateChartAndTableDay() {
        String from = ChangeDateToString.changeDate(tkdtTheoNgay.getDatefrom());
        String to = ChangeDateToString.changeDate(tkdtTheoNgay.getDateto());
        tkdtTheoNgay.getCenter1().removeAll();
        JPanel newChartPanel = ColumnsChart.createColumnChart2("Doanh thu",tkdtTheoNgay.getDoanhthuChart(),"Ngày","Doanh thu",tkdtBUS.getngayvatongtien(from,to),900,300);
        tkdtTheoNgay.getCenter1().add(newChartPanel);
        tkdtTheoNgay.loadList(tkdtBUS.getdanhsach(from,to));
        tkdtTheoNgay.repaint();
        tkdtTheoNgay.revalidate();
    }
}
