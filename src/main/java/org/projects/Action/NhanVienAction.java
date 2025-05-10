package org.projects.Action;

import org.projects.BUS.NhanVienBus;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.Nhanvien.ShowAddNhanVienConsole;
import org.projects.GUI.DiaLog.Nhanvien.ShowChiTietNhanVienConsole;
import org.projects.GUI.DiaLog.Nhanvien.ShowDeleteNhanVienConsole;
import org.projects.GUI.DiaLog.Nhanvien.ShowFixNhanVienConsole;
import org.projects.GUI.Panel.NhanVienPack.AddNhanVienConsole;
import org.projects.GUI.Panel.NhanVienPack.DeleteNhanVienConsole;
import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.GUI.utils.ExportExcel;
import org.projects.GUI.utils.InputValid;
import org.projects.GUI.utils.UIUtils;
import org.projects.entity.NhanVienEntity;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NhanVienAction extends FocusAdapter implements ActionListener, MouseListener, ItemListener, KeyListener, DocumentListener {
    private NhanVien nv;
    private ShowAddNhanVienConsole show_add_nv;
    private ShowDeleteNhanVienConsole show_del_nv;
    private ShowChiTietNhanVienConsole show_detail_nv;
    private ShowFixNhanVienConsole show_fix_nv;
    static NhanVienBus bus;
    String sql = "SELECT ma_nhan_vien FROM nhan_vien WHERE ma_nhan_vien = ?";
    private JTextField textField;
    private int fieldIndex;
    private ArrayList<JTextField> listAdd;
    private ArrayList<JLabel> errorLabels;

    public NhanVienAction(NhanVien nv) {
        this.nv = nv;
        bus = new NhanVienBus(nv);
    }

    public NhanVienAction(JTextField textField, int fieldIndex, ArrayList<JTextField> listAdd, ArrayList<JLabel> errorLabels) {
        this.textField = textField;
        this.fieldIndex = fieldIndex;
        this.listAdd = listAdd;
        this.errorLabels = errorLabels;
    }

    public NhanVienAction(){}
    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent source = (JComponent) e.getSource();
        String nameButton = e.getActionCommand();
        if(show_add_nv != null) {
            if (source.equals(show_add_nv.add.getSaveButton())) {
                show_add_nv.add.insertData();
                if(show_add_nv.add.comboBox.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn chức vụ" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_add_nv.add.comboBox.requestFocusInWindow();
                }else {
                    NhanVienEntity nve = new NhanVienEntity(Integer.parseInt(show_add_nv.add.getMa()), show_add_nv.add.getTen()
                            , show_add_nv.add.getEmail(), show_add_nv.add.getSdt(), show_add_nv.add.getChuc_vu(), show_add_nv.add.getLuong(), show_add_nv.add.getGioitinh(), show_add_nv.add.getAvatar());
                    if (bus.them(nve)) {
                        JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công", "thông báo", JOptionPane.INFORMATION_MESSAGE);
                        nv.loadList(bus.getList());
                        show_add_nv.close();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại", "thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (source.equals(show_add_nv.add.getCancelButton())) {
                show_add_nv.close();
            } else if (source.equals(show_add_nv.add.getResetButton())) {
                show_add_nv.add.resetForm();
            }
        }
        if(show_del_nv != null) {
            if (source.equals(show_del_nv.del.getCancelButton())) {
                show_del_nv.close();
            }else if(source.equals(show_del_nv.del.getOkButton())){
                NhanVienEntity nve = nv.getRow();
                if(bus.xoa(nve)){
                    JOptionPane.showMessageDialog(null , "Đã xóa thành công" , "thông báo" ,JOptionPane.INFORMATION_MESSAGE);
                    nv.loadList(bus.getList());
                }else{
                    JOptionPane.showMessageDialog(null , "Xóa thất bại" , "thông báo" , JOptionPane.ERROR_MESSAGE);
                }
                show_del_nv.close();
            }
        }
        if(show_fix_nv != null) {
            if (source.equals(show_fix_nv.fix.getCancelButton())) {
                show_fix_nv.close();
            }else if (source.equals(show_fix_nv.fix.getUpdateButton())){
                show_fix_nv.fix.insertData();
                if(show_fix_nv.fix.comboBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn chức vụ" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_fix_nv.fix.comboBox.requestFocusInWindow();
                }else{
                    NhanVienEntity nve = new NhanVienEntity(Integer.parseInt(show_fix_nv.fix.getMa()), show_fix_nv.fix.getTen()
                            , show_fix_nv.fix.getEmail(), show_fix_nv.fix.getStd(), show_fix_nv.fix.getChucvu() , show_fix_nv.fix.getLuong(), show_fix_nv.fix.getGioitinh() , show_fix_nv.fix.getAvatar());
                    if(bus.sua(nve)){
                        JOptionPane.showMessageDialog(null , "Đã sửa thành công" , "thông báo" ,JOptionPane.INFORMATION_MESSAGE);
                        nv.loadList(bus.getList());
                        show_fix_nv.close();
                    }else{
                        JOptionPane.showMessageDialog(null , "Sửa không thành công" , "thông báo" ,JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if(nv != null) {
            JButton refresh = nv.getHeader().getSearch().getSearchButton();
            if (source instanceof JButton && source.equals(refresh)) {
                nv.getHeader().getSearch().getSearchComboBox().setSelectedItem("---");
                nv.getHeader().getSearch().getSearchField().setText("");
                this.nv.searchfunction(nv.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString(), nv.getHeader().getSearch().getSearchField().getText());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        if(nv != null){
            for (String name : nv.getHeader().getHeaderFunc().getHm().keySet()) {
                generalFunction gf = nv.getHeader().getHeaderFunc().getHm().get(name);
                if(c.equals(gf)){
                    if (name == null && name.trim().isEmpty()) return;
                    if("add".equals(name)){
                        show_add_nv = new ShowAddNhanVienConsole();
                        show_add_nv.add.getResetButton().addActionListener(this);
                        show_add_nv.add.getSaveButton().addActionListener(this);
                        show_add_nv.add.getCancelButton().addActionListener(this);
                    }else if("excel".equals(name)){
                        JFileChooser fileChooser = new JFileChooser();
                        int result = fileChooser.showSaveDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            String path = fileChooser.getSelectedFile().getAbsolutePath();
                            if (!path.endsWith(".xlsx")) {
                                path += ".xlsx";
                            }
                            ExportExcel.exportToExcel(nv.getTable(), path);
                        }
                    }else{
                        NhanVienEntity info = nv.getRow();
                        if(info == null){
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng", "thông báo", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if("update".equals(name)){
                            info = nv.getRow();
                            show_fix_nv = new ShowFixNhanVienConsole();
                            if(info != null){
                                show_fix_nv.fix.setInfo(info);
                                show_fix_nv.Show();
                                show_fix_nv.fix.getUpdateButton().addActionListener(this);
                                show_fix_nv.fix.getCancelButton().addActionListener(this);
                            }
                        }else if("detail".equals(name)){
                            info = nv.getRow();
                            show_detail_nv = new ShowChiTietNhanVienConsole();
                            if(info != null){
                                show_detail_nv.chiTietUserConsole.setInfo(info);
                                show_detail_nv.Show();
                            }
                        }
                        else if("delete".equals(name)){
                            show_del_nv = new ShowDeleteNhanVienConsole(info);
                            show_del_nv.del.getOkButton().addActionListener(this);
                            show_del_nv.del.getCancelButton().addActionListener(this);
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
            String textField = nv.getHeader().getSearch().getSearchField().getText();
            nv.loadList(bus.search(keyword, textField));
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

    public void doSearch(){
        String key = nv.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        String text = nv.getHeader().getSearch().getSearchField().getText();
        if (!key.equals("---")) {
            nv.loadList(bus.search(key, text));
        }
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


    // xu li focus
    public void checkMaNhanVienALL(JTextField textField, int index) {
        if (InputValid.checkRong_addPlace(textField.getName(), textField.getText())) {
            InputValid.showError(index, "Vui lòng nhập mã nhân viên" , errorLabels , listAdd , false);
        } else if (!InputValid.checkMaNhanVien(textField.getText())) {
            InputValid.showError(index, "Mã nhân viên chỉ nhận giá trị số nguyên",errorLabels , listAdd , false);
        } else if (InputValid.checkSameId(Integer.parseInt(textField.getText()), sql)) {
            InputValid.showError(index, "Mã nhân viên bị trùng" , errorLabels , listAdd ,false);
        } else {
            InputValid.clearError(index , errorLabels , listAdd , false);
        }
    }

    public void checkEmailNhanVienALL(JTextField textField, int index) {
        if (InputValid.checkRong_addPlace(textField.getName(), textField.getText())) {
            InputValid.showError(index, "Vui lòng nhập email" , errorLabels , listAdd , false);
        } else if (!InputValid.checkEmailNhanVien(textField.getText())) {
            InputValid.showError(index, "Email không đúng định dạng" ,errorLabels , listAdd , false);
        } else {
            InputValid.clearError(index , errorLabels , listAdd , false);
        }
    }

    public void checkSDTNhanVienALL(JTextField textField, int index) {
        if (InputValid.checkRong_addPlace(textField.getName(), textField.getText())) {
            InputValid.showError(index, "Vui lòng nhập số điện thoại" , errorLabels, listAdd , false);
        } else if (!InputValid.checkSoDienThoai(textField.getText())) {
            InputValid.showError(index, "Số điện thoại không hợp lệ" , errorLabels , listAdd , false);
        } else {
            InputValid.clearError(index , errorLabels , listAdd, false);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField source = (JTextField) e.getSource();
        int index = listAdd.indexOf(source);
        //System.out.println(source.getText());
        switch (source.getName()) {
            case "Nhập mã nhân viên":
                checkMaNhanVienALL(source, index);
                break;
            case "Nhập Email":
                checkEmailNhanVienALL(source, index);
                break;
            case "Nhập số điện thoại":
                checkSDTNhanVienALL(source, index);
                break;
            default:
                if (InputValid.checkRong_addPlace(source.getName(), source.getText())) {
                    InputValid.showError(index, "Vui lòng " + source.getName().toLowerCase() , errorLabels , listAdd , false);
                } else {
                    InputValid.clearError(index , errorLabels , listAdd , false);
                }
                break;
        }
    }

}
