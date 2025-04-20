package org.projects.BUS;

import org.projects.DAO.ChiTietHoaDonFullDAO;
import org.projects.entity.ChiTietHoaDonFullEntity;

import java.util.List;

public class ChiTietHoaDonFullBUS {
    private ChiTietHoaDonFullDAO dao;
    public ChiTietHoaDonFullBUS(){
        dao = new ChiTietHoaDonFullDAO();
    }
    public List<ChiTietHoaDonFullEntity> getChiTietHoaDonFull(int maHD){
        return dao.showList(maHD);
    }

}
