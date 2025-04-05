package org.projects.BUS;

import org.projects.DAO.SanPhamDao;
import org.projects.entity.SanPhamEntity;

import java.util.List;

public class SanPhamBus {

    private final SanPhamDao sanPhamDao;

    public SanPhamBus() {
        this.sanPhamDao = new SanPhamDao();
    }

    public List<SanPhamEntity> getAllSanPham() {
        return sanPhamDao.showlist();
    }

    public void addSanPham() {
        System.out.println("add san pham");
    }
}
