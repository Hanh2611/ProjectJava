package org.projects.GUI.Panel.KhachHangPack;

import com.formdev.flatlaf.extras.FlatSVGIcon;
//import org.projects.BUS.MainBUS;
import org.projects.Action.KhachHangAction;
import org.projects.Action.NhanVienAction;
import org.projects.BUS.NhanVienBus;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.utils.InputValid;
import org.projects.entity.KhachHangEntity;
import org.projects.entity.NhanVienEntity;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static org.projects.GUI.Panel.NhanVienPack.AddNhanVienConsole.addPlaceholderStyle;
import static org.projects.GUI.Panel.NhanVienPack.AddNhanVienConsole.uploadToCloudinary;
import static org.projects.GUI.Panel.NhanVienPack.ChiTietUserConsole.getRadioSex;

public class FixKhachHang extends JPanel {
    static String changeImage;
    static JPanel parentImg;
    private static JPanel mainImg;
    private JButton update, cancel;
    private KhachHangAction action;
    public JComboBox<String> comboBox;
    public JPanel genderPanel;
    public ArrayList<JTextField> listAdd;
    public String avatar;
    GridBagConstraints c = new GridBagConstraints();
    GridBagConstraints f = new GridBagConstraints();
    private String ma , ten , diachi , std;
    public ArrayList<JLabel> errorLabels;
    public FixKhachHang() {
//        initComponents();
    }
    public void insertData(){
        setMa(listAdd.get(0).getText().trim());
        setTen(listAdd.get(1).getText().trim());
        setStd(listAdd.get(2).getText().trim());
        setDiachi(listAdd.get(3).getText().trim());
        setAvatar(getAvatar());
    }
    public void setInfo(KhachHangEntity info) {
        setMa(Integer.toString(info.getMa()));
        setTen(info.getTen());
        setStd(info.getSdt());
        setDiachi(info.getDiaChi());
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
        c.fill = GridBagConstraints.BOTH;
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
        String[] list = {getMa() , getTen() , getStd() , getDiachi()};
//        String[] items = {"-- Chọn vai trò --", "Nhân viên bán hàng", "Kế toán", "Nhân viên kho", "Quản lí sản phẩm", "Nhân viên kĩ thuật", "Giám đốc"};
        String [] str = {"Mã KH: ", "Tên: ", "Sdt: ", "Địa chỉ: "};
        listAdd = new ArrayList<>();
        errorLabels = new ArrayList<>();

//        comboBox = new JComboBox<>(items);
        for (int index = 0; index < list.length; index++) {
            String s = list[index];
            JPanel p = new JPanel(new BorderLayout(5, 5));
            p.setBackground(new Color(240, 240, 240));
//            p.setPreferredSize(new Dimension(400 , 40));

//            p.setBorder(border);
            JTextField jTextFieldLabel = new JTextField(str[index]);
            jTextFieldLabel.setEditable(false);
            jTextFieldLabel.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
            jTextFieldLabel.setPreferredSize(new Dimension(100, 40));
            jTextFieldLabel.setBackground(new Color(240, 240, 240));
            jTextFieldLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
            JTextField jTextFieldValue = new JTextField(s);
            if(index == 0){
                jTextFieldValue.setEnabled(false);
            }
            //jTextFieldValue.setHorizontalAlignment(JTextField.CENTER);
            jTextFieldValue.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
            jTextFieldValue.setPreferredSize(new Dimension(220, 40));
            jTextFieldValue.setBackground(new Color(240, 240, 240));
            jTextFieldValue.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
            p.add(jTextFieldLabel , BorderLayout.WEST);
            p.add(jTextFieldValue , BorderLayout.CENTER);
            mainInfo.add(p);
            JPanel errorPanel = new JPanel();
            errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            JLabel errorLabel = new JLabel("");
            errorLabel.setForeground(Color.RED);
            errorLabel.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 12));
            errorLabels.add(errorLabel);
            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            errorPanel.setBackground(new Color(240, 240, 240));
            jTextFieldValue.addFocusListener(new KhachHangAction(jTextFieldValue , index , listAdd , errorLabels));
            errorPanel.add(errorLabel);
            mainInfo.add(errorPanel);
            mainInfo.add(Box.createVerticalStrut(5));
            listAdd.add(jTextFieldValue);
        }
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10 , 65 , 10 , 65),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.white , 0),
                        BorderFactory.createEmptyBorder(5 , 5 , 5 , 5)
                )
        );
        mainInfo.setBorder(border);
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

    public JPanel mainIMG() {
        mainImg = new JPanel();
        mainImg.setOpaque(true);
        mainImg.setPreferredSize(new Dimension(500, 200));
        mainImg.setMinimumSize(new Dimension(500, 200));
        mainImg.setMaximumSize(new Dimension(500, 200));
        JButton button_add_image = getJButton();
        SwingUtilities.invokeLater(button_add_image::requestFocusInWindow);
        mainImg.setLayout(new BorderLayout(5, 5));
        if(getAvatar() == null){
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

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
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
                        InputValid.showError(i, "Vui lòng nhập tên nhân viên", errorLabels, listAdd, false);
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
