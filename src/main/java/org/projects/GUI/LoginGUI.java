package org.projects.GUI;

import org.projects.Action.LoginAction;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.utils.FocusListenerUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.formdev.flatlaf.extras.FlatSVGIcon;

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
        FlatSVGIcon iconMiniMarket = new FlatSVGIcon("icon/supermarket.svg", 200, 200);
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
        JButton iconOpenEye = handleComponents.createButtonIcon("icon/eye.svg", 20, 20);
        iconOpenEye.setBounds(270,5, 30,30);
        iconOpenEye.setVisible(false);

        JButton iconCloseEye = handleComponents.createButtonIcon("icon/closed-eyes.svg", 20, 20);
        iconCloseEye.setBounds(270, 5, 30, 30);
        iconCloseEye.setVisible(true);

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

        //them controller click dang ki,dang nhap
        LoginAction action = new LoginAction(this);
        dangKyLabel2.addMouseListener(action);
        dangNhapButton.addMouseListener(action);


        dangKyPanel.add(dangKyLabel1);
        dangKyPanel.add(dangKyLabel2);
        panel.add(dangKyPanel);
    }


    //getter and setter
    public JButton getDangNhapButton() {return dangNhapButton;}
    public JLabel getDangKyLabel2(){return dangKyLabel2;}  
}
