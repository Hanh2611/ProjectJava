package org.projects.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class labelText extends JPanel {
    private JLabel label;
    private JTextField textField;
    public  labelText(String text,int width,int height)  {
        this.setLayout(new GridLayout(2,1));
        this.label = new JLabel(text);
        this.textField = new JTextField(width);
        this.setSize(width,height);
        this.add(label);
        this.add(textField);
    }
}
