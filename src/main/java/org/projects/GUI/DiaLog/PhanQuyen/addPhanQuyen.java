package org.projects.GUI.DiaLog.PhanQuyen;

import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.LoginGUI;
import org.projects.GUI.utils.FocusListenerUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class addPhanQuyen extends JDialog {
    private JPanel titleBar, contentPanel;
    private JTextField textField;
    private JLabel nameLabel;

    public addPhanQuyen(JFrame parent) {
        super(parent, "ADD PHAN QUYEN", true);
        this.setBackground(Color.decode("#FFFFFF"));
        setLayout(new FlowLayout(0, 0, 0));
        setSize(900, 700);
        setLocationRelativeTo(parent);
        setUndecorated(true);
        titleBar = new JPanel();
        objectFactory.titleBar(titleBar, this);
        this.add(titleBar);
        init();
        setVisible(true);
    }
    public void init() {
        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(900, 660));
        contentPanel.setLayout(new FlowLayout( FlowLayout.CENTER, 10, 10));
        nameLabel = new JLabel("Tên nhóm quyền: ");
        nameLabel.setFont(new Font("JetBrains Mono",Font.BOLD,14));
        textField = handleComponents.createTextField("Nhập tên nhóm quyền.....", 60, 190, 500, 50);
        textField.setPreferredSize(new Dimension(500, 30));
        textField.addFocusListener(FocusListenerUtils.createPlaceholderTextField("Nhập tên nhóm quyền.....", textField));
        contentPanel.add(nameLabel);
        contentPanel.add(textField);
        this.add(contentPanel);
    }

    public void initManagementDirectory() {
        JPanel panel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel checkBoxPanel = new JPanel();
        panel.setPreferredSize(new Dimension(900, 100));
        panel.setBackground(Color.gray);
    }
}
