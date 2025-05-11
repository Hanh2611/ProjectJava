package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.HoaDonEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO implements ChucNangDAO<HoaDonEntity> {

    @Override
    public List<HoaDonEntity> showlist() {
        List<HoaDonEntity> list = new ArrayList<>();
        String query = "SELECT \n" +
                "    hd.ma_hoa_don, \n" +
                "    nv.ten_nhan_vien, \n" +
                "    kh.ten_khach_hang, \n" +
                "    hd.ngay_tao, \n" +
                "    hd.tong_gia_tri, \n" +
                "    hd.trang_thai\n" +
                "FROM \n" +
                "    hoa_don hd\n" +
                "JOIN \n" +
                "    nhan_vien nv ON hd.ma_nhan_vien = nv.ma_nhan_vien\n" +
                "JOIN \n" +
                "    khach_hang kh ON hd.ma_khach_hang = kh.ma_khach_hang\n" +
                "WHERE \n" +
                "    hd.is_delete = 0";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonEntity hd = new HoaDonEntity(
                        rs.getInt("ma_hoa_don"),
                        rs.getString("ten_nhan_vien"),
                        rs.getString("ten_khach_hang"),
                        rs.getTimestamp("ngay_tao"),
                        rs.getDouble("tong_gia_tri"),
                        rs.getString("trang_thai")
                );
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int them(HoaDonEntity add) {
        String query = "INSERT INTO hoa_don (ma_nhan_vien, ma_khach_hang, tong_gia_tri, trang_thai) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, add.getMaNV());
            ps.setInt(2, add.getMaKh());
            ps.setDouble(3, add.getTongGiaTri());
            ps.setString(4, add.getTrangThai());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);  // trả về maHD vừa insert
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;  // Lỗi nếu không lấy được mã
    }



    @Override
    public int sua(HoaDonEntity fix) {
        int ketQua = 0;
        String sql = "UPDATE hoa_don SET ma_khach_hang = ?, ma_nhan_vien = ?, tong_gia_tri = ?, trang_thai = ? WHERE ma_hoa_don = ?";

        try (Connection conn = DatabasesConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, fix.getMaKh());
            stmt.setInt(2, fix.getMaNV());
            stmt.setDouble(3, fix.getTongGiaTri());
            stmt.setString(4, fix.getTrangThai());
            stmt.setInt(5, fix.getMaHoaDon());

            ketQua = stmt.executeUpdate(); // Trả về số dòng bị ảnh hưởng (1 nếu thành công)

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int xoa(HoaDonEntity delete) {
        String query = "UPDATE hoa_don SET is_delete = 1 WHERE ma_hoa_don = ?";

        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {

            ps.setInt(1, delete.getMaHoaDon());
            return ps.executeUpdate(); // trả về 1 nếu thành công

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public HoaDonEntity search(int id) {
        return null;
    }

    public boolean isExistedInHoaDon(int spId){
        String sql = """
                    SELECT 1
                    FROM chi_tiet_hoa_don cthd
                    WHERE cthd.ma_san_pham = ?
                    LIMIT 1
                    """;
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, spId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
