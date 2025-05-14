package org.projects.Action;

import org.projects.BUS.ThongKeHoaDonBUS;
import org.projects.GUI.Chart.ColumnsChart;
import org.projects.GUI.Panel.ThongkePack.thongkeHoadon;
import org.projects.GUI.utils.ChangeDateToString;
import org.projects.entity.ThongkeHoaDonEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ThongKeHoaDonAction implements ActionListener, ItemListener {
    private thongkeHoadon tkhd;
    private ThongKeHoaDonBUS tkhdBUS;
    public ThongKeHoaDonAction(thongkeHoadon tkhd,ThongKeHoaDonBUS tkhdBUS) {
        this.tkhd = tkhd;
        this.tkhdBUS = tkhdBUS;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tkhd.getReset()) {
            tkhd.getDateFrom().setDate(null);
            tkhd.getDateTo().setDate(null);
            tkhd.getCbxtrangthai().setSelectedItem("Tất cả");
            updateChart();
            List<ThongkeHoaDonEntity> all = tkhdBUS.getList();
            tkhd.loadlist(all);
        } else {
            updateChart();
            String from = ChangeDateToString.changeDate(tkhd.getDateFrom().getDate());
            String to = ChangeDateToString.changeDate(tkhd.getDateTo().getDate());
            String trangthai = tkhd.getCbxtrangthai().getSelectedItem().toString();
            List<ThongkeHoaDonEntity> loc = tkhdBUS.getListtheongayvatrangthai(from, to, trangthai);
            tkhd.loadlist(loc);
        }
        // Cập nhật lại giao diện
        tkhd.revalidate();
        tkhd.repaint();
    }

    public void updateChart() {
        Date fromDate = tkhd.getDateFrom().getDate();
        Date toDate = tkhd.getDateTo().getDate();
        String from = fromDate != null ? new SimpleDateFormat("yyyy-MM-dd").format(fromDate) : "";
        String to = toDate != null ? new SimpleDateFormat("yyyy-MM-dd").format(toDate) : "";
        String trangthai = tkhd.getCbxtrangthai().getSelectedItem().toString();
        HashMap<String,Integer> hm = tkhdBUS.getSLHDtheongay(from,to,trangthai);
        tkhd.getCenter1().removeAll();
        JPanel center = ColumnsChart.createColumnsChart("Số lượng hóa đơn theo ngày", tkhd.getHoadonChart(), "Ngày", "Số lượng", hm,600,300);
        tkhd.setCenter1(center);
        tkhd.setSoluonghoadontheongay(hm);
        tkhd.setCenter(center);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String tt = e.getItem().toString();
            tkhd.getCbxtrangthai().setSelectedItem(tt);
        }
    }
}
