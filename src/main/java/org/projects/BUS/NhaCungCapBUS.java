package org.projects.BUS;

import org.projects.DAO.NhaCungCapDAO;
import org.projects.GUI.Panel.NhaCungCap;
import org.projects.entity.NhaCungCapEntity;

import java.util.ArrayList;
import java.util.List;

public class NhaCungCapBUS {
    private static NhaCungCap ncc;
    private static NhaCungCapDAO nccDao = new NhaCungCapDAO();
    private static List<NhaCungCapEntity> listncc = new ArrayList<>();

    public NhaCungCapBUS(NhaCungCap ncc) {
        this.ncc = ncc;
    }

    public static List<NhaCungCapEntity> getList() {
        listncc = nccDao.showlist();
        return listncc;
    }

    public static boolean them(NhaCungCapEntity nccEntity) {
        if(ncc != null) {
            if(nccDao.them(nccEntity) > 0) {
                ncc.reloadDAO();
                return true;
            }
        }
        return false;
    }

    public static boolean sua(NhaCungCapEntity nccEntity) {
        if(ncc != null) {
            if(nccDao.sua(nccEntity) > 0) {
                ncc.reloadDAO();
                return true;
            }
        }
        return false;
    }

    public static boolean xoa(NhaCungCapEntity nccEntity) {
        if(ncc != null) {
            if(nccDao.xoa(nccEntity) > 0) {
                ncc.reloadDAO();
                return true;
            }
        }
        return false;
    }

    public static List<NhaCungCapEntity> search(String keyword,String textField) {
        getList();
        List<NhaCungCapEntity> newlist = new ArrayList<>();
        textField = textField.toLowerCase();
        switch (keyword) {
            case "---":
                newlist.addAll(listncc);
                break;
            case "mã":
                for(NhaCungCapEntity nccEntity : listncc) {
                    if(String.valueOf(nccEntity.getMaNCC()).contains(textField)) {
                        newlist.add(nccEntity);
                    }
                }
                break;
            case "tên":
                for(NhaCungCapEntity nccEntity : listncc) {
                    if(String.valueOf(nccEntity.getTenNCC()).toLowerCase().contains(textField)) {
                        newlist.add(nccEntity);
                    }
                }
                break;
            case "địa chỉ":
                for(NhaCungCapEntity nccEntity : listncc) {
                    if(String.valueOf(nccEntity.getTenNCC()).toLowerCase().contains(textField)) {
                        newlist.add(nccEntity);
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
