package org.projects.GUI.Panel.KhachHangPack;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.DAO.ChiTietHoaDonFullDAO;
import org.projects.GUI.Panel.HoaDon;
import org.projects.GUI.Panel.NhanVienPack.AddNhanVienConsole;
import org.projects.entity.ChiTietHoaDonFullEntity;
import org.projects.entity.KhachHangEntity;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.*;
import java.util.List;

public class DetailKhachHangConsole extends JPanel {
    private String ma, ten, diachi, sdt;
    DefaultTableModel tableModel;
    private String avatar ;
    public DetailKhachHangConsole() {}

    public void setInfo(KhachHangEntity info) {
        setMa(Integer.toString(info.getMa()));
        setTen(info.getTen());
        setSdt(info.getSdt());
        setDiachi(info.getDiaChi());
        setAvatar(info.getAvatar());
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

        JLabel imgLabel;
        FlatSVGIcon flatSVGIcon;
        JPanel panel;
        System.out.println(getAvatar());
        if(getAvatar() != null) {
            panel = AddNhanVienConsole.getJPanel(getAvatar() , 250 , 250);
            left.add(panel, BorderLayout.CENTER);
        } else {
            imgLabel = new JLabel();
            flatSVGIcon = new FlatSVGIcon("icon/user.svg" , 250 , 250);
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imgLabel.setIcon(flatSVGIcon);
            left.add(imgLabel, BorderLayout.CENTER);
        }

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
        FlatSVGIcon iconIdNV = new FlatSVGIcon("icon/idNV.svg", 20, 20);
        FlatSVGIcon iconNameNV = new FlatSVGIcon("icon/nameNV.svg", 20, 20);
        FlatSVGIcon iconPhoneNV = new FlatSVGIcon("icon/phone.svg", 20, 20);
        FlatSVGIcon iconAddrNV = new FlatSVGIcon("icon/phucvu.svg", 20, 20);
        FlatSVGIcon[] iconList = {iconIdNV, iconNameNV, iconPhoneNV, iconAddrNV};
        String[] values = {getMa(), getTen(), getSdt(), getDiachi()};
        int index = 0;
        for (String labelText : info) {
            JPanel pan = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            pan.setBackground(new Color(240, 240, 240));
            JLabel iconLabel = new JLabel(iconList[index]);
            pan.add(iconLabel);
            JTextField field = new JTextField(labelText + values[index]);
            field.setFont(new Font("JetBrains Mono", Font.ITALIC, 15));
            field.setHorizontalAlignment(SwingConstants.LEFT);
            field.setBackground(new Color(240, 240, 240));
            field.setEditable(false);
            field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
            field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            pan.add(field);
            infoPanel.add(pan);
            infoPanel.add(Box.createVerticalStrut(10));
            index++;
            index %= iconList.length;
        }

        right.add(infoPanel, BorderLayout.CENTER);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.6;
        gbc.weighty = 0;
        this.add(right, gbc);

        JPanel bothPanel = new JPanel();
        bothPanel.setLayout(new BoxLayout(bothPanel, BoxLayout.Y_AXIS));

        String[] summaryCols = {"Mã HD" , "Tổng tiền", "Ngày lập" , "Tình trạng"};
        tableModel = new DefaultTableModel(summaryCols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        setupTableStyle(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setPreferredSize(new Dimension(700, 200));
        bothPanel.add(scrollPane);
        DefaultTableModel detailModel = new DefaultTableModel(new String[]{"Tên SP", "SL", "Thành tiền"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tableDetail = new JTable(detailModel);
        setupTableStyle(tableDetail);
        tableDetail.getColumnModel().getColumn(0).setPreferredWidth(400);
        tableDetail.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableDetail.getColumnModel().getColumn(2).setPreferredWidth(300);
        JScrollPane scrollPaneDetail = new JScrollPane(tableDetail);
        scrollPaneDetail.setBackground(Color.WHITE);
        scrollPaneDetail.setPreferredSize(new Dimension(710, 170));
        bothPanel.add(scrollPaneDetail);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(bothPanel, gbc);

        ReloadLSmua();
        table.getSelectionModel().addListSelectionListener(ev -> {
            if (!ev.getValueIsAdjusting()) {
                detailModel.setRowCount(0);
                int r = table.getSelectedRow();
                if (r >= 0) {
                    int maHD = (int) tableModel.getValueAt(r, 0);
                    ChiTietHoaDonFullDAO dao = new ChiTietHoaDonFullDAO();
                    List<ChiTietHoaDonFullEntity> ds = dao.showListByKhachHang(Integer.parseInt(getMa()));
                    for (ChiTietHoaDonFullEntity e : ds) {
                        if (e.getMaHD() == maHD) {
                            detailModel.addRow(new Object[]{
                                    e.getTenSP(),
                                    e.getSoLuong(),
                                    HoaDon.formatCurrency((long) e.getThanhTien())
                            });
                        }
                    }
                }
            }
        });

        return this;
    }

    private void setupTableStyle(JTable table) {
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setGridColor(new Color(220, 220, 220));
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 13));
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(Color.white);
        header.setFont(new Font("JETBRAINS MONO", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    public void ReloadLSmua() {
        ChiTietHoaDonFullDAO dao = new ChiTietHoaDonFullDAO();
        tableModel.setRowCount(0);
        List<ChiTietHoaDonFullEntity> ds = dao.showListByKhachHang(Integer.parseInt(getMa()));
        Set<Integer> seen = new HashSet<>();
        List<ChiTietHoaDonFullEntity> list = new ArrayList<>();
        for (ChiTietHoaDonFullEntity e : ds) {
            if (seen.add(e.getMaHD())) {
                list.add(e);
            }
        }
        for (ChiTietHoaDonFullEntity e : list) {
            Object[] row = {
                    e.getMaHD(),
                    HoaDon.formatCurrency((long)e.getTongGiaTri()),
                    e.getNgayTao(),
                    e.getTrangThai(),
            };
            tableModel.addRow(row);
        }
        list.sort(Comparator.comparingInt(ChiTietHoaDonFullEntity::getMaHD));
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

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }
    public String getAvatar(){
        return avatar;
    }
}
