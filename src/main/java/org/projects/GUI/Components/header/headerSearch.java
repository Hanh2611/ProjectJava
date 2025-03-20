package org.projects.GUI.Components.header;

import java.awt.*;

public class headerSearch extends javax.swing.JPanel {
    public headerSearch(Dimension parentSize) {
        this.setPreferredSize(new Dimension((int) (parentSize.width*0.5), parentSize.height));
        this.setOpaque(true);
        this.setBackground(Color.black);
        this.setVisible(true);
    }
}
