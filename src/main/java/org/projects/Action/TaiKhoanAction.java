package org.projects.Action;

import org.projects.BUS.*;
import org.projects.DAO.NguoiDungDAO;
import org.projects.DAO.NhanVienDao;
import org.projects.DAO.QuyenNguoiDungDAO;
import org.projects.DAO.TaiKhoanDAO;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.TaiKhoanDialog;
import org.projects.GUI.Panel.TaiKhoan;
import org.projects.GUI.utils.Helper;
import org.projects.GUI.utils.InputValid;
import org.projects.config.DatabasesConfig;
import org.projects.entity.NguoiDungEntity;
import org.projects.entity.NhanVienEntity;
import org.projects.entity.TaiKhoanEntity;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class TaiKhoanAction implements ActionListener, ItemListener, MouseListener, DocumentListener {
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
                    themtaikhoan();
                    tkDialog.dispose();
                } else if(action.equals("Cập nhật")) {
                    suataikhoan();
                    tkDialog.dispose();
                }
            }
        }
        JButton refresh = tk.getHeader().getSearch().getSearchButton();
        if(c instanceof JButton && c.equals(refresh)) {
            tk.getHeader().getSearch().getSearchComboBox().setSelectedItem("--");
            tk.getHeader().getSearch().getSearchField().setText("");
//            this.tk.searchFunction(tk.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString(),tk.getHeader().getSearch().getSearchField().getText());
            tk.loadDataIntoTable();
        }
    }

    private void themtaikhoan() {
        String tendangnhap = tkDialog.getTendangnhap().getTextField().getText();
        String matkhau = tkDialog.getMatkhau().getTextField().getText();
        int manv;
        try {
            manv = Integer.parseInt(tkDialog.getManhanvien().getCbx().getSelectedItem().toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!InputValid.tenHopLe(tendangnhap)) {
            JOptionPane.showMessageDialog(null, "Kiểm tra lại các giá trị nhập vào", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tạo người dùng
        NguoiDungEntity ndEntity = new NguoiDungEntity();
       for(NhanVienEntity nvEntity : NhanVienBus.getList()) {
           if(manv == nvEntity.getMaNhanVien()) {
               ndEntity.setTenNguoiDung(nvEntity.getTenNhanVien());
           }
        }
       for(NhanVienEntity nvEntity : NhanVienBus.getList()) {
           if(manv == nvEntity.getMaNhanVien()) {
               ndEntity.setTenNguoiDung(nvEntity.getTenNhanVien());
           }
       }
        int manguoidung = new NguoiDungDAO().them(ndEntity);
        if (manguoidung <= 0) {
            JOptionPane.showMessageDialog(null, "Thêm người dùng thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean tmp = NhanVienDao.updateThemMaNguoiDungChoNhanVienSauKhiTaoTaiKhoan(manguoidung, manv);
        System.out.println(tmp);
        NhanVienBus.getList();
        TaiKhoanEntity tkEntity = new TaiKhoanEntity(tendangnhap, matkhau);
        tkEntity.setMaNguoiDung(manguoidung);
        System.out.println(tkDialog.getQuyen().getCbx().getSelectedItem().toString());
        QuyenNguoiDungBUS.addQuyenNguoiDung(tkEntity.getMaNguoiDung(), PhanQuyenBUS.getMaNhomQuyenByName(tkDialog.getQuyen().getCbx().getSelectedItem().toString()));
//        tkEntity.setQuyenNguoiDung(PhanQuyenBUS.getMaNhomQuyenByName(tkDialog.getQuyen().getCbx().getSelectedItem().toString()));
        if (TaiKhoanBUS.themtaikhoan(tkEntity)) {
            JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            tk.loadDataIntoTable();
        }
    }

    private void suataikhoan() {
        TaiKhoanEntity tkEntity = tk.getSelectedTaiKhoanEntity();

        if (tkEntity == null) {
            JOptionPane.showMessageDialog(null, "Không có thông tin tài khoản để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String matKhauMoi = tkDialog.getMatkhau().getTextField().getText().trim();
        String tenNhomQuyen = tkDialog.getQuyen().getCbx().getSelectedItem().toString();
        String trangThaiMoi = tkDialog.getTrangthai().getCbx().getSelectedItem().toString();

        // Kiểm tra mật khẩu hợp lệ
        if (!InputValid.tenDangNhapHopLe(matKhauMoi)) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không hợp lệ (ít nhất 6 ký tự).", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int maNguoiDung = tkEntity.getMaNguoiDung();
        int maNhomQuyen = TaiKhoanBUS.getmaquyentutenquyen(tenNhomQuyen);
        // Cập nhật mật khẩu và trạng thái trong bảng tài khoản
        boolean capNhatTK = TaiKhoanDAO.capnhatmatkhauvatrangthai(tkEntity.getTenDangNhap(), matKhauMoi, trangThaiMoi);

        // Cập nhật quyền người dùng
        boolean capNhatQuyen = QuyenNguoiDungDAO.capnhapquyen(maNguoiDung, maNhomQuyen);

        if (capNhatTK && capNhatQuyen) {
            JOptionPane.showMessageDialog(null, "Cập nhật tài khoản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            tk.loadDataIntoTable();
            tkDialog.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void xoataikhoan() {

    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        String keyword = e.getItem().toString();
        String textfield = tk.getHeader().getSearch().getSearchField().getText();
        tk.searchFunction(keyword, textfield);
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
                        new TaiKhoanDialog(name,tk,null);
                    } else if("update".equals(name)) {
                        TaiKhoanEntity taikhoanduocchon = tk.getSelectedTaiKhoanEntity();
                        if(taikhoanduocchon != null) {
                            new TaiKhoanDialog(name,tk,taikhoanduocchon);
                        }
                    } else if("detail".equals(name)) {
                        TaiKhoanEntity taikhoanduocchon = tk.getSelectedTaiKhoanEntity();
                        if(taikhoanduocchon != null) {
                            new TaiKhoanDialog(name,tk,taikhoanduocchon);
                        }
                    } else if("delete".equals(name)) {
                        xoataikhoan();
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
    public void insertUpdate(DocumentEvent e) {
        String keyword = tk.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        String textfield = tk.getHeader().getSearch().getSearchField().getText();
        tk.searchFunction(keyword, textfield);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String keyword = tk.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        String textfield = tk.getHeader().getSearch().getSearchField().getText();
        tk.searchFunction(keyword, textfield);
    }


    @Override
    public void changedUpdate(DocumentEvent e) {
        String keyword = tk.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        String textfield = tk.getHeader().getSearch().getSearchField().getText();
        tk.searchFunction(keyword, textfield);
    }
}
