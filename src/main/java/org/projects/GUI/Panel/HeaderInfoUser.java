package org.projects.GUI.Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import org.projects.GUI.LoginGUI;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.utils.Session;
import org.projects.config.DatabasesConfig;

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
    public String getTenNguoiDung(int maNguoiDung) {
        String tenNguoiDung = null;
        String query = "SELECT ten_nguoi_dung FROM nguoi_dung WHERE ma_nguoi_dung = ?";
        System.out.println(maNguoiDung);
        try (Connection conn = DatabasesConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query))   {

            ps.setInt(1, maNguoiDung);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tenNguoiDung = rs.getString("ten_nguoi_dung");
                System.out.println(tenNguoiDung);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tenNguoiDung;
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
        int id = Session.curUser.getMaNguoiDung();
        String user = getTenNguoiDung(id);
        tenNguoiDung = new JLabel(user);
        tenNguoiDung.setBounds(95, 35, 200, 40);
        tenNguoiDung.setFont(new Font("JetBrains Mono",Font.BOLD,13));
        tenNguoiDung.setForeground(Color.BLACK);
        this.add(tenNguoiDung);

//        chucVu = new JLabel("Chức vụ");
//        chucVu.setBounds(95, 40, 200, 40);
//        chucVu.setFont(new Font("JetBrains Mono",Font.PLAIN,12));
//        chucVu.setForeground(Color.BLACK);
//        this.add(chucVu);
        
    }
}
