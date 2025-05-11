package org.projects.Action;

import org.projects.BUS.KhachHangBUS;
import org.projects.BUS.NhanVienBus;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.KhachHang.ShowAddKhachHang;
import org.projects.GUI.DiaLog.KhachHang.ShowDelKhachHang;
import org.projects.GUI.DiaLog.KhachHang.ShowDeltailKhachHang;
import org.projects.GUI.DiaLog.KhachHang.ShowFixKhachHang;
import org.projects.GUI.Panel.KhachHangPack.KhachHang;
import org.projects.GUI.utils.ExportExcel;
import org.projects.GUI.utils.InputValid;
import org.projects.entity.KhachHangEntity;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.util.ArrayList;

public class KhachHangAction extends FocusAdapter implements ActionListener  , MouseListener, ItemListener, KeyListener, DocumentListener {
    private KhachHang kh;
    private ShowAddKhachHang showAddKhachHang;
    private ShowFixKhachHang showFixKhachHang;
    private ShowDeltailKhachHang showDeltailKhachHang;
    private ShowDelKhachHang showDelKhachHang;
    KhachHangBUS bus;
    public JTextField textField;
    public int fieldIndex;
    public ArrayList<JTextField> listAdd;
    public ArrayList<JLabel> errorLabels;

    public KhachHangAction(KhachHang kh) {
        this.kh = kh;
        bus = new KhachHangBUS(kh);
    }
    public KhachHangAction(JTextField textField, int fieldIndex, ArrayList<JTextField> listAdd, ArrayList<JLabel> errorLabels) {
        this.textField = textField;
        this.fieldIndex = fieldIndex;
        this.listAdd = listAdd;
        this.errorLabels = errorLabels;
    }
    public KhachHangAction(){}
    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent source = (JComponent) e.getSource();
        String nameButton = e.getActionCommand();
        String sql = "select ma_khach_hang from khach_hang where ma_khach_hang = ?";
        if(showAddKhachHang != null) {
            if (source.equals(showAddKhachHang.add.getSaveButton())) {
                if(!showAddKhachHang.add.checkValid()){
                    JOptionPane.showMessageDialog(null,"Vui lòng kiểm tra lại thông tin" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                KhachHangEntity khe = new KhachHangEntity(Integer.parseInt(showAddKhachHang.add.getMa()), showAddKhachHang.add.getTen()
                        , showAddKhachHang.add.getSdt(), showAddKhachHang.add.getDiachi() , showAddKhachHang.add.getAvatar());
                if (bus.them(khe)) {
                    JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công", "thông báo", JOptionPane.INFORMATION_MESSAGE);
                    kh.loadList(bus.getList());
                    showAddKhachHang.close();
                }else {
                    JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại", "thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } else if (source.equals(showAddKhachHang.add.getCancelButton())) {
//                System.out.println("cancel");
                showAddKhachHang.close();
            } else if (source.equals(showAddKhachHang.add.getResetButton())) {
//                System.out.println("reset");
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
                    kh.loadList(bus.getList());
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
                if(!showFixKhachHang.fix.checkValid()){
                    JOptionPane.showMessageDialog(null,"Vui lòng kiểm tra lại thông tin" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    KhachHangEntity khe = new KhachHangEntity(Integer.parseInt(showFixKhachHang.fix.getMa()), showFixKhachHang.fix.getTen()
                            ,showFixKhachHang.fix.getStd(), showFixKhachHang.fix.getDiachi() , showFixKhachHang.fix.getAvatar());
                    if(bus.sua(khe)){
                        JOptionPane.showMessageDialog(null , "Đã sửa thành công" , "thông báo" ,JOptionPane.INFORMATION_MESSAGE);
                        showFixKhachHang.close();
                    }else{
                        JOptionPane.showMessageDialog(null , "Sửa không thành công" , "thông báo" ,JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if(kh != null) {
            JButton refresh = kh.getHeader().getSearch().getSearchButton();
            if(source instanceof JButton && source.equals(refresh)) {
                kh.getHeader().getSearch().getSearchComboBox().setSelectedItem("---");
                kh.getHeader().getSearch().getSearchField().setText("");
                this.kh.searchfunction(kh.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString(),kh.getHeader().getSearch().getSearchField().getText());
            }
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
                    }else if("excel".equals(name)){
                        JFileChooser fileChooser = new JFileChooser();
                        int result = fileChooser.showSaveDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            String path = fileChooser.getSelectedFile().getAbsolutePath();
                            if (!path.endsWith(".xlsx")) {
                                path += ".xlsx";
                            }
                            ExportExcel.exportToExcel(kh.getTable(), path); // thay myJTable bằng JTable của bạn
                        }
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
//                                System.out.println(info.ma);
//                                System.out.println(info.ten);
//                                System.out.println(info.sdt);
//                                System.out.println(info.diaChi);
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

    public void checkTen(JTextField textField, int index) {
        if (InputValid.checkRong_addPlace("Nhập họ và tên", textField.getText())) {
            InputValid.showError(index, "Vui lòng nhập tên khách hàng" , errorLabels, listAdd , false);
        }else InputValid.clearError(index , errorLabels , listAdd , false);
    }

    public void checkSDT(JTextField textField, int index) {
        if (InputValid.checkRong_addPlace("Nhập số điện thoại", textField.getText())) {
            InputValid.showError(index, "Vui lòng nhập số điện thoại" , errorLabels, listAdd , false);
        } else if (!InputValid.checkSoDienThoai(textField.getText())) {
            InputValid.showError(index, "Số điện thoại không hợp lệ" , errorLabels , listAdd , false);
        } else {
            InputValid.clearError(index , errorLabels , listAdd, false);
        }
    }
    public void checkDiaChi(JTextField textField, int index) {
        if (InputValid.checkRong_addPlace("Nhập địa chỉ", textField.getText())) {
            InputValid.showError(index, "Vui lòng nhập địa chỉ khách hàng" , errorLabels, listAdd , false);
        }else InputValid.clearError(index , errorLabels , listAdd , false);
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField source = (JTextField) e.getSource();
        int index = listAdd.indexOf(source);
        switch (index) {
            case 1:
                checkTen(source , index);
                break;
            case 2:
                checkSDT(source, index);
                break;
            case 3:
                checkDiaChi(source, index);
                break;
            default:
                if (InputValid.checkRong_addPlace(source.getName(), source.getText())) {
                    InputValid.showError(index, "Vui lòng nhập thông tin", errorLabels, listAdd, false);
                } else {
                    InputValid.clearError(index, errorLabels, listAdd, false);
                }
                break;
        }
    }
}
