package org.projects.GUI.Panel.NhanVienPack;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.Action.NhanVienAction;
import org.projects.GUI.utils.InputValid;
import org.projects.entity.NhanVienEntity;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static org.projects.GUI.Panel.NhanVienPack.AddNhanVienConsole.uploadToCloudinary;
import static org.projects.GUI.Panel.NhanVienPack.ChiTietUserConsole.getRadioSex;

public class FixNhanVienConsole extends JPanel {
    static String changeImage;
    static JPanel parentImg;
    private static JPanel mainImg;
    private JButton update, cancel;
    private NhanVienAction action;
    private boolean isResettingComboBox = false;
    public JComboBox<String> comboBox;
    public JPanel genderPanel;
    ChiTietUserConsole chiTietUserConsole = new ChiTietUserConsole();
    public ArrayList<JTextField> listAdd;
    GridBagConstraints c = new GridBagConstraints();
    GridBagConstraints f = new GridBagConstraints();
    public ArrayList<JLabel> errorLabels;
    private String ma , ten , email , std , chucvu;
    private int luong ;
    private boolean gioitinh;
    private String avatar;
    public FixNhanVienConsole() {}
    public void insertData(){
        setMa(listAdd.get(0).getText().trim());
        setTen(listAdd.get(1).getText().trim());
        setEmail(listAdd.get(2).getText().trim());
        setStd(listAdd.get(3).getText().trim());
        if(listAdd.get(4).getText().trim().equals("")){
            setLuong(0);
        }else if(Long.parseLong(listAdd.get(4).getText().trim()) > Integer.MAX_VALUE) setLuong(Integer.MAX_VALUE);
        else setLuong(Integer.parseInt(listAdd.get(4).getText().trim()));
        setChucvu((String)comboBox.getSelectedItem());
        setGioitinh(chiTietUserConsole.isGioitinh());
        System.out.println(getAvatar());
        setAvatar(getAvatar());
    }
    public void setInfo(NhanVienEntity info) {
        setMa(Integer.toString(info.getMaNhanVien()));
        setTen(info.getTenNhanVien());
        setEmail(info.getEmailNhanVien());
        setStd(info.getSdtNhanVien());
        setChucvu(info.getChucvu());
        setLuong(info.getLuong());
        setGioitinh(info.getGioitinh());
        setAvatar(info.getAvatar());
    }
    public JPanel initComponents() {
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
        return this;
    }

