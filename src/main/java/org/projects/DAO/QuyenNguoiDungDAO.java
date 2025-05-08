package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.QuyenNguoiDung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static int getMaquyennguoidung(int manguoidung) {
        String query = "select ma_nhom_quyen from quyen_nguoi_dung where ma_nguoi_dung = ?";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ) {
            prs.setInt(1, manguoidung);
            ResultSet rs = prs.executeQuery();
            if (rs.next()) {
                return rs.getInt("ma_nhom_quyen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean ganquyengnuoidung(int manguoidung,int manhomquyen) {
        String query = "INSERT INTO quyen_nguoi_dung (ma_nguoi_dung, ma_nhom_quyen) VALUES (?, ?)";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement prs = c.prepareStatement(query)) {
            prs.setInt(1, manguoidung);
            prs.setInt(2, manhomquyen);
            return prs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean capnhapquyen(int manguoidung,int manhomquyen) {
        String query = "update quyen_nguoi_dung set ma_nhom_quyen = ? where ma_nguoi_dung = ?";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setInt(1, manhomquyen);
            ps.setInt(2, manguoidung);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
