package org.projects.GUI.Panel.NhanVienPack;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.projects.GUI.Panel.NhanVienPack.NhanVien.getRadioSex;
import static org.projects.GUI.Panel.NhanVienPack.NhanVien.main;

public class AddNhanVienConsole extends JPanel {
    JRadioButton radioButton;
    public AddNhanVienConsole() {
        initComponents();
    }
    public void initComponents() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(500,700));
        this.setMaximumSize(new Dimension(500,700));
        this.setMinimumSize(new Dimension(500,700));
        this.setBackground(new Color(240 , 240 , 240));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.4;
        JPanel img = mainIMG();
        JPanel info = mainINFO();
        this.add(img, c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.6;
        this.add(info, c);
    }
    public JPanel mainINFO(){
        JPanel mainInfo = new JPanel();
        mainInfo.setLayout(new BoxLayout(mainInfo , BoxLayout.Y_AXIS));
        mainInfo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainInfo.setBackground(new Color(240,240,240));
        mainInfo.setOpaque(true);
        String[] list = {"Nhập mã số nhân viên" , "Nhập họ và tên" , "Nhập số điện thoại" , "Nhập địa chỉ" , "Nhập Email" , "Lương (đ/h)" , "Buổi làm (từ .. đến ..)"};
        String[] items = {"-- Chọn vai trò --" , "Tiếp thị" , "Quản lí" , "Thu ngân" , "Giao dịch" , "Nhân viên quán"};

        JComboBox<String> comboBox = new JComboBox<>(items);
        for(String s : list){
            JTextField jTextField = new JTextField(s);
            addPlaceholderStyle(jTextField , s);
            jTextField.setBackground(new Color(240,240,240));
            jTextField.setForeground(new Color(192,192,192));
            jTextField.setFont(new Font("JETBRAINS MONO", Font.ITALIC, 14));
            jTextField.setMaximumSize(new Dimension(500 , 40));
            jTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220,220,220)));
            mainInfo.add(jTextField);
            mainInfo.add(Box.createVerticalStrut(5));
        }
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (index == 0) {
                    c.setEnabled(false);
                    c.setForeground(Color.GRAY);
                }
                return c;
            }
        });
        comboBox.addActionListener(e -> {
            if (comboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this , "Bạn không thể chọn mục này, vui lòng chọn lại");
                comboBox.setSelectedIndex(0);
            }
        });
        comboBox.setMaximumSize(new Dimension(500 , 40));
        comboBox.setBackground(new Color(240,240,240));
        comboBox.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
        mainInfo.add(comboBox);
        mainInfo.add(Box.createVerticalStrut(5));
        JPanel genderPanel = getRadioSex(true , true);
        genderPanel.setMaximumSize(new Dimension(500 , 40));
        mainInfo.add(genderPanel);
        mainInfo.add(Box.createVerticalStrut(5));
        JPanel combobox_sex = new JPanel();
        combobox_sex.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.8;
        c.weighty = 1;
        combobox_sex.add(comboBox, c);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.2;
        c.weighty = 1;
        combobox_sex.add(genderPanel, c);
        combobox_sex.setMaximumSize(new Dimension(500 , 40));
        mainInfo.add(combobox_sex);
        mainInfo.add(Box.createVerticalStrut(30));
        JButton reset = new JButton("Làm mới");
        reset.setBackground(new Color(0,191,255));
        JButton save = new JButton("Lưu");
        save.setBackground(new Color(50,205,50));
        JButton cancel = new JButton("Hủy");
        cancel.setBackground(new Color(250,128,114));
        JPanel buttonPanel = new JPanel(new GridLayout(1 , 3 , 50 , 20));
        ArrayList<JButton> buttons = new ArrayList<>();
        buttonPanel.setMaximumSize(new Dimension(500 , 40));
        buttonPanel.setPreferredSize(new Dimension(500 , 40));
        buttons.add(reset);
        buttons.add(cancel);
        buttons.add(save);
        for(JButton b : buttons){
            b.setPreferredSize(new Dimension(100 , 40));
            b.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
            buttonPanel.add(b);
        }
        mainInfo.add(buttonPanel);
        return mainInfo;
    }
    public JPanel mainIMG(){
        JPanel mainImg = new JPanel();
        mainImg.setOpaque(true);
        mainImg.setPreferredSize(new Dimension(500,200));
        mainImg.setMinimumSize(new Dimension(500,200));
        mainImg.setMaximumSize(new Dimension(500,200));
        JButton ADD = new JButton("ADD IMAGE");
        ADD.setBackground(new Color(135,206,250));
        SwingUtilities.invokeLater(ADD::requestFocusInWindow);
        mainImg.add(ADD);
        return mainImg;
    }
    private void addPlaceholderStyle(JTextComponent textComp, String placeholder) {
        textComp.setText(placeholder);
        textComp.setForeground(Color.BLACK);

        textComp.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textComp.getText().equals(placeholder)) {
                    textComp.setText("");
                    textComp.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textComp.getText().isEmpty()) {
                    textComp.setText(placeholder);
                    textComp.setForeground(new Color(192,192,192));
                }
            }
        });
    }
}
