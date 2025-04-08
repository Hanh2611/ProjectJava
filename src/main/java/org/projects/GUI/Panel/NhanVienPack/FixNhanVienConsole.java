package org.projects.GUI.Panel.NhanVienPack;

import com.formdev.flatlaf.extras.FlatSVGIcon;
//import org.projects.BUS.MainBUS;
import org.projects.Action.NhanVienAction;
import org.projects.BUS.NhanVienBus;
import org.projects.GUI.Components.handleComponents;
import org.projects.entity.NhanVienEntity;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static org.projects.GUI.Panel.NhanVienPack.AddNhanVienConsole.addPlaceholderStyle;
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
    public ArrayList<JTextField> listAdd;
    GridBagConstraints c = new GridBagConstraints();
    GridBagConstraints f = new GridBagConstraints();
    private String ma , ten , email , std , chucvu;
    public FixNhanVienConsole() {
//        initComponents();
    }
    public void insertData(){
        setMa(listAdd.get(0).getText().trim());
        setTen(listAdd.get(1).getText().trim());
        setEmail(listAdd.get(2).getText().trim());
        setStd(listAdd.get(3).getText().trim());
        setChucvu((String)comboBox.getSelectedItem());
    }
    public void setInfo(NhanVienEntity info) {
        setMa(Integer.toString(info.getMaNhanVien()));
        setTen(info.getTenNhanVien());
        setEmail(info.getEmailNhanVien());
        setStd(info.getSdtNhanVien());
        setChucvu(info.getChucvu());
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
        String[] list = {getMa() , getTen() , getEmail() , getStd()};
        String[] items = {"-- Chọn vai trò --", "Nhân viên bán hàng", "Kế toán", "Nhân viên kho", "Quản lí sản phẩm", "Nhân viên kĩ thuật", "Giám đốc"};
        String [] str = {"Mã số: ", "Tên: ", "Email: ", "Sdt: "};
        listAdd = new ArrayList<>();
        comboBox = new JComboBox<>(items);
        int index = 0;
        for (String s : list) {
            JPanel p = new JPanel();
            p.setLayout(new FlowLayout(FlowLayout.LEFT , 5 , 5));
            p.setBackground(new Color(240, 240, 240));
            p.setPreferredSize(new Dimension(500, 40));
            JTextField jTextField2 = new JTextField(str[index]);
            jTextField2.setEditable(false);
            JTextField jTextField = new JTextField(s);
            if(index == 0){
                jTextField.setEditable(false);
            }
            jTextField.setPreferredSize(new Dimension(220, 40));
            jTextField2.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
            jTextField.setBackground(new Color(240, 240, 240));
            jTextField.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
            jTextField2.setPreferredSize(new Dimension(80, 40));
            jTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
            jTextField2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
            p.add(jTextField2);
            p.add(jTextField);
            mainInfo.add(p);
            mainInfo.add(Box.createVerticalStrut(5));
            listAdd.add(jTextField);
            index++;
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

    public JPanel mainIMG() {
        mainImg = new JPanel();
        mainImg.setOpaque(true);
        mainImg.setPreferredSize(new Dimension(500, 200));
        mainImg.setMinimumSize(new Dimension(500, 200));
        mainImg.setMaximumSize(new Dimension(500, 200));
        JButton button_add_image = getJButton();
        SwingUtilities.invokeLater(button_add_image::requestFocusInWindow);
        mainImg.setLayout(new BorderLayout(5, 5));
        changeImage = Objects.requireNonNull(getClass().getResource("/Img/user.jpg")).getPath();
        parentImg = new JPanel();
        parentImg = getJPanel(changeImage);
        FlatSVGIcon addIcon = new FlatSVGIcon("icon/add-folder.svg", 20, 20);
        JLabel label = new JLabel(addIcon);
        button_add_image.add(label);
        mainImg.add(button_add_image, BorderLayout.NORTH);
        mainImg.add(parentImg, BorderLayout.CENTER);
        mainImg.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        return mainImg;
    }

    private static JButton getJButton() {
        JButton button_add_image = new JButton("ADD IMAGE");
        button_add_image.addActionListener(e -> {
            JComponent source = (JComponent) e.getSource();
            String actionCommand = e.getActionCommand();

            if ("ADD IMAGE".equals(actionCommand)) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Ảnh (JPG, PNG, GIF)", "jpg", "png", "gif"));

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("File được chọn: " + selectedFile.getAbsolutePath());
                    changeImage = selectedFile.getAbsolutePath();
                    JPanel newParentImg = getJPanel(changeImage);
                    mainImg.remove(parentImg);
                    mainImg.add(newParentImg, BorderLayout.CENTER);
                    parentImg = newParentImg;
                    mainImg.revalidate();
                    mainImg.repaint();
                }
            }
        });
        button_add_image.setBackground(new Color(135, 206, 250));
        button_add_image.setPreferredSize(new Dimension(10, 30));
        button_add_image.setFont(new Font("JETBRAINS MONO", Font.BOLD, 14));
        return button_add_image;
    }

    private static JPanel getJPanel(String path) {
//        FlatSVGIcon addIcon_user = new FlatSVGIcon(image, 200, 200);
        ImageIcon addIcon_user = new ImageIcon(path);
        Image scale = addIcon_user.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
//        JLabel img = new RoundedImageLabel(addIcon_user , 100 , 100);
        JLabel img = new JLabel(new ImageIcon(scale));
        img.setHorizontalAlignment(SwingConstants.CENTER);
        img.setOpaque(true);
        JPanel parentImg = new JPanel();
        parentImg.setOpaque(true);
        parentImg.setPreferredSize(new Dimension(500, 150));
        parentImg.setMinimumSize(new Dimension(500, 150));
        parentImg.setMaximumSize(new Dimension(500, 150));
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
}
