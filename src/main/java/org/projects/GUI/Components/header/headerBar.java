package org.projects.GUI.Components.header;

import java.awt.*;

public class headerBar extends javax.swing.JPanel {
    public headerBar() {
        this.setPreferredSize(new Dimension(1100,150));
        init();
        this.setVisible(true);
    }
    public void init() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT,0, 0));
        this.add(new headerFunction(new Dimension(1100, 150)));
        this.add(new headerSearch(new Dimension(1100, 150)));
    }
}
