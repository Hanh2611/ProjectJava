package org.projects.BUS;

import org.projects.DAO.LoginDAO;
import org.projects.entity.TaiKhoanEntity;

public class LoginBUS {
    public static TaiKhoanEntity login(TaiKhoanEntity taiKhoanEntity) {
        if (taiKhoanEntity.getTenDangNhap() == null || taiKhoanEntity.getTenDangNhap().isEmpty() || taiKhoanEntity.getMatKhau().isEmpty()) {
            return null;
        }
        return new LoginDAO().verifyLogin(taiKhoanEntity);
    }
}
