package org.projects.BUS;

import org.projects.DAO.DanhMucSanPhamDAO;
import org.projects.entity.DanhMucSanPhamEntity;

import java.util.List;

public class DanhMucSanPhamBus {

    private final DanhMucSanPhamDAO danhMucSanPhamDAO;

    public DanhMucSanPhamBus() {
        this.danhMucSanPhamDAO = new DanhMucSanPhamDAO();
    }

    public List<DanhMucSanPhamEntity> getAllDanhMucSanPham() {
        return danhMucSanPhamDAO.showlist();
    }

    public int getIdDanhMuc(String tenDanhMuc) {
        return danhMucSanPhamDAO.getIdByName(tenDanhMuc);
    }

    public int createDanhMuc(String danhMuc) {
        if (danhMuc == null || danhMuc.isEmpty()) {
            return -1;
        }
        DanhMucSanPhamEntity danhMucSanPham = new DanhMucSanPhamEntity(danhMuc);
        danhMucSanPham.setTenDanhMuc(danhMuc);
        return danhMucSanPhamDAO.them(danhMucSanPham);
    }
}
