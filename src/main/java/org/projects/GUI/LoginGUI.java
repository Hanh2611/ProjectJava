package org.projects.GUI;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginGUI extends JFrame {
    public LoginGUI() {
        this.setTitle("Mini Market");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.init();

    }
    public JButton createButtonIcon(String iconPath) {
        ImageIcon iconButton = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
        JButton button = new JButton(iconButton);
        button.setBorder(BorderFactory.createEmptyBorder());  // Xóa viền của JButton
        button.setOpaque(false);  // tắt nền
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);  // tắt viền
        button.setBackground(null);
        return button;
    }
    private void init() {
        Color MainColor = Color.decode("#3498db");

        //----Logic Interface--------
        //left content
        JPanel leftContent = new JPanel();
        leftContent.setBackground(MainColor);
        leftContent.setBounds(0,0,350,600);
        leftContent.setLayout(null);

        ImageIcon iconMiniMarket = new ImageIcon(new ImageIcon("assets/icon/supermarket.png").getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH));
        JLabel miniMarket = new JLabel(iconMiniMarket);
        miniMarket.setBounds(70,120,220,220);
        leftContent.add(miniMarket);

        JLabel titleMiniMarket = new JLabel("Siêu Thị Mini");
        titleMiniMarket.setFont(new Font("JetBrains Mono",Font.PLAIN,30));
        titleMiniMarket.setForeground(Color.WHITE);
        titleMiniMarket.setBounds(70,310,400,80);
        leftContent.add(titleMiniMarket);

        //right content
        JPanel rightContent = new JPanel();
        rightContent.setBounds(350,0,450,600);
        rightContent.setLayout(null);

        //title Sign In
        JLabel dangNhapLabel = new JLabel("Đăng nhập", JLabel.CENTER);
        dangNhapLabel.setFont(new Font("JetBrains Mono",Font.PLAIN,26));
        dangNhapLabel.setForeground(Color.BLACK);
        dangNhapLabel.setBounds(140,80,160,36);
        rightContent.add(dangNhapLabel);

        //label ten dang nhap
        JLabel tenDangNhapLabel = new JLabel("Tên Đăng Nhập");
        tenDangNhapLabel.setFont(new Font("JetBrains Mono",Font.PLAIN,20));
        tenDangNhapLabel.setForeground(Color.BLACK);
        tenDangNhapLabel.setBounds(60,150,310,30);
        rightContent.add(tenDangNhapLabel);

        //label mat khau
        JLabel matKhauLabel = new JLabel("Mật Khẩu");
        matKhauLabel.setFont(new Font("JetBrains Mono",Font.PLAIN,20));
        matKhauLabel.setForeground(Color.BLACK);
        matKhauLabel.setBounds(60,230,310,30);
        rightContent.add(matKhauLabel);

        //field ten dang nhap
        JTextField tenDangNhapField = new JTextField("nhập tên đăng nhập.....");
        tenDangNhapField.setFont(new Font("JetBrains Mono",Font.PLAIN,13));
        tenDangNhapField.setForeground(Color.BLACK);
        tenDangNhapField.setBounds(60,180,310,40);
        rightContent.add(tenDangNhapField);

        //field mat khau
        JPasswordField matKhauField = new JPasswordField("Nhập mật khẩu....");
        matKhauField.setFont(new Font("JetBrains Mono",Font.PLAIN,13));
        matKhauField.setEchoChar((char) 0);
        matKhauField.setForeground(Color.BLACK);
        matKhauField.setBounds(60,260,310,40);
        rightContent.add(matKhauField);

        //checkbox hien thi matkhau
        JCheckBox hienThiMatKhauCheckBox = new JCheckBox("Hiển thị mật khẩu");
        hienThiMatKhauCheckBox.setFont(new Font("JetBrains Mono",Font.PLAIN,12));
        hienThiMatKhauCheckBox.setForeground(Color.BLACK);
        hienThiMatKhauCheckBox.setBounds(60,300,310,30);
        rightContent.add(hienThiMatKhauCheckBox);

        hienThiMatKhauCheckBox.addActionListener(e -> {
            if(hienThiMatKhauCheckBox.isSelected()) {
                matKhauField.setEchoChar((char)0);
            }else matKhauField.setEchoChar('*');
        });

        //button dang nhap
        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setFont(new Font("JetBrains Mono",Font.PLAIN,28));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(MainColor);
        loginButton.setBorderPainted(false); // xoa vien border
        loginButton.setBounds(60,350,310,40);
        rightContent.add(loginButton);
        //add on loginGUI
        this.add(leftContent);
        this.add(rightContent);

        tenDangNhapField.addFocusListener(new FocusListener() {
            @Override
            //function nay la thao tac khi focus vao ô nhập
            public void focusGained(FocusEvent e) {
                if(String.valueOf(tenDangNhapField.getText()).equals("nhập tên đăng nhập.....")) {
                    tenDangNhapField.setText(""); //xóa palceholder khi focus vào
                    tenDangNhapField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(String.valueOf(tenDangNhapField.getText()).equals("")) {
                    tenDangNhapField.setText("nhập tên đăng nhập....."); // khi focus ra ngoài thì set lại text
                    tenDangNhapField.setForeground(Color.GRAY);
                }
            }
        });

        matKhauField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(String.valueOf(matKhauField.getPassword()).equals("Nhập mật khẩu....")) {
                    matKhauField.setText("");
                    matKhauField.setForeground(Color.BLACK);
                    matKhauField.setEchoChar('*'); // focus vào nhập thì set cho các kí tự là *
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(String.valueOf(matKhauField.getPassword()).equals("")) {
                    matKhauField.setText("Nhập mật khẩu....");
                    matKhauField.setForeground(Color.GRAY);
                    matKhauField.setEchoChar((char) 0); // hover ra thì set kí tự về bình thường
                }
            }
        });

    }
}
