package org.projects.BUS;

import org.projects.DAO.SanPhamDao;
import org.projects.GUI.Panel.SanPham;
import org.projects.entity.SanPhamEntity;

import java.util.ArrayList;
import java.util.List;

public class SanPhamBus {

    private final SanPham sanPham;
    private final SanPhamDao sanPhamDao;

    public SanPhamBus(SanPham sanPham) {
        this.sanPham = sanPham;
        this.sanPhamDao = new SanPhamDao();
    }

    public List<SanPhamEntity> getAllSanPham() {
        return sanPhamDao.showlist();
    }

    public SanPhamEntity getSanPhamById(int id) {
        return sanPhamDao.findById(id);
    }

    public List<SanPhamEntity> searchSanPham(String keyword, String value) {
        List<SanPhamEntity> listSanPham = new ArrayList<>();
        if (keyword.equals("---")) {
            listSanPham = sanPhamDao.showlist();
        } else {
            listSanPham = sanPhamDao.findBy(keyword, value);
        }
        return listSanPham;
    }

    public void addSanPham() {
        System.out.println("add san pham");
    }
}
