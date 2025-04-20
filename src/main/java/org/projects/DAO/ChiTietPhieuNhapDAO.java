package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.ChiTietPhieuNhapEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ChiTietPhieuNhapDAO implements ChucNangDAO<ChiTietPhieuNhapEntity> {

    @Override
    public List<ChiTietPhieuNhapEntity> showlist() {
        return List.of();
    }

    @Override
    public int them(ChiTietPhieuNhapEntity add) {
        String query = "INSERT INTO chi_tiet_phieu_nhap (ma_phieu_nhap, ma_san_pham, so_luong, gia_nhap, thanh_tien) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {

            ps.setInt(1, add.getMaPN());
            ps.setInt(2, add.getMaSP());
            ps.setInt(3, add.getSoLuong());
            ps.setDouble(4, add.getGiaNhap());
            ps.setDouble(5, add.getThanhTien());

            return ps.executeUpdate(); // Trả về số dòng bị ảnh hưởng (1 nếu thành công)

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0; // Lỗi
    }

    @Override
    public int sua(ChiTietPhieuNhapEntity fix) {
        return 0;
    }

    public int xoaTheoMaPhieuNhap(int maPhieuNhap) {
        String query = "DELETE FROM chi_tiet_phieu_nhap WHERE ma_phieu_nhap = ?";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {

            ps.setInt(1, maPhieuNhap);
            return ps.executeUpdate(); // Trả về số dòng bị ảnh hưởng

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int xoa(ChiTietPhieuNhapEntity delete) {
        String query = "DELETE FROM chi_tiet_phieu_nhap WHERE ma_phieu_nhap = ? AND ma_san_pham = ?";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setInt(1, delete.getMaPN());
            ps.setInt(2, delete.getMaSP());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ChiTietPhieuNhapEntity search(int id) {
        return null;
    }
}
