package org.projects.Action;

import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.Panel.HoaDon;

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
                JOptionPane.showMessageDialog(null, "Xóa hóa đơn");
                break;
            case "detail":
                JOptionPane.showMessageDialog(null, "Chi tieets hóa đơn");
                break;

        }
        }
}

