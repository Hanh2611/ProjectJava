package org.projects.BUS;

import org.projects.DAO.TaiKhoanDAO;
import org.projects.entity.TaiKhoan;

import java.util.List;

public class TaiKhoanBUS {
    public static List<TaiKhoan> getListTaiKhoan() {
        return new TaiKhoanDAO().showlist();
    }

    public static String getTenNguoiDung(int maNguoiDung) {
        return new TaiKhoanDAO().getTenNguoiDung(maNguoiDung);
    }

    public static String getLoaiNguoiDung(int maNguoiDung) {
        return new TaiKhoanDAO().getLoaiNguoiDung(maNguoiDung);
    }
}
