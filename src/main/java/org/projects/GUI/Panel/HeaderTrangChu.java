package org.projects.GUI.Panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.LoginGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HeaderTrangChu extends JPanel{
    private JPanel left,right;
    private JLabel icon,title,close;
    private JButton dangnhap;
    public HeaderTrangChu() {
        this.setLayout(new GridLayout(1,2));
        this.setBackground(LoginGUI.MainColor);
        left = new JPanel();
        right = new JPanel();
        left.setOpaque(false);
        right.setOpaque(false);
        init();
    }
    private void init() {
        left.setLayout(new FlowLayout(FlowLayout.LEFT));
        FlatSVGIcon svg = new FlatSVGIcon("icon/marketplace.svg",30,30);
        icon = new JLabel(svg);
        title = new JLabel("Siêu thị Mini ");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Tahoma", Font.BOLD, 19));
        icon.setBorder(BorderFactory.createEmptyBorder(10,10,10,0));
        title.setBorder(BorderFactory.createEmptyBorder(12,5,10,0));
        left.add(icon);
        left.add(title);
        right.setLayout(new FlowLayout(FlowLayout.RIGHT));
        dangnhap = handleComponents.createButtonIcon("icon/sign-up.svg",40,40);
        dangnhap.setBorder(BorderFactory.createEmptyBorder(5,10,10,0));
        FlatSVGIcon svg1 = new FlatSVGIcon("icon/close.svg",30,30);
        close = new JLabel(svg1);
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));
        close.setBorder(BorderFactory.createEmptyBorder(5,5,10,5));
        right.add(dangnhap);
        right.add(close);
        //xu li cac acction click vao close va dang nhap
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int tb = JOptionPane.showConfirmDialog(null,"Bạn có chắc muốn thoát ? ","thông báo",JOptionPane.YES_NO_OPTION);
                if(tb == JOptionPane.YES_NO_OPTION){
                    JOptionPane.showMessageDialog(null,"Cảm ơn bạn đã sử dụng dịch vụ,Hẹn gặp lại!","Thông báo ",JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
        });

        dangnhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null,"Cảm ơn bạn đã sử dụng dịch vụ","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new LoginGUI().setVisible(true);
                    }
                });
            }
        });
        this.add(left);
        this.add(right);
    }
}
