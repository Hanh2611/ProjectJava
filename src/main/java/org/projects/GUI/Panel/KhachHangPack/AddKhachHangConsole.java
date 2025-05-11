package org.projects.GUI.Panel.KhachHangPack;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.Action.KhachHangAction;
import org.projects.Action.NhanVienAction;
import org.projects.DAO.KhachHangDAO;
import org.projects.GUI.utils.InputValid;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Objects;

import static org.projects.GUI.Panel.NhanVienPack.AddNhanVienConsole.uploadToCloudinary;
import static org.projects.GUI.Panel.NhanVienPack.ChiTietUserConsole.getRadioSex;

public class AddKhachHangConsole extends JPanel {
    static String changeImage;
    static JPanel parentImg;
    private static JPanel mainImg;
    private JButton reset, save, cancel;
    private KhachHangAction action;
    private boolean isResettingComboBox = false;
    public JComboBox<String> comboBox;
    public JPanel genderPanel;
    public ArrayList<JTextField> listAdd;
    public ArrayList<JLabel> errorLabels;
    public String avatar;
    GridBagConstraints c = new GridBagConstraints();
    GridBagConstraints f = new GridBagConstraints();
    private String ma ,ten , sdt , diachi;
    KhachHangDAO dao = new KhachHangDAO();
    public AddKhachHangConsole() {
        initComponents();
    }

