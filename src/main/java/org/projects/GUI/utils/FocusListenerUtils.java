package org.projects.GUI.utils;

import java.awt.event.FocusListener;
import java.awt.Color;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class FocusListenerUtils {

    // Dành cho JTextField thông thường
    public static FocusListener createPlaceholderTextField(String placeholder, JTextField textField) {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                handleFocusGained(textField, placeholder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                handleFocusLost(textField, placeholder);
            }
        };
    }

    // Dành riêng cho JPasswordField
    public static FocusListener createPlaceholderPasswordField(String placeholder, JPasswordField passwordField) {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                handleFocusGained(passwordField, placeholder);
                passwordField.setEchoChar('*');
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(placeholder);
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setEchoChar((char) 0);
                }
            }
        };
    }

    
    private static void handleFocusGained(JTextField textField, String placeholder) {
        if (textField.getText().equals(placeholder)) {
            textField.setText("");
            textField.setForeground(Color.BLACK);
        }
    }

    
    private static void handleFocusLost(JTextField textField, String placeholder) {
        if (textField.getText().isEmpty()) {
            textField.setText(placeholder);
            textField.setForeground(Color.GRAY);
        }
    }
}