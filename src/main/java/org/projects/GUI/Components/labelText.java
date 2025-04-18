package org.projects.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class labelText extends JPanel {
    private JLabel label;
    private JTextField textField;
    public  labelText(String text,int width,int height)  {
        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(240, 240, 240));

        label = new JLabel(text);
        label.setFont(new Font("Jetbrains Mono", Font.PLAIN, 14));
        label.setForeground(new Color(50, 50, 50));

        textField = new JTextField(width);
        textField.setFont(new Font("Jetbrains Mono", Font.PLAIN, 14));
        textField.setForeground(Color.BLACK);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        add(label, BorderLayout.NORTH);
        add(textField, BorderLayout.CENTER);
        setMaximumSize(new Dimension(500, 60));
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
