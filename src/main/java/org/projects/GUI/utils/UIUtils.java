package org.projects.GUI.utils;

import javax.swing.JComponent;

import org.projects.GUI.MainGUI;


public class UIUtils {

    public static void refreshComponent(JComponent component) {
        if (component != null) {
            component.revalidate();
            component.repaint();
        }
    }
    public static void refreshMain(MainGUI mainG) {
        mainG.revalidate();
        mainG.repaint();
    }
}
