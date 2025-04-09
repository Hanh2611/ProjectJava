package org.projects.Action;

import org.projects.BUS.SanPhamBus;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.SanPham.AddSanPhamDialog;
import org.projects.GUI.Panel.SanPham;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;

public class SanPhamAction implements ActionListener, MouseListener, ItemListener, DocumentListener {

    private SanPham sanPham;
    private AddSanPhamDialog addSanPhamDialog;
    private SanPhamBus sanPhamBus;

    public SanPhamAction(SanPham sanPham, AddSanPhamDialog addSanPhamDialog) {
        this.sanPham = sanPham;
        this.addSanPhamDialog = addSanPhamDialog;
        this.sanPhamBus = new SanPhamBus(sanPham);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent c = (JComponent) e.getSource();
        String namebtn = e.getActionCommand();
        if(sanPham != null) {
            if(addSanPhamDialog != null && c instanceof JButton){
                if("Thêm".equals(namebtn)) {
                    String ten = addSanPhamDialog.getTenSanPhamField().getTextField().getText().trim();
                    String phanloai = addSanPhamDialog.getPhanLoaiField().getSelectedItem().toString();
                    String gia = addSanPhamDialog.getGiaBanField().getTextField().getText().trim();
                    String donvi = addSanPhamDialog.getDonViField().getTextField().getText().trim();
                    String quycach = addSanPhamDialog.getQuyCachField().getSelectedItem().toString();
                    String hinhAnh = addSanPhamDialog.getFileChooser().getSelectedFile().getAbsolutePath();
                    System.out.println("ten: " + ten);
                    System.out.println("phanloai: " + phanloai);
                    System.out.println("gia: " + gia);
                    System.out.println("quycach: " + quycach);
                    System.out.println("donvi: " + donvi);
                    System.out.println("hinhAnh: " + hinhAnh);
                    if(ten.equals("") || gia.equals("") || quycach.equals("") || donvi.equals("") || hinhAnh.equals("")
                    ){
                        JOptionPane.showMessageDialog(null, "Vui lòng nhâp đầy đủ các giá trị", "thông báo", JOptionPane.ERROR_MESSAGE);
                    } else {
                        sanPhamBus.addSanPham();
                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công", "thông báo", JOptionPane.INFORMATION_MESSAGE);
                        sanPham.loadList(sanPhamBus.getAllSanPham());
                        addSanPhamDialog.dispose();
                    }
                }
            }
        }
        JButton refresh = sanPham.getHeader().getSearch().getSearchButton();
        if(c instanceof JButton && c.equals(refresh)) {
            String keyword = sanPham.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
            String textField = sanPham.getHeader().getSearch().getSearchField().getText();
            sanPham.getHeader().getSearch().getSearchField().setText("");
            sanPham.searchFunction(keyword, textField);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        System.out.println(c);
        if (sanPham != null) {
            for (String name : sanPham.getHeader().getHeaderFunc().getHm().keySet()) {
                generalFunction gf = sanPham.getHeader().getHeaderFunc().getHm().get(name);
                if (c.equals(gf)) {
                    if (name == null && name.trim().isEmpty()) return;
                    if ("add".equals(name)) {
                        new AddSanPhamDialog(sanPham);
                    } else {
//                        NhaCungCapEntity nccEntity = ncc.getRow();
//                        if (nccEntity == null) {
//                            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng", "thông báo", JOptionPane.ERROR_MESSAGE);
//                        } else {
//                            if ("update".equals(name) || "detail".equals(name)) {
//                                new NhaCungCapDialog(name, ncc);
//                            } else if ("delete".equals(name)) {
//                                int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa ?", "thông báo", JOptionPane.YES_NO_OPTION);
//                                if (option == JOptionPane.YES_OPTION) {
//                                    if (NhaCungCapBUS.xoa(nccEntity)) {
//                                        JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp thành công", "thông báo", JOptionPane.INFORMATION_MESSAGE);
//                                    }
//                                } else {
//                                    JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp thất bại", "thông báo", JOptionPane.ERROR_MESSAGE);
//                                }
//                                ncc.loadList(NhaCungCapBUS.getList());
//                            }
//                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
            String selectedItem = (String) comboBox.getSelectedItem();
            String textField = sanPham.getHeader().getSearch().getSearchField().getText();
            sanPham.loadList(sanPhamBus.searchSanPham(selectedItem, textField));
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (e.getDocument() == sanPham.getHeader().getSearch().getSearchField().getDocument()) {
            String text = sanPham.getHeader().getSearch().getSearchField().getText();
            String selectedItem = (String) sanPham.getHeader().getSearch().getSearchComboBox().getSelectedItem();
            sanPham.loadList(sanPhamBus.searchSanPham(selectedItem, text));
        }
    }
}
