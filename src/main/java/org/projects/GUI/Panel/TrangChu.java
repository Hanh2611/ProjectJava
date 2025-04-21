package org.projects.GUI.Panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class TrangChu extends JPanel {
    public TrangChu() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(245, 245, 245));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JLabel logo = new JLabel(new FlatSVGIcon("icon/supermarket.svg", 100, 100));
        header.add(logo, BorderLayout.WEST);
        JPanel textHeader = new JPanel(new BorderLayout());
        header.add(textHeader, BorderLayout.CENTER);
        textHeader.setOpaque(false);
        textHeader.setLayout(new BoxLayout(textHeader, BoxLayout.Y_AXIS));
        JLabel text1 = new JLabel("   SIÊU THỊ MINI");
        text1.setForeground(Color.BLACK);
        text1.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
        JLabel text2 = new JLabel("    - Người bạn đồng hành cũng mọi gia đình -");
        text2.setForeground(Color.BLACK);
        text2.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        text1.setAlignmentX(Component.LEFT_ALIGNMENT);
        text2.setAlignmentX(Component.LEFT_ALIGNMENT);
        textHeader.add(Box.createVerticalStrut(30));
        textHeader.add(text1);
        textHeader.add(Box.createVerticalStrut(10));
        textHeader.add(text2);
        add(header, BorderLayout.NORTH);

        JPanel borderCenter = new JPanel();
        borderCenter.setLayout(new BoxLayout(borderCenter, BoxLayout.Y_AXIS));
        borderCenter.setBackground(new Color(245, 245, 245));
        borderCenter.setOpaque(true);
        JPanel center = new JPanel(new GridLayout(1 , 3 , 30 , 10));
        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        center.setOpaque(true);
        center.setBackground(new Color(245, 245, 245));
        JPanel test1 = createBox("icon/supplier.svg" , "" , "AN TOÀN-CHẤT LƯỢNG" , 255);
        JPanel test2 = createBox("icon/phucvu.svg" , "" , "PHỤC VỤ TẬN TÌNH" , 255);
        JPanel test3 = createBox("icon/categories.svg" , "" , "CÀNG MUA CÀNG VUI" , 255);
        borderCenter.add(Box.createVerticalStrut(20));
        center.add(test1);
        center.add(test2);
        center.add(test3);
        borderCenter.add(center);
        borderCenter.add(Box.createVerticalStrut(85));
        add(borderCenter, BorderLayout.CENTER);
    }

    JPanel createBox(String img , String text , String slogan , int rgb) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBackground(new Color(rgb , rgb , rgb));
        box.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        FlatSVGIcon icon = new FlatSVGIcon(img, 150, 150);
        JPanel borderIcon = new JPanel(new BorderLayout());
        JLabel label = new JLabel(icon);
        borderIcon.setOpaque(true);
        borderIcon.setBackground(new Color(250 , 250 , 250));
        borderIcon.add(label , BorderLayout.CENTER);
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.WHITE, 0),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                )
        );
        borderIcon.setBorder(border);
        box.add(borderIcon);
        box.add(Box.createVerticalStrut(20));
        box.setOpaque(true);
        JTextField field = new JTextField(slogan);
        field.setFont(new Font("JetBrains Mono", Font.BOLD, 21));
        field.setHorizontalAlignment(SwingConstants.CENTER);
        field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        box.add(field);
        box.add(Box.createVerticalStrut(20));
        return box;
    }
}
