package org.projects.GUI.Panel.NhanVienPack;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.entity.NhanVienEntity;

import javax.swing.*;
import java.awt.*;

public class ChiTietUserConsole extends JPanel { ;
    private String ma, ten , email , sdt , chucvu , avatar;
    private static boolean gioitinh;
    private int luong;
    public ChiTietUserConsole() {
    }
    public void setInfo(NhanVienEntity info) {
        setMa(Integer.toString(info.getMaNhanVien()));
        setTen(info.getTenNhanVien());
        setEmail(info.getEmailNhanVien());
        setSdt(info.getSdtNhanVien());
        setChucvu(info.getChucvu());
        setLuong(info.getLuong());
        setGioitinh(info.getGioitinh());
        setAvatar(info.getAvatar());
    }
    public JPanel setupDetailBox_USER() {
        this.setLayout(new GridBagLayout());
        this.setOpaque(true);
        this.setBackground(new Color(240, 240, 240));
        this.setPreferredSize(new Dimension(700, 400));
        this.setMaximumSize(new Dimension(700, 400));
        this.setMinimumSize(new Dimension(700, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel left = new JPanel(new GridBagLayout());
        left.setOpaque(true);
        left.setBackground(new Color(240, 240, 240));
        left.setPreferredSize(new Dimension(250, 400));
        left.setMinimumSize(new Dimension(250, 400));
        left.setMaximumSize(new Dimension(250, 400));

        JLabel imgLabel;
        FlatSVGIcon flatSVGIcon;
        JPanel panel;
        GridBagConstraints gbcImg = new GridBagConstraints();
        gbcImg.gridx = 0;
        gbcImg.gridy = 0;
        gbcImg.weightx = 1.0;
        gbcImg.weighty = 1.0;
        gbcImg.anchor = GridBagConstraints.CENTER;
        gbcImg.fill = GridBagConstraints.NONE;

        System.out.println(getAvatar());
        if(getAvatar() != null) {
            panel = AddNhanVienConsole.getJPanel(getAvatar() , 350 , 350);
            left.add(panel, gbcImg);
        } else {
            imgLabel = new JLabel();
            flatSVGIcon = new FlatSVGIcon("icon/user.svg" , 250 , 250);
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imgLabel.setIcon(flatSVGIcon);
            left.add(imgLabel, gbcImg);
        }

        gbc.gridx = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1;
        this.add(left, gbc);

        JPanel right = new JPanel(new BorderLayout());
        right.setOpaque(true);
        right.setBackground(new Color(240, 240, 240));
        right.setPreferredSize(new Dimension(420, 400));
        right.setMaximumSize(new Dimension(420, 400));
        right.setMinimumSize(new Dimension(420, 400));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(true);
        infoPanel.setBackground(new Color(240, 240, 240));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] info = {"Mã nhân viên: ", "Họ và tên: ","Email: ", "Số điện thoại: ", "Chức vụ: " , "Lương: "};
        FlatSVGIcon iconIdNV = new FlatSVGIcon("icon/idNV.svg", 20, 20) ;
        FlatSVGIcon iconNameNV = new FlatSVGIcon("icon/nameNV.svg", 20, 20) ;
        FlatSVGIcon iconBrithDay = new FlatSVGIcon("icon/brithday.svg", 20, 20);
        FlatSVGIcon iconEmailNV = new FlatSVGIcon("icon/email.svg", 20, 20) ;
        FlatSVGIcon iconPhoneNV = new FlatSVGIcon("icon/phone.svg", 20, 20) ;
        FlatSVGIcon iconphucvuNV = new FlatSVGIcon("icon/phucvu.svg", 20, 20) ;
        FlatSVGIcon iconSalaryNV = new FlatSVGIcon("icon/money-dollars-svgrepo-com.svg", 20, 20) ;
        FlatSVGIcon[] iconList = {iconIdNV, iconNameNV,iconEmailNV,iconPhoneNV,iconphucvuNV,iconSalaryNV};
        int index = 0;
        String[] values = { getMa(), getTen(), getEmail(), getSdt(), getChucvu() , convert(String.valueOf(getLuong()))};
        for (String labelText : info) {
            JPanel pan = new JPanel(new FlowLayout(FlowLayout.LEFT , 5 , 0));
            pan.setBackground(new Color(240, 240, 240));
            JLabel iconLabel = new JLabel(iconList[index]);
            pan.add(iconLabel);
            JTextField field = new JTextField(labelText + values[index]);
            field.setFont(new Font("JetBrains Mono", Font.ITALIC, 15));
            field.setHorizontalAlignment(SwingConstants.LEFT);
            field.setBackground(new Color(240, 240, 240));
            field.setEditable(false);
            field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220,220,220)));
            field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            pan.add(field);
            infoPanel.add(pan);
            infoPanel.add(Box.createVerticalStrut(10));
            index++;
            index %= iconList.length;
        }

        JPanel genderPanel = getRadioSex(false , isGioitinh());
        infoPanel.add(genderPanel);

        right.add(infoPanel, BorderLayout.CENTER);

        gbc.gridx = 1;
        gbc.weightx = 0.6;
        this.add(right, gbc);
        return this;
    }

    public static JPanel getRadioSex(boolean edit, boolean data) {
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genderPanel.setOpaque(true);
        genderPanel.setBackground(new Color(240, 240, 240));
//        System.out.println(data);
        JRadioButton radioNam = new JRadioButton("Nam", data);
        data = !data;
        JRadioButton radioNu = new JRadioButton("Nữ", data);
        radioNam.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
        radioNu.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
        if(!edit) {
            radioNam.setEnabled(false);
            radioNu.setEnabled(false);
        }
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioNam);
        genderGroup.add(radioNu);
        radioNam.addActionListener(e -> setGioitinh(true));
        radioNu .addActionListener(e -> setGioitinh(false));
        genderPanel.add(radioNam);
        genderPanel.add(radioNu);
        return genderPanel;
    }

    public String convert(String value){
        int cnt = 0;
        String ans = "";
        String temp = "";
        int n = value.length();
        for(int i = n - 1 ; i >= 0 ; i--){
            if(cnt % 3 == 0 && cnt != 0 && cnt != n){
                temp += '.';
            }
            temp += value.charAt(i);
            cnt++;
        }
        for(int i = temp.length() - 1 ; i >= 0 ; i--){
            ans = ans + temp.charAt(i);
        }
        ans += 'đ';
        return ans;
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

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public static void setGioitinh(boolean gioitinh) {
        ChiTietUserConsole.gioitinh = gioitinh;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setLuong(int luong) {
        this.luong = luong;
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
}
