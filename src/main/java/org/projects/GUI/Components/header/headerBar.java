package org.projects.GUI.Components.header;

import java.awt.*;

public class headerBar extends javax.swing.JPanel {
    public headerBar(String listItemHeader[][]) {
        this.setPreferredSize(new Dimension(1100,100));
        init(listItemHeader);
        this.setVisible(true);
    }
    public void init(String listItemHeader[][]) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT,0, 0));
        this.add(new headerFunction(new Dimension(1100, 100), listItemHeader));
        this.add(new headerSearch(new Dimension(1100, 100)));
    }
}
