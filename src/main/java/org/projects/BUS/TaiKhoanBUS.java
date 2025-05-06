package org.projects.BUS;

import org.projects.DAO.NguoiDungDAO;
import org.projects.DAO.NhomQuyenDAO;
import org.projects.DAO.TaiKhoanDAO;
import org.projects.entity.NguoiDungEntity;
import org.projects.entity.TaiKhoanEntity;

import java.util.List;

public class TaiKhoanBUS {
    public static List<TaiKhoanEntity> getListTaiKhoan() {
        return new TaiKhoanDAO().showlist();
    }

    public static String getTenNguoiDung(int maNguoiDung) {
        return new TaiKhoanDAO().getTenNguoiDung(maNguoiDung);
    }

    public static String getLoaiNguoiDung(int maNguoiDung) {
        return new TaiKhoanDAO().getLoaiNguoiDung(maNguoiDung);
    }

    public static List<String> getDanhsachLoainguoidung() {
        return new NguoiDungDAO().getdanhsach();
    }

    public static List<String> laytennhomquyen() {
        return new NhomQuyenDAO().getDanhsachtennhomquyen();
    }
    public static List<String> laytrangthai() {
        return new TaiKhoanDAO().gettrangthai();
    }

    public static boolean themtaikhoan(TaiKhoanEntity taikhoan) {
        return new TaiKhoanDAO().them(taikhoan) > 0;
    }
}
