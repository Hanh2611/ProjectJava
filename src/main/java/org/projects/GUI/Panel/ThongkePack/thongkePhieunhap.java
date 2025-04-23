package org.projects.GUI.Panel.ThongkePack;

import com.toedter.calendar.JDateChooser;
import org.projects.BUS.NhaCungCapBUS;
import org.projects.BUS.NhanVienBus;
import org.projects.GUI.Components.handleComponents;
import org.projects.entity.NhaCungCapEntity;
import org.projects.entity.NhanVienEntity;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class thongkePhieunhap extends JPanel {
    private JPanel west;
    private JPanel headerWest;
    private JLabel fromLabel;
    private JDateChooser dateFrom;
    private JLabel toLabel;
    private JDateChooser dateTo;
    private JLabel tenNcc;
    private JComboBox<String> nccBox;
    private JLabel tenNv;
    private JComboBox<String> nvBox;
    private JButton timkiembtn;
    private JButton resetbtn;
    private JPanel bottomWest;

    private JPanel center;
    private JPanel bottom;
    public thongkePhieunhap() {
        this.setLayout(new BorderLayout(10,10));
        this.setPreferredSize(new Dimension(940, 1000));
        this.setBackground(Color.WHITE);

        //west
        west = new JPanel(new GridLayout(2,1,5,5));
        west.setPreferredSize(new Dimension(350,1000));
        west.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        west.setBackground(Color.WHITE);
        headerWest = new JPanel();
        headerWest.setLayout(new BoxLayout(headerWest,BoxLayout.Y_AXIS));
        JPanel datePanel = new JPanel(new GridLayout(2,2));
        fromLabel = handleComponents.setLabelText("Từ ngày: ");
        dateFrom = handleComponents.createDate(100,25);
        toLabel = handleComponents.setLabelText("Đến ngày: ");
        dateTo = handleComponents.createDate(100,25);
        datePanel.add(fromLabel);
        datePanel.add(dateFrom);
        datePanel.add(toLabel);
        datePanel.add(dateTo);
        JPanel tenNccPanel = new JPanel(new GridLayout(2,1));
        tenNcc = handleComponents.setLabelText("Nhà cung cấp: ");
        nccBox = new JComboBox<>();
        nccBox.addItem("Tất cả");
        for(NhaCungCapEntity ncce: NhaCungCapBUS.getList()) {
            nccBox.addItem(ncce.getTenNCC());
        }
        nccBox.setSelectedItem("Tất cả");
        tenNccPanel.add(tenNcc);
        tenNccPanel.add(nccBox);
        JPanel tenNvPanel = new JPanel(new GridLayout(2,1));
        tenNv = handleComponents.setLabelText("Nhân viên: ");
        nvBox = new JComboBox<>();
        nvBox.addItem("Tất cả");
        for(NhanVienEntity nve : NhanVienBus.getList()) {
            nvBox.addItem(nve.getTenNhanVien());
        }
        nvBox.setSelectedItem("Tất cả");
        tenNvPanel.add(tenNv);
        tenNvPanel.add(nvBox);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        timkiembtn = handleComponents.createButton("Tìm",70,25);
        timkiembtn.setBackground(Color.decode("#55efc4"));
        resetbtn = handleComponents.createButton("Reset",70,25);
        resetbtn.setBackground(Color.decode("#0984e3"));
        buttonPanel.add(timkiembtn);
        buttonPanel.add(resetbtn);

        headerWest.add(datePanel);
        headerWest.add(tenNccPanel);
        headerWest.add(tenNvPanel);
        headerWest.add(buttonPanel);

        bottomWest = new JPanel(new GridLayout(1,1));

        west.add(headerWest);
        west.add(bottomWest);

        this.add(west, BorderLayout.WEST);
    }
}
