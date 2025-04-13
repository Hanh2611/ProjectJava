package org.projects.Action;

import org.projects.BUS.HoaDonBUS;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.Panel.HoaDon;
import org.projects.entity.HoaDonEntity;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
                JOptionPane.showMessageDialog(null, "Cập nhật hóa đơn");
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
                JOptionPane.showMessageDialog(null, "Chi tieets hóa đơn");
                break;

        }
        }
}

