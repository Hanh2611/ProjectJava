package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.TaiKhoanEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginDAO implements ChucNangDAO<TaiKhoanEntity> {

    @Override
    public List<TaiKhoanEntity> showlist() {
        return List.of();
    }

    @Override
    public int them(TaiKhoanEntity add) {
        return 0;
    }

    @Override
    public int sua(TaiKhoanEntity fix) {
        return 0;
    }

    @Override
    public int xoa(TaiKhoanEntity delete) {
        return 0;
    }

    @Override
    public TaiKhoanEntity search(int id) {
        return null;
    }


    public TaiKhoanEntity verifyLogin(TaiKhoanEntity taiKhoanEntity) {
        String query = "select * from tai_khoan where ten_dang_nhap = ? and mat_khau = ?";
        try(Connection connection = DatabasesConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, taiKhoanEntity.getTenDangNhap());
            preparedStatement.setString(2, taiKhoanEntity.getMatKhau());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new TaiKhoanEntity(
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