    public JPanel mainINFO() {
        JPanel mainInfo = new JPanel();
        mainInfo.setLayout(new BoxLayout(mainInfo, BoxLayout.Y_AXIS));
        mainInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainInfo.setBackground(new Color(240, 240, 240));
        mainInfo.setOpaque(true);
        String[] list = {getMa() , getTen() , getEmail() , getStd() , Integer.toString(getLuong())};
        String[] items = {"-- Chọn vai trò --", "Nhân viên bán hàng", "Nhân viên kho",};
        String [] str = {"Mã NV: ", "Tên: ", "Email: ", "Sdt: ", "Lương: "};
        listAdd = new ArrayList<>();
        errorLabels = new ArrayList<>();
        comboBox = new JComboBox<>(items);
        int index = 0;
        for (int i = 0 ; i < list.length ; i++) {
            JPanel p = new JPanel();
            p.setLayout(new FlowLayout(FlowLayout.LEFT , 5 , 5));
            p.setBackground(new Color(240, 240, 240));
            JTextField jTextField2 = new JTextField(str[index]);
            jTextField2.setEditable(false);
            JTextField jTextField = new JTextField(list[i]);
            jTextField.setName(list[i]);
            if(index == 0){
                jTextField.setEnabled(false);
            }
            jTextField.setPreferredSize(new Dimension(220, 40));
            jTextField2.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
            jTextField.setBackground(new Color(240, 240, 240));
            jTextField2.setBackground(new Color(240, 240, 240));
            jTextField.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
            jTextField2.setPreferredSize(new Dimension(100, 40));
            jTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
            jTextField2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
            JPanel errorPanel = new JPanel();
            errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

            JLabel errorLabel = new JLabel("");
            errorLabel.setForeground(Color.RED);
            errorLabel.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 12));
            errorLabels.add(errorLabel);
            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            errorPanel.setBackground(new Color(240, 240, 240));
            final int value = i;
            jTextField.addFocusListener(new NhanVienAction(jTextField , value , listAdd , errorLabels));
            p.add(jTextField2);
            p.add(jTextField);
            errorPanel.add(errorLabel);
            mainInfo.add(p);
            mainInfo.add(errorPanel);
            mainInfo.add(Box.createVerticalStrut(5));
            listAdd.add(jTextField);
            index++;
            if(i == 4) {
                jTextField.getDocument().addDocumentListener(new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        validateField(jTextField, value);
                        InputValid.resetBorder(jTextField, false);
                    }

                    public void removeUpdate(DocumentEvent e) {
                        validateField(jTextField, value);
                        InputValid.resetBorder(jTextField, false);
                    }

                    public void insertUpdate(DocumentEvent e) {
                        validateField(jTextField, value);
                        InputValid.resetBorder(jTextField, false);
                    }

                    private void validateField(JTextField textField, int index) {
                        if (index == 4) {
                            InputValid.validateLuongInput(textField, index, errorLabels, listAdd);
                        } else {
                            InputValid.clearError(index, errorLabels, listAdd, false);
                        }
                    }
                });
            }
        }
        comboBox.setSelectedItem(getChucvu());
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
        genderPanel = getRadioSex(true, getGioitinh());
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
        mainInfo.add(combobox_sex);
        mainInfo.add(Box.createVerticalStrut(30));
        update = new JButton("Cập nhật");
        update.setBackground(new Color(0, 191, 255));
        cancel = new JButton("Hủy");
        cancel.setBackground(new Color(250, 128, 114));
        // action thêm
        update.addActionListener(action);
        cancel.addActionListener(action);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 50, 20));
        ArrayList<JButton> buttons = new ArrayList<>();
        buttonPanel.setMaximumSize(new Dimension(500, 40));
        buttonPanel.setPreferredSize(new Dimension(500, 40));
        buttons.add(update);
        buttons.add(cancel);
        for (JButton b : buttons) {
            b.setPreferredSize(new Dimension(100, 40));
            b.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
            buttonPanel.add(b);
        }
        mainInfo.add(buttonPanel);
        return mainInfo;
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

    public JPanel mainIMG() {
        mainImg = new JPanel();
        mainImg.setOpaque(true);
        mainImg.setPreferredSize(new Dimension(500, 200));
        mainImg.setMinimumSize(new Dimension(500, 200));
        mainImg.setMaximumSize(new Dimension(500, 200));
        JButton button_add_image = getJButton();
        SwingUtilities.invokeLater(button_add_image::requestFocusInWindow);
        mainImg.setLayout(new BorderLayout(5, 5));
        if(getAvatar() == null) {
            changeImage = Objects.requireNonNull(getClass().getResource("/Img/upload.png")).getPath();
        }else changeImage = getAvatar();
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

    public JButton getCancelButton() {
        return cancel;
    }

    public JButton getUpdateButton() {
        return update;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public boolean getGioitinh() {
        return gioitinh;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getLuong() {
        return luong;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean checkVaildALL() {
        boolean ok = true;
        if(listAdd == null) {
            System.out.println("listAdd is null");
            return false;
        }
        System.out.println(listAdd.size());
        for (int i = 0; i < listAdd.size(); i++) {
            JTextField tf = listAdd.get(i);
            System.out.println(tf.getText());
            switch (i) {
                case 0:
                    if (InputValid.checkRong_addPlace("Nhập mã nhân viên", tf.getText())) {
                        InputValid.showError(i, "Vui lòng nhập mã nhân viên", errorLabels, listAdd, false);
                        ok = false;
                    } else if (!InputValid.checkMa(tf.getText())) {
                        InputValid.showError(i, "Mã nhân viên chỉ nhận giá trị số nguyên", errorLabels, listAdd, false);
                        ok = false;
                    }
                    break;
                case 1:
                    if (InputValid.checkRong_addPlace("Nhập họ và tên", tf.getText())) {
                        InputValid.showError(i, "Vui lòng nhập tên nhân viên", errorLabels, listAdd, false);
                        ok = false;
                    }
                    break;
                case 2:
                    if (InputValid.checkRong_addPlace("Nhập Email", tf.getText())) {
                        InputValid.showError(i, "Vui lòng nhập email", errorLabels, listAdd, false);
                        ok = false;
                    } else if (!InputValid.checkEmail(tf.getText())) {
                        InputValid.showError(i, "Email không đúng định dạng", errorLabels, listAdd, false);
                        ok = false;
                    }
                    break;
                case 3:
                    if (InputValid.checkRong_addPlace("Nhập số điện thoại", tf.getText())) {
                        InputValid.showError(i, "Vui lòng nhập số điện thoại", errorLabels, listAdd, false);
                        ok = false;
                    } else if (!InputValid.checkSoDienThoai(tf.getText())) {
                        InputValid.showError(i, "Số điện thoại không hợp lệ", errorLabels, listAdd, false);
                        ok = false;
                    }
                    break;
                case 4:
                    String luongText = tf.getText().trim();
                    if (InputValid.checkRong_addPlace("Nhập lương nhân viên", luongText)) {
                        InputValid.showError(i, "Vui lòng nhập lương nhân viên", errorLabels, listAdd, false);
                        ok = false;
                    } else {
                        try {
                            long luong = Long.parseLong(luongText);
                            if (luong < 1000) {
                                InputValid.showError(i, "Lương phải lớn hơn hoặc bằng 1,000", errorLabels, listAdd, false);
                                ok = false;
                            } else if (luong > Integer.MAX_VALUE) {
                                InputValid.showError(i, "Lương vượt quá giới hạn cho phép", errorLabels, listAdd, false);
                                ok = false;
                            }
                        } catch (NumberFormatException e) {
                            InputValid.showError(i, "Lương phải là số nguyên", errorLabels, listAdd, false);
                            ok = false;
                        }
                    }
                    break;
            }
        }
        return ok;
    }
}
