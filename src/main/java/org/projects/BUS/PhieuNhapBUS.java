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
                return true;
            }
        }
        return false;
    }

    public static boolean sua(PhieuNhapEntity pn){
        if(phieuNhap != null){
            if(pnDao.sua(pn) > 0){
                phieuNhap.reloadDAO();
                return true;
            }
        }
        return false;
    }

    public static boolean xoa(PhieuNhapEntity pn){

        if(phieuNhap != null){
            if(pnDao.xoa(pn) > 0){
                phieuNhap.reloadDAO();
                return true;
            }
        }
        return false;
    }

    //khong su dung
    public static List<PhieuNhapEntity> search(String keyword, String textField) {
        getList();
        List<PhieuNhapEntity> newlist = new ArrayList<PhieuNhapEntity>();
        textField = textField.toLowerCase();
        switch (keyword) {
            case "---":
                newlist.addAll(listPN);
                break;

            case "mã":
                for (PhieuNhapEntity pn : listPN) {
                    if (String.valueOf(pn.getMaNCC()).contains(textField)) {
                        newlist.add(pn);
                    }
                }
                break;
            case "tên":
                for (PhieuNhapEntity pn : listPN) {
                    if (String.valueOf(pn.getMaNCC()).contains(textField)) {}
                }
                break;
            case "địa chỉ":
                for(PhieuNhapEntity pn : listPN){
                    if(String.valueOf(pn.getMaNCC()).contains(textField)){
                        newlist.add(pn);
                    }
                }
                break;
            default:
                System.out.println("khong tim thay" + keyword + "va" + textField);
                break;
        }
    return newlist;
    }
}