package org.projects.GUI.Components;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class labelText extends JPanel {
    private JLabel label;
    private JTextField textField;
    private JLabel errorLabel;
    private JComboBox<String> cbx;
    public  labelText(String text,int width,int columns)  {
        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(240, 240, 240));

        label = new JLabel(text);
        label.setFont(new Font("Jetbrains Mono", Font.PLAIN, 14));
        label.setForeground(new Color(50, 50, 50));

        textField = new JTextField(width);
        textField.setFont(new Font("Jetbrains Mono", Font.PLAIN, 14));
        textField.setForeground(Color.BLACK);
//        textField.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(new Color(200, 200, 200)),
//                BorderFactory.createEmptyBorder(5, 5, 5, 5)
//        ));
        textField.setPreferredSize(new Dimension(400, 30));

        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Jetbrains Mono", Font.ITALIC, 10));

        add(label, BorderLayout.NORTH);
        add(textField, BorderLayout.CENTER);
        add(errorLabel, BorderLayout.SOUTH);
    }

    public  labelText(String text,ArrayList<String> lst)  {
        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(240, 240, 240));

        label = new JLabel(text);
        label.setFont(new Font("Jetbrains Mono", Font.PLAIN, 14));
        label.setForeground(new Color(50, 50, 50));

        cbx = new JComboBox<>();
        for(String s : lst) cbx.addItem(s);
        cbx.setFont(new Font("Jetbrains Mono", Font.PLAIN, 14));
        cbx.setForeground(Color.BLACK);
        cbx.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        add(label, BorderLayout.NORTH);
        add(cbx, BorderLayout.CENTER);
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

    public JComboBox<String> getCbx() {
        return cbx;
    }

    public void setCbx(JComboBox<String> cbx) {
        this.cbx = cbx;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(JLabel errorLabel) {
        this.errorLabel = errorLabel;
    }
}
