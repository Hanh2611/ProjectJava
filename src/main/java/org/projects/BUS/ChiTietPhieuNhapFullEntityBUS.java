package org.projects.BUS;

import org.projects.DAO.ChiTietPhieuNhapFullEntityDAO;
import org.projects.entity.ChiTietPhieuNhapFullEntity;

import java.util.List;

public class ChiTietPhieuNhapFullEntityBUS {
    private ChiTietPhieuNhapFullEntityDAO dao;

    public ChiTietPhieuNhapFullEntityBUS() {
        dao = new ChiTietPhieuNhapFullEntityDAO();
    }

    public List<ChiTietPhieuNhapFullEntity> getChiTietByMaPN(int maPN) {
        return dao.showlist(maPN);
    }
}
