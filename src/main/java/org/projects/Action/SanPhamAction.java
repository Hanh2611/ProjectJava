package org.projects.Action;

import org.projects.BUS.DanhMucSanPhamBus;
import org.projects.BUS.SanPhamBus;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.SanPham.AddSanPhamDialog;
import org.projects.GUI.DiaLog.SanPham.DetailSanPhamDialog;
import org.projects.GUI.DiaLog.SanPham.UpdateSanPhamDialog;
import org.projects.GUI.Panel.SanPham;
import org.projects.GUI.utils.HashName;
import org.projects.GUI.utils.Helper;
import org.projects.entity.DanhMucSanPhamEntity;
import org.projects.entity.Enum.QuyCach;
import org.projects.entity.SanPhamEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class SanPhamAction implements ActionListener, MouseListener, ItemListener, KeyListener {

    private final SanPham sanPham;
    private AddSanPhamDialog addSanPhamDialog;
    private UpdateSanPhamDialog updateSanPhamDialog;
    private DetailSanPhamDialog detailSanPhamDialog;
    private final SanPhamBus sanPhamBus;
    private final DanhMucSanPhamBus danhMucSanPhamBus;
    private SanPhamEntity sanPhamEntity;

    public SanPhamAction(SanPham sanPham) {
        this.sanPham = sanPham;
        this.sanPhamBus = new SanPhamBus(sanPham);
        this.danhMucSanPhamBus = new DanhMucSanPhamBus();
    }

    public SanPhamAction(SanPham sanPham, AddSanPhamDialog addSanPhamDialog) {
        this.sanPham = sanPham;
        this.addSanPhamDialog = addSanPhamDialog;
        this.sanPhamBus = new SanPhamBus(sanPham);
        this.danhMucSanPhamBus = new DanhMucSanPhamBus();
    }

    public SanPhamAction(SanPham sanPham, UpdateSanPhamDialog updateSanPhamDialog) {
        this.sanPham = sanPham;
        this.updateSanPhamDialog = updateSanPhamDialog;
        this.sanPhamBus = new SanPhamBus(sanPham);
        this.danhMucSanPhamBus = new DanhMucSanPhamBus();
    }

    public SanPhamAction(SanPham sanPham, DetailSanPhamDialog detailSanPhamDialog) {
        this.sanPham = sanPham;
        this.detailSanPhamDialog = detailSanPhamDialog;
        this.sanPhamBus = new SanPhamBus(sanPham);
        this.danhMucSanPhamBus = new DanhMucSanPhamBus();
    }

    private boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent c = (JComponent) e.getSource();
        String namebtn = e.getActionCommand();
        if(sanPham != null) {
            if((addSanPhamDialog != null || updateSanPhamDialog != null) && c instanceof JButton){
                if("Thêm".equals(namebtn)) {
                    if(addSanPhamDialog.getSelectedFile() == null) {
                        JOptionPane.showMessageDialog(addSanPhamDialog, "Vui lòng chọn hình ảnh", "thông báo", JOptionPane.ERROR_MESSAGE);
                    } else if(addSanPhamDialog.getTenSanPhamField().getTextField().getText().trim().equals("")){
                        JOptionPane.showMessageDialog(addSanPhamDialog, "Vui lòng nhập tên sản phẩm", "thông báo", JOptionPane.ERROR_MESSAGE);
                    } else if(addSanPhamDialog.getGiaBanField().getTextField().getText().trim().equals("")){
                        JOptionPane.showMessageDialog(addSanPhamDialog, "Vui lòng nhập giá sản phẩm", "thông báo", JOptionPane.ERROR_MESSAGE);
                    } else if(addSanPhamDialog.getDonViField().getTextField().getText().trim().equals("")){
                        JOptionPane.showMessageDialog(addSanPhamDialog, "Vui lòng nhập đơn vị sản phẩm", "thông báo", JOptionPane.ERROR_MESSAGE);
                    } else if(addSanPhamDialog.getQuyCachField().getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(addSanPhamDialog, "Vui lòng chọn quy cách sản phẩm", "thông báo", JOptionPane.ERROR_MESSAGE);
                    } else if(addSanPhamDialog.getPhanLoaiField().getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(addSanPhamDialog, "Vui lòng chọn phân loại sản phẩm", "thông báo", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String ten = addSanPhamDialog.getTenSanPhamField().getTextField().getText().trim();
                        String phanLoai = addSanPhamDialog.getPhanLoaiField().getSelectedItem().toString();
                        String donVi = addSanPhamDialog.getDonViField().getTextField().getText().trim();
                        String gia = addSanPhamDialog.getGiaBanField().getTextField().getText().trim();
                        String quyCach = addSanPhamDialog.getQuyCachField().getSelectedItem().toString();
                        File hinhAnh = addSanPhamDialog.getSelectedFile();
                        String newFileName = HashName.convertToSlug(ten) + HashName.getFileExtension(hinhAnh.getName());
                        File destinationFile = new File(Helper.imageBasePath, newFileName);

                        if (!isDouble(gia)) {
                            JOptionPane.showMessageDialog(addSanPhamDialog, "Giá không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        try {
                            new File(Helper.imageBasePath).mkdirs();
                            Files.copy(hinhAnh.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(addSanPhamDialog, "Error saving the image.");
                        }

                        int idDanhMuc = danhMucSanPhamBus.getIdDanhMuc(phanLoai);
                        DanhMucSanPhamEntity danhMucSanPhamEntity = new DanhMucSanPhamEntity(idDanhMuc, phanLoai);
                        this.sanPhamEntity = new SanPhamEntity(ten, danhMucSanPhamEntity, donVi, Double.parseDouble(gia), QuyCach.fromValue(quyCach), newFileName);

                        if(sanPhamBus.addSanPham(sanPhamEntity)) {
                            JOptionPane.showMessageDialog(addSanPhamDialog, "Thêm sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            addSanPhamDialog.dispose();
                        } else {
                            JOptionPane.showMessageDialog(addSanPhamDialog, "Thêm sản phẩm thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    return;
                }
                if ("Lưu".equals(namebtn)) {
                    int choice = JOptionPane.showConfirmDialog(updateSanPhamDialog, "Bạn có muốn lưu thay đổi không?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        if(updateSanPhamDialog.getSelectedFile() == null) {
                            JOptionPane.showMessageDialog(updateSanPhamDialog, "Vui lòng chọn hình ảnh", "thông báo", JOptionPane.ERROR_MESSAGE);
                        } else if(updateSanPhamDialog.getTenSanPhamField().getTextField().getText().trim().equals("")){
                            JOptionPane.showMessageDialog(updateSanPhamDialog, "Vui lòng nhập tên sản phẩm", "thông báo", JOptionPane.ERROR_MESSAGE);
                        } else if(updateSanPhamDialog.getGiaBanField().getTextField().getText().trim().equals("")){
                            JOptionPane.showMessageDialog(updateSanPhamDialog, "Vui lòng nhập giá sản phẩm", "thông báo", JOptionPane.ERROR_MESSAGE);
                        } else if(updateSanPhamDialog.getDonViField().getTextField().getText().trim().equals("")){
                            JOptionPane.showMessageDialog(updateSanPhamDialog, "Vui lòng nhập đơn vị sản phẩm", "thông báo", JOptionPane.ERROR_MESSAGE);
                        } else if(updateSanPhamDialog.getQuyCachField().getSelectedItem() == null) {
                            JOptionPane.showMessageDialog(updateSanPhamDialog, "Vui lòng chọn quy cách sản phẩm", "thông báo", JOptionPane.ERROR_MESSAGE);
                        } else if(updateSanPhamDialog.getPhanLoaiField().getSelectedItem() == null) {
                            JOptionPane.showMessageDialog(updateSanPhamDialog, "Vui lòng chọn phân loại sản phẩm", "thông báo", JOptionPane.ERROR_MESSAGE);
                        } else {
                            int id = updateSanPhamDialog.getSanPhamEntity().getId();
                            String ten = updateSanPhamDialog.getTenSanPhamField().getTextField().getText().trim();
                            String phanLoai = updateSanPhamDialog.getPhanLoaiField().getSelectedItem().toString();
                            String donVi = updateSanPhamDialog.getDonViField().getTextField().getText().trim();
                            String gia = updateSanPhamDialog.getGiaBanField().getTextField().getText().trim();
                            String soLuongTon = updateSanPhamDialog.getSoLuongTonField().getTextField().getText().trim();
                            String quyCach = updateSanPhamDialog.getQuyCachField().getSelectedItem().toString();
                            boolean trangThai = updateSanPhamDialog.getIsAvailable().isSelected();
                            File hinhAnh = updateSanPhamDialog.getSelectedFile();
                            String oldFileName = HashName.convertToSlug(updateSanPhamDialog.getSanPhamEntity().getTenSanPham()) + HashName.getFileExtension(updateSanPhamDialog.getSanPhamEntity().getHinhAnh());
                            String newFileName = HashName.convertToSlug(ten) + HashName.getFileExtension(hinhAnh.getName());
                            File destinationFile = new File(Helper.getProductImagePath(newFileName));

                            if (!isDouble(soLuongTon)) {
                                JOptionPane.showMessageDialog(updateSanPhamDialog, "Số lượng tồn không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            if (!isDouble(gia)) {
                                JOptionPane.showMessageDialog(updateSanPhamDialog, "Giá không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            try {
                                new File(Helper.imageBasePath).mkdirs();
                                Files.copy(hinhAnh.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                                if (!ten.equals(updateSanPhamDialog.getSanPhamEntity().getTenSanPham())) {
                                    Files.delete(new File(Helper.getProductImagePath(oldFileName)).toPath());
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error saving the image.");
                            }

                            int idDanhMuc = danhMucSanPhamBus.getIdDanhMuc(phanLoai);
                            DanhMucSanPhamEntity danhMucSanPhamEntity = new DanhMucSanPhamEntity(idDanhMuc, phanLoai);
                            this.sanPhamEntity = new SanPhamEntity(id, ten, danhMucSanPhamEntity, donVi, Double.parseDouble(gia), Double.parseDouble(soLuongTon), QuyCach.fromValue(quyCach), newFileName, trangThai);

                            if (sanPhamBus.updateSanPham(sanPhamEntity)) {
                                JOptionPane.showMessageDialog(updateSanPhamDialog, "Cập nhật sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                updateSanPhamDialog.dispose();
                            } else {
                                JOptionPane.showMessageDialog(updateSanPhamDialog, "Cập nhật sản phẩm thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(updateSanPhamDialog, "Đã hoàn tác việc cập nhật", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        updateSanPhamDialog.dispose();
                    }
                    return;
                }
            }
        }
        JButton refresh = sanPham.getHeader().getSearch().getSearchButton();
        if(c instanceof JButton && c.equals(refresh)) {
            sanPham.getHeader().getSearch().getSearchField().setText("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        if (sanPham != null) {
            for (String name : sanPham.getHeader().getHeaderFunc().getHm().keySet()) {
                generalFunction gf = sanPham.getHeader().getHeaderFunc().getHm().get(name);
                if (c.equals(gf)) {
                    if (name == null && name.trim().isEmpty()) return;
                    if ("add".equals(name)) {
                        new AddSanPhamDialog(sanPham);
                        return;
                    }
                    SanPhamEntity sanPhamEntity = sanPham.getSelectedRow();
                    if (sanPhamEntity == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    switch (name) {
                        case "update" -> {
                            new UpdateSanPhamDialog(sanPham, sanPhamEntity);
                            return;
                        }
                        case "detail" -> {
                            new DetailSanPhamDialog(sanPham, sanPhamEntity);
                            return;
                        }
                        case "delete" -> {
                            int choice = JOptionPane.showConfirmDialog(updateSanPhamDialog, "Bạn có chắc muốn xóa sản phẩm này?", "Cảnh báo", JOptionPane.YES_NO_OPTION);
                            //TODO: Kiểm tra nếu sản phẩm đã có trong 1 hóa đơn bất kì thì không thể xóa
                            if(choice == JOptionPane.YES_OPTION){
                                if(sanPhamBus.deleteSanPham(sanPhamEntity)){
                                    try {
                                        Files.delete(new File(Helper.getProductImagePath(sanPhamEntity.getHinhAnh())).toPath());
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                    JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Xóa sản phẩm thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            return;
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
        generalFunction c = (generalFunction) e.getSource();
        c.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        c.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
    public void keyTyped(KeyEvent e) {
        if (e.getSource() instanceof JTextField textField) {
            String text = textField.getText();
            System.out.println(text);
            String selectedItem = (String) sanPham.getHeader().getSearch().getSearchComboBox().getSelectedItem();
            sanPham.loadList(sanPhamBus.searchSanPham(selectedItem, text));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
