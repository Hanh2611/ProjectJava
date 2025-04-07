package org.projects.BUS;

import org.projects.DAO.CapQuyenDAO;
import org.projects.DAO.DanhMucQuanLyDAO;
import org.projects.DAO.NhomQuyenDAO;
import org.projects.DAO.QuyenNguoiDungDAO;
import org.projects.entity.CapQuyen;
import org.projects.entity.DanhMucQuanLy;
import org.projects.entity.NhomQuyen;
import org.projects.entity.QuyenNguoiDung;

import java.util.*;

public class PhanQuyenBUS {
    public PhanQuyenBUS() {
    }
    public static List<NhomQuyen> getNhomQuyen() {
        return new NhomQuyenDAO().showlist();
    }
    public static List<DanhMucQuanLy> getDanhMucQuanLy() {return new DanhMucQuanLyDAO().showlist();}
    public static int addNhomQuyen(String nameNhomQuyen) {
        NhomQuyen q = new NhomQuyen(nameNhomQuyen);
        int maNhomQuyen = new NhomQuyenDAO().them(q);
        return maNhomQuyen;
    }

    public static void addCapQuyen(HashMap<String, List<Boolean>> danhMucData, int maNhomQuyen) {
        String[] hanhDong = {"them", "sua", "xoa", "xem", "excel"};
        for (String key : danhMucData.keySet()) {
            int maDanhMuc = DanhMucQuanLyDAO.getMaDanhMuc(key);
            int pos = 0;
            for (Boolean value : danhMucData.get(key)) {
                CapQuyen capQuyen = new CapQuyen();
                if (value == true) {
                    capQuyen.setHanh_dong(hanhDong[pos]);
                    capQuyen.setMa_nhom_quyen(maNhomQuyen);
                    capQuyen.setMa_danh_muc_quan_ly(maDanhMuc);
                    new CapQuyenDAO().them(capQuyen);
                }
                pos++;
            }
        }
    }

    public static void deleteCapQuyen(int maNhomQuyen) {
        CapQuyen capQuyen = new CapQuyen();
        capQuyen.setMa_nhom_quyen(maNhomQuyen);
        new CapQuyenDAO().xoa(capQuyen);
    }

    public static void deleteNhomQuyen(int maNhomQuyen) {
        NhomQuyen nhomQuyen = new NhomQuyen();
        QuyenNguoiDung quyenNguoiDung = new QuyenNguoiDung();
        quyenNguoiDung.setMa_nhom_quyen(maNhomQuyen);
        nhomQuyen.setMaNhomQuyen(maNhomQuyen);
        new QuyenNguoiDungDAO().xoa(quyenNguoiDung);
        deleteCapQuyen(maNhomQuyen);
        new NhomQuyenDAO().xoa(nhomQuyen);
    }

    public static void updateNhomQuyen(HashMap<String, List<Boolean>> danhMucData, int maNhomQuyen) {
        deleteCapQuyen(maNhomQuyen);
        addCapQuyen(danhMucData, maNhomQuyen);
    }

    public static boolean checkCapQuyen(CapQuyen capQuyen) {
        return new CapQuyenDAO().check(capQuyen);
    }

    public static void updateNameNhomQuyen(int maNhomQuyen, String nameNhomQuyen) {
        new NhomQuyenDAO().sua(new NhomQuyen(maNhomQuyen, nameNhomQuyen));
    }
}