    public void initComponents() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(500, 700));
        this.setMaximumSize(new Dimension(500, 700));
        this.setMinimumSize(new Dimension(500, 700));
        this.setBackground(new Color(240, 240, 240));
        f.fill = GridBagConstraints.BOTH;
        f.insets = new Insets(5, 5, 5, 5);
        f.gridx = 0;
        f.gridy = 0;
        f.weightx = 1;
        f.weighty = 0.4;
        JPanel img = mainIMG();
        JPanel info = mainINFO();
        this.add(img, f);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.6;
        this.add(info, c);
    }

    public JPanel mainINFO() {
        JPanel mainInfo = new JPanel();
        mainInfo.setLayout(new BoxLayout(mainInfo, BoxLayout.Y_AXIS));
        mainInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainInfo.setBackground(new Color(240, 240, 240));
        mainInfo.setOpaque(true);
        String[] list = {"Nhập họ và tên" ,"Nhập số điện thoại", "Nhập địa chỉ"};
        String[] items = {"-- Chọn vai trò --", "Nhân viên bán hàng", "Kế toán", "Nhân viên kho", "Quản lí sản phẩm", "Nhân viên kĩ thuật", "Giám đốc"};
        listAdd = new ArrayList<>();
        errorLabels = new ArrayList<>();
        comboBox = new JComboBox<>(items);
        int newMaNhanVien = dao.getMaxKhachHang() + 1;
        JTextField maNhanVienField = new JTextField(String.valueOf(newMaNhanVien));
        maNhanVienField.setVisible(false);
        listAdd.add(maNhanVienField);
        JLabel maNhanVienError = new JLabel("");
        maNhanVienError.setVisible(false);
        errorLabels.add(maNhanVienError);
        for (int i = 0 ; i < list.length ; i++) {
            JTextField jTextField = new JTextField(list[i]);
            addPlaceholderStyle(jTextField, list[i]);
            jTextField.setName(list[i]);
            jTextField.setBackground(new Color(240, 240, 240));
            jTextField.setForeground(new Color(192, 192, 192));
            jTextField.setFont(new Font("JETBRAINS MONO", Font.ITALIC, 14));
            jTextField.setMaximumSize(new Dimension(500, 40));
            jTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
            JPanel errorPanel = new JPanel();
            errorPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            JLabel errorLabel = new JLabel("");
            errorLabel.setForeground(Color.RED);
            errorLabel.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 12));
            errorLabels.add(errorLabel);
            errorLabel.setHorizontalAlignment(SwingConstants.LEFT);
            errorPanel.setBackground(new Color(240, 240, 240));
            final int index = i;
            jTextField.addFocusListener(new KhachHangAction(jTextField , index , listAdd , errorLabels));
            errorPanel.add(errorLabel);
            mainInfo.add(jTextField);
            mainInfo.add(errorPanel);
            mainInfo.add(Box.createVerticalStrut(5));
            listAdd.add(jTextField);
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
            if (isResettingComboBox) {
                return;
            }
            if (comboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Bạn không thể chọn mục này, vui lòng chọn lại");
                comboBox.setSelectedIndex(0);
            }
        });
        comboBox.setMaximumSize(new Dimension(500, 40));
        comboBox.setBackground(new Color(240, 240, 240));
        comboBox.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
        mainInfo.add(comboBox);
        mainInfo.add(Box.createVerticalStrut(5));
        genderPanel = getRadioSex(true, true);
        genderPanel.setMaximumSize(new Dimension(500, 40));
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
        combobox_sex.setMaximumSize(new Dimension(500, 40));
        //mainInfo.add(combobox_sex);
        mainInfo.add(Box.createVerticalStrut(30));
        reset = new JButton("Làm mới");
        reset.setBackground(new Color(0, 191, 255));
        save = new JButton("Lưu");
        save.setBackground(new Color(50, 205, 50));
        cancel = new JButton("Hủy");
        cancel.setBackground(new Color(250, 128, 114));
        // action thêm
        reset.addActionListener(action);
        save.addActionListener(action);
        cancel.addActionListener(action);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 50, 20));
        ArrayList<JButton> buttons = new ArrayList<>();
        buttonPanel.setMaximumSize(new Dimension(500, 40));
        buttonPanel.setPreferredSize(new Dimension(500, 40));
        buttons.add(reset);
        buttons.add(cancel);
        buttons.add(save);
        for (JButton b : buttons) {
            b.setPreferredSize(new Dimension(100, 40));
            b.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
            buttonPanel.add(b);
        }
        mainInfo.add(buttonPanel);
        return mainInfo;
    }

    public void insertData(){
        setMa(listAdd.get(0).getText().trim());
        setTen(listAdd.get(1).getText().trim());
        setSdt(listAdd.get(2).getText().trim());
        setDiachi(listAdd.get(3).getText().trim());
        setAvatar(getAvatar());
    }

    public JPanel mainIMG() {
        mainImg = new JPanel();
        mainImg.setOpaque(true);
        mainImg.setPreferredSize(new Dimension(500, 200));
        mainImg.setMinimumSize(new Dimension(500, 200));
        mainImg.setMaximumSize(new Dimension(500, 200));
        JButton button_add_image = getJButton();
        SwingUtilities.invokeLater(button_add_image::requestFocusInWindow);
        mainImg.setLayout(new BorderLayout(5, 5));
        changeImage = Objects.requireNonNull(getClass().getResource("/Img/upload.png")).getPath();
        parentImg = new JPanel();
        parentImg = getJPanel(changeImage , 220 , 150);
        FlatSVGIcon addIcon = new FlatSVGIcon("icon/add-folder.svg", 20, 20);
        JLabel label = new JLabel(addIcon);
        button_add_image.add(label);
        mainImg.add(button_add_image, BorderLayout.NORTH);
        mainImg.add(parentImg, BorderLayout.CENTER);
        mainImg.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        return mainImg;
    }

    public JButton getJButton() {
        JButton button_add_image = new JButton("ADD IMAGE");
        button_add_image.addActionListener(e -> {
            if ("ADD IMAGE".equals(e.getActionCommand())) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Ảnh (JPG, PNG, GIF)", "jpg", "png", "gif"));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile != null && selectedFile.exists()) {
                        String imageUrl = uploadToCloudinary(selectedFile);
                        if (imageUrl != null) {
                            //System.out.println("Link ảnh cloud: " + imageUrl);
                            setAvatar(imageUrl);
                            changeImage = imageUrl;
                            JPanel newParentImg = getJPanel(changeImage , 220 , 150);
                            mainImg.remove(parentImg);
                            mainImg.add(newParentImg, BorderLayout.CENTER);
                            parentImg = newParentImg;
                            mainImg.revalidate();
                            mainImg.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Upload ảnh thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "File không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        button_add_image.setBackground(new Color(135, 206, 250));
        button_add_image.setPreferredSize(new Dimension(10, 30));
        button_add_image.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
        return button_add_image;
    }

    public static JPanel getJPanel(String path , int height , int vertical) {
        JPanel parentImg = new JPanel();
        parentImg.setOpaque(true);
        parentImg.setPreferredSize(new Dimension(500, vertical));
        parentImg.setMinimumSize(new Dimension(500, vertical));
        parentImg.setMaximumSize(new Dimension(500, vertical));
        JLabel img = null;
        try {
            ImageIcon addIcon_user;
            if (path != null && (path.startsWith("http://") || path.startsWith("https://"))) {
                addIcon_user = new ImageIcon(new java.net.URL(path));
            } else {
                addIcon_user = new ImageIcon(path);
            }
            Image scale = addIcon_user.getImage().getScaledInstance(220, height, Image.SCALE_SMOOTH);
            img = new JLabel(new ImageIcon(scale));
        } catch (Exception e) {
            img = new JLabel("Không thể load ảnh");
        }
        img.setHorizontalAlignment(SwingConstants.CENTER);
        img.setOpaque(true);
        parentImg.add(img);
        return parentImg;
    }

    public static void addPlaceholderStyle(JTextComponent textComp, String placeholder) {
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
                    textComp.setForeground(new Color(192, 192, 192));
                }
            }
        });
    }

    public JButton getSaveButton() {
        return save;
    }

    public JButton getCancelButton() {
        return cancel;
    }

    public JButton getResetButton() {
        return reset;
    }

    public void resetForm() {
        for(JLabel label : errorLabels) {
            label.setText("");
        }
        for (JTextField textField : listAdd) {
            textField.setText(textField.getName());
            textField.setForeground(new Color(192, 192, 192));
            InputValid.resetBorder(textField , false);
        }
        isResettingComboBox = true;
        comboBox.setSelectedIndex(0);
        genderPanel = getRadioSex(true, true);
        genderPanel.repaint();
        genderPanel.revalidate();
        changeImage = Objects.requireNonNull(getClass().getResource("/Img/upload.png")).getPath();
        isResettingComboBox = false;
        JPanel newParentImg = getJPanel(changeImage , 220 , 150);
        mainImg.remove(parentImg);
        mainImg.add(newParentImg, BorderLayout.CENTER);
        parentImg = newParentImg;
        mainImg.revalidate();
        mainImg.repaint();
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public boolean checkValid() {
        boolean ok = true;
        if(listAdd == null) {
            System.out.println("listAdd is null");
            return false;
        }
        //System.out.println(listAdd.size());
        for (int i = 0; i < listAdd.size(); i++) {
            JTextField tf = listAdd.get(i);
            switch (i) {
                case 1:
                    if (InputValid.checkRong_addPlace("Nhập họ và tên", tf.getText())) {
                        InputValid.showError(i, "Vui lòng nhập tên khách hàng", errorLabels, listAdd, false);
                        ok = false;
                    }
                    break;
                case 2:
                    if (InputValid.checkRong_addPlace("Nhập số điện thoại", tf.getText())) {
                        InputValid.showError(i, "Vui lòng nhập số điện thoại", errorLabels, listAdd, false);
                        ok = false;
                    } else if (!InputValid.checkSoDienThoai(tf.getText())) {
                        InputValid.showError(i, "Số điện thoại không hợp lệ", errorLabels, listAdd, false);
                        ok = false;
                    }
                    break;
                case 3:
                    if (InputValid.checkRong_addPlace("Nhập địa chỉ", tf.getText())) {
                        InputValid.showError(i, "Vui lòng nhập địa chỉ", errorLabels, listAdd, false);
                        ok = false;
                    }
                    break;
            }
        }
        return ok;
    }
}
