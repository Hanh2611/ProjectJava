package org.projects.GUI.DiaLog;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class SignUpDialog extends JDialog{
    public SignUpDialog() {
        this.setTitle("Đăng ký");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.init();
        this.setVisible(true);
    }
    private void init() {
        this.setLayout(null);
        this.setBackground(Color.WHITE);
    }
}
