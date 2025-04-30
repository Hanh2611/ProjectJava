package org.projects.BUS;

import org.projects.DAO.NhanVienDao;
import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.entity.NhaCungCapEntity;
import org.projects.entity.NhanVienEntity;

import java.util.ArrayList;
import java.util.List;

public class NhanVienBus {
    private NhanVien nv;
    private static NhanVienDao nvDao = new NhanVienDao();
    private static List<NhanVienEntity> list = new ArrayList<>();
    public NhanVienBus(NhanVien nv) {
        this.nv = nv;
    }

    public static List<NhanVienEntity> getList() {
        list = nvDao.showlist();
        return list;
    }

    public boolean them(NhanVienEntity nhanVienEntity) {
        if (nv != null) {
            if (nvDao.them(nhanVienEntity) > 0) {
                nv.reloadDAO();
                return true;
            }
        }
        System.out.println(nv);
        return false;
    }

    public boolean sua(NhanVienEntity nhanVienEntity) {
        if(nv != null) {
            if(nvDao.sua(nhanVienEntity) > 0) {
                nv.reloadDAO();
                return true;
            }
        }
        return false;
    }

    public boolean xoa(NhanVienEntity nhanVienEntity) {
        if(nv != null) {
            if(nvDao.xoa(nhanVienEntity) > 0) {
                nv.reloadDAO();
                return true;
            }
        }
        return false;
    }

    public static List<NhanVienEntity> search(String key , String text){
        getList();
        List<NhanVienEntity> newList = new ArrayList<>();
        text = text.toLowerCase();
        if(key.equals("---")){
            newList.addAll(list);
        }else if(key.equals("mã")){
            for(NhanVienEntity nve : list){
                if(String.valueOf(nve.getMaNhanVien()).toLowerCase().contains(text)){
                    newList.add(nve);
                }
            }
        }else if(key.equals("tên")){
            for(NhanVienEntity nve : list){
                if(nve.getTenNhanVien().toLowerCase().contains(text)){
                    newList.add(nve);
                    System.out.println(nve.getTenNhanVien());
                }
            }
        }else if(key.equals("chức vụ")){
            for(NhanVienEntity nve : list){
                if(nve.getChucvu().toLowerCase().contains(text)){
                    newList.add(nve);
                }
            }
        }
        return newList;
    }
}
