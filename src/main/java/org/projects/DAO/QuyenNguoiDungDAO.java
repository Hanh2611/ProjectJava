package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.QuyenNguoiDung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class QuyenNguoiDungDAO implements ChucNangDAO<QuyenNguoiDung>{

    @Override
    public List<QuyenNguoiDung> showlist() {
        return List.of();
    }

    @Override
    public int them(QuyenNguoiDung add) {
        return 0;
    }

    @Override
    public int sua(QuyenNguoiDung fix) {
        return 0;
    }

    @Override
    public int xoa(QuyenNguoiDung quyenNguoiDung) {
        int result = 0;
        String query = "delete from quyen_nguoi_dung where ma_nhom_quyen = ?";
        try(Connection connection = DatabasesConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, quyenNguoiDung.getMa_nhom_quyen());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public QuyenNguoiDung search(int id) {
        return null;
    }
}
