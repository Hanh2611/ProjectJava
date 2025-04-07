package org.projects.Action;

import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.Nhanvien.ShowAddNhanVienConsole;
import org.projects.GUI.DiaLog.Nhanvien.ShowChiTietNhanVienConsole;
import org.projects.GUI.DiaLog.Nhanvien.ShowDeleteNhanVienConsole;
import org.projects.GUI.Panel.NhanVienPack.AddNhanVienConsole;
import org.projects.GUI.Panel.NhanVienPack.DeleteNhanVienConsole;
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
    private ShowAddNhanVienConsole show_add_nv;
    private ShowDeleteNhanVienConsole show_del_nv;
    private ShowChiTietNhanVienConsole show_detail_nv;
    public NhanVienAction(NhanVien nv) {
        this.nv = nv;
    }
    public NhanVienAction(){}
    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent source = (JComponent) e.getSource();
        String nameButton = e.getActionCommand();
        if(show_add_nv != null) {
            if (source.equals(show_add_nv.add.getSaveButton())) {
                System.out.println("save");
            } else if (source.equals(show_add_nv.add.getCancelButton())) {
                System.out.println("cancel");
                show_add_nv.close();
            } else if (source.equals(show_add_nv.add.getResetButton())) {
                System.out.println("reset");
            }
        }
        if(show_del_nv != null) {
            if (source.equals(show_del_nv.del.getCancelButton())) {
                System.out.println("del cancel");
                show_del_nv.close();
            }else if(source.equals(show_del_nv.del.getOkButton())){
                System.out.println("del ok");
                show_del_nv.close();
            }
        }
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
                        show_add_nv = new ShowAddNhanVienConsole();
                        show_add_nv.add.getResetButton().addActionListener(this);
                        show_add_nv.add.getSaveButton().addActionListener(this);
                        show_add_nv.add.getCancelButton().addActionListener(this);
                    }else{
                        NhanVienEntity clickOnRowTable = nv.getRow();
                        if(clickOnRowTable == null){
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng", "thông báo", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if("update".equals(name)){
                            // chưa làm
                        }else if("detail".equals(name)){
                            show_detail_nv = new ShowChiTietNhanVienConsole(clickOnRowTable,false);
                        }
                        else if("delete".equals(name)){
                            show_del_nv = new ShowDeleteNhanVienConsole(clickOnRowTable);
                            show_del_nv.del.getOkButton().addActionListener(this);
                            show_del_nv.del.getCancelButton().addActionListener(this);
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
