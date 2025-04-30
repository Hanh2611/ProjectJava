package org.projects.Action;

import org.projects.BUS.ChiTietHoaDonFullBUS;
import org.projects.BUS.HoaDonBUS;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.Panel.HoaDon;
import org.projects.entity.ChiTietHoaDonFullEntity;
import org.projects.entity.HoaDonEntity;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class HoaDonAction extends MouseAdapter {
    private HoaDon hoaDon;
    public HoaDonAction(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        generalFunction func = (generalFunction) e.getSource();
        String action = func.getNameFunction();
        switch (action) {
            case "add":
                hoaDon.showThemHD();
                break;
            case "update":
                JTable table = hoaDon.getTable();
                int selectedRow = table.getSelectedRow();
                if(selectedRow != -1) {
                    int maHD = (int) table.getValueAt(selectedRow, 0);
                    ChiTietHoaDonFullBUS bus = new ChiTietHoaDonFullBUS();
                    List<ChiTietHoaDonFullEntity> list = bus.getChiTietHoaDonFull(maHD);
                    hoaDon.showSuaHD(list);
                }
                break;
            case "delete":
                JTable table1 = hoaDon.getTable();
                int row = table1.getSelectedRow();
                if (row != -1) {
                    int maHD = (int) table1.getModel().getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc muốn xóa hóa đơn này",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        HoaDonEntity hd = new HoaDonEntity();
                        hd.setMaHoaDon(maHD);
                        boolean result = HoaDonBUS.xoa(hd);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Xóa hóa đơn thành công");
                            hoaDon.reloadDAO();
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa thất bại");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn để xóa");
                }
                break;
            case "detail":
                JTable table2 = hoaDon.getTable();
                int selectedRow2 = table2.getSelectedRow();
                if (selectedRow2 != -1) {
                    int maHD = (int) table2.getModel().getValueAt(selectedRow2, 0);
                    ChiTietHoaDonFullBUS bus1 = new ChiTietHoaDonFullBUS();
                    List<ChiTietHoaDonFullEntity> list1 = bus1.getChiTietHoaDonFull(maHD);
                    hoaDon.showChiTietHD(list1);

                break;

        }
        }
}
}

