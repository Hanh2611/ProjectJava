package org.projects.BUS;

import org.projects.DAO.ThongKeHoaDonDAO;
import org.projects.entity.ThongkeHoaDonEntity;

import java.util.HashMap;
import java.util.List;

public class ThongKeHoaDonBUS {
    private final ThongKeHoaDonDAO tkhdDAO;
    public ThongKeHoaDonBUS() {
        tkhdDAO = new ThongKeHoaDonDAO();
    }
    public HashMap<String,Integer> getSLHDtheongay(String from,String to,String trangthai) {
        if ((from == null || from.isEmpty()) && (to == null || to.isEmpty()) && trangthai.equalsIgnoreCase("Tất cả")) {
            return tkhdDAO.getALLsoluong();
        }
        return tkhdDAO.getSoluongtheongay(from, to, trangthai);
    }

    public List<ThongkeHoaDonEntity> getList() {
        return tkhdDAO.showtable();
    }

    public List<ThongkeHoaDonEntity> getListtheongayvatrangthai(String from,String to,String trangthai) {
        return tkhdDAO.getHoadontheongayvatrangthai(from,to,trangthai);
    }
}
