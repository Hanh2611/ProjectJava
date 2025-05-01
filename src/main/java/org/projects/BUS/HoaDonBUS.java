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
    public static boolean xoa(HoaDonEntity pn) {
        if (hoaDon != null) {
            if (hoaDonDAO.xoa(pn) > 0) {
                hoaDon.reloadDAO(); // cái này có thể dư nếu gọi ngoài rồi
                return true;
            }
        }
        return false; // bổ sung return false
    }

}
