package org.projects.Action;

import org.projects.BUS.ChiTietPhieuNhapFullEntityBUS;
import org.projects.BUS.PhieuNhapBUS;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.DiaLog.PhieuNhap.ChiTietPN;
import org.projects.GUI.Panel.PhieuNhap;
import org.projects.entity.ChiTietPhieuNhapFullEntity;
import org.projects.entity.PhieuNhapEntity;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.util.List;

public class PhieuNhapAction extends MouseAdapter implements ActionListener, DocumentListener, ItemListener {
    private PhieuNhap panel;
    private PhieuNhapEntity phieuNhapEntity;
    private ChiTietPN chiTietPN;
    private headerBar header;

    public PhieuNhapAction(PhieuNhap panel) {
        this.panel = panel;
    }

    public void mouseClicked(MouseEvent e) {
        generalFunction func = (generalFunction) e.getSource();
        String action = func.getNameFunction();
        switch (action) {
            case "add":
                panel.showThemPN();
                break;
            case "update":
                JTable table = panel.getTable();
                int selectedRow = table.getSelectedRow();
                if(selectedRow != -1) {
                    int maPN = (int) table.getValueAt(selectedRow, 0);
                    ChiTietPhieuNhapFullEntityBUS bus = new ChiTietPhieuNhapFullEntityBUS();
                    List<ChiTietPhieuNhapFullEntity> list = bus.getChiTietByMaPN(maPN);
                    panel.showCapNhatPN(list);

                }
                else{
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn phiếu nhập để chỉnh sửa");
                }
                break;
            case "delete":
                JTable table1 = panel.getTable();
                int selectedRow1 = table1.getSelectedRow();
                if(selectedRow1 != -1) {
                    int maPN = (int) table1.getValueAt(selectedRow1, 0);
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn xóa phiếu nhập này?",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        PhieuNhapEntity pn = new PhieuNhapEntity();
                        pn.setMaPN(maPN);
                        boolean result = PhieuNhapBUS.xoa(pn);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Xóa phiếu nhập thành công!");
                            panel.reloadDAO(); // nếu có
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa thất bại!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu nhập để xóa!");
                }
                break;
            case "detail":
                JTable table2 = panel.getTable();
                int selectedRow2 = table2.getSelectedRow();
                if (selectedRow2 != -1) {
                    int maPN = (int) table2.getValueAt(selectedRow2, 0);
                    ChiTietPhieuNhapFullEntityBUS bus1 = new ChiTietPhieuNhapFullEntityBUS();
                    List<ChiTietPhieuNhapFullEntity> list1 = bus1.getChiTietByMaPN(maPN);
                    // Gọi panel để hiển thị
                    panel.showChiTietPN(list1);
                    break;
                }
                else{
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn phiếu nhập");
                }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent c = (JComponent) e.getSource();
        JButton refresh = panel.getHeader().getSearch().getSearchButton();
        if (c instanceof JButton && c.equals(refresh)) {
            panel.getHeader().getSearch().getSearchComboBox().setSelectedItem("---");
            panel.getHeader().getSearch().getSearchField().setText("");
            this.panel.searchfunction(panel.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString(), panel.getHeader().getSearch().getSearchField().getText());
        }

    }
        @Override
        public void insertUpdate (DocumentEvent e){

        }

        @Override
        public void removeUpdate (DocumentEvent e){

        }

        @Override
        public void changedUpdate (DocumentEvent e){
            String keyword = panel.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
            String textfield = e.getDocument().toString();
            System.out.println(textfield);
            panel.loadList(PhieuNhapBUS.search(keyword,textfield));
        }


        @Override
        public void itemStateChanged (ItemEvent e){
            String keyword = e.getItem().toString();
            String textfield = panel.getHeader().getSearch().getSearchField().getText();
            panel.loadList(PhieuNhapBUS.search(keyword,textfield));
        }
}
