package org.projects.GUI.Panel.KhachHangPack;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.Panel.NhanVienPack.RoundedImageLabel;
import org.projects.entity.KhachHangEntity;
import org.projects.entity.NhanVienEntity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class DetailKhachHangConsole extends JPanel { ;
    private String ma, ten , diachi , sdt;
    public DetailKhachHangConsole() {
//        setupDetailBox_USER();
    }
    public void setInfo(KhachHangEntity info) {
        setMa(Integer.toString(info.getMa()));
        setTen(info.getTen());
        setSdt(info.getSdt());
        setDiachi(info.getDiaChi());
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

        // Panel bên trái chứa ảnh
        JPanel left = new JPanel(new BorderLayout());
        left.setOpaque(true);
        left.setBackground(new Color(240, 240, 240));
        left.setPreferredSize(new Dimension(250, 400));
        left.setMinimumSize(new Dimension(250, 400));
        left.setMaximumSize(new Dimension(250, 400));
        // Load ảnh và resize (nên đảm bảo ảnh có kích thước phù hợp)
        String changeImg = Objects.requireNonNull(getClass().getResource("/Img/user.jpg")).getPath();
        ImageIcon icon = new ImageIcon(new ImageIcon(changeImg).getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH));
        JLabel imgLabel = new RoundedImageLabel(icon, 20, 20);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        left.add(imgLabel, BorderLayout.CENTER);

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

        String[] info = {"Mã nhân viên: ", "Họ và tên: ", "Số điện thoại: ", "Địa chỉ: "};
        FlatSVGIcon iconIdNV = new FlatSVGIcon("icon/idNV.svg", 20, 20) ;
        FlatSVGIcon iconNameNV = new FlatSVGIcon("icon/nameNV.svg", 20, 20) ;
        FlatSVGIcon iconBrithDay = new FlatSVGIcon("icon/brithday.svg", 20, 20);
        FlatSVGIcon iconEmailNV = new FlatSVGIcon("icon/email.svg", 20, 20) ;
        FlatSVGIcon iconPhoneNV = new FlatSVGIcon("icon/phone.svg", 20, 20) ;
        FlatSVGIcon iconphucvuNV = new FlatSVGIcon("icon/phucvu.svg", 20, 20) ;
        FlatSVGIcon iconSalaryNV = new FlatSVGIcon("icon/money-dollars-svgrepo-com.svg", 20, 20) ;
        FlatSVGIcon[] iconList = {iconIdNV, iconNameNV,iconPhoneNV,iconphucvuNV};
        int index = 0;
        String[] values = { getMa(), getTen(), getSdt(), getDiachi()};
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

        JPanel genderPanel = getRadioSex(false , true);
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

        genderPanel.add(radioNam);
        genderPanel.add(radioNu);
        return genderPanel;
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

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
