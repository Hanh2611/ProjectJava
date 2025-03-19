package org.projects;

import com.formdev.flatlaf.FlatLightLaf;
import org.projects.GUI.LoginGUI;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginGUI();
            }
        });
    }
}
