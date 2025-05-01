package org.projects.GUI.DiaLog.KhachHang;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.Components.Transition.mainTransition;
import org.projects.GUI.Panel.KhachHangPack.DetailKhachHangConsole;
import org.projects.GUI.Panel.KhachHangPack.KhachHang;
import org.projects.GUI.Panel.NhanVienPack.ChiTietUserConsole;
import org.projects.GUI.Panel.NhanVienPack.NhanVien;

import javax.swing.*;
import java.awt.*;

public class ShowDeltailKhachHang extends JDialog {
    KhachHang khachHang;
    mainTransition transition = new mainTransition();
    public DetailKhachHangConsole detailKhachHangConsole;
    public ShowDeltailKhachHang(){
        khachHang = new KhachHang();
        detailKhachHangConsole = new DetailKhachHangConsole();
    }
    public void Show() {
        init();
//        transition.showZoomIn(this , 700 , 800);
    }

    public void init() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel detailPanel = detailKhachHangConsole.setupDetailBox_USER();
        this.setUndecorated(true);
        FlatSVGIcon svgIcon = new FlatSVGIcon("icon/cashier.svg", 32, 32);
        this.setIconImage(svgIcon.getImage());
        JLabel titleLabel = new JLabel("Chi tiết khách hàng" , SwingConstants.CENTER);
        titleLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setBackground(new Color(0, 102, 204));
        titleLabel.setOpaque(true);
        JButton cancelButton = NhanVien.add_cancelIcon(this);
        titleLabel.add(cancelButton);
        this.getContentPane().add(titleLabel, BorderLayout.NORTH);
        this.getContentPane().add(detailPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
