package org.projects.BUS;

import org.projects.DAO.QuyenNguoiDungDAO;
import org.projects.entity.QuyenNguoiDung;

public class QuyenNguoiDungBUS {
    public static int getMaNhomQuyenByMaNguoiDung(int maNguoiDung) {
        return new QuyenNguoiDungDAO().getMaquyennguoidung(maNguoiDung);
    }
    public static int addQuyenNguoiDung(int maNguoiDung, int quyenNguoiDung) {
        return new QuyenNguoiDungDAO().them(new QuyenNguoiDung(maNguoiDung, quyenNguoiDung));
    }
}
