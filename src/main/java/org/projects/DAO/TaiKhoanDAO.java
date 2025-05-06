package org.projects.DAO;

import org.projects.entity.TaiKhoanEntity;
import org.projects.config.DatabasesConfig;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO implements ChucNangDAO<TaiKhoanEntity>{

    @Override
    public List<TaiKhoanEntity> showlist() {
        List<TaiKhoanEntity> result = new ArrayList<TaiKhoanEntity>();
        String query = "select * from tai_khoan";
        try (Connection connection = DatabasesConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TaiKhoanEntity tk = new TaiKhoanEntity(resultSet.getString("ten_dang_nhap"), resultSet.getString("mat_khau"), resultSet.getString("trang_thai"), resultSet.getInt("ma_nguoi_dung"), resultSet.getInt("quyen_nguoi_dung"));
                result.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public int them(TaiKhoanEntity add) {
        String query = "insert into tai_khoan (ten_dang_nhap,ma_nguoi_dung,mat_khau,quyen_nguoi_dung,trang_thai) values(?,?,?,?,?)";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);) {
            prs.setString(1,add.getTenDangNhap());
            prs.setInt(2,add.getMaNguoiDung());
            prs.setString(3,add.getMatKhau());
            prs.setInt(4,add.getQuyenNguoiDung());
            prs.setString(5,add.getTrangThai());
            return prs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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

    public String getTenNguoiDung(int maNguoiDung) {
        String queryOnNhanVien = "select * from nhan_vien where ma_nguoi_dung = ?";
        String queryOnKhachHang = "select * from khach_hang where ma_nguoi_dung = ?";
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
                        result = resultSet2.getString("ten_khach_hang");
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
        String query = "select loai_nguoi_dung from nguoi_dung where ma_nguoi_dung = ?";
        String result = "";
        try (Connection connection = DatabasesConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, maNguoiDung);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("loai_nguoi_dung");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public List<String> gettrangthai() {
        List<String> result = new ArrayList<>();
        String query = "select DISTINCT tk.trang_thai\n" +
                "from tai_khoan tk";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);
        ResultSet rs = prs.executeQuery()) {
            while (rs.next()) {
                result.add(rs.getString("trang_thai"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
