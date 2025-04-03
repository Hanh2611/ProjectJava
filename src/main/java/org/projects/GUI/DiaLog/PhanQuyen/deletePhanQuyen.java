package org.projects.GUI.DiaLog.PhanQuyen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deletePhanQuyen extends JDialog {
    public deletePhanQuyen(JFrame parent) {
        super(parent, "delete PHAN QUYEN", true); // Modal dialog

        // Tạo nút bấm
        JButton btnClick = new JButton("Click Me");

        // Thêm sự kiện bấm nút
        btnClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(parent, "Button Clicked!");
            }
        });

        // Thiết lập layout
        setLayout(new FlowLayout());
        add(btnClick);

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
