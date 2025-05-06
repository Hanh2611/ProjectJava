package org.projects.BUS;

import org.projects.DAO.PhieuNhapDAO;
import org.projects.GUI.Panel.PhieuNhap;
import org.projects.entity.PhieuNhapEntity;

import java.util.ArrayList;
import java.util.List;

public class PhieuNhapBUS {
    private static PhieuNhap phieuNhap;
    private static PhieuNhapDAO pnDao = new PhieuNhapDAO();
    private static List<PhieuNhapEntity> listPN;

    public PhieuNhapBUS(PhieuNhap phieuNhap) {
        this.phieuNhap = phieuNhap;
    }

    public static List<PhieuNhapEntity> getList(){
        listPN = pnDao.showlist();
        return listPN;
    }

    public static boolean them(PhieuNhapEntity pn){
        if(phieuNhap != null){
            if(pnDao.them(pn) > 0){
                phieuNhap.reloadDAO();
                listPN = getList();  // Cập nhật lại listPN

                return true;
            }
        }
        return false;
    }

    public static boolean sua(PhieuNhapEntity pn){
        if(phieuNhap != null){
            if(pnDao.sua(pn) > 0){
                phieuNhap.reloadDAO();
                listPN = getList();  // Cập nhật lại listPN

                return true;
            }
        }
        return false;
    }

    public static boolean xoa(PhieuNhapEntity pn){

        if(phieuNhap != null){
            if(pnDao.xoa(pn) > 0){
                phieuNhap.reloadDAO();
                listPN = getList();  // Cập nhật lại listPN
                return true;
            }
        }
        return false;
    }

    //khong su dung

    public static List<PhieuNhapEntity> search(String keyword, String textField) {
        listPN = getList();  // Cập nhật lại listPN
        List<PhieuNhapEntity> newlist = new ArrayList<>();
        textField = textField.toLowerCase();
        switch (keyword.toLowerCase()) {
            case "tất cả":
                newlist.addAll(listPN);
                break;
            case "mã":
                for (PhieuNhapEntity phieuNhapEntity : listPN) {
                    if (String.valueOf(phieuNhapEntity.getMaPN()).contains(textField)) {
                        newlist.add(phieuNhapEntity);
                    }
                }
                break;
            case "tên nhân viên":
                for (PhieuNhapEntity phieuNhapEntity : listPN) {
                    if (String.valueOf(phieuNhapEntity.getTenNV()).toLowerCase().contains(textField)) {
                        newlist.add(phieuNhapEntity);
                    }
                }
                break;
            case "tên ncc":
                for (PhieuNhapEntity phieuNhapEntity : listPN) {
                    if (String.valueOf(phieuNhapEntity.getTenNCC()).toLowerCase().contains(textField)) {
                        newlist.add(phieuNhapEntity);
                    }
                }
                break;
            default:
                System.out.println("không tìm thấy " + keyword + " và " + textField);
                break;
        }
        System.out.println("danh sach ban dau: " + listPN.size());
        System.out.println("danh sach sau khi tim kiem theo " + keyword + " va " + textField + ": " + newlist.size());
        return newlist;
    }

}