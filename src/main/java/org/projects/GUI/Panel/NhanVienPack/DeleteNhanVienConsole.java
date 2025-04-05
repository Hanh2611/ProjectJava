package org.projects.GUI.Panel.NhanVienPack;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;

public class DeleteNhanVienConsole extends JPanel {
    DeleteNhanVienConsole(){
        initComponents();
    }
    public void initComponents(){
        this.setLayout(new BorderLayout(5, 5));
        this.setPreferredSize(new Dimension(700 , 400));
        FlatSVGIcon svgIcon = new FlatSVGIcon("icons/red-trash-can-icon.svg" , 700 , 400);
        JLabel label = new JLabel(svgIcon);

        this.add(label, BorderLayout.CENTER);
    }
}
