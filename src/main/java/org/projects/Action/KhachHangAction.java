package org.projects.Action;

import org.projects.BUS.KhachHangBUS;
import org.projects.BUS.NhanVienBus;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.KhachHang.ShowAddKhachHang;
import org.projects.GUI.DiaLog.KhachHang.ShowDelKhachHang;
import org.projects.GUI.DiaLog.KhachHang.ShowDeltailKhachHang;
import org.projects.GUI.DiaLog.KhachHang.ShowFixKhachHang;
import org.projects.GUI.Panel.KhachHangPack.KhachHang;
import org.projects.entity.KhachHangEntity;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;

public class KhachHangAction implements ActionListener  , MouseListener, ItemListener, KeyListener, DocumentListener {
    private KhachHang kh;
    private ShowAddKhachHang showAddKhachHang;
    private ShowFixKhachHang showFixKhachHang;
    private ShowDeltailKhachHang showDeltailKhachHang;
    private ShowDelKhachHang showDelKhachHang;
    KhachHangBUS bus;
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public KhachHangAction(KhachHang kh) {
        this.kh = kh;
        bus = new KhachHangBUS(kh);
    }
    public KhachHangAction(){}
    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent source = (JComponent) e.getSource();
        String nameButton = e.getActionCommand();
        if(showAddKhachHang != null) {
            if (source.equals(showAddKhachHang.add.getSaveButton())) {
                System.out.println("save");
                showAddKhachHang.add.insertData();
                if(showAddKhachHang.add.getMa().equals("Nhập mã khách hàng")) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhập mã khách hàng" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    showAddKhachHang.add.listAdd.get(0).requestFocusInWindow();
                }else if(!showAddKhachHang.add.getMa().matches("\\d+")){
                    JOptionPane.showMessageDialog(null,"Mã nhân viên chỉ nhận giá trị số nguyên" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    showAddKhachHang.add.listAdd.get(0).requestFocusInWindow();
                }
                else if(showAddKhachHang.add.getTen().equals("Nhập họ và tên")) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhập tên" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    showAddKhachHang.add.listAdd.get(1).requestFocusInWindow();
                }else if(showAddKhachHang.add.getSdt().equals("Nhập số điện thoại")) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhập số điện thoại" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    showAddKhachHang.add.listAdd.get(2).requestFocusInWindow();
                }else if(!showAddKhachHang.add.getSdt().matches("0\\d{9}")){
                    JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    showAddKhachHang.add.listAdd.get(2).requestFocusInWindow();
                }else if(showAddKhachHang.add.getDiachi().equals("Nhập địa chỉ")) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhập địa chỉ" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    showAddKhachHang.add.listAdd.get(3).requestFocusInWindow();
                }else {
                    KhachHangEntity khe = new KhachHangEntity(Integer.parseInt(showAddKhachHang.add.getMa()), showAddKhachHang.add.getTen()
                            , showAddKhachHang.add.getSdt(), showAddKhachHang.add.getDiachi());
                    if (bus.them(khe)) {
                        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công", "thông báo", JOptionPane.INFORMATION_MESSAGE);
                        kh.loadList(bus.getList());
                        showAddKhachHang.close();
                    }else {
                        JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại", "thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (source.equals(showAddKhachHang.add.getCancelButton())) {
                System.out.println("cancel");
                showAddKhachHang.close();
            } else if (source.equals(showAddKhachHang.add.getResetButton())) {
                System.out.println("reset");
                showAddKhachHang.add.resetForm();
            }
        }
        if(showDelKhachHang != null) {
            if (source.equals(showDelKhachHang.del.getCancelButton())) {
//                System.out.println("del cancel");
                showDelKhachHang.close();
            }else if(source.equals(showDelKhachHang.del.getOkButton())){
//                System.out.println("del ok");
                KhachHangEntity khe = kh.getRow();
                if(bus.xoa(khe)){
                    JOptionPane.showMessageDialog(null , "Đã xóa thành công" , "thông báo" ,JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null , "Xóa thất bại" , "thông báo" , JOptionPane.ERROR_MESSAGE);
                }
                showDelKhachHang.close();
            }
        }
        if(showFixKhachHang != null) {
            if (source.equals(showFixKhachHang.fix.getCancelButton())) {
//                System.out.println("fix ok cancel");
                showFixKhachHang.close();
            }else if (source.equals(showFixKhachHang.fix.getUpdateButton())){
//                System.out.println("fix ok update");
                showFixKhachHang.fix.insertData();
                if(showFixKhachHang.fix.getTen().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhập tên khách hàng" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    showFixKhachHang.fix.listAdd.get(1).requestFocusInWindow();
                }else if(showFixKhachHang.fix.getStd().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhập số điện thoại" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    showFixKhachHang.fix.listAdd.get(2).requestFocusInWindow();
                }else if(!showFixKhachHang.fix.getStd().matches("0\\d{9}")){
                    JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    showFixKhachHang.fix.listAdd.get(2).requestFocusInWindow();
                }else if(showFixKhachHang.fix.getDiachi().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhập địa chỉ" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    showFixKhachHang.fix.listAdd.get(3).requestFocusInWindow();
                }else{
                    KhachHangEntity khe = new KhachHangEntity(Integer.parseInt(showFixKhachHang.fix.getMa()), showFixKhachHang.fix.getTen()
                            ,showFixKhachHang.fix.getStd(), showFixKhachHang.fix.getDiachi());
                    if(bus.sua(khe)){
                        JOptionPane.showMessageDialog(null , "Đã sửa thành công" , "thông báo" ,JOptionPane.INFORMATION_MESSAGE);
                        showFixKhachHang.close();
                    }else{
                        JOptionPane.showMessageDialog(null , "Sửa không thành công" , "thông báo" ,JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        JButton refresh = kh.getHeader().getSearch().getSearchButton();
        if(source instanceof JButton && source.equals(refresh)) {
            kh.getHeader().getSearch().getSearchComboBox().setSelectedItem("---");
            kh.getHeader().getSearch().getSearchField().setText("");
            this.kh.searchfunction(kh.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString(),kh.getHeader().getSearch().getSearchField().getText());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        if(kh != null){
            for (String name : kh.getHeader().getHeaderFunc().getHm().keySet()) {
                generalFunction gf = kh.getHeader().getHeaderFunc().getHm().get(name);
                if(c.equals(gf)){
                    if (name == null && name.trim().isEmpty()) return;
//                    System.out.println("ten cua nut la : " + name);
                    if("add".equals(name)){
                        showAddKhachHang = new ShowAddKhachHang();
                        showAddKhachHang.add.getResetButton().addActionListener(this);
                        showAddKhachHang.add.getSaveButton().addActionListener(this);
                        showAddKhachHang.add.getCancelButton().addActionListener(this);
                    }else{
                        KhachHangEntity info = kh.getRow();
                        if(info == null){
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng", "thông báo", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if("update".equals(name)){
                            info = kh.getRow();
                            if(info != null){
                                showFixKhachHang = new ShowFixKhachHang();
                                showFixKhachHang.fix.setInfo(info);
                                System.out.println(info.ma);
                                System.out.println(info.ten);
                                System.out.println(info.sdt);
                                System.out.println(info.diaChi);
                                showFixKhachHang.Show();
                                showFixKhachHang.fix.getUpdateButton().addActionListener(this);
                                showFixKhachHang.fix.getCancelButton().addActionListener(this);
//                                System.out.println("ok");
                            }
                        }else if("detail".equals(name)){
                            info = kh.getRow();
                            showDeltailKhachHang = new ShowDeltailKhachHang();
                            if(info != null){
                                showDeltailKhachHang.detailKhachHangConsole.setInfo(info);
                                showDeltailKhachHang.Show();
//                                System.out.println("ok");
                            }
                        }
                        else if("delete".equals(name)){
                            showDelKhachHang = new ShowDelKhachHang(info);
                            showDelKhachHang.del.getOkButton().addActionListener(this);
                            showDelKhachHang.del.getCancelButton().addActionListener(this);
                        }
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
            String keyword = e.getItem().toString();
            String textField = kh.getHeader().getSearch().getSearchField().getText();
            System.out.println(keyword);
            System.out.println(textField);
            kh.loadList(bus.search(keyword, textField));
        }
    }

    public void doSearch(){
        String key = kh.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        String text = kh.getHeader().getSearch().getSearchField().getText();
        if (!key.equals("---")) {
            kh.loadList(bus.search(key, text));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        doSearch();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        doSearch();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        doSearch();
    }
}
