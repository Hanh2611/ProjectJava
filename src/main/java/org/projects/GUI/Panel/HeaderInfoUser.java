package org.projects.GUI.Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import org.projects.GUI.LoginGUI;
import org.projects.GUI.Components.handleComponents;

public class HeaderInfoUser extends JPanel {
    private JLabel iconUser;
    private JLabel tenNguoiDung;
    private JLabel chucVu;
    Color BACKGROUND_USER = Color.decode("#f1f2f6");

    public HeaderInfoUser() {
        this.init();
        this.setPreferredSize(new Dimension(300, 100));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setVisible(true);
    }
    private void init() {
        this.setLayout(null);
        //icon user
        FlatSVGIcon icon = new FlatSVGIcon("icon/user.svg", 70, 70);

        iconUser = new JLabel(icon);
        iconUser.setBounds(10,15,70,70);
        this.setBackground(BACKGROUND_USER);
        this.add(iconUser);

        //ten nguoi dung + chuc vu
        tenNguoiDung = new JLabel("Tên người dùng");
        tenNguoiDung.setBounds(95, 20, 200, 40);
        tenNguoiDung.setFont(new Font("JetBrains Mono",Font.BOLD,13));
        tenNguoiDung.setForeground(Color.BLACK);
        this.add(tenNguoiDung);

        chucVu = new JLabel("Chức vụ");
        chucVu.setBounds(95, 40, 200, 40);
        chucVu.setFont(new Font("JetBrains Mono",Font.PLAIN,12));
        chucVu.setForeground(Color.BLACK);
        this.add(chucVu);
        
    }
}
