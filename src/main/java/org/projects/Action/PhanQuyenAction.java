package org.projects.Action;

import org.projects.BUS.PhanQuyenBUS;
import org.projects.DAO.CapQuyenDAO;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.DiaLog.NhaCungCapDialog;
import org.projects.GUI.DiaLog.PhanQuyen.addPhanQuyen;
import org.projects.GUI.DiaLog.PhanQuyen.performPhanQuyen;
import org.projects.GUI.DiaLog.PhanQuyen.updatePhanQuyen;
import org.projects.GUI.Panel.*;
import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.GUI.Panel.PhanQuyenPack.PhanQuyen;
import org.projects.GUI.Panel.ThongkePack.ThongKe;
import org.projects.GUI.utils.Session;

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
                    } else if ("update".equals(name)) {
                        int row = mainTable.getSelectedRow();
                        if (row == -1) {
                            JOptionPane.showMessageDialog(mainGUI, "Chưa có nhóm quyền được chọn!");
                            return;
                        }
                        int maNhomQuyen = (int) mainTable.getValueAt(row, 0);
                        String nameNhomQuyen = (String) mainTable.getValueAt(row, 1);
                        new updatePhanQuyen(mainGUI, maNhomQuyen, nameNhomQuyen);
                        phanQuyen.getTableModel().setRowCount(0);
                        phanQuyen.loadData();
                        PhanQuyenBUS.getListAction();
                        PhanQuyen pq = new PhanQuyen();
                        ListItem.getMapItem().put("PhanQuyen", pq);
                        ListItem.showPanel("PhanQuyen");
                        ListItem.getMapItem().put("TrangChu", new TrangChu());
//        mapItem.put("SanPham", new SanPham());
                        ListItem.getMapItem().put("TaiKhoan", new TaiKhoan());
                        ListItem.getMapItem().put("PhieuNhap", new PhieuNhap());
                        ListItem.getMapItem().put("HoaDon", new HoaDon());
                        ListItem.getMapItem().put("KhachHang", new KhachHang());
                        ListItem.getMapItem().put("NhanVien", new NhanVien());
                        ListItem.getMapItem().put("NhaCungCap", new NhaCungCap());
                        ListItem.getMapItem().put("PhanQuyen", new PhanQuyen());
                        ListItem.getMapItem().put("ThongKe", new ThongKe());
                    } else {
                        int row = mainTable.getSelectedRow();
                        if (row == -1) {
                            JOptionPane.showMessageDialog(mainGUI, "Chưa có nhóm quyền được chọn!");
                            return;
                        }
                        int maNhomQuyen = (int) mainTable.getValueAt(row, 0);
                        String nameNhomQuyen = (String) mainTable.getValueAt(row, 1);
                        new performPhanQuyen(mainGUI, maNhomQuyen, nameNhomQuyen);
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