package org.projects.GUI.Panel.NhanVienPack;

import javax.swing.*;
import java.awt.*;

public class ChiTietAdminConsole extends JPanel {
    public ChiTietAdminConsole() {
        setupDetailBox_ADMIN();
    }
    // khi có quyền admin thì sẽ log ra lương , ngày làm việc , thưởng , ...
    public void setupDetailBox_ADMIN(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.RED);
        this.setPreferredSize(new Dimension(600 , 650));
        JPanel topLayout = new JPanel();
        JPanel botLayout = new JPanel();
        topLayout.setBackground(Color.BLUE);
        botLayout.setBackground(Color.YELLOW);
        // xây dựng top layout
        topLayout.setLayout(new GridLayout(1 , 2));
        topLayout.setPreferredSize(new Dimension(600 , 325));
        JPanel topImage = new JPanel();
        JPanel topInformation = new JPanel();
        topImage.setBackground(Color.PINK);
        topInformation.setBackground(Color.RED);
        topImage.setOpaque(true);
        topInformation.setOpaque(true);
        topLayout.add(topImage);
        topLayout.add(topInformation);
        topLayout.setOpaque(true);


        botLayout.setOpaque(true);
        this.add(topLayout, BorderLayout.NORTH);
        this.add(botLayout, BorderLayout.CENTER);
        this.setOpaque(true);

    }
}
