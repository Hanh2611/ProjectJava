package org.projects.Action;

import org.projects.BUS.ChiTietPhieuNhapFullEntityBUS;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.PhieuNhap.ChiTietPN;
import org.projects.GUI.Panel.PhieuNhap;
import org.projects.entity.ChiTietPhieuNhapFullEntity;
import org.projects.entity.PhieuNhapEntity;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PhieuNhapAction extends MouseAdapter {
    private PhieuNhap panel;
    private PhieuNhapEntity phieuNhapEntity;
    private ChiTietPN chiTietPN;

    public PhieuNhapAction(PhieuNhap panel) {
        this.panel = panel;
    }

    public void mouseClicked(MouseEvent e) {
        generalFunction func = (generalFunction) e.getSource();
        String action = func.getNameFunction();
        switch (action) {
            case "add":
                panel.showThemPN();
                break;
            case "update":
                JTable table = panel.getTable();
                int selectedRow = table.getSelectedRow();
                if(selectedRow != -1) {
                    int maPN = (int) table.getValueAt(selectedRow, 0);
                    ChiTietPhieuNhapFullEntityBUS bus = new ChiTietPhieuNhapFullEntityBUS();
                    List<ChiTietPhieuNhapFullEntity> list = bus.getChiTietByMaPN(maPN);
                    panel.showCapNhatPN(list);

                }
                else{
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn phiếu nhập để chỉnh sửa");
                }
                break;
            case "delete":
                JOptionPane.showMessageDialog(null, "Chức năng xóa chưa triển khai");
                break;
            case "detail":
                JTable table1 = panel.getTable();
                int selectedRow1 = table1.getSelectedRow();
                if (selectedRow1 != -1) {
                    int maPN = (int) table1.getValueAt(selectedRow1, 0);
                    ChiTietPhieuNhapFullEntityBUS bus1 = new ChiTietPhieuNhapFullEntityBUS();
                    List<ChiTietPhieuNhapFullEntity> list1 = bus1.getChiTietByMaPN(maPN);
                    // Gọi panel để hiển thị
                    panel.showChiTietPN(list1);
                    break;
                }
                else{
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn phiếu nhập");
                }

        }

    }
}

