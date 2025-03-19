package org.projects.GUI.Components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class handleComponents {
    public static final Font DEFAULT_FONT = new Font("JetBrains Mono", Font.PLAIN, 18);
    public static final Font PLACEHOLDER_FONT = new Font("JetBrains Mono", Font.PLAIN, 13);
    public static final Font TITLE_FONT = new Font("JetBrains Mono", Font.PLAIN, 20);
    public static final Font HEADER_FONT = new Font("JetBrains Mono", Font.PLAIN, 30);

    //XU LI BUTTON ICON
    public static JButton createButtonIcon(String iconPath, int width, int height) {
        FlatSVGIcon iconButton = new FlatSVGIcon(iconPath, width, height);
        JButton button = new JButton(iconButton);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBackground(null);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }


    //XU LY LABEL
    public static JLabel createLabel(String label,int x,int y,int width,int height) {
        JLabel lb = new JLabel(label);
        lb.setFont(TITLE_FONT);
        lb.setForeground(Color.BLACK);
        lb.setBounds(x, y, width, height);
        return lb;
    }

    //XU LY JTEXTFIELD
     public static JTextField createTextField(String placeholder, int x, int y, int width, int height) {
        JTextField tf = new JTextField(placeholder);
        tf.setFont(PLACEHOLDER_FONT);
        tf.setForeground(Color.GRAY);
        tf.setBounds(x, y, width, height);
        return tf;
    }

    //XU LY JPASSWORDFIELD
    public static JPasswordField createPasswordField(String placeholder, int x, int y, int width, int height) {
        JPasswordField pf = new JPasswordField(placeholder);
        pf.setFont(PLACEHOLDER_FONT);
        pf.setEchoChar((char) 0);
        pf.setForeground(Color.GRAY);
        pf.setBounds(x, y, width, height);
        return pf;
    }
}

