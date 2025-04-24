package org.projects.BUS;

import org.projects.DAO.ThongKePhieuNhapDAO;
import org.projects.entity.ThongKePhieuNhapEntity;

import java.util.HashMap;
import java.util.List;

public class ThongKePhieuNhapBUS {
    private static ThongKePhieuNhapDAO tkpnDAO = new ThongKePhieuNhapDAO();

    public HashMap<String,Integer> getnhacungcapvasoluong() {
        return  tkpnDAO.getnhacungcapvasoluong();
    }

    public HashMap<String,Double> gettonggiatriphieunhap() {
        return tkpnDAO.gettonggiatriphieunhap();
    }

    public List<ThongKePhieuNhapEntity> showlist() {
        return tkpnDAO.getlist();
    }
}
