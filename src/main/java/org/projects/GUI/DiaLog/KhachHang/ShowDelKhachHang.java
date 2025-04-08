package org.projects.GUI.DiaLog.KhachHang;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.Components.Transition.mainTransition;
import org.projects.GUI.Panel.KhachHangPack.DelKhachHangConsole;
import org.projects.GUI.Panel.KhachHangPack.KhachHang;
import org.projects.GUI.Panel.NhanVienPack.DeleteNhanVienConsole;
import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.entity.KhachHangEntity;
import org.projects.entity.NhanVienEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowDelKhachHang extends JDialog {
    KhachHang khachHang;
    public DelKhachHangConsole del;
    public mainTransition transition = new mainTransition();
    public ShowDelKhachHang(KhachHangEntity entity) {
        khachHang = new KhachHang();
        del = new DelKhachHangConsole();
        initComponents();
        transition.showZoomIn(this,700 , 400);
    }
    public ShowDelKhachHang(){}
    void initComponents() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setUndecorated(true);
        this.setSize(new Dimension(700, 400));
        this.setMinimumSize(new Dimension(700, 400));
        JPanel detailPanel = del;
        FlatSVGIcon svgIcon = new FlatSVGIcon("icon/cashier.svg", 32, 32);
        this.setIconImage(svgIcon.getImage());
        JLabel titleLabel = new JLabel("Xóa khách hàng" , SwingConstants.CENTER);
        titleLabel.setFont(new Font("JetBrains Mono",Font.BOLD, 20));
        titleLabel.setForeground(Color.white);
        titleLabel.setBackground(new Color(0, 102, 204));
        titleLabel.setOpaque(true);
        this.getContentPane().add(titleLabel, BorderLayout.NORTH);
        this.getContentPane().add(detailPanel);
        this.pack();
    }
    public void close(){
        transition.closeWithZoomOut(this);
    }
}
