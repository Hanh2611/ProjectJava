package org.projects.GUI.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import org.projects.GUI.MainGUI;

public class MenuItemComponents extends JPanel{
    private String icon;
    private String name;
    private String panel;
    private JLabel namePanel;
    private JLabel iconLabel;
    private JLabel nameLabel;
    public MenuItemComponents(String icon,String name,String panel,MainGUI mainGui) {
        this.setPreferredSize(new Dimension(250,40));
        this.setBorder(new BevelBorder(BevelBorder.RAISED));
        this.setLayout(new FlowLayout(0,20,15));
        this.setOpaque(true);
        
        ImageIcon iconPath = new ImageIcon(new ImageIcon(icon).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        iconLabel = new JLabel(iconPath,JLabel.CENTER);
        this.add(iconLabel);

        nameLabel = new JLabel(name);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("JetBrains Mono",Font.BOLD,16));
        this.add(nameLabel);
    }   

}
