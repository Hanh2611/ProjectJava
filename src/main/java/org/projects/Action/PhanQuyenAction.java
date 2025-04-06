package org.projects.Action;

import org.projects.BUS.PhanQuyenBUS;
import org.projects.DAO.CapQuyenDAO;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.NhaCungCapDialog;
import org.projects.GUI.DiaLog.PhanQuyen.addPhanQuyen;
import org.projects.GUI.Panel.NhaCungCap;
import org.projects.GUI.Panel.PhanQuyenPack.PhanQuyen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static org.projects.Action.LoginAction.mainGUI;

public class PhanQuyenAction implements ActionListener, MouseListener {
    private PhanQuyen phanQuyen;
    private JTable mainTable;
    public PhanQuyenAction(PhanQuyen phanQuyen, JTable mainTable) {
        this.phanQuyen = phanQuyen;
        this.mainTable = mainTable;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        System.out.println(c);
        if(phanQuyen != null) {
            for(String name : phanQuyen.getHeader().getHeaderFunc().getHm().keySet()) {
                generalFunction gf = phanQuyen.getHeader().getHeaderFunc().getHm().get(name);
                if(c.equals(gf)) {
                    if(name == null && name.trim().isEmpty()) return;
                    if("add".equals(name)) {
                        new addPhanQuyen(mainGUI);
                        phanQuyen.getTableModel().setRowCount(0);
                        phanQuyen.loadData();
                    } else if ("delete".equals(name)) {
                        int row = mainTable.getSelectedRow();
                        if (row == -1) {
                            JOptionPane.showMessageDialog(mainGUI, "Chưa có nhóm quyền được chọn!");
                            return;
                        }
                        int maNhomQuyen = (int) mainTable.getValueAt(row, 0);
                        PhanQuyenBUS.deleteNhomQuyen(maNhomQuyen);
                        JOptionPane.showMessageDialog(mainGUI, "Xóa nhóm quyền thành công!");
                        phanQuyen.getTableModel().setRowCount(0);
                        phanQuyen.loadData();
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