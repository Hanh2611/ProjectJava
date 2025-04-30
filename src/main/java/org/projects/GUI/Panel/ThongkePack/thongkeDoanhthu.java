package org.projects.GUI.Panel.ThongkePack;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class thongkeDoanhthu extends JPanel {
    private JTabbedPane doanhthuPanel;
    private JPanel doanhthutheongayPanel;
    private JPanel doanhthutheothangPanel;
    private JPanel doanhthutheoNamPanel;
    private HashMap<String, JPanel> danhsachdoanhthuMap;

    public thongkeDoanhthu() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        doanhthutheongayPanel = new thongkedoanhthutheoNgay();
        doanhthutheothangPanel = new thongkedoanhthutheoThang();
        doanhthutheoNamPanel = new thongkedoanhthutheoNam();
        JPanel[] lstDoanhthu = new JPanel[]{doanhthutheongayPanel, doanhthutheothangPanel, doanhthutheoNamPanel};
        String[] tendoanhthuPanel = new String[]{"Theo ngày","Theo tháng","Theo năm"};
        danhsachdoanhthuMap = new HashMap<>();
        for (int i = 0; i < tendoanhthuPanel.length; i++) {
            danhsachdoanhthuMap.put(tendoanhthuPanel[i], lstDoanhthu[i]);
        }
        doanhthuPanel = new JTabbedPane();
        doanhthuPanel.setPreferredSize(new Dimension(940,1000));
        doanhthuPanel.setFont(new Font("Jetbrains Mono", Font.PLAIN, 15));
        doanhthuPanel.setBackground(Color.WHITE);
        for(String i : danhsachdoanhthuMap.keySet()) {
            doanhthuPanel.addTab(i, danhsachdoanhthuMap.get(i));
        }
        this.add(doanhthuPanel, BorderLayout.CENTER);
    }
}
