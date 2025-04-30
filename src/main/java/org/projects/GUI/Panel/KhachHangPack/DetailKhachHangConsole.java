package org.projects.GUI.Panel.KhachHangPack;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.DAO.ChiTietHoaDonFullDAO;
import org.projects.GUI.Panel.NhanVienPack.RoundedImageLabel;
import org.projects.entity.ChiTietHoaDonFullEntity;
import org.projects.entity.KhachHangEntity;
import org.projects.entity.NhanVienEntity;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetailKhachHangConsole extends JPanel { ;
    private String ma, ten , diachi , sdt;
    public DetailKhachHangConsole() {}
    DefaultTableModel tableModel;
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
        this.setPreferredSize(new Dimension(700, 800));
        this.setMaximumSize(new Dimension(700, 800));
        this.setMinimumSize(new Dimension(700, 800));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel left = new JPanel(new BorderLayout());
        left.setOpaque(true);
        left.setBackground(new Color(240, 240, 240));
        left.setPreferredSize(new Dimension(250, 300));
        left.setMinimumSize(new Dimension(250, 300));
        left.setMaximumSize(new Dimension(250, 300));

//        String changeImg = Objects.requireNonNull(getClass().getResource("/Img/user.jpg")).getPath();
//        ImageIcon icon = new ImageIcon(new ImageIcon(changeImg).getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH));
        JLabel imgLabel = new JLabel();
        FlatSVGIcon flatSVGIcon = new FlatSVGIcon("icon/user.svg" , 250 , 250);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imgLabel.setIcon(flatSVGIcon);
        left.add(imgLabel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.weightx = 0.4;
        gbc.gridy = 0;
        gbc.weighty = 0;
        this.add(left, gbc);

        JPanel right = new JPanel(new BorderLayout());
        right.setOpaque(true);
        right.setBackground(new Color(240, 240, 240));
        right.setPreferredSize(new Dimension(420, 300));
        right.setMaximumSize(new Dimension(420, 300));
        right.setMinimumSize(new Dimension(420, 300));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(true);
        infoPanel.setBackground(new Color(240, 240, 240));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] info = {"Mã khách hàng: ", "Họ và tên: ", "Số điện thoại: ", "Địa chỉ: "};
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

//        JPanel genderPanel = getRadioSex(false , true);
//        infoPanel.add(genderPanel);

        right.add(infoPanel, BorderLayout.CENTER);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.6;
        gbc.weighty = 0;
        this.add(right, gbc);

        JPanel bothPanel = new JPanel();
        String[] col = {"Mã SP", "Tên SP", "SL", "Tổng Giá" , "Ngày Lập"};
        tableModel = new DefaultTableModel(col, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(Color.white);
        header.setFont(new Font("JETBRAINS MONO", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        bothPanel.setOpaque(true);
        bothPanel.setPreferredSize(new Dimension(700, 400));
        table.setBackground(new Color(245, 245, 245));
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setGridColor(new Color(220, 220, 220));
        table.setSelectionBackground(new Color(204, 229, 255));
        table.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 13));
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setOpaque(true);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.white, 2),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                )
        );
        scrollPane.setPreferredSize(new Dimension(700, 400));
        scrollPane.setBorder(border);
        scrollPane.setBackground(new Color(220, 220, 220));
        bothPanel.add(scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(bothPanel, gbc);
        ReloadLSmua();
        return this;
    }
    public void ReloadLSmua(){
        ChiTietHoaDonFullDAO dao = new ChiTietHoaDonFullDAO();
        List<ChiTietHoaDonFullEntity> ds = new ArrayList<>();
        ds.addAll(dao.showListByKhachHang(Integer.parseInt(getMa())));

        for (ChiTietHoaDonFullEntity e : ds) {
            Object[] row = {
                    e.getMaSP(),
                    e.getTenSP(),
                    e.getSoLuong(),
                    e.getThanhTien(),
                    e.getNgayTao()
            };
            tableModel.addRow(row);
        }
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
