package org.projects.GUI.Components.header;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class headerBar extends javax.swing.JPanel {
    private headerFunction headerFunc;
    public headerBar(String listItemHeader[][]) {
        this.setPreferredSize(new Dimension(940,100));
        this.setLayout(new FlowLayout(FlowLayout.LEFT,5, 0));
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        init(listItemHeader);
        this.setVisible(true);
    }
    public void init(String listItemHeader[][]) {
        this.add(new headerSearch(new Dimension(950, 100)));
        headerFunc = new headerFunction(new Dimension(950,100),listItemHeader);
        this.add(headerFunc);
//        this.add(new headerFunction(new Dimension(950, 100), listItemHeader));
    }
    //getter
    public headerFunction getHeaderFunc() {
        return headerFunc;
    }
}
