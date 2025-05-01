package org.projects.Action;

import org.projects.BUS.ThongKeSanPhamBUS;
import org.projects.DAO.ThongKeSanPhamDAO;
import org.projects.GUI.Panel.ThongkePack.thongkeSanpham;
import org.projects.GUI.utils.ExportExcel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public  class ThongKeSanPhamAction implements ItemListener, ActionListener {
    private thongkeSanpham tksp;
    private ThongKeSanPhamBUS tkspBUS;
    public ThongKeSanPhamAction(thongkeSanpham tksp, ThongKeSanPhamBUS tkspBUS) {
        this.tksp = tksp;
        this.tkspBUS = tkspBUS;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            if (!path.endsWith(".xlsx")) {
                path += ".xlsx";
            }
            ExportExcel.exportToExcel(tksp.getSanphamTable(), path); // thay myJTable bằng JTable của bạn
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String item = e.getItem().toString();
        tksp.loadList(tkspBUS.search(item));
    }
}
