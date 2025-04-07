package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.NhomQuyen;

import java.sql.*;
import java.util.*;

public class NhomQuyenDAO implements ChucNangDAO<NhomQuyen> {
    @Override
    public List<NhomQuyen> showlist() {
        String query = "select * from nhom_quyen";
        List<NhomQuyen> listNhomQuyen = new ArrayList<>();

        try (Connection connection = DatabasesConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                NhomQuyen nhomQuyen = new NhomQuyen(resultSet.getInt("ma_nhom_quyen"), resultSet.getString("ten_nhom_quyen"));
                listNhomQuyen.add(nhomQuyen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhomQuyen;
    }

    @Override
    public int them(NhomQuyen nhomQuyen) {
        int result = 0, maNhomQuyen = -1;
        String tenNhomQuyen = nhomQuyen.getTenNomQuyen();
        String query = "insert into nhom_quyen(ten_nhom_quyen) values (?)";
        try(Connection connection = DatabasesConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, tenNhomQuyen);
            result = statement.executeUpdate();
            if (result > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        maNhomQuyen = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maNhomQuyen;
    }

    @Override
    public int sua(NhomQuyen nhomQuyen) {
        int result = 0;
        String query = "update nhom_quyen set ten_nhom_quyen = ? where ma_nhom_quyen = ?";
        try (Connection connection = DatabasesConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nhomQuyen.getTenNomQuyen());
            statement.setInt(2, nhomQuyen.getMaNhomQuyen());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int xoa(NhomQuyen nhomQuyen) {
        int result = 0;
        String query = "delete from nhom_quyen where ma_nhom_quyen = ?";
        try (Connection connection = DatabasesConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, nhomQuyen.getMaNhomQuyen());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public NhomQuyen search(int id) {
        return null;
    }

    public List<Integer> getNhomQuyenOfUser(int maNguoiDung) {
        List<Integer> result = new ArrayList<>();
        String query = "select * from quyen_nguoi_dung where ma_nguoi_dung = ?";
        return result;
    }
}
