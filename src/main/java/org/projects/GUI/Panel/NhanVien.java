package org.projects.GUI.Panel;

import org.projects.GUI.Components.header.headerBar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.CompoundBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class NhanVien extends JPanel {
    private JTable table;
    private JPanel topPanel;

    public NhanVien() {
        init();
        setupLayout();
        setupDetailBox();
    }

    private void init(){
        String [] col = {"Mã NV", "Tên nhân viên", "Email", "SĐT", "Chức vụ"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0){
            // không cho chính sửa trực tiếp trong bảng
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //test
        for(int i = 1; i <= 30; i++) {
//            tableModel.addRow(new Object[]{
//                    i,
//                    "Nhân viên " + i,
//                    "Địa chỉ chi tiết số " + i + ", Quận 1, TP.HCM",
//                    "0123-456-789",
//                    "2023-04-2" + (i % 10)
//            });
        }

        table = new JTable(tableModel);
        CustomTable();
    }

    private void CustomTable(){
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(500);
        table.getColumnModel().getColumn(3).setPreferredWidth(220);
        table.getColumnModel().getColumn(4).setPreferredWidth(220);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(new Color(240, 240, 240));
        header.setFont(new Font("JETBRAINS MONO", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        table.setBackground(new Color(245, 245, 245));
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setGridColor(new Color(220, 220, 220));
        table.setSelectionBackground(new Color(204, 229, 255));
        table.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 13));
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setOpaque(true);
        // Căn giữa nội dung trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void setupHeader(){
        String listItemHeader[][] = {
                {"icon/add.svg", "Thêm", "Them"},
                {"icon/content-writing.svg", "Sửa", "Sua"},
                {"icon/trash.svg", "Xóa", "Xoa"},
                {"icon/details.svg", "Chi tiết", "ChiTiet"},
                {"icon/excel.svg", "Xuất excel", "Excel"}
        };
        topPanel.setBackground(Color.WHITE);
        topPanel.setPreferredSize(new Dimension(1100, 100));
        topPanel.setLayout(new FlowLayout(0, 0, 10));
        topPanel.setBackground(Color.decode("#CAECF7"));
        topPanel.add(new headerBar(listItemHeader, new String[]{"add","update","delete","detail"},new String[]{"--"}));
    }

    public CompoundBorder setupBorder(){
        return BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.WHITE, 2),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                )
        );
    }

    private void setupLayout() {
        setLayout(new BorderLayout(0 , 10));
        setBackground(new Color(240, 240, 240));
        topPanel = new JPanel();
        setupHeader();
        add(topPanel, BorderLayout.NORTH);

        // Scroll
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        CompoundBorder compoundBorderScroll = setupBorder();
        scrollPane.setBorder(compoundBorderScroll);

        // Panel trung tâm
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        centerPanel.add(scrollPane, gbc);
        add(centerPanel, BorderLayout.CENTER);
    }
    
    public void setupDetailBox(){
        // Ý tưởng
        // Chia thành 3 ngăn với tỉ lể 0,5 top bot
        // Ở top chia thành 2 ngăn với bên trái chứa hình ảnh nhân viên , ở bên phải là chi tiết nhân viên
        // Ở bot là thành phần thông kê phạt, nghỉ làm , lương ...
        JLayeredPane detailBoxPanel = new JLayeredPane();
        detailBoxPanel.setLayout(new BorderLayout());
        detailBoxPanel.setBackground(Color.RED);
        detailBoxPanel.setPreferredSize(new Dimension(600 , 700));
        JPanel topLayout = new JPanel();
        JPanel botLayout = new JPanel();
        topLayout.setBackground(Color.BLUE);
        botLayout.setBackground(Color.YELLOW);
        // xây dựng top layout
        topLayout.setLayout(new GridLayout(1 , 1));
        topLayout.setPreferredSize(new Dimension(600 , 350));
        JPanel topImage = new JPanel();
        JPanel topInformation = new JPanel();
        topImage.setBackground(Color.PINK);
        topInformation.setBackground(Color.RED);
        topImage.setOpaque(true);
        topInformation.setOpaque(true);
        topLayout.add(topImage);
        topLayout.add(topInformation);


        topLayout.setOpaque(true);
        botLayout.setOpaque(true);
        detailBoxPanel.add(topLayout, BorderLayout.NORTH);
        detailBoxPanel.add(botLayout, BorderLayout.CENTER);
        detailBoxPanel.setOpaque(true);
        add(detailBoxPanel, BorderLayout.CENTER);
    }

}