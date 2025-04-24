package org.projects.Action;

import org.projects.BUS.ThongKePhieuNhapBUS;
import org.projects.GUI.Chart.ColumnsChart;
import org.projects.GUI.Chart.PieChart;
import org.projects.GUI.Panel.ThongkePack.thongkePhieunhap;
import org.projects.GUI.utils.ChangeDateToString;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.jar.JarEntry;

public class ThongKePhieuNhapAction implements ActionListener, ItemListener {
    private thongkePhieunhap tkpn;
    private ThongKePhieuNhapBUS tkpnBUS;

    public ThongKePhieuNhapAction(thongkePhieunhap tkpn,ThongKePhieuNhapBUS tkpnBUS) {
        this.tkpn = tkpn;
        this.tkpnBUS = tkpnBUS;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tkpn.getTimkiembtn()) {
            String from = ChangeDateToString.changeDate(tkpn.getDateFrom());
            String to = ChangeDateToString.changeDate(tkpn.getDateTo());
            String tenncc = tkpn.getNccBox().getSelectedItem().toString();
            String tennv = tkpn.getNvBox().getSelectedItem().toString();
            updateChartandTable();
            tkpn.loadList(tkpnBUS.getListtheonhieutieuchi(from,to,tenncc,tennv));
        } else if(e.getSource() == tkpn.getResetbtn()) {
            tkpn.getDateFrom().setDate(null);
            tkpn.getDateTo().setDate(null);
            tkpn.getNccBox().setSelectedIndex(0);
            tkpn.getNvBox().setSelectedIndex(0);
            updateChartandTable();
            tkpn.loadList(tkpnBUS.showlist());
        }
        tkpn.repaint();
        tkpn.revalidate();
    }

    public void updateChartandTable() {
        String from = ChangeDateToString.changeDate(tkpn.getDateFrom());
        String to = ChangeDateToString.changeDate(tkpn.getDateTo());
        String tenncc = tkpn.getNccBox().getSelectedItem().toString();
        String tennv = tkpn.getNvBox().getSelectedItem().toString();

        tkpn.remove(tkpn.getBottomWest());
        JPanel newBottomWestPanel = PieChart.createPieChart("Tổng lượng hàng nhập theo nhà cung cấp",tkpn.getPie(),tkpnBUS.loctheonhacungcapvasoluong(from,to,tenncc,tennv),350,1000);
        tkpn.setBottomWest(newBottomWestPanel);

        tkpn.remove(tkpn.getContentChart());
        JPanel newContentChartPanel = ColumnsChart.createColumnChart2("Tổng giá trị nhập theo ngày",tkpn.getColumns(),"Ngày","Tổng giá trị",tkpnBUS.loctheonhieutieuchi(from,to,tenncc,tennv),550,300);
        tkpn.setContentChart(newContentChartPanel);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            tkpn.getNccBox().setSelectedItem(e.getItem());
            tkpn.getNvBox().setSelectedItem(e.getItem());
        }
    }
}
