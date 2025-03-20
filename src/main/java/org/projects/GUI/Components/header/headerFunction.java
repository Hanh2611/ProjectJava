package org.projects.GUI.Components.header;

import org.projects.GUI.Components.handleComponents;

import javax.swing.*;
import java.awt.*;

public class headerFunction extends JPanel {
    public headerFunction(Dimension parentSize, String listItemHeader[][]) {
        this.setPreferredSize(new Dimension((int) (parentSize.width*0.3), parentSize.height));
        this.setOpaque(true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.CENTER;
        this.setBackground(Color.GRAY);
        for (String [] i : listItemHeader) {
            String iconPath = i[0];
            JButton button = handleComponents.createButtonIcon(iconPath, 50, 50);
            this.add(button, c);
            c.gridx++;
        }
        this.setVisible(true);
    }
}
