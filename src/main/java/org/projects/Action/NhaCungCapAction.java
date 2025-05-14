package org.projects.Action;

import org.projects.BUS.NhaCungCapBUS;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.NhaCungCapDialog;
import org.projects.GUI.Panel.NhaCungCap;
import org.projects.GUI.utils.InputValid;
import org.projects.entity.NhaCungCapEntity;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NhaCungCapAction implements ActionListener, MouseListener,ItemListener, DocumentListener {
    private NhaCungCap ncc;
    private NhaCungCapDialog nccDialog;
    private NhaCungCapEntity nccEntity;

    public NhaCungCapAction(NhaCungCap ncc, NhaCungCapDialog nccDialog) {
        this.ncc = ncc;
        this.nccDialog = nccDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent c = (JComponent) e.getSource();
        String namebtn = e.getActionCommand();
        if (ncc != null) {
            if (nccDialog != null && c instanceof JButton) {
                if ("Thêm".equals(namebtn)) {
                    String ten = nccDialog.getTenNCC().getTextField().getText().trim();
                    String sdt = nccDialog.getSodienthoaiNCC().getTextField().getText().trim();
                    String email = nccDialog.getEmailNCC().getTextField().getText().trim();
                    String dc = nccDialog.getDiachiNCC().getTextField().getText().trim();

                    if(!validInput(ten,sdt,email,dc)) {
                        return;
                    }
                    NhaCungCapEntity nccEntity = new NhaCungCapEntity(ten, sdt, email, dc);
                    if (NhaCungCapBUS.them(nccEntity)) {
                        JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thành công", "thông báo", JOptionPane.INFORMATION_MESSAGE);
                        nccDialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thất bại", "thông báo", JOptionPane.ERROR_MESSAGE);
                        nccDialog.requestFocus();
                    }
                } else if ("Cập nhật".equals(namebtn)) {
                    nccEntity = ncc.getRow();
                    if (nccEntity == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng", "thông báo", JOptionPane.ERROR_MESSAGE);
                    }

                    if (ncc != null && nccDialog != null) {
                        nccEntity.setTenNCC(nccDialog.getTenNCC().getTextField().getText().trim());
                        nccEntity.setSoDienThoaiNCC(nccDialog.getSodienthoaiNCC().getTextField().getText().trim());
                        nccEntity.setEmailNCC(nccDialog.getEmailNCC().getTextField().getText().trim());
                        nccEntity.setDiaChiNCC(nccDialog.getDiachiNCC().getTextField().getText().trim());

                        if(!validInput(nccEntity.getTenNCC(),nccEntity.getSoDienThoaiNCC(),nccEntity.getEmailNCC(),nccEntity.getDiaCHiNCC())) {
                            return;
                        }
                        NhaCungCapEntity newnccEntity = new NhaCungCapEntity(nccEntity.getMaNCC(), nccEntity.getTenNCC(), nccEntity.getSoDienThoaiNCC(), nccEntity.getEmailNCC(), nccEntity.getDiaCHiNCC());
                        if (NhaCungCapBUS.sua(newnccEntity)) {
                            JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            nccDialog.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Cập nhật thông tin Thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                            nccDialog.requestFocus();
                        }
                    }
                } else if ("".equals(namebtn)) {
                    //khong biet de ten gi
                } else if ("Thoát".equals(namebtn)) {
                    nccDialog.dispose();
                }
            }

            //nut reset
            JButton refresh = ncc.getHeader().getSearch().getSearchButton();
            if(c instanceof JButton && c.equals(refresh)) {
                ncc.getHeader().getSearch().getSearchComboBox().setSelectedItem("---");
                ncc.getHeader().getSearch().getSearchField().setText("");
                this.ncc.searchfunction(ncc.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString(),ncc.getHeader().getSearch().getSearchField().getText());
            }
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        if (ncc != null) {
            for (String name : ncc.getHeader().getHeaderFunc().getHm().keySet()) {
                generalFunction gf = ncc.getHeader().getHeaderFunc().getHm().get(name);
                if (c.equals(gf)) {
                    if (name == null && name.trim().isEmpty()) return;
                    if ("add".equals(name)) {
                        new NhaCungCapDialog(name, ncc);
                    } else {
                        NhaCungCapEntity nccEntity = ncc.getRow();
                        if (nccEntity == null) {
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng", "thông báo", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if ("update".equals(name) || "detail".equals(name)) {
                                new NhaCungCapDialog(name, ncc);
                            } else if ("delete".equals(name)) {
                                int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa ?", "thông báo", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION) {
                                    if (NhaCungCapBUS.xoa(nccEntity)) {
                                        JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp thành công", "thông báo", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp thất bại", "thông báo", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

        @Override
        public void mouseReleased (MouseEvent e){

        }

        @Override
        public void mouseEntered (MouseEvent e){
            if(e.getSource() instanceof JButton) {
                JButton c = (JButton) e.getSource();
                if(ncc != null && nccDialog != null) {
                    if(c.equals(nccDialog.getChucnangBTN())) {
                        nccDialog.getChucnangBTN().setBackground(Color.decode("#22a6b3"));
                    }
                    if(nccDialog.getThoatBTN().equals(c)) {
                            nccDialog.getThoatBTN().setBackground(Color.decode("#eb4d4b"));
                    }
                }
            }
        }

        @Override
        public void mouseExited (MouseEvent e){
            if(e.getSource() instanceof JButton) {
                JButton c = (JButton) e.getSource();
                if(ncc != null && nccDialog != null) {
                    if(c.equals(nccDialog.getChucnangBTN())) {
                        nccDialog.getChucnangBTN().setBackground(Color.decode("#7ed6df"));
                    }
                    if( nccDialog.getThoatBTN().equals(c)) {
                        nccDialog.getThoatBTN().setBackground(Color.decode("#ff7979"));
                    }
                }
            }
        }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String keyword = e.getItem().toString();
        String textfield = ncc.getHeader().getSearch().getSearchField().getText();
        ncc.loadList(NhaCungCapBUS.search(keyword,textfield));
    }


    @Override
    public void insertUpdate(DocumentEvent e) {
        String keyword = ncc.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        String textfield = ncc.getHeader().getSearch().getSearchField().getText();
        if(!keyword.equals("---")) {
            ncc.loadList(NhaCungCapBUS.search(keyword,textfield));
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String keyword = ncc.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        String textfield = ncc.getHeader().getSearch().getSearchField().getText();
        if(!keyword.equals("---")) {
            ncc.loadList(NhaCungCapBUS.search(keyword,textfield));
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String keyword = ncc.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        String textfield = ncc.getHeader().getSearch().getSearchField().getText();
        if(!keyword.equals("---")) {
            ncc.loadList(NhaCungCapBUS.search(keyword,textfield));
        }
    }

    public boolean validInput(String tenncc,String sdtncc,String emailncc,String diachincc) {
        boolean isComplete = true;

        if(!InputValid.tenHopLe(tenncc)) {
            InputValid.showError(0,"Tên không hợp lệ",(ArrayList<JLabel>) nccDialog.getListErrorLabel(),(ArrayList<JTextField>) nccDialog.getListTextField(),true);
            isComplete = false;
        } else {
            InputValid.clearError(0,(ArrayList<JLabel>) nccDialog.getListErrorLabel(),(ArrayList<JTextField>) nccDialog.getListTextField(),true);
        }

        if(!InputValid.checkSoDienThoai(sdtncc)) {
            InputValid.showError(1,"Số điện thoại không hợp lệ",(ArrayList<JLabel>) nccDialog.getListErrorLabel(),(ArrayList<JTextField>) nccDialog.getListTextField(),true);
            isComplete = false;
        } else {
            InputValid.clearError(1,(ArrayList<JLabel>) nccDialog.getListErrorLabel(),(ArrayList<JTextField>) nccDialog.getListTextField(),true);
        }

        if(!InputValid.checkEmail(emailncc)) {
            InputValid.showError(2,"Email không hợp lệ",(ArrayList<JLabel>) nccDialog.getListErrorLabel(),(ArrayList<JTextField>) nccDialog.getListTextField(),true);
            isComplete = false;
        } else {
            InputValid.clearError(2,(ArrayList<JLabel>) nccDialog.getListErrorLabel(),(ArrayList<JTextField>) nccDialog.getListTextField(),true);
        }

        if(!InputValid.diachiHople(diachincc)) {
            InputValid.showError(3,"Địa chỉ không hợp lệ",(ArrayList<JLabel>) nccDialog.getListErrorLabel(),(ArrayList<JTextField>) nccDialog.getListTextField(),true);
            isComplete = false;
        } else {
            InputValid.clearError(3,(ArrayList<JLabel>) nccDialog.getListErrorLabel(),(ArrayList<JTextField>) nccDialog.getListTextField(),true);
        }

        return isComplete;
    }
}
