package org.projects.GUI.Components.header;

import java.awt.*;

public class headerSearch extends javax.swing.JPanel {
    public headerSearch(Dimension parentSize) {
        this.setPreferredSize(new Dimension((int) (parentSize.width*0.7), parentSize.height));
        this.setOpaque(true);
        this.setBackground(Color.GRAY);
        this.setVisible(true);
    }
}
