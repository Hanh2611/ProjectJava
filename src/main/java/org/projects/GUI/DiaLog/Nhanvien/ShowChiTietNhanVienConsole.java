package org.projects.GUI.DiaLog.Nhanvien;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.Components.Transition.mainTransition;
import org.projects.GUI.Panel.NhanVienPack.ChiTietAdminConsole;
import org.projects.GUI.Panel.NhanVienPack.ChiTietUserConsole;
import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.entity.NhanVienEntity;

import javax.swing.*;
import java.awt.*;

public class ShowChiTietNhanVienConsole extends JDialog {
    NhanVien nhanVien;
    mainTransition transition = new mainTransition();
    public ChiTietUserConsole chiTietUserConsole;
    public ShowChiTietNhanVienConsole(){
        nhanVien = new NhanVien();
        chiTietUserConsole = new ChiTietUserConsole();
    }
    public void Show() {
        init();
        transition.showFadeIn(this , 700 , 400);
    }

    public void init() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel detailPanel = chiTietUserConsole.setupDetailBox_USER();
        this.setUndecorated(true);
        FlatSVGIcon svgIcon = new FlatSVGIcon("icon/cashier.svg", 32, 32);
        this.setIconImage(svgIcon.getImage());
        JLabel titleLabel = new JLabel("Chi tiết nhân viên" , SwingConstants.CENTER);
        titleLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setBackground(new Color(0, 102, 204));
        titleLabel.setOpaque(true);
        JButton cancelButton = NhanVien.add_cancelIcon(this);
        titleLabel.add(cancelButton);
        this.getContentPane().add(titleLabel, BorderLayout.NORTH);
        this.getContentPane().add(detailPanel);
        this.pack();
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
    }
}
