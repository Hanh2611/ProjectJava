package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.ChiTietHoaDonEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ChiTietHoaDonDAO implements ChucNangDAO<ChiTietHoaDonEntity>  {

    @Override
    public List<ChiTietHoaDonEntity> showlist() {
        return List.of();
    }
    @Override
    public int them(ChiTietHoaDonEntity add) {
        String query = "INSERT INTO chi_tiet_hoa_don (ma_san_pham, ma_hoa_don, so_luong, gia_ban, thanh_tien) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {

            ps.setInt(1, add.getMaSP());
            ps.setInt(2, add.getMaHD());
            ps.setInt(3, add.getSoLuong());
            ps.setDouble(4, add.getGiaBan());
            ps.setDouble(5, add.getThanhTien());

            return ps.executeUpdate(); // Trả về 1 nếu thành công
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0; // Trả về 0 nếu thất bại
    }


    @Override
    public int sua(ChiTietHoaDonEntity fix) {
        return 0;
    }
    public int xoaTatCaTheoMaHD(int maHD) {
        int ketQua = 0;
        String sql = "DELETE FROM chi_tiet_hoa_don WHERE ma_hoa_don = ?";

        try (Connection conn = DatabasesConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maHD);
            ketQua = stmt.executeUpdate(); // Số dòng chi tiết đã xóa

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }
    @Override
    public int xoa(ChiTietHoaDonEntity delete) {
        int ketQua = 0;
        String sql = "DELETE FROM chi_tiet_hoa_don WHERE ma_hoa_don = ? AND ma_san_pham = ?";

        try (Connection conn = DatabasesConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, delete.getMaHD());
            stmt.setInt(2, delete.getMaSP());

            ketQua = stmt.executeUpdate(); // Trả về số dòng bị xóa

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public ChiTietHoaDonEntity search(int id) {
        return null;
    }
}
