package org.projects.Action;

import org.projects.BUS.PhanQuyenBUS;
import org.projects.DAO.CapQuyenDAO;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.DiaLog.NhaCungCapDialog;
import org.projects.GUI.DiaLog.PhanQuyen.addPhanQuyen;
import org.projects.GUI.DiaLog.PhanQuyen.performPhanQuyen;
import org.projects.GUI.DiaLog.PhanQuyen.updatePhanQuyen;
import org.projects.GUI.MainGUI;
import org.projects.GUI.Panel.*;
import org.projects.GUI.Panel.KhachHangPack.KhachHang;
import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.GUI.Panel.PhanQuyenPack.PhanQuyen;
import org.projects.GUI.Panel.ThongkePack.ThongKe;
import org.projects.GUI.utils.Session;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

import static org.projects.Action.LoginAction.mainGUI;

public class PhanQuyenAction implements ActionListener, MouseListener, ItemListener, DocumentListener {
    private PhanQuyen phanQuyen;
    private JTable mainTable;
    public PhanQuyenAction(PhanQuyen phanQuyen, JTable mainTable) {
        this.phanQuyen = phanQuyen;
        this.mainTable = mainTable;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent c = (JComponent) e.getSource();
        JButton refresh = phanQuyen.getHeader().getSearch().getSearchButton();
        if(c instanceof JButton && c.equals(refresh)) {
            phanQuyen.getHeader().getSearch().getSearchComboBox().setSelectedItem("--");
            phanQuyen.getHeader().getSearch().getSearchField().setText("");
            phanQuyen.loadData();
        }
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
                        mainGUI.getMenuTask().getLi().getMapItem();
                        mainGUI.getMenuTask().getLi().getMapItem().put("PhanQuyen", pq);
                        mainGUI.getMenuTask().getLi().getMapItem().put("TrangChu", new TrangChu());
//        mapItem.put("SanPham", new SanPham());
                        mainGUI.getMenuTask().getLi().getMapItem().put("TaiKhoan", new TaiKhoan());
                        mainGUI.getMenuTask().getLi().getMapItem().put("PhieuNhap", new PhieuNhap());
                        mainGUI.getMenuTask().getLi().getMapItem().put("HoaDon", new HoaDon());
                        mainGUI.getMenuTask().getLi().getMapItem().put("KhachHang", new KhachHang());
                        mainGUI.getMenuTask().getLi().getMapItem().put("NhanVien", new NhanVien());
                        mainGUI.getMenuTask().getLi().getMapItem().put("NhaCungCap", new NhaCungCap());
                        mainGUI.getMenuTask().getLi().getMapItem().put("PhanQuyen", new PhanQuyen());
                        mainGUI.getMenuTask().getLi().getMapItem().put("ThongKe", new ThongKe());
                        mainGUI.getMenuTask().getLi().showPanel("PhanQuyen");
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

    @Override
    public void itemStateChanged(ItemEvent e) {
        String keyword = e.getItem().toString();
        String textfield = phanQuyen.getHeader().getSearch().getSearchField().getText();
        phanQuyen.searchFunction(keyword, textfield);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String keyword = phanQuyen.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        String textfield = phanQuyen.getHeader().getSearch().getSearchField().getText();
        phanQuyen.searchFunction(keyword, textfield);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String keyword = phanQuyen.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        String textfield = phanQuyen.getHeader().getSearch().getSearchField().getText();
        phanQuyen.searchFunction(keyword, textfield);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String keyword = phanQuyen.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        String textfield = phanQuyen.getHeader().getSearch().getSearchField().getText();
        phanQuyen.searchFunction(keyword, textfield);
    }
}