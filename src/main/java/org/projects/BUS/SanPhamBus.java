package org.projects.BUS;

import org.projects.DAO.DanhMucSanPhamDAO;
import org.projects.DAO.SanPhamDAO;
import org.projects.GUI.Panel.SanPham;
import org.projects.entity.SanPhamEntity;

import java.util.ArrayList;
import java.util.List;

public class SanPhamBus {

    private SanPham sanPham;
    private final SanPhamDAO sanPhamDao;

    public SanPhamBus() {
        this.sanPhamDao = new SanPhamDAO();
    }

    public SanPhamBus(SanPham sanPham) {
        this.sanPham = sanPham;
        this.sanPhamDao = new SanPhamDAO();
    }

    public List<SanPhamEntity> getAllSanPham() {
        return sanPhamDao.showlist();
    }

    public List<SanPhamEntity> getAllAvailableSanPham() {
        return sanPhamDao.showlistAvailable();
    }

    public SanPhamEntity getSanPhamById(int id) {
        return sanPhamDao.findById(id);
    }

    public List<SanPhamEntity> searchSanPham(String keyword, String value) {
        List<SanPhamEntity> listSanPham = new ArrayList<>();
        switch (keyword) {
            case "Mã" -> {
                for (SanPhamEntity sanPhamEntity : sanPhamDao.showlist()) {
                    if (String.valueOf(sanPhamEntity.getId()).contains(value)) {
                        listSanPham.add(sanPhamEntity);
                    }
                }
            }
            case "Phân loại" -> {
                for (SanPhamEntity sanPhamEntity : sanPhamDao.showlist()) {
                    if (sanPhamEntity.getPhanLoai().getTenDanhMuc().equalsIgnoreCase(value)) {
                        listSanPham.add(sanPhamEntity);
                    }
                }
            }
            case "Tên" -> {
                for (SanPhamEntity sanPhamEntity : sanPhamDao.showlist()) {
                    if (sanPhamEntity.getTenSanPham().toLowerCase().contains(value.toLowerCase())) {
                        listSanPham.add(sanPhamEntity);
                    }
                }
            }
        }
        return listSanPham;
    }

    public List<SanPhamEntity> getSanPhamByDanhMuc(int id) {
        return sanPhamDao.getSanPhamByDanhMuc(id);
    }

    public boolean addSanPham(SanPhamEntity sanPhamEntity) {
        if(sanPhamDao.them(sanPhamEntity) > 0) {
            sanPham.reloadDAO();
            return true;
        } else {
            return false;
        }
    }

    public boolean updateSanPham(SanPhamEntity sanPhamEntity) {
        if(sanPhamDao.sua(sanPhamEntity) > 0) {
            sanPham.reloadDAO();
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteSanPham(SanPhamEntity sanPhamEntity) {
        if(sanPhamDao.xoa(sanPhamEntity) > 0) {
            sanPham.reloadDAO();
            return true;
        } else {
            return false;
        }
    }
}
