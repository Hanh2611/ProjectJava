package org.projects.Action;

import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.AddSanPhamDialog;
import org.projects.GUI.DiaLog.NhaCungCapDialog;
import org.projects.GUI.Panel.SanPham;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SanPhamAction implements ActionListener, MouseListener {

    private SanPham sanPham;
    private AddSanPhamDialog addSanPhamDialog;

    public SanPhamAction(SanPham sanPham, AddSanPhamDialog addSanPhamDialog) {
        this.sanPham = sanPham;
        this.addSanPhamDialog = addSanPhamDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        System.out.println(c);
        if(sanPham != null) {
            for(String name : sanPham.getHeader().getHeaderFunc().getHm().keySet()) {
                generalFunction gf = sanPham.getHeader().getHeaderFunc().getHm().get(name);
                if(c.equals(gf)) {
                    if(name == null && name.trim().isEmpty()) return;
                    System.out.println("ten cua nut la : " + name);
                    if("add".equals(name)) {
                        new AddSanPhamDialog(name, sanPham);
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
