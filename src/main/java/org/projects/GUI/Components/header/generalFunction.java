package org.projects.GUI.Components.header;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class generalFunction extends JPanel {
    private String iconPathFunciton;
    private String nameIcon;
    private String nameFunction;
    private JLabel iconPathFunctionLabel;
    private JLabel nameIconLabel;

    public generalFunction(String iconPathFunciton, String nameIcon, String nameFunction) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        this.iconPathFunciton = iconPathFunciton;
        this.nameIcon = nameIcon;
        this.nameFunction = nameFunction;
        FlatSVGIcon icon = new FlatSVGIcon(iconPathFunciton, 40, 40);
        this.setPreferredSize(new Dimension(60,60));
        this.setBackground(Color.WHITE);
        iconPathFunctionLabel = new JLabel(icon,JLabel.CENTER);
        this.add(iconPathFunctionLabel);

        nameIconLabel = new JLabel(nameIcon,JLabel.CENTER);
        this.add(nameIconLabel);

        this.setOpaque(false);
        this.setFocusable(true);

    }
    //getter setter
    public String getIconPathFunciton() {
        return iconPathFunciton;
    }

    public void setIconPathFunciton(String iconPathFunciton) {
        this.iconPathFunciton = iconPathFunciton;
    }

    public String getNameIcon() {
        return nameIcon;
    }

    public void setNameIcon(String nameIcon) {
        this.nameIcon = nameIcon;
    }

    public String getNameFunction() {
        return nameFunction;
    }

    public void setNameFunction(String nameFunction) {
        this.nameFunction = nameFunction;
    }

    public JLabel getIconPathFunctionLabel() {
        return iconPathFunctionLabel;
    }

    public void setIconPathFunctionLabel(JLabel iconPathFunctionLabel) {
        this.iconPathFunctionLabel = iconPathFunctionLabel;
    }

    public JLabel getNameIconLabel() {
        return nameIconLabel;
    }

    public void setNameIconLabel(JLabel nameIconLabel) {
        this.nameIconLabel = nameIconLabel;
    }
}
