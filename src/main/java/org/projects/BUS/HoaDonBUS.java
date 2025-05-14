package org.projects.BUS;

import org.projects.DAO.HoaDonDAO;
import org.projects.GUI.Panel.HoaDon;
import org.projects.entity.HoaDonEntity;

import java.util.ArrayList;
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

    public static List<HoaDonEntity> search(String keyword, String textField) {
        listHoaDon = getList();  // Cập nhật lại listPN
        List<HoaDonEntity> newlist = new ArrayList<>();
        textField = textField.toLowerCase();
        switch (keyword.toLowerCase()) {
            case "tất cả":
                newlist.addAll(listHoaDon);
                break;
            case "mã":
                for (HoaDonEntity hoaDonEntity : listHoaDon) {
                    if (String.valueOf(hoaDonEntity.getMaHoaDon()).contains(textField)) {
                        newlist.add(hoaDonEntity);
                    }
                }
                break;
            case "tên nhân viên":
                for (HoaDonEntity hoaDonEntity : listHoaDon) {
                    if (String.valueOf(hoaDonEntity.getTenNV()).toLowerCase().contains(textField)) {
                        newlist.add(hoaDonEntity);
                    }
                }
                break;
            case "tên khách hàng":
                for (HoaDonEntity hoaDonEntity : listHoaDon) {
                    if (String.valueOf(hoaDonEntity.getTenKh()).toLowerCase().contains(textField)) {
                        newlist.add(hoaDonEntity);
                    }
                }
                break;
            default:
                System.out.println("không tìm thấy " + keyword + " và " + textField);
                break;
        }
        System.out.println("danh sach ban dau: " + listHoaDon.size());
        System.out.println("danh sach sau khi tim kiem theo " + keyword + " va " + textField + ": " + newlist.size());
        return newlist;
    }

    public static boolean isExistedInHoaDon(int spId){
        return hoaDonDAO.isExistedInHoaDon(spId);
    }

    public static void payment(HoaDonEntity hoaDon) {
        new HoaDonDAO().updateState(hoaDon.getMaHoaDon());
    }
}
