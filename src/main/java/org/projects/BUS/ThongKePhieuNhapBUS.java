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

    public HashMap<String,Double> loctheonhieutieuchi(String from, String to,String tenncc,String tennv) {
        return tkpnDAO.loctheonhieutieuchi(from,to,tenncc,tennv);
    }
    public HashMap<String,Integer> loctheonhacungcapvasoluong(String from, String to,String tenncc,String tennv) {
        return tkpnDAO.getNCCVaSoLuongTheoTieuChi(from,to,tenncc,tennv);
    }
    public List<ThongKePhieuNhapEntity> getListtheonhieutieuchi(String from, String to,String tenncc,String tennv) {
        return tkpnDAO.getListTheoTieuChi(from,to,tenncc,tennv);
    }
}
