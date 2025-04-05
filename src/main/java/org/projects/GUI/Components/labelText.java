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
        this.textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        this.textField.setForeground(Color.BLACK);
        this.setSize(width,height);
        this.add(label);
        this.add(textField);
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public JLabel getLabel() {
        return label;
    }

    public JTextField getTextField() {
        return textField;
    }
}
