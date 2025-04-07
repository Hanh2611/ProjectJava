package org.projects.GUI.Panel.NhanVienPack;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.Action.NhanVienAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteNhanVienConsole extends JPanel {
    JButton okButton , cancelButton;
    private NhanVienAction action = new NhanVienAction();
    public DeleteNhanVienConsole(){
        initComponents();
    }
    public void initComponents(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(700 , 400));
        FlatSVGIcon svgIcon = new FlatSVGIcon("icon/red-trash-can-icon.svg", 100, 100);
        JLabel label = new JLabel(svgIcon);
        label.setBounds(300 , 70 , 100, 100);
        JTextField textPane = new JTextField("Bạn có muốn xóa nhân viên này không ?");
        textPane.setEditable(false);
        textPane.setBounds(130 , 200 , 700, 30);
        textPane.setFont(new Font("JETBRAINS MONO", Font.BOLD, 20));
        textPane.setForeground(Color.BLACK);
        textPane.setBorder(null);
        textPane.setFocusable(false);
        textPane.setOpaque(false);
        okButton = new JButton("Có");
        cancelButton = new JButton("Không");
        okButton.setBorderPainted(false);
        cancelButton.setBorderPainted(false);
        okButton.setPreferredSize(new Dimension(100, 50));
        cancelButton.setPreferredSize(new Dimension(100, 50));
        okButton.setBackground(new Color(50 , 205 , 50));
        cancelButton.setBackground(new Color(250, 118, 114));
        okButton.addActionListener(action);
        cancelButton.addActionListener(action);
        cancelButton.setForeground(Color.BLACK);
        okButton.setForeground(Color.BLACK);
        okButton.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
        cancelButton.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
        cancelButton.setBounds(200 , 300 , 100, 50);
        okButton.setBounds(400 , 300 , 100, 50);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.add(textPane);
        this.add(label);
        this.add(okButton);
        this.add(cancelButton);
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getOkButton() {
        return okButton;
    }
}
