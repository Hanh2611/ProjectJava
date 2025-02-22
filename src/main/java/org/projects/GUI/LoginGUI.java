package org.projects.GUI;

import org.projects.Main;
import org.projects.BUS.LoginBUS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginGUI extends JFrame {
    private JButton dangNhapButton;
    private JLabel dangKyLabel2;
    Color MainColor = Color.decode("#3498db");

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
        

        //----Logic Interface--------
        //left content
        JPanel leftContent = new JPanel();
        leftContent.setBackground(MainColor);
        leftContent.setBounds(0,0,350,600);
        leftContent.setLayout(null);

        ImageIcon iconMiniMarket = new ImageIcon(new ImageIcon("src/main/java/org/projects/assets/icon/supermarket.png").getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH));
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
        dangNhapLabel.setFont(new Font("JetBrains Mono",Font.PLAIN,30));
        dangNhapLabel.setForeground(MainColor);
        dangNhapLabel.setBounds(70,80,300,36);
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
        dangNhapButton = new JButton("Đăng nhập");
        dangNhapButton.setFont(new Font("JetBrains Mono",Font.PLAIN,28));
        dangNhapButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dangNhapButton.setForeground(Color.WHITE);
        dangNhapButton.setBackground(MainColor);
        dangNhapButton.setBorderPainted(false); // xoa vien border
        dangNhapButton.setBounds(60,350,310,40);
        rightContent.add(dangNhapButton);

        //Link dang ki
        JPanel dangKyPanel = new JPanel(new FlowLayout());
        dangKyPanel.setBounds(60,395,320,30);
        JLabel dangKyLabel1 = new JLabel("Bạn chưa có tài khoản ?");
        dangKyLabel1.setFont(new Font("JetBrains Mono",Font.PLAIN,12));
        dangKyLabel1.setForeground(Color.BLACK);
        dangKyLabel2 = new JLabel("Đăng ký ngay");
        dangKyLabel2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dangKyLabel2.setFont(new Font("JetBrains Mono",Font.ITALIC,12));
        dangKyLabel2.setForeground(MainColor);
        dangKyPanel.add(dangKyLabel1);
        dangKyPanel.add(dangKyLabel2);
        rightContent.add(dangKyPanel);

        //MouseListener cho Dang ky
        LoginBUS action = new LoginBUS(this);
        dangKyLabel2.addMouseListener(action);

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
        //tranh run tu dong focus vao jtextfield
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(0,0,0,0);
        this.add(loginPanel);
        loginPanel.requestFocusInWindow();
    }

    private class TaoTaiKhoan extends JDialog {
        public TaoTaiKhoan() {
            this.setTitle("Đăng ký");
            this.setSize(500,500);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialogInit();
            this.setVisible(true);
        }
        public void dialogInit() {
            this.setLayout(null);
            this.setBackground(Color.WHITE);
            //title dang ky
            JLabel titleDangky = new JLabel("Tạo tài khoản");
            titleDangky.setFont(new Font("JetBrains Mono",Font.PLAIN,30));
            titleDangky.setForeground(MainColor);
            titleDangky.setBounds(120,50,300,30);
            this.add(titleDangky);

            //label sdt
            JLabel sdtLabel = new JLabel("Số điện thoại ");
            sdtLabel.setFont(new Font("JetBrains Mono",Font.PLAIN,18));
            sdtLabel.setForeground(Color.BLACK);
            sdtLabel.setBounds(100,100,200,30);
            this.add(sdtLabel);

            //label ten dang nhap
            JLabel tenDangNhapLabel = new JLabel("Tên đăng nhập ");
            tenDangNhapLabel.setFont(new Font("JetBrains Mono",Font.PLAIN, 18));
            tenDangNhapLabel.setForeground(Color.BLACK);
            tenDangNhapLabel.setBounds(100,170,200,30);
            this.add(tenDangNhapLabel);
            
            //label mat khau
            JLabel matKhauLabel = new JLabel("Mật Khẩu ");
            matKhauLabel.setFont(new Font("JetBrains Mono",Font.PLAIN, 18));
            matKhauLabel.setForeground(Color.BLACK);
            matKhauLabel.setBounds(100,240,200,30);
            this.add(matKhauLabel);

            //label nhap lai mat khau
            JLabel nhapLaiMatKhauLabel = new JLabel("Nhập lại mật Khẩu ");
            nhapLaiMatKhauLabel.setFont(new Font("JetBrains Mono",Font.PLAIN, 18));
            nhapLaiMatKhauLabel.setForeground(Color.BLACK);
            nhapLaiMatKhauLabel.setBounds(100,310,200,30);
            this.add(nhapLaiMatKhauLabel);
            
            //textfield sdt
            JTextField sdtField = new JTextField("nhập số điện thoại....");
            sdtField.setFont(new Font("JetBrains Mono",Font.PLAIN, 13));
            sdtField.setForeground(Color.GRAY);
            sdtField.setBounds(100, 130, 300, 40);
            this.add(sdtField);

            //textfield ten dang nhap
            JTextField tenDangNhapField = new JTextField("nhập tên đăng nhập....");
            tenDangNhapField.setFont(new Font("JetBrains Mono",Font.PLAIN, 13));
            tenDangNhapField.setForeground(Color.GRAY);
            tenDangNhapField.setBounds(100, 200, 300, 40);
            this.add(tenDangNhapField);
            //textfield mat khau
            JTextField matKhauField = new JTextField("nhập mật khẩu....");
            matKhauField.setFont(new Font("JetBrains Mono",Font.PLAIN, 13));
            matKhauField.setForeground(Color.GRAY);
            matKhauField.setBounds(100, 270, 300, 40);
            this.add(matKhauField);
            //textfield nhap lai mat khau
            JTextField nhapLaiMatKhauField = new JTextField("nhập lại mật khẩu....");
            nhapLaiMatKhauField.setFont(new Font("JetBrains Mono",Font.PLAIN, 13));
            nhapLaiMatKhauField.setForeground(Color.GRAY);
            nhapLaiMatKhauField.setBounds(100, 340, 300, 40);
            this.add(nhapLaiMatKhauField);

            //button dang ky
            JButton dangKyButton = new JButton("Đăng ký");
            dangKyButton.setFont(new Font("JetBrains Mono",Font.PLAIN,28));
            dangKyButton.setBackground(MainColor);
            dangKyButton.setForeground(Color.WHITE);
            dangKyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            dangKyButton.setBorderPainted(false);
            dangKyButton.setBounds(100,400,300,45);
            this.add(dangKyButton);
        }
    }


    public JButton getDangNhapButton() {return dangNhapButton;}
    public JLabel getDangKyLabel2(){return dangKyLabel2;}   
    public void HienThiDangKy() {
            new TaoTaiKhoan();
    }
}
