package org.projects.BUS;

import org.projects.DAO.NhanVienDao;
import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.entity.NhaCungCapEntity;
import org.projects.entity.NhanVienEntity;

import java.util.ArrayList;
import java.util.List;

public class NhanVienBus {
    private NhanVien nv;
    private NhanVienDao nvDao = new NhanVienDao();
    private List<NhanVienEntity> list = new ArrayList<>();
    public NhanVienBus(NhanVien nv) {
        this.nv = nv;
    }

    public List<NhanVienEntity> getList() {
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
}
