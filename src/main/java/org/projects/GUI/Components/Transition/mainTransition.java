package org.projects.GUI.Components.Transition;
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

    public void showFadeIn(JDialog dialog, int width, int height) {
        //dialog.setUndecorated(true);
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(null);
        dialog.setOpacity(0f);
        dialog.setVisible(true);

        Timer timer = new Timer(10, null);
        timer.addActionListener(new ActionListener() {
            float opacity = 0f;
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.05f;
                if (opacity >= 1f) {
                    opacity = 1f;
                    timer.stop();
                }
                dialog.setOpacity(opacity);
            }
        });
        timer.start();
    }

    public void closeFadeOut(JDialog dialog) {
        Timer timer = new Timer(10, null);
        timer.addActionListener(new ActionListener() {
            float opacity = 1f;
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= 0.05f;
                if (opacity <= 0f) {
                    opacity = 0f;
                    dialog.setVisible(false);
                    dialog.dispose();
                    timer.stop();
                }
                dialog.setOpacity(opacity);
            }
        });
        timer.start();
    }

    public void showSlideIn(JDialog dialog, int width, int height) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int startX = screen.width - 500;
        int endX = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        dialog.setSize(width, height);
        dialog.setLocation(startX, y);
        dialog.setVisible(true);

        Timer timer = new Timer(10, null);
        timer.addActionListener(new ActionListener() {
            int step = 0, totalSteps = 20;
            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                int x = startX - (int)((startX - endX) * (step / (float) totalSteps));
                dialog.setLocation(x, y);
                if (step >= totalSteps) {
                    dialog.setLocation(endX, y);
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public void closeSlideOut(JDialog dialog) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int startX = dialog.getX();
        int endX = -dialog.getWidth() + 500;
        int y = dialog.getY();

        Timer timer = new Timer(10, null);
        timer.addActionListener(new ActionListener() {
            int step = 0, totalSteps = 35;
            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                int x = startX + (int)((endX - startX) * (step / (float) totalSteps));
                dialog.setLocation(x, y);
                if (step >= totalSteps) {
                    dialog.setVisible(false);
                    dialog.dispose();
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public void closeSlideOutSP(JDialog dialog) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int startX = dialog.getX();
        int endX = -dialog.getWidth() + 700;
        int y = dialog.getY();

        Timer timer = new Timer(10, null);
        timer.addActionListener(new ActionListener() {
            int step = 0, totalSteps = 15;
            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                int x = startX + (int)((endX - startX) * (step / (float) totalSteps));
                dialog.setLocation(x, y);
                if (step >= totalSteps) {
                    dialog.setVisible(false);
                    dialog.dispose();
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public void showSplitHorizontal(JDialog dialog, int width, int height) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screen.width - width) / 2;
        int centerY = (screen.height - height) / 2;

        dialog.setSize(0, height);
        dialog.setLocation(centerX + width / 2, centerY);
        dialog.setVisible(true);

        Timer timer = new Timer(10, null);
        timer.addActionListener(new ActionListener() {
            int step = 0, totalSteps = 20;
            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                double fraction = step / (double) totalSteps;
                int w = (int) (width * fraction);
                int x = centerX + (width - w) / 2;
                dialog.setSize(w, height);
                dialog.setLocation(x, centerY);
                if (step >= totalSteps) {
                    dialog.setSize(width, height);
                    dialog.setLocation(centerX, centerY);
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public void closeSplitHorizontal(JDialog dialog) {
        int width = dialog.getWidth();
        int height = dialog.getHeight();
        Point location = dialog.getLocation();

        int startX = location.x;
        int centerY = location.y;

        Timer timer = new Timer(10, null);
        timer.addActionListener(new ActionListener() {
            int step = 0, totalSteps = 20;
            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                double fraction = step / (double) totalSteps;
                int x = (int) (startX - (width / 2 * fraction));
                dialog.setLocation(x, centerY);
                if (step >= totalSteps) {
                    dialog.setVisible(false);
                    dialog.dispose();
                    timer.stop();
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
