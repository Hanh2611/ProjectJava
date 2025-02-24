package org.projects.GUI;

import org.projects.Main;
import org.projects.BUS.LoginBUS;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.utils.FocusListenerUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI extends JFrame {
    private JButton dangNhapButton;
    private JLabel dangKyLabel2;
    public static final Color MainColor = Color.decode("#3498db");

    public LoginGUI() {
        this.setTitle("Mini Market");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.init();
    }

    private void init() {
        // Left Panel
        JPanel leftPanel = createLeftPanel();
        this.add(leftPanel);

        // Right Panel
        JPanel rightPanel = createRightPanel();
        this.add(rightPanel);

        // tranh focus vao textfield ban dau
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBounds(0,0,0,0);
        this.add(emptyPanel);
        emptyPanel.requestFocusInWindow();
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(MainColor);
        panel.setBounds(0, 0, 350, 600);

        // Icon
        ImageIcon iconMiniMarket = new ImageIcon(
            new ImageIcon("src/main/java/org/projects/assets/icon/supermarket.png")
            .getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        JLabel miniMarket = new JLabel(iconMiniMarket);
        miniMarket.setBounds(70, 120, 200, 200);
        panel.add(miniMarket);

        // Title
        JLabel titleMiniMarket = new JLabel("Siêu Thị Mini");
        titleMiniMarket.setFont(new Font("JetBrains Mono", Font.PLAIN, 30));
        titleMiniMarket.setForeground(Color.WHITE);
        titleMiniMarket.setBounds(60, 300, 400, 80);
        panel.add(titleMiniMarket);
        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(350, 0, 450, 600);

        addLoginComponents(panel);
        addSingUp(panel);
        return panel;
    }

    private void addLoginComponents(JPanel panel) {
        // title dang nhap
        JLabel dangNhapLabel = new JLabel("Đăng nhập", JLabel.CENTER);
        dangNhapLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 30));
        dangNhapLabel.setForeground(MainColor);
        dangNhapLabel.setBounds(70, 80, 300, 36);
        panel.add(dangNhapLabel);

        //label ten dang nhap
        JLabel tenDangNhapLabel = handleComponents.createLabel(
            "Tên Đăng Nhập",
            60, 150, 310, 30);
        panel.add(tenDangNhapLabel);

        //label mat khau
        JLabel matKhauLabel = handleComponents.createLabel(
            "Mật khẩu",
            60, 240, 310, 30);
        panel.add(matKhauLabel);
         
        //field ten dang nhap
        JTextField tenDangNhapField = handleComponents.createTextField(
            "nhập tên đăng nhập.....", 
            60, 190, 310, 40
        );
        tenDangNhapField.addFocusListener(FocusListenerUtils.createPlaceholderTextField(
            "nhập tên đăng nhập.....", 
            tenDangNhapField
        ));
        panel.add(tenDangNhapField);

        //panel chua mat khau field + icon an/hien
        JLayeredPane matKhauPane = new JLayeredPane();
        matKhauPane.setBounds(60,280,310,40);
        
        //field mat khau
        JPasswordField matKhauField = handleComponents.createPasswordField(
            "Nhập mật khẩu....", 
            0, 0, 310, 40
        );
        matKhauField.addFocusListener(FocusListenerUtils.createPlaceholderPasswordField(
            "Nhập mật khẩu....", 
            matKhauField
        ));
        

        //icon an hien mat khau
        JButton iconOpenEye = handleComponents.createButtonIcon(
            "src/main/java/org/projects/assets/icon/hide.png", 20, 20);
        iconOpenEye.setBounds(270,5, 30,30);
        iconOpenEye.setVisible(false); //hien ban dau

        JButton iconCloseEye = handleComponents.createButtonIcon(
            "src/main/java/org/projects/assets/icon/view.png", 20, 20);
        iconCloseEye.setBounds(270, 5, 30, 30);
        iconCloseEye.setVisible(true); //an ban dau

        iconOpenEye.setFocusable(false); //ngan icon nhan focus
        iconCloseEye.setFocusable(false);
        
        matKhauPane.add(matKhauField,JLayeredPane.DEFAULT_LAYER);
        matKhauPane.add(iconOpenEye,JLayeredPane.PALETTE_LAYER);
        matKhauPane.add(iconCloseEye,JLayeredPane.PALETTE_LAYER);
        panel.add(matKhauPane);

        setupPasswordToggle(matKhauField, iconOpenEye, iconCloseEye);

        //button dang nhap
        dangNhapButton = new JButton("Đăng nhập");
        dangNhapButton.setFont(new Font("JetBrains Mono", Font.PLAIN, 28));
        dangNhapButton.setForeground(Color.WHITE);
        dangNhapButton.setBackground(MainColor);
        dangNhapButton.setBounds(60, 340, 310, 40);
        panel.add(dangNhapButton);


    }
    public static  void setupPasswordToggle(JPasswordField pf, JButton showBtn, JButton hideBtn) {
        hideBtn.setVisible(true);
        hideBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pf.setEchoChar((char) 0); //hien thi mat khau
                showBtn.setVisible(true);
                hideBtn.setVisible(false);
                pf.requestFocusInWindow(); // tranh focus ra cho khac
                
            }
        });

        showBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pf.setEchoChar('*');
                showBtn.setVisible(false);
                hideBtn.setVisible(true);
                pf.requestFocusInWindow();
            }
        });
    }

    private void addSingUp(JPanel panel) {
        JPanel dangKyPanel = new JPanel(new FlowLayout());
        dangKyPanel.setBounds(60, 385, 320, 30);

        JLabel dangKyLabel1 = new JLabel("Bạn chưa có tài khoản ?");
        dangKyLabel1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        dangKyLabel1.setForeground(Color.BLACK);

        dangKyLabel2 = new JLabel("Đăng ký ngay");
        dangKyLabel2.setFont(new Font("JetBrains Mono", Font.ITALIC, 12));
        dangKyLabel2.setForeground(MainColor);
        dangKyLabel2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //them controller click dang ki ngay
        LoginBUS actionDangKy = new LoginBUS(this);
        dangKyLabel2.addMouseListener(actionDangKy);

        dangKyPanel.add(dangKyLabel1);
        dangKyPanel.add(dangKyLabel2);
        panel.add(dangKyPanel);
    }


    //getter and setter
    public JButton getDangNhapButton() {return dangNhapButton;}
    public JLabel getDangKyLabel2(){return dangKyLabel2;}  
}
    
    // private class TaoTaiKhoan extends JDialog {
    //     public TaoTaiKhoan() {
    //         this.setTitle("Đăng ký");
    //         this.setSize(500,500);
    //         this.setLocationRelativeTo(null);
    //         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //         this.init();
    //         this.setVisible(true);
    //     }
    //     private void init() {
    //         this.setLayout(null);
    //         this.setBackground(Color.WHITE);
    //         //title dang ky
    //         JLabel titleDangky = new JLabel("Tạo tài khoản");
    //         titleDangky.setFont(new Font("JetBrains Mono",Font.PLAIN,30));
    //         titleDangky.setForeground(MainColor);
    //         titleDangky.setBounds(120,50,300,30);
    //         this.add(titleDangky);

    //         //label sdt
    //         JLabel sdtLabel = new JLabel("Số điện thoại ");
    //         sdtLabel.setFont(new Font("JetBrains Mono",Font.PLAIN,18));
    //         sdtLabel.setForeground(Color.GRAY);
    //         sdtLabel.setBounds(100,100,200,30);
    //         this.add(sdtLabel);

    //         //label ten dang nhap
    //         JLabel tenDangNhapLabel = new JLabel("Tên đăng nhập ");
    //         tenDangNhapLabel.setFont(new Font("JetBrains Mono",Font.PLAIN, 18));
    //         tenDangNhapLabel.setForeground(Color.GRAY);
    //         tenDangNhapLabel.setBounds(100,170,200,30);
    //         this.add(tenDangNhapLabel);
            
    //         //label mat khau
    //         JLabel matKhauLabel = new JLabel("Mật Khẩu ");
    //         matKhauLabel.setFont(new Font("JetBrains Mono",Font.PLAIN, 18));
    //         matKhauLabel.setForeground(Color.GRAY);
    //         matKhauLabel.setBounds(100,240,200,30);
    //         this.add(matKhauLabel);

    //         //label nhap lai mat khau
    //         JLabel nhapLaiMatKhauLabel = new JLabel("Nhập lại mật Khẩu ");
    //         nhapLaiMatKhauLabel.setFont(new Font("JetBrains Mono",Font.PLAIN, 18));
    //         nhapLaiMatKhauLabel.setForeground(Color.GRAY);
    //         nhapLaiMatKhauLabel.setBounds(100,310,200,30);
    //         this.add(nhapLaiMatKhauLabel);
            
    //         //textfield sdt
    //         JTextField sdtField = new JTextField("nhập số điện thoại....");
    //         sdtField.setFont(new Font("JetBrains Mono",Font.PLAIN, 13));
    //         sdtField.setForeground(Color.GRAY);
    //         sdtField.setBounds(100, 130, 300, 40);
    //         this.add(sdtField);

    //         //textfield ten dang nhap
    //         JTextField tenDangNhapField = new JTextField("nhập tên đăng nhập....");
    //         tenDangNhapField.setFont(new Font("JetBrains Mono",Font.PLAIN, 13));
    //         tenDangNhapField.setForeground(Color.GRAY);
    //         tenDangNhapField.setBounds(100, 200, 300, 40);
    //         this.add(tenDangNhapField);
    //         //textfield mat khau
    //         JPasswordField matKhauField = new JPasswordField("nhập mật khẩu....");
    //         matKhauField.setFont(new Font("JetBrains Mono",Font.PLAIN, 13));
    //         matKhauField.setForeground(Color.GRAY);
    //         matKhauField.setBounds(100, 270, 300, 40);
    //         matKhauField.setEchoChar((char) 0);
    //         this.add(matKhauField);
    //         //textfield nhap lai mat khau
    //         JPasswordField nhapLaiMatKhauField = new JPasswordField("nhập lại mật khẩu....");
    //         nhapLaiMatKhauField.setFont(new Font("JetBrains Mono",Font.PLAIN, 13));
    //         nhapLaiMatKhauField.setForeground(Color.GRAY);
    //         nhapLaiMatKhauField.setBounds(100, 340, 300, 40);
    //         nhapLaiMatKhauField.setEchoChar((char) 0);
    //         this.add(nhapLaiMatKhauField);

    //         //button dang ky
    //         JButton dangKyButton = new JButton("Đăng ký");
    //         dangKyButton.setFont(new Font("JetBrains Mono",Font.PLAIN,28));
    //         dangKyButton.setBackground(MainColor);
    //         dangKyButton.setForeground(Color.WHITE);
    //         dangKyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    //         dangKyButton.setBorderPainted(false);
    //         dangKyButton.setBounds(100,400,300,45);
    //         this.add(dangKyButton);

    //         //su kien hover textfield
    //         sdtField.addFocusListener(new FocusListener() {

    //             @Override
    //             public void focusGained(FocusEvent e) {
    //                 if(String.valueOf(sdtField.getText()).equals("nhập số điện thoại....")) {
    //                     sdtField.setText("");
    //                     sdtField.setForeground(Color.BLACK);
    //                 }
    //             }

    //             @Override
    //             public void focusLost(FocusEvent e) {
    //                 if(String.valueOf(sdtField.getText()).equals("")) {
    //                     sdtField.setText("nhập số điện thoại....");
    //                     sdtField.setForeground(Color.GRAY);
    //                 }
    //             }
    //         });

    //         tenDangNhapField.addFocusListener(new FocusListener() {

    //             @Override
    //             public void focusGained(FocusEvent e) {
    //                 if(String.valueOf(tenDangNhapField.getText()).equals("nhập tên đăng nhập....")) {
    //                     tenDangNhapField.setText("");
    //                     tenDangNhapField.setForeground(Color.BLACK);
    //                 }
    //             }

    //             @Override
    //             public void focusLost(FocusEvent e) {
    //                 if(String.valueOf(tenDangNhapField.getText()).equals("")) {
    //                     tenDangNhapField.setText("nhập tên đăng nhập....");
    //                     tenDangNhapField.setForeground(Color.GRAY);
    //                 }
    //             }
    //         });

    //         matKhauField.addFocusListener(new FocusListener() {

    //             @Override
    //             public void focusGained(FocusEvent e) {
    //                 if(String.valueOf(matKhauField.getPassword()).equals("nhập mật khẩu....")) {
    //                     matKhauField.setText("");
    //                     matKhauField.setForeground(Color.BLACK);
    //                     matKhauField.setEchoChar('*');
    //                 }
    //             }

    //             @Override
    //             public void focusLost(FocusEvent e) {
    //                 if(String.valueOf(matKhauField.getPassword()).equals("")) {
    //                     matKhauField.setText("nhập mật khẩu....");
    //                     matKhauField.setForeground(Color.GRAY);
    //                     matKhauField.setEchoChar((char) 0);
    //                 }
    //             }
                
    //         });

    //         nhapLaiMatKhauField.addFocusListener(new FocusListener() {

    //             @Override
    //             public void focusGained(FocusEvent e) {
    //                 if(String.valueOf(nhapLaiMatKhauField.getPassword()).equals("nhập lại mật khẩu....")) {
    //                     nhapLaiMatKhauField.setText("");
    //                     nhapLaiMatKhauField.setForeground(Color.BLACK);
    //                     nhapLaiMatKhauField.setEchoChar('*');
    //                 }
    //             }

    //             @Override
    //             public void focusLost(FocusEvent e) {
    //                 if(String.valueOf(nhapLaiMatKhauField.getPassword()).equals("")) {
    //                     nhapLaiMatKhauField.setText("nhập lại mật khẩu....");
    //                     nhapLaiMatKhauField.setForeground(Color.GRAY);
    //                     nhapLaiMatKhauField.setEchoChar((char) 0);
    //                 }
    //             }
                
    //         });
    //     }
    // }


    
