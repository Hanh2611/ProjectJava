package org.projects.BUS;

import org.projects.DAO.LoginDAO;
import org.projects.entity.TaiKhoan;

public class LoginBUS {
    public static TaiKhoan login(TaiKhoan taiKhoan) {
        if (taiKhoan.getTenDangNhap() == null || taiKhoan.getTenDangNhap().isEmpty() || taiKhoan.getMatKhau().isEmpty()) {
            return null;
        }
        return new LoginDAO().verifyLogin(taiKhoan);
    }
}
