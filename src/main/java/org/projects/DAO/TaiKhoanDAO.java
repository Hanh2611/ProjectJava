package org.projects.DAO;

import org.projects.entity.TaiKhoan;
import org.projects.config.DatabasesConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO implements ChucNangDAO<TaiKhoan>{

    @Override
    public List<TaiKhoan> showlist() {
        List<TaiKhoan> result = new ArrayList<TaiKhoan>();
        String query = "select * from tai_khoan";
        try (Connection connection = DatabasesConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TaiKhoan tk = new TaiKhoan(resultSet.getString("ten_dang_nhap"), resultSet.getString("mat_khau"), resultSet.getString("trang_thai"), resultSet.getInt("ma_nguoi_dung"), resultSet.getInt("quyen_nguoi_dung"));
                result.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
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

    public String getTenNguoiDung(int maNguoiDung) {
        String queryOnNhanVien = "select ten_nhan_vien from nhan_vien where ma_nguoi_dung = ?";
        String queryOnKhachHang = "select ten_khach_hang from khach_hang where ma_nguoi_dung = ?";
        String result = "";
        try (Connection connection = DatabasesConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(queryOnNhanVien);) {
            preparedStatement.setInt(1, maNguoiDung);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("ten_nhan_vien");
            } else {
                try (Connection connection2 = DatabasesConfig.getConnection();
                PreparedStatement preparedStatement2 = connection2.prepareStatement(queryOnKhachHang);) {
                    preparedStatement2.setInt(1, maNguoiDung);
                    ResultSet resultSet2 = preparedStatement2.executeQuery();
                    if (resultSet2.next()) {
                        result = resultSet.getString("ten_khach_hang");
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                    return result;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public String getLoaiNguoiDung(int maNguoiDung) {
        String query = "select";
    }
}
