package org.projects.GUI.Components.header;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.Components.handleComponents;

import javax.swing.*;
import java.awt.*;

public class headerFunction extends JPanel {
    public headerFunction(Dimension parentSize, String listItemHeader[][]) {
        this.setPreferredSize(new Dimension((int) (390), parentSize.height));
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        init(listItemHeader);
        this.setVisible(true);
    }
    public void init(String listItemHeader[][]) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.CENTER;
        for (String [] i : listItemHeader) {
            JPanel button = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            FlatSVGIcon iconPath = new FlatSVGIcon(i[0], 40, 40);
            JLabel label = new JLabel(iconPath, JLabel.CENTER);
            button.setPreferredSize(new Dimension(60, 60));
            button.setOpaque(true);
            button.setBackground(Color.WHITE);
            button.add(label);
            JLabel directionLabel = new JLabel(i[1], JLabel.CENTER);
            button.add(directionLabel);
            button.add(new JLabel(new ImageIcon((new ImageIcon(i[0])).getImage().getScaledInstance(50, 50, 4))));
            this.add(button, c);
            c.gridx++;
        }
    }
}
