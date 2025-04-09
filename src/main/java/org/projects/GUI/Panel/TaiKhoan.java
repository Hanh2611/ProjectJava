package org.projects.GUI.Panel;

import org.projects.BUS.PhanQuyenBUS;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.Components.layoutCompoment;
import org.projects.GUI.utils.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

//todo: mình sẽ cho tạo tài khoản mặc định sẽ là khách hàng, sau đó mình sẽ set tài khoản vào với nhân viên
public class TaiKhoan extends JPanel{
    private headerBar header;
    private JTable mainTable;
    private JScrollPane scrollPane;
    private JPanel contentPanel;
    private DefaultTableModel tableModel;
    private String[][] listItemHeader;
    public TaiKhoan() {
        listItemHeader = new String[][]{
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"},
                {"icon/excel.svg", "Xuất excel", "export"}
        };
        this.setBackground(Color.decode("#CAECF7"));
        this.setOpaque(true);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        initHeader();
    }

    public void initHeader() {
        header = new headerBar(listItemHeader, Session.quyenTaiKhoan.get(PhanQuyenBUS.getMaDanhMuc("TaiKhoan") - 1), new String[]{"--"});
        this.add(header);
    }

    public void initContent() {

    }

    public void initTable() {

    }
}
