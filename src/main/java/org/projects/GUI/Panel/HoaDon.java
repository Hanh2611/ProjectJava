package org.projects.GUI.Panel;

import org.projects.Action.HoaDonAction;
import org.projects.GUI.Components.header.headerBar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class HoaDon extends JPanel{
    private headerBar header;
    private JPanel contentPanel,main;
    private DefaultTableModel tableModel;
    private CardLayout cardLayout;
    private JTable table;
    private HoaDonAction actionHandler;

    public HoaDon() {
        String[][] listItemHeader = {
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"}
        };
        header = new headerBar(listItemHeader,new String[]{"add","update","delete","detail"});
        this.add(header);
        init();
    }
    private void init() {
        JPanel main = createTablePanel();
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);
        contentPanel.setPreferredSize(new Dimension(    940, 650));
        contentPanel.add(main, "trangchinh");
        add(contentPanel);
        cardLayout.show(contentPanel, "trangchinh");
        actionHandler = new HoaDonAction(this);
        for (String name : header.getHeaderFunc().getHm().keySet()) {
            header.getHeaderFunc().getHm().get(name).addMouseListener(actionHandler);
        }
    }
    private JPanel createTablePanel() {
        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.WHITE);
        right.setOpaque(false);

        // Dữ liệu bảng
        String[] columnNames = {"Mã Phiếu Nhập", "Mã Nhân Viên", "Mã NCC", "Ngày Nhập", "Tổng tiền"};
        Object[][] data = {
                {"19", "Cty TGDD", "Trần Sinh", "20/4/2023", "7.5M"},
                {"20", "Cty FPT", "Nguyễn A", "21/4/2023", "12M"},
                {"21", "Cty Viettel", "Lê B", "22/4/2023", "9.3M"},
                {"22", "Cty Mobifone", "Phạm C", "23/4/2023", "5.75M"},
                {"23", "Cty VNPT", "Hoàng D", "24/4/2023", "11.2M"},
                {"24", "Cty Apple", "Nguyễn Văn A", "25/4/2023", "15M"}
        };

        tableModel = new DefaultTableModel(columnNames, 0);
        for (Object[] row : data) {
            tableModel.addRow(row);
        }

        table = new JTable(tableModel);
        customizeTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(940, 640));


        right.add(scrollPane, BorderLayout.CENTER);
        return right;
    }
private void customizeTable() {
    table.setDefaultEditor(Object.class, null);
    table.setRowHeight(30);
    table.setShowGrid(true);
    table.setFocusable(false);
    table.setBackground(new Color(245, 245, 245));
    table.setGridColor(new Color(220, 220, 220));
    table.setSelectionBackground(new Color(204, 229, 255));
    table.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 13));
    table.setIntercellSpacing(new Dimension(0, 0));

    // Căn giữa nội dung bảng
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    table.setDefaultRenderer(Object.class, centerRenderer);

    // Cấu hình header
    JTableHeader header = table.getTableHeader();
    header.setBackground(new Color(0, 102, 204));
    header.setForeground(new Color(240, 240, 240));
    header.setFont(new Font("JETBRAINS MONO", Font.BOLD, 13));
    header.setPreferredSize(new Dimension(header.getWidth(), 35));
    }
    private void showChiTietHD(){

    }
    private void showThemHD(){

    }
    private void showSuaHD(){

    }
}
