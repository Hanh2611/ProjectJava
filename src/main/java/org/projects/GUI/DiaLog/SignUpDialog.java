package org.projects.GUI.DiaLog;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.projects.DAO.UserDao;
import org.projects.GUI.LoginGUI;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.utils.FocusListenerUtils;

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

        //title dang ky
        JLabel titleDangKy = new JLabel("Tạo tài khoản");
        titleDangKy.setFont(handleComponents.HEADER_FONT);
        titleDangKy.setForeground(LoginGUI.MainColor);
        titleDangKy.setBounds(100, 50, 300, 30);
        this.add(titleDangKy);

        //label sdt
        JLabel sdtLabel = handleComponents.createLabel(
            "Số điện thoại", 100, 100, 200, 30);
        sdtLabel.setFont(handleComponents.DEFAULT_FONT);
        sdtLabel.setForeground(Color.BLACK);
        this.add(sdtLabel);

        //label ten dang nhap
        JLabel tenDangNhapLabel = handleComponents.createLabel(
            "Tên đăng nhập ", 100, 170, 200, 30);
        tenDangNhapLabel.setFont(handleComponents.DEFAULT_FONT);
        tenDangNhapLabel.setForeground(Color.BLACK);
        this.add(tenDangNhapLabel);

        //label mat khau
        JLabel matKhauLabel = handleComponents.createLabel(
            "Mật Khẩu ", 100, 240, 200, 30);
        matKhauLabel.setFont(handleComponents.DEFAULT_FONT);
        matKhauLabel.setForeground(Color.BLACK);
        this.add(matKhauLabel);

        //label nhap lai mat khau
        JLabel nhapLaiMatKhauLabel = handleComponents.createLabel(
            "Nhập lại mật Khẩu ", 100, 310, 200, 30);
        nhapLaiMatKhauLabel.setFont(handleComponents.DEFAULT_FONT);
        nhapLaiMatKhauLabel.setForeground(Color.BLACK);
        this.add(nhapLaiMatKhauLabel);

        //textfield sdt
        JTextField sdtField = handleComponents.createTextField(
            "nhập số điện thoại....", 100, 130, 310, 40);
        sdtField.addFocusListener(FocusListenerUtils.createPlaceholderTextField("nhập số điện thoại....",sdtField));
        this.add(sdtField);

        //textfield ten dang nhap
        JTextField tenDangNhapField = handleComponents.createTextField(
            "nhập tên đăng nhập....", 100, 200, 310, 40);
        tenDangNhapField.addFocusListener(FocusListenerUtils.createPlaceholderTextField("nhập tên đăng nhập....", tenDangNhapField));
        this.add(tenDangNhapField);


        //tao pane chua passwordfield + icon an/hien
        JLayeredPane matKhauPane = new JLayeredPane();
        matKhauPane.setBounds(100, 270, 400, 40);

        JLayeredPane nhapLaiMatKhauPane = new JLayeredPane();
        nhapLaiMatKhauPane.setBounds(100, 340, 400, 40);

        //textfield mat khau
        JPasswordField matKhauField = handleComponents.createPasswordField(
            "nhập mật khẩu....", 0, 0, 310, 40);
        matKhauField.addFocusListener(FocusListenerUtils.createPlaceholderPasswordField("nhập mật khẩu....", matKhauField));
        

        //textfield nhap lai mat khau
        JPasswordField nhapLaiMatKhauField = handleComponents.createPasswordField(
            "nhập lại mật khẩu....", 0, 0, 310, 40);
        nhapLaiMatKhauField.addFocusListener(FocusListenerUtils.createPlaceholderPasswordField("nhập lại mật khẩu....", nhapLaiMatKhauField));
        

        //icon an hien mat khau
        JButton iconOpenEye1 = handleComponents.createButtonIcon(
            "src/main/java/org/projects/assets/icon/hide.png", 20, 20);
        iconOpenEye1.setBounds(270,5, 30,30);
        iconOpenEye1.setVisible(false); //hien ban dau

        JButton iconCloseEye1 = handleComponents.createButtonIcon(
            "src/main/java/org/projects/assets/icon/view.png", 20, 20);
        iconCloseEye1.setBounds(270, 5, 30, 30);
        iconCloseEye1.setVisible(true); //an ban dau

        iconOpenEye1.setFocusable(false); //ngan icon nhan focus
        iconCloseEye1.setFocusable(false);
        
        matKhauPane.add(matKhauField,JLayeredPane.DEFAULT_LAYER);
        matKhauPane.add(iconOpenEye1,JLayeredPane.PALETTE_LAYER);
        matKhauPane.add(iconCloseEye1,JLayeredPane.PALETTE_LAYER);


        //icon an hien mat khau
        JButton iconOpenEye2 = handleComponents.createButtonIcon(
            "src/main/java/org/projects/assets/icon/hide.png", 20, 20);
        iconOpenEye2.setBounds(270,5, 30,30);
        iconOpenEye2.setVisible(false); //hien ban dau

        JButton iconCloseEye2 = handleComponents.createButtonIcon(
            "src/main/java/org/projects/assets/icon/view.png", 20, 20);
        iconCloseEye2.setBounds(270, 5, 30, 30);
        iconCloseEye2.setVisible(true); //an ban dau

        iconOpenEye2.setFocusable(false); //ngan icon nhan focus
        iconCloseEye2.setFocusable(false);
        

        nhapLaiMatKhauPane.add(nhapLaiMatKhauField,JLayeredPane.DEFAULT_LAYER);
        nhapLaiMatKhauPane.add(iconOpenEye2,JLayeredPane.PALETTE_LAYER);
        nhapLaiMatKhauPane.add(iconCloseEye2,JLayeredPane.PALETTE_LAYER);

        this.add(matKhauPane);
        this.add(nhapLaiMatKhauPane);
        
        //add su kien an hien icon
        LoginGUI.setupPasswordToggle(matKhauField, iconOpenEye1, iconCloseEye1);
        LoginGUI.setupPasswordToggle(nhapLaiMatKhauField, iconOpenEye2, iconCloseEye2);

        //button dang ky
        JButton dangKyButton = new JButton("Đăng ký");
        dangKyButton.setFont(new Font("JetBrains Mono",Font.PLAIN,28));
        dangKyButton.setBackground(LoginGUI.MainColor);
        dangKyButton.setForeground(Color.WHITE);
        dangKyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dangKyButton.setBorderPainted(false);
        dangKyButton.setBounds(100, 400, 310, 45);
        this.add(dangKyButton);

        //them su kien click vao dki +connect + save db
        dangKyButton.addActionListener(e -> {
            String sdt = sdtField.getText();
            String tendangnhap = tenDangNhapField.getText();
            String matkhau = String.valueOf(matKhauField.getPassword());
            String nhaplaimatkhau = String.valueOf(nhapLaiMatKhauField.getPassword());


            if(sdt.equals("nhập số điện thoại....")) {
                JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống","thông báo",JOptionPane.ERROR_MESSAGE);
                sdtField.requestFocusInWindow();
                return;
            }
            else if(tendangnhap.equals("nhập tên đăng nhập....")) {
                JOptionPane.showMessageDialog(null, "Tên đăng nhập không được để trống","thông báo",JOptionPane.ERROR_MESSAGE);
                tenDangNhapField.requestFocusInWindow();
                return;
            }
            else if(matkhau.equals("nhập mật khẩu....")) {
                JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống","thông báo",JOptionPane.ERROR_MESSAGE);
                matKhauField.requestFocusInWindow();
                return;
            }
            else if(nhaplaimatkhau.equals("nhập lại mật khẩu....")) {
                JOptionPane.showMessageDialog(null, "Nhập lại mật khẩu không được để trống","thông báo",JOptionPane.ERROR_MESSAGE);
                nhapLaiMatKhauField.requestFocusInWindow();
                return;
            }
            else if(!matkhau.equals(nhaplaimatkhau)) {
                JOptionPane.showMessageDialog(null, "mật khẩu không khớp","thông báo",JOptionPane.ERROR_MESSAGE);
                return;
            }
            UserDao user = new UserDao();
            boolean check = user.DangKy(tendangnhap,matkhau,sdt);
            if(check) {
                JOptionPane.showMessageDialog(this,"Đăng kí thành công","thông báo",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }else JOptionPane.showMessageDialog(this,"Đăng kí không thành công", "thông báo",JOptionPane.ERROR_MESSAGE);
        });
        
    }

    public static void HienThiDangKy() {
        new SignUpDialog();
    }
}
