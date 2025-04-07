package org.projects.GUI.Components;

import org.projects.GUI.Components.header.headerBar;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.util.List;

public class layoutCompoment {
    public static void addHeader(JPanel panel, String listItemHeader[][],List<String> listAction) {
        panel.setPreferredSize(new Dimension(1100, 1000));
        panel.setLayout(new FlowLayout(0, 0, 10));
        panel.setBackground(Color.decode("#CAECF7"));
        panel.add(new headerBar(listItemHeader, listAction));
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 10, 10, 10),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.WHITE, 0),
                        BorderFactory.createEmptyBorder(0, 0, 0, 0)
                )
        );
        panel.setBorder(border);
    }
    public static void setupMainPanel(JPanel panel) {
        panel.setPreferredSize(new Dimension(940, 650));
    }
}
