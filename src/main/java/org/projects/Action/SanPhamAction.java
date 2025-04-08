package org.projects.Action;

import org.projects.BUS.NhaCungCapBUS;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.AddSanPhamDialog;
import org.projects.GUI.DiaLog.NhaCungCapDialog;
import org.projects.GUI.Panel.SanPham;
import org.projects.entity.NhaCungCapEntity;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;

public class SanPhamAction implements ActionListener, MouseListener, ItemListener, DocumentListener {

    private SanPham sanPham;
    private AddSanPhamDialog addSanPhamDialog;

    public SanPhamAction(SanPham sanPham, AddSanPhamDialog addSanPhamDialog) {
        this.sanPham = sanPham;
        this.addSanPhamDialog = addSanPhamDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent c = (JComponent) e.getSource();
        String namebtn = e.getActionCommand();
        if(sanPham != null) {
            if(addSanPhamDialog != null && c instanceof JButton){
                if("Thêm".equals(namebtn)){
//                    String ten = addSanPhamDialog.getTenSanPhamField().getText
//                    String phanloai = addSanPhamDialog.getPhanLoai().getTextField().getText().trim();
//                    String donvi = addSanPhamDialog.getDonVi().getTextField().getText().trim();
//                    String gia = addSanPhamDialog.getGiaBan().getTextField().getText().trim();
//                    String quycach = addSanPhamDialog.getQuyCach().getTextField().getText().trim();
//                    String img = addSanPhamDialog.getImg().getTextField().getText().trim();
//                    if(ten.isEmpty() || phanloai.isEmpty() || gia.isEmpty() || soluong.isEmpty() || quycach.isEmpty() || donvi.isEmpty()){
//                        JOptionPane.showMessageDialog(null, "Vui lòng nhâp đầy đủ các giá trị", "thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        System.out.println(c);
        if (sanPham != null) {
            for (String name : sanPham.getHeader().getHeaderFunc().getHm().keySet()) {
                generalFunction gf = sanPham.getHeader().getHeaderFunc().getHm().get(name);
                if (c.equals(gf)) {
                    if (name == null && name.trim().isEmpty()) return;
                    if ("add".equals(name)) {
                        new AddSanPhamDialog(sanPham);
                    } else {
//                        NhaCungCapEntity nccEntity = ncc.getRow();
//                        if (nccEntity == null) {
//                            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng", "thông báo", JOptionPane.ERROR_MESSAGE);
//                        } else {
//                            if ("update".equals(name) || "detail".equals(name)) {
//                                new NhaCungCapDialog(name, ncc);
//                            } else if ("delete".equals(name)) {
//                                int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa ?", "thông báo", JOptionPane.YES_NO_OPTION);
//                                if (option == JOptionPane.YES_OPTION) {
//                                    if (NhaCungCapBUS.xoa(nccEntity)) {
//                                        JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp thành công", "thông báo", JOptionPane.INFORMATION_MESSAGE);
//                                    }
//                                } else {
//                                    JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp thất bại", "thông báo", JOptionPane.ERROR_MESSAGE);
//                                }
//                                ncc.loadList(NhaCungCapBUS.getList());
//                            }
//                        }
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
//        String keyword = e.getItem().toString();
//        String textfield = sanPham.getHeader().getSearch().getSearchField().getText();
//        sanPham.loadList(NhaCungCapBUS.search(keyword,textfield));
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
//        String keyword = ncc.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
//        String textfield = e.getDocument().toString();
//        ncc.loadList(NhaCungCapBUS.search(keyword,textfield));
    }
}
