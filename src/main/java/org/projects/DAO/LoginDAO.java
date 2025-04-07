package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.TaiKhoan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginDAO implements ChucNangDAO<TaiKhoan> {

    @Override
    public List<TaiKhoan> showlist() {
        return List.of();
    }

    @Override
    public int them(TaiKhoan add) {
        return 0;
    }

    @Override
    public int sua(TaiKhoan fix) {
        return 0;
    }

    @Override
    public int xoa(TaiKhoan delete) {
        return 0;
    }

    @Override
    public TaiKhoan search(int id) {
        return null;
    }


    public TaiKhoan verifyLogin(TaiKhoan taiKhoan) {
        String query = "select * from tai_khoan where ten_dang_nhap = ? and mat_khau = ?";
        try(Connection connection = DatabasesConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, taiKhoan.getTenDangNhap());
            preparedStatement.setString(2, taiKhoan.getMatKhau());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new TaiKhoan(
                        rs.getString("ten_dang_nhap"),
                        rs.getString("mat_khau"),
                        rs.getString("trang_thai"),
                        rs.getInt("ma_nguoi_dung")
                );
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
