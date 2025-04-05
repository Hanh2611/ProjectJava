package org.projects.Action;

import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.Nhanvien.ShowAddNhanVienConsole;
import org.projects.GUI.DiaLog.Nhanvien.ShowChiTietNhanVienConsole;
import org.projects.GUI.DiaLog.Nhanvien.ShowDeleteNhanVienConsole;
import org.projects.GUI.Panel.NhanVienPack.AddNhanVienConsole;
import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.entity.NhanVienEntity;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NhanVienAction implements ActionListener  , MouseListener {
    private NhanVien nv;
    private ShowDeleteNhanVienConsole delete_console;
    private ShowAddNhanVienConsole add_console;
    private ShowChiTietNhanVienConsole detail_console;
    private AddNhanVienConsole add_img;
    public NhanVienAction(NhanVien nv) {
        this.nv = nv;
    }
    public NhanVienAction(){}
    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent source = (JComponent) e.getSource();
        String nameButton = e.getActionCommand();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        if(nv != null){
            for (String name : nv.getHeader().getHeaderFunc().getHm().keySet()) {
                generalFunction gf = nv.getHeader().getHeaderFunc().getHm().get(name);
                if(c.equals(gf)){
                    if (name == null && name.trim().isEmpty()) return;
                    System.out.println("ten cua nut la : " + name);
                    if("add".equals(name)){
                        add_console = new ShowAddNhanVienConsole();
                    }else{
                        NhanVienEntity clickOnRowTable = nv.getRow();
                        if(clickOnRowTable == null){
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng", "thông báo", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if("update".equals(name)){
                            // chưa làm
                        }else if("detail".equals(name)){
                            detail_console = new ShowChiTietNhanVienConsole(clickOnRowTable,false);
                        }
                        else if("delete".equals(name)){
                            delete_console = new ShowDeleteNhanVienConsole(clickOnRowTable);
                        }
                    }
                }
            }
        }
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
