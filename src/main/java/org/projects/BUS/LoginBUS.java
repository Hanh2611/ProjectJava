package org.projects.BUS;

import org.projects.GUI.LoginGUI;
import org.projects.GUI.MainGUI;
import org.projects.GUI.DiaLog.SignUpDialog;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class LoginBUS implements MouseListener {
    private LoginGUI loginGUI;
    public LoginBUS(LoginGUI loginGUI) {
        this.loginGUI = loginGUI;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(loginGUI.getDangKyLabel2())){
            SignUpDialog.HienThiDangKy();
        }

        if(e.getSource().equals(loginGUI.getDangNhapButton())) {
                JOptionPane.showMessageDialog(loginGUI, "Đăng nhập thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new MainGUI();
                        loginGUI.setVisible(false);
                    }
                });
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
