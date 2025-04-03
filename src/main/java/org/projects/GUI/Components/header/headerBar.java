package org.projects.GUI.Components.header;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class headerBar extends javax.swing.JPanel {
    private headerFunction headerFunc;
    public headerBar(String[][] listItemHeader, String[] listAction) {
        this.setPreferredSize(new Dimension(940,100));
        this.setLayout(new FlowLayout(FlowLayout.LEFT,5, 0));
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        init(listItemHeader,listAction);
        this.setVisible(true);
    }
    public void init(String[][] listItemHeader, String[] listAction) {
        this.add(new headerSearch(new Dimension(950, 100)));
        headerFunc = new headerFunction(new Dimension(950,100),listItemHeader,listAction);
        this.add(headerFunc);
//        this.add(new headerFunction(new Dimension(950, 100), listItemHeader));
    }
    //getter
    public headerFunction getHeaderFunc() {
        return headerFunc;
    }
}
