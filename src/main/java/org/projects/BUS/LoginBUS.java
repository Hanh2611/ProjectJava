package org.projects.BUS;

import org.projects.GUI.LoginGUI;
import org.projects.GUI.DiaLog.SignUpDialog;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
