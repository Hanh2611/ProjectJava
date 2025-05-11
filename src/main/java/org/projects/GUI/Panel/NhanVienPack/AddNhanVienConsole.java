package org.projects.GUI.Panel.NhanVienPack;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.Action.NhanVienAction;
import org.projects.DAO.NhanVienDao;
import org.projects.GUI.utils.InputValid;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import static org.projects.GUI.Panel.NhanVienPack.ChiTietUserConsole.getRadioSex;

public class AddNhanVienConsole extends JPanel {
    static String changeImage;
    static JPanel parentImg;
    private static JPanel mainImg;
    private JButton reset, save, cancel;
    private NhanVienAction action;
    private boolean isResettingComboBox = false;
    private static String avatar;
    private ArrayList<JLabel> errorLabels;
    public JComboBox<String> comboBox;
    public JPanel genderPanel;
    public boolean isReseting = false;
    public ArrayList<JTextField> listAdd;
    GridBagConstraints c = new GridBagConstraints();
    GridBagConstraints f = new GridBagConstraints();
    private String ma;
    private String ten , email , sdt , chuc_vu;
    private int luong ;
    private boolean gioitinh;
    public ArrayList<String> default_list;
    NhanVienDao dao = new NhanVienDao();
    ChiTietUserConsole chiTietUserConsole = new ChiTietUserConsole();
    public AddNhanVienConsole() {
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
        String[] list = {"Nhập mã nhân viên","Nhập họ và tên", "Nhập Email" ,"Nhập số điện thoại" , "Nhập lương nhân viên"};
        String[] items = {"-- Chọn vai trò --", "Nhân viên bán hàng", "Nhân viên kho"};
        listAdd = new ArrayList<>();
        errorLabels = new ArrayList<>();
        default_list = new ArrayList<>();
        comboBox = new JComboBox<>(items);
        int newMaNhanVien = dao.getMaxMaNhanVien() + 1;
        JTextField maNhanVienField = new JTextField(String.valueOf(newMaNhanVien));
        maNhanVienField.setVisible(false);
        listAdd.add(maNhanVienField);
        JLabel maNhanVienError = new JLabel("");
        maNhanVienError.setVisible(false);
        errorLabels.add(maNhanVienError);
        default_list.add(String.valueOf(newMaNhanVien));
        for (int i = 1 ; i < list.length ; i++) {
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
            default_list.add(list[i]);
            JLabel errorLabel = new JLabel("");
            errorLabel.setForeground(Color.RED);
            errorLabel.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 12));
            errorLabels.add(errorLabel);
            errorLabel.setHorizontalAlignment(SwingConstants.LEFT);
            errorPanel.setBackground(new Color(240, 240, 240));
            final int index = i;
            jTextField.addFocusListener(new NhanVienAction(jTextField , index , listAdd , errorLabels));
            errorPanel.add(errorLabel);
            mainInfo.add(jTextField);
            mainInfo.add(errorPanel);
            mainInfo.add(Box.createVerticalStrut(5));
            listAdd.add(jTextField);
            if(index == 4) {
                jTextField.getDocument().addDocumentListener(new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        if (isReseting) return;
                        if (jTextField.getName().equals("Nhập lương nhân viên")) {
                            InputValid.validateLuongInput(jTextField, index, errorLabels, listAdd);
                        } else {
                            InputValid.clearError(index, errorLabels, listAdd, false);
                        }
                    }

                    public void removeUpdate(DocumentEvent e) {
                        if (isReseting) return;
                        if (jTextField.getName().equals("Nhập lương nhân viên")) {
                            InputValid.validateLuongInput(jTextField, index, errorLabels, listAdd);
                        } else {
                            InputValid.clearError(index, errorLabels, listAdd, false);
                        }
                    }

                    public void insertUpdate(DocumentEvent e) {
                        if (isReseting) return;
                        if (jTextField.getName().equals("Nhập lương nhân viên")) {
                            InputValid.validateLuongInput(jTextField, index, errorLabels, listAdd);
                        } else {
                            InputValid.clearError(index, errorLabels, listAdd, false);
                        }
                    }
                });
            }
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
        setEmail(listAdd.get(2).getText().trim());
        setSdt(listAdd.get(3).getText().trim());
        if(listAdd.get(4).getText().trim().equals("Nhập lương nhân viên")){
            setLuong(0);
        }else if(Long.parseLong(listAdd.get(4).getText().trim()) > Integer.MAX_VALUE) setLuong(Integer.MAX_VALUE);
        else setLuong(Integer.parseInt(listAdd.get(4).getText().trim()));
        setChuc_vu((String)comboBox.getSelectedItem());
        setGioitinh(chiTietUserConsole.isGioitinh());
        System.out.println(getAvatar());
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

    public static String uploadToCloudinary(File file) {
        try {
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "dmw5hl35v",
                    "api_key", "186529119916437",
                    "api_secret", "Tb2U6kR2RHvEaPmRqgRo1bYHfvQ"
            ));
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            return (String) uploadResult.get("secure_url");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        isReseting = true;
        for(JLabel label : errorLabels) {
            label.setText("");
        }
        int i = 0;
        for (JTextField textField : listAdd) {
            textField.setText(default_list.get(i));
            //System.out.println(default_list.get(i));
            i++;
            InputValid.resetBorder(textField, false);
            textField.setForeground(new Color(192, 192, 192));
        }
        isResettingComboBox = true;
        comboBox.setSelectedIndex(0);
        genderPanel = getRadioSex(true, true);
        genderPanel.repaint();
        genderPanel.revalidate();
        changeImage = Objects.requireNonNull(getClass().getResource("/Img/upload.png")).getPath();
        isResettingComboBox = false;
        JPanel newParentImg = getJPanel(changeImage, 220, 150);
        mainImg.remove(parentImg);
        mainImg.add(newParentImg, BorderLayout.CENTER);
        parentImg = newParentImg;
        mainImg.revalidate();
        mainImg.repaint();
        isReseting = false;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getChuc_vu() {
        return chuc_vu;
    }

    public void setChuc_vu(String chuc_vu) {
        this.chuc_vu = chuc_vu;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public boolean getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getAvatar() {
        return avatar;
    }

    public  void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean checkVaildALL() {
        boolean ok = true;
        if(listAdd == null) {
            //System.out.println("listAdd is null");
            return false;
        }
        //System.out.println(listAdd.size());
        for (int i = 0; i < listAdd.size(); i++) {
            JTextField tf = listAdd.get(i);
            //System.out.println(tf.getText());
            switch (i) {
                case 1:
                    if (InputValid.checkRong_addPlace("Nhập họ và tên", tf.getText())) {
                        InputValid.showError(i, "Vui lòng nhập tên nhân viên" , errorLabels , listAdd , false);
                        ok = false;
                    }
//                    System.out.println("lỗi");
                    break;
                case 2:
                    if (InputValid.checkRong_addPlace("Nhập Email", tf.getText())) {
                        InputValid.showError(i, "Vui lòng nhập email" , errorLabels , listAdd , false);
                        ok = false;
                    } else if (!InputValid.checkEmail(tf.getText())) {
                        InputValid.showError(i, "Email không đúng định dạng" , errorLabels , listAdd , false);
                        ok = false;
                    }
//                    System.out.println("lỗi");
                    break;
                case 3:
                    if (InputValid.checkRong_addPlace("Nhập số điện thoại", tf.getText())) {
                        InputValid.showError(i, "Vui lòng nhập số điện thoại" , errorLabels , listAdd , false);
                        ok = false;
                    } else if (!InputValid.checkSoDienThoai(tf.getText())) {
                        InputValid.showError(i, "Số điện thoại không hợp lệ", errorLabels , listAdd , false);
                        ok = false;
                    }
                    break;
                case 4:
                    String luongText = tf.getText().trim();
                    if (InputValid.checkRong_addPlace("Nhập lương nhân viên", luongText)) {
                        InputValid.showError(i, "Vui lòng nhập lương nhân viên" , errorLabels , listAdd , false);
                        ok = false;
                    } else {
                        try {
                            long luong = Long.parseLong(luongText);
                            if (luong < 1000) {
                                InputValid.showError(i, "Lương phải lớn hơn hoặc bằng 1,000" , errorLabels , listAdd , false);
                                ok = false;
                            } else if (luong > Integer.MAX_VALUE) {
                                InputValid.showError(i, "Lương vượt quá giới hạn cho phép" , errorLabels , listAdd , false);
                                ok = false;
                            }
                        } catch (NumberFormatException e) {
                            InputValid.showError(i, "Lương phải là số nguyên" , errorLabels , listAdd , false);
                            ok = false;
                        }
                    }
                    break;
            }
        }
        return ok;
    }
}
