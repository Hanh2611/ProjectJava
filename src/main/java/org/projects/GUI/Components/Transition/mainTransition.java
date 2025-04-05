package org.projects.GUI.Components.Transition;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.Panel.NhanVienPack.DeleteNhanVienConsole;
import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.entity.NhanVienEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainTransition{

    public void showZoomIn(JDialog jDialog, int width, int height) {
        Dimension targetSize = new Dimension(width, height);
        Point targetLocation = getCenteredLocation(targetSize);

        jDialog.setLocation(targetLocation.x + targetSize.width / 2, targetLocation.y + targetSize.height / 2);
        jDialog.setVisible(true);

        Timer timer = new Timer(5, null);
        timer.addActionListener(new ActionListener() {
            int step = 0;
            final int totalSteps = 18;
            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                int n_width = (int) (targetSize.width * step / (float) totalSteps);
                int n_height = (int) (targetSize.height * step / (float) totalSteps);
                jDialog.setSize(width, height);
                jDialog.setLocation(getCenteredLocation(new Dimension(n_width, n_height)));
                if (step >= totalSteps) {
                    jDialog.setSize(targetSize);
                    jDialog.setLocation(targetLocation);
                    timer.stop();
                }
            }
        });
        timer.start();
    }


    public void closeWithZoomOut(JDialog jDialog) {
        Dimension currentSize = jDialog.getSize();
        Point currentLocation = jDialog.getLocation();

        Timer timer = new Timer(1, null);
        timer.addActionListener(new ActionListener() {
            int step = 0;
            final int totalSteps = 20;
            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                int width = (int) (currentSize.width * (1 - step / (float) totalSteps));
                int height = (int) (currentSize.height * (1 - step / (float) totalSteps));
                if (width <= 0 || height <= 0) {
                    jDialog.setVisible(false);
                    jDialog.dispose();
                    timer.stop();
                } else {
                    jDialog.setSize(width, height);
                    jDialog.setLocation(getCenteredLocation(new Dimension(width, height)));
                }
            }
        });
        timer.start();
    }

    private Point getCenteredLocation(Dimension size) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - size.width) / 2;
        int y = (screenSize.height - size.height) / 2;
        return new Point(x, y);
    }
}
