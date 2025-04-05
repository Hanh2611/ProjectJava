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
}
