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
import org.projects.entity.NhanVienEntity;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NhanVienAction implements ActionListener  , MouseListener {
    private NhanVien nv;
    private ShowAddNhanVienConsole show_add_nv;
    private ShowDeleteNhanVienConsole show_del_nv;
    private ShowChiTietNhanVienConsole show_detail_nv;
    private ShowFixNhanVienConsole show_fix_nv;
    NhanVienBus bus;
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$";
    public NhanVienAction(NhanVien nv) {
        this.nv = nv;
        bus = new NhanVienBus(nv);
    }
    public NhanVienAction(){}
    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent source = (JComponent) e.getSource();
        String nameButton = e.getActionCommand();
        if(show_add_nv != null) {
            if (source.equals(show_add_nv.add.getSaveButton())) {
                System.out.println("save");
                show_add_nv.add.insertData();
                if(show_add_nv.add.getMa().equals("Nhập mã nhân viên")) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhâp mã nhân viên" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_add_nv.add.listAdd.get(0).requestFocusInWindow();
                }else if(!show_add_nv.add.getMa().matches("\\d+")){
                    JOptionPane.showMessageDialog(null,"Mã nhân viên chỉ nhận giá trị số nguyên" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_add_nv.add.listAdd.get(0).requestFocusInWindow();
                }
                else if(show_add_nv.add.getTen().equals("Nhập họ và tên")) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhâp họ tên nhân viên" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_add_nv.add.listAdd.get(1).requestFocusInWindow();
                }else if(show_add_nv.add.getEmail().equals("Nhập Email")) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhập email" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_add_nv.add.listAdd.get(2).requestFocusInWindow();
                }else if(!show_add_nv.add.getEmail().matches(emailRegex)) {
                    JOptionPane.showMessageDialog(null,"Email không đúng định dạng" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_add_nv.add.listAdd.get(2).requestFocusInWindow();
                }else if(show_add_nv.add.getSdt().equals("Nhập số điện thoại")) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhâp số điện thoại" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_add_nv.add.listAdd.get(3).requestFocusInWindow();
                }else if(!show_add_nv.add.getSdt().matches("0\\d{9}")){
                    JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_add_nv.add.listAdd.get(3).requestFocusInWindow();
                }else if(show_add_nv.add.comboBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn chức vụ" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_add_nv.add.comboBox.requestFocusInWindow();
                }else {
                    NhanVienEntity nve = new NhanVienEntity(Integer.parseInt(show_add_nv.add.getMa()), show_add_nv.add.getTen()
                            , show_add_nv.add.getEmail(), show_add_nv.add.getSdt(), show_add_nv.add.getChuc_vu());
                    if (bus.them(nve)) {
                        JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công", "thông báo", JOptionPane.INFORMATION_MESSAGE);
                        nv.loadList(bus.getList());
                        show_add_nv.close();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại", "thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (source.equals(show_add_nv.add.getCancelButton())) {
                System.out.println("cancel");
                show_add_nv.close();
            } else if (source.equals(show_add_nv.add.getResetButton())) {
                System.out.println("reset");
                show_add_nv.add.resetForm();
            }
        }
        if(show_del_nv != null) {
            if (source.equals(show_del_nv.del.getCancelButton())) {
//                System.out.println("del cancel");
                show_del_nv.close();
            }else if(source.equals(show_del_nv.del.getOkButton())){
//                System.out.println("del ok");
                NhanVienEntity nve = nv.getRow();
                if(bus.xoa(nve)){
                    JOptionPane.showMessageDialog(null , "Đã xóa thành công" , "thông báo" ,JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null , "Xóa thất bại" , "thông báo" , JOptionPane.ERROR_MESSAGE);
                }
                show_del_nv.close();
            }
        }
        if(show_fix_nv != null) {
            if (source.equals(show_fix_nv.fix.getCancelButton())) {
//                System.out.println("fix ok cancel");
                show_fix_nv.close();
            }else if (source.equals(show_fix_nv.fix.getUpdateButton())){
//                System.out.println("fix ok update");
                show_fix_nv.fix.insertData();
                if(show_fix_nv.fix.getTen().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhâp tên nhân viên" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_fix_nv.fix.listAdd.get(1).requestFocusInWindow();
                }else if(show_fix_nv.fix.getEmail().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhâp email" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_fix_nv.fix.listAdd.get(2).requestFocusInWindow();
                }else if(show_fix_nv.fix.getStd().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhâp số điện thoại" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_fix_nv.fix.listAdd.get(3).requestFocusInWindow();
                }else if(!show_fix_nv.fix.getStd().matches("0\\d{9}")){
                    JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_fix_nv.fix.listAdd.get(3).requestFocusInWindow();
                }else if(show_fix_nv.fix.comboBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn chức vụ" ,"thông báo", JOptionPane.ERROR_MESSAGE);
                    show_fix_nv.fix.comboBox.requestFocusInWindow();
                }else{
                    NhanVienEntity nve = new NhanVienEntity(Integer.parseInt(show_fix_nv.fix.getMa()), show_fix_nv.fix.getTen()
                            , show_fix_nv.fix.getEmail(), show_fix_nv.fix.getStd(), show_fix_nv.fix.getChucvu());
                    if(bus.sua(nve)){
                        JOptionPane.showMessageDialog(null , "Đã sửa thành công" , "thông báo" ,JOptionPane.INFORMATION_MESSAGE);
                        show_fix_nv.close();
                    }else{
                        JOptionPane.showMessageDialog(null , "Sửa không thành công" , "thông báo" ,JOptionPane.ERROR_MESSAGE);
                    }
                }
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
                    System.out.println("ten cua nut la : " + name);
                    if("add".equals(name)){
                        show_add_nv = new ShowAddNhanVienConsole();
                        show_add_nv.add.getResetButton().addActionListener(this);
                        show_add_nv.add.getSaveButton().addActionListener(this);
                        show_add_nv.add.getCancelButton().addActionListener(this);
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
//                                System.out.println("ok");
                            }
                        }else if("detail".equals(name)){
                            info = nv.getRow();
                            show_detail_nv = new ShowChiTietNhanVienConsole();
                            if(info != null){
                                show_detail_nv.chiTietUserConsole.setInfo(info);
                                show_detail_nv.Show();
//                                System.out.println("ok");
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
}
