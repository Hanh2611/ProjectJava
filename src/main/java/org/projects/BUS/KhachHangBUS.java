package org.projects.BUS;

import org.projects.DAO.KhachHangDAO;
import org.projects.DAO.NhanVienDao;
import org.projects.GUI.Panel.KhachHangPack.KhachHang;
import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.entity.KhachHangEntity;
import org.projects.entity.NhaCungCapEntity;
import org.projects.entity.NhanVienEntity;

import java.util.ArrayList;
import java.util.List;

public class KhachHangBUS {
    private KhachHang khachHang;
    private static KhachHangDAO khDao = new KhachHangDAO();
    private static List<KhachHangEntity> list = new ArrayList<>();
    public KhachHangBUS(KhachHang kh) {
        this.khachHang = kh;
    }

    public static List<KhachHangEntity> getList() {
        list = khDao.showlist();
        return list;
    }

    public boolean them(KhachHangEntity khachHangEntity) {
        if (khachHang != null) {
            if (khDao.them(khachHangEntity) > 0) {
                khachHang.reloadDAO();
                return true;
            }
        }
        return false;
    }

    public boolean sua(KhachHangEntity khachHangEntity) {
        if(khachHang != null) {
            if(khDao.sua(khachHangEntity) > 0) {
                khachHang.reloadDAO();
                return true;
            }
        }
        return false;
    }

    public boolean xoa(KhachHangEntity khachHangEntity) {
        if(khachHang != null) {
            if(khDao.xoa(khachHangEntity) > 0) {
                khachHang.reloadDAO();
                return true;
            }
        }
        return false;
    }

    public static List<KhachHangEntity> search(String key, String text) {
        getList();
        List<KhachHangEntity> newList = new ArrayList<>();
        text = text.toLowerCase();
        if(key.equals("---")){
            newList.addAll(list);
        }else if(key.equals("mã")){
            for(KhachHangEntity khe : list){
                if(String.valueOf(khe.getMa()).toLowerCase().contains(text)){
                    newList.add(khe);
                }
            }
        }else if(key.equals("tên")){
            for(KhachHangEntity khe : list){
                if(khe.getTen().toLowerCase().contains(text)){
                    newList.add(khe);
                }
            }
        }else if(key.equals("địa chỉ")){
            for(KhachHangEntity khe : list){
                if(khe.getDiaChi().toLowerCase().contains(text)){
                    newList.add(khe);
                }
            }
        }
        return newList;
    }
}
