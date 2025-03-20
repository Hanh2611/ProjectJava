package org.projects.GUI.Panel;

import com.formdev.flatlaf.ui.FlatLineBorder;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NhanVien extends JPanel{
    JPanel topContent , botContent , Head , Tail;
    public NhanVien() {
        this.setBackground(Color.BLUE);
        init();
        this.setVisible(true);
    }
    public void init(){
        String[] listCustomer = {"Mã khách hàng" , "Tên khách hàng" , "Địa chỉ" , "SĐT" , "Ngày tham gia"};
        // Tạo girdBag layout cho this
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0 , 0 , 0 ,0);
        Head = new JPanel();
        Tail = new JPanel();
        Head.setSize(1100 , 300);
        Tail.setSize(1100 , 700);
//        Head.setBackground(Color.BLUE);
//        Tail.setBackground(Color.RED);
//        Head.setOpaque(true);
//        Tail.setOpaque(true);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 100;
        gbc.weightx = 1;
        this.add(Head, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1000;
        gbc.weightx = 1;
        this.add(Tail, gbc);
        // Làm việc với topContent
        topContent = new JPanel();
        topContent.setBackground(Color.white);
        topContent.setOpaque(true);
        topContent.setPreferredSize(new Dimension(1100, 300));
        // Làm việc với botContent
        botContent = new JPanel();
        botContent.setLayout(new FlowLayout(FlowLayout.LEFT , 0 ,0));
        botContent.setBackground(Color.GRAY);
        botContent.setOpaque(true);
        botContent.setPreferredSize(new Dimension(1100, 40));
        for(String s : listCustomer){
            JLabel label = new JLabel(s);
            label.setPreferredSize(new Dimension(200 , 30));
            botContent.add(label);
        }
        // Thêm vào this
        Head.add(topContent);
        Tail.add(botContent);
    }
}
