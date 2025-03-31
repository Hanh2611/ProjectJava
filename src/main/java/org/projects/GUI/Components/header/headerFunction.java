package org.projects.GUI.Components.header;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class headerFunction extends JPanel {
    private generalFunction gl;
    private HashMap<String,generalFunction> hm = new HashMap<>();
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
            gl = new generalFunction(i[0],i[1],i[2]);
            hm.put(i[2],gl);
            this.add(gl, c);
            c.gridx++;
        }
    }
    //getter
    public HashMap<String,generalFunction> getHm() {
        return hm;
    }

    public generalFunction getGl() {
        return gl;
    }
}
