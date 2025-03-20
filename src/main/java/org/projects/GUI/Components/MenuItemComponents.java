package org.projects.GUI.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import com.formdev.flatlaf.extras.FlatSVGIcon;


import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import org.projects.GUI.MainGUI;

public class MenuItemComponents extends JPanel{
    private String iconn;
    private String name;
    private String panel;
    private JLabel namePanel;
    private JLabel iconLabel;
    private JLabel nameLabel;
    public MenuItemComponents(String iconn,String name,String panel,MainGUI mainGui) {
        this.setPreferredSize(new Dimension(250,30));
        this.setBorder(new BevelBorder(BevelBorder.RAISED));
        this.setLayout(new FlowLayout(0,20,15));
        this.setOpaque(true);

        FlatSVGIcon iconPath = new FlatSVGIcon(iconn, 30, 30);
        iconLabel = new JLabel(iconPath,JLabel.CENTER);
        this.add(iconLabel);

        nameLabel = new JLabel(name);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("JetBrains Mono",Font.BOLD,16));
        this.add(nameLabel);
    }   

}
