package org.projects.BUS;

import org.projects.DAO.ThongKeSanPhamDAO;
import org.projects.entity.ThongKeSanPhamEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThongKeSanPhamBUS {
    private static ThongKeSanPhamDAO tkspDAO = new ThongKeSanPhamDAO();

    public HashMap<String,Integer> getDanhmucvasoluongsp() {
        HashMap<String,Integer> hm = tkspDAO.getSoluongsanphamtheodanhmuc();
        if(hm.size() > 0) return hm;
        return new HashMap<>();
    }

    public double getSoluongtonsp() {
        return tkspDAO.getSoluongton();
    }

    public List<ThongKeSanPhamEntity> showlist() {
        return tkspDAO.listTable();
    }

    public List<String> getTendanhmuc() {
        return tkspDAO.getDanhmucSanpham();
    }

    public List<ThongKeSanPhamEntity> search(String tendanhmuc) {
        List<ThongKeSanPhamEntity> all = showlist();
        if(tendanhmuc.equalsIgnoreCase("Tất cả")) return all;
        List<ThongKeSanPhamEntity> newlist = new ArrayList<ThongKeSanPhamEntity>();
        for(ThongKeSanPhamEntity tkspE : showlist()) {
            if(tendanhmuc.equalsIgnoreCase(tkspE.getTenDanhMuc())) newlist.add(tkspE);
        }
        return newlist;
    }
}
