package org.projects.Action;

import org.projects.BUS.TaiKhoanBUS;
import org.projects.DAO.NguoiDungDAO;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.TaiKhoanDialog;
import org.projects.GUI.Panel.TaiKhoan;
import org.projects.GUI.utils.InputValid;
import org.projects.entity.NguoiDungEntity;
import org.projects.entity.TaiKhoanEntity;

import javax.swing.*;
import java.awt.event.*;

public class TaiKhoanAction implements ActionListener, ItemListener, MouseListener {
    private TaiKhoan tk;
    private TaiKhoanDialog tkDialog;
    private TaiKhoanBUS tkBUS;
    public TaiKhoanAction(TaiKhoan tk, TaiKhoanDialog tkDialog) {
        this.tk = tk;
        this.tkDialog = tkDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent c = (JComponent) e.getSource();
        String action = e.getActionCommand();
        if(tk != null) {
            if(tkDialog != null && c instanceof JButton) {
                if(action.equals("Thêm")) {
                    String tennguoidung = tkDialog.getTennguoidung().getTextField().getText();
                    String loainguoidung = tkDialog.getLoainguoidung().getCbx().getSelectedItem().toString();
                    String tendangnhap = tkDialog.getTendangnhap().getTextField().getText();
                    String matkhau = tkDialog.getMatkhau().getTextField().getText();
                    String quyen = tkDialog.getQuyen().getCbx().getSelectedItem().toString();
                    String trangthai = tkDialog.getTrangthai().getCbx().getSelectedItem().toString();
                    if(InputValid.tenNguoiDungHopLe(tennguoidung) || InputValid.tenHopLe(loainguoidung) || InputValid.tenDangNhapHopLe(tendangnhap) || InputValid.tenNguoiDungHopLe(quyen) || InputValid.tenHopLe(trangthai)) {
                        NguoiDungEntity ndEntity = new NguoiDungEntity(loainguoidung,tennguoidung);
                        int manguoidung = new NguoiDungDAO().them(ndEntity);
                        TaiKhoanEntity tkEntity = new TaiKhoanEntity(tendangnhap,matkhau,trangthai,manguoidung);
                        if(TaiKhoanBUS.themtaikhoan(tkEntity)) {
                            JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công", "thông báo", JOptionPane.INFORMATION_MESSAGE);
                            tk.loadDataIntoTable();
                        }
                    }
                }
            }
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        if(tk != null) {
            for(String name : tk.getHeader().getHeaderFunc().getHm().keySet()) {
                generalFunction gf = tk.getHeader().getHeaderFunc().getHm().get(name);
                if(c.equals(gf)) {
                    if(name == null || name.trim().isEmpty()) return;
                    if("add".equals(name)) {
                        new TaiKhoanDialog(name,tk);
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
