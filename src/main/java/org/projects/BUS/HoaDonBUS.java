package org.projects.BUS;

import org.projects.DAO.HoaDonDAO;
import org.projects.GUI.Panel.HoaDon;
import org.projects.entity.HoaDonEntity;

import java.util.List;

public class HoaDonBUS {
    private static HoaDon hoaDon;
    private static HoaDonDAO hoaDonDAO = new HoaDonDAO();
    private static List<HoaDonEntity> listHoaDon;
    public HoaDonBUS(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }
    public static List<HoaDonEntity> getList(){
        listHoaDon = hoaDonDAO.showlist();
        return listHoaDon;
    }

}
