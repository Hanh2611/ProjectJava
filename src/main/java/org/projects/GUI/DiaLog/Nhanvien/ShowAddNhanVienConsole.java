package org.projects.GUI.DiaLog.Nhanvien;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.Components.Transition.mainTransition;
import org.projects.GUI.Panel.NhanVienPack.AddNhanVienConsole;
import org.projects.GUI.Panel.NhanVienPack.NhanVien;

import javax.swing.*;
import java.awt.*;
public class ShowAddNhanVienConsole extends JDialog {
    NhanVien nhanVien;
    public AddNhanVienConsole add;
    public mainTransition transition = new mainTransition();
    public ShowAddNhanVienConsole() {
        nhanVien = new NhanVien();
        add = new AddNhanVienConsole();
        init();
        transition.showSplitHorizontal(this , 500 , 700);
    }
    public void init(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setUndecorated(true);
        JPanel detailPanel = add;
        FlatSVGIcon svgIcon = new FlatSVGIcon("icon/cashier.svg", 32, 32);
        this.setIconImage(svgIcon.getImage());
        this.setSize(new Dimension(500, 700));
        this.setMinimumSize(new Dimension(500, 700));
        JLabel titleLabel = new JLabel("Thêm nhân viên", SwingConstants.CENTER);
        titleLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
        titleLabel.setForeground(Color.white);
        titleLabel.setBackground(new Color(0, 102, 204));
        titleLabel.setOpaque(true);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(titleLabel, BorderLayout.NORTH);
        this.getContentPane().add(detailPanel, BorderLayout.CENTER);
        this.pack();
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
    }
    public void close(){
        transition.closeSplitHorizontal(this);
        //this.dispose();
    }
}
