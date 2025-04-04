package org.projects.Action;

import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.Panel.PhieuNhap;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PhieuNhapAction extends MouseAdapter {
    private PhieuNhap panel;

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
                JOptionPane.showMessageDialog(null, "Chức năng sửa chưa triển khai");
                break;
            case "delete":
                JOptionPane.showMessageDialog(null, "Chức năng xóa chưa triển khai");
                break;
            case "detail":
                panel.showChiTietPN();
                break;
        }

    }
}
