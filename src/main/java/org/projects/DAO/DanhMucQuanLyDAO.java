package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.DanhMucQuanLy;
import org.projects.entity.NhomQuyen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DanhMucQuanLyDAO implements ChucNangDAO<DanhMucQuanLy> {

    @Override
    public List<DanhMucQuanLy> showlist() {
        String query = "select * from danh_muc_quan_ly";
        List<DanhMucQuanLy> listDanhMucQuanLy = new ArrayList<DanhMucQuanLy>();
        try (Connection connection = DatabasesConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                DanhMucQuanLy danhMucQuanLy = new DanhMucQuanLy(resultSet.getString("ten_danh_muc_quan_ly"), resultSet.getInt("ma_danh_muc_quan_ly"));
                listDanhMucQuanLy.add(danhMucQuanLy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDanhMucQuanLy;
    }

    @Override
    public int them(DanhMucQuanLy add) {
        return 0;
    }

    @Override
    public int sua(DanhMucQuanLy fix) {
        return 0;
    }

    @Override
    public int xoa(DanhMucQuanLy delete) {
        return 0;
    }

    @Override
    public DanhMucQuanLy search(int id) {
        return null;
    }

    public static int getMaDanhMuc(String tenDanhMuc) {
        int result = -1;
        String query = "select ma_danh_muc_quan_ly from danh_muc_quan_ly where ten_danh_muc_quan_ly = ?";
        try (Connection connection  = DatabasesConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, tenDanhMuc);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("ma_danh_muc_quan_ly");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Integer> getDanhMucQuanLyByMaNguoiDung(int maNguoiDung) {
        List<Integer> result = new ArrayList<>();
        String query = "SELECT * " +
                "FROM danh_muc_quan_ly " +
                "WHERE EXISTS ( " +
                "    SELECT * " +
                "    FROM quyen_nguoi_dung " +
                "    JOIN cap_quyen ON cap_quyen.ma_nhom_quyen = quyen_nguoi_dung.ma_nhom_quyen " +
                "    WHERE danh_muc_quan_ly.ma_danh_muc_quan_ly = cap_quyen.ma_danh_muc_quan_ly " +
                "    AND quyen_nguoi_dung.ma_nguoi_dung = ? " +
                ")";
        try (Connection conection = DatabasesConfig.getConnection();
        PreparedStatement statement = conection.prepareStatement(query);) {
            statement.setInt(1, maNguoiDung);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getInt("ma_danh_muc_quan_ly"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public List<Integer> getDanhMucQuanLyByMaNhomQuyen(int maNhomQuyen) {
        System.out.println(maNhomQuyen);
        List<Integer> result = new ArrayList<>();
        String query = "SELECT * " +
                "FROM danh_muc_quan_ly " +
                "WHERE EXISTS ( " +
                "    SELECT * " +
                "    FROM cap_quyen " +
                "    WHERE danh_muc_quan_ly.ma_danh_muc_quan_ly = cap_quyen.ma_danh_muc_quan_ly " +
                "    AND cap_quyen.ma_nhom_quyen = ? " +
                ")";
        try (Connection conection = DatabasesConfig.getConnection();
             PreparedStatement statement = conection.prepareStatement(query);) {
            statement.setInt(1, maNhomQuyen);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getInt("ma_danh_muc_quan_ly"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public List<List<String>> quyenHanhDong(List<Integer> maNhomQuyen) {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            result.add(new ArrayList<>());
        }
        String[] hanhDong = {"them", "sua", "xoa", "xem", "excel"};
        String query = "select * from cap_quyen where ma_nhom_quyen = ?";
        try (Connection connection = DatabasesConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);) {
            for (int maQuyen : maNhomQuyen) {
                statement.setInt(1, maQuyen);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    if (!result.get(resultSet.getInt("ma_danh_muc_quan_ly") - 1).contains(resultSet.getString("hanh_dong")))
                        result.get(resultSet.getInt("ma_danh_muc_quan_ly") - 1).add(resultSet.getString("hanh_dong"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }
}
