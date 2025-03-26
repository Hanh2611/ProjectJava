package org.projects.GUI.Components.header;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class headerFunction extends JPanel {
    private MainGUI mg;
    private generalFunction gl;
    public headerFunction(Dimension parentSize, String listItemHeader[][]) {
        this.mg = mg;
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
            gl = new generalFunction(i[0],i[1],i[2],mg);
            gl.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                   generalFunction btn = (generalFunction) e.getSource();
                   if(btn.getNameFunction().equals(i[2])) {
                       System.out.println("nut duoc nhan la: " + i[1]);
                   }
                }
            });
            this.add(gl, c);
            c.gridx++;
        }
    }
}
