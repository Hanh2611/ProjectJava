package org.projects.Action;

import org.projects.BUS.LoginBUS;
import org.projects.BUS.PhanQuyenBUS;
import org.projects.DAO.DanhMucQuanLyDAO;
import org.projects.GUI.LoginGUI;
import org.projects.GUI.MainGUI;
import org.projects.GUI.DiaLog.SignUpDialog;
import org.projects.GUI.utils.Session;
import org.projects.entity.TaiKhoan;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class LoginAction implements MouseListener {
    private LoginGUI loginGUI;
    private TaiKhoan user;
    public static MainGUI mainGUI;
    public LoginAction(LoginGUI loginGUI) {
        this.loginGUI = loginGUI;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(loginGUI.getDangKyLabel2())){
            SignUpDialog.HienThiDangKy();
        }
        if(e.getSource().equals(loginGUI.getDangNhapButton())) {
            TaiKhoan user = LoginBUS.login(new TaiKhoan(loginGUI.getTenDangNhapField().getText(), loginGUI.getMatKhauField().getText()));
            //todo: kiểm tra trạng thái
            //todo: focus textfield
            if (user == null) {
                JOptionPane.showMessageDialog(loginGUI, "Tên đăng nhập hoặc mật khẩu không đúng","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(loginGUI, "Đăng nhập thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                Session.curUser = user;
                Session.maDanhMucQuyen = PhanQuyenBUS.getQuyenDanhMuc(user);
                PhanQuyenBUS.getListAction();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainGUI = new MainGUI();
                        loginGUI.setVisible(false);
                    }
                });
            }
        }
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
