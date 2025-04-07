package org.projects.GUI.DiaLog.PhanQuyen;

import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.LoginGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class objectFactory {
    public static void titleBar(JPanel titleBar, JDialog dialog, String title) {
        JButton exitButton = handleComponents.createButtonIcon("icon/close.svg", 30, 30);
        JLabel titleText = new JLabel(title, JLabel.LEFT);
        titleText.setFont(new Font("JetBrains Mono",Font.PLAIN,13));
        titleBar.add(titleText);
        titleText.setBounds(10, 0, 200, 40);
        titleBar.setLayout(null);
        titleBar.setBackground(LoginGUI.MainColor);
        titleBar.setPreferredSize(new Dimension(900, 40));
        titleBar.add(exitButton);
        exitButton.setBounds(860, 0, 40, 40);
        exitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dialog.dispose();
            }
        });
    }
}
