package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.ChiTietPhieuNhapEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
    public List<ChiTietPhieuNhapEntity> getChiTietByMaPhieuNhap(int maPhieuNhap) {
        List<ChiTietPhieuNhapEntity> list = new ArrayList<>();
        String query = "SELECT * FROM chi_tiet_phieu_nhap WHERE ma_phieu_nhap = ?";

        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {

            ps.setInt(1, maPhieuNhap);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietPhieuNhapEntity ct = new ChiTietPhieuNhapEntity();
                    ct.setMaPN(rs.getInt("ma_phieu_nhap"));
                    ct.setMaSP(rs.getInt("ma_san_pham"));
                    ct.setSoLuong(rs.getInt("so_luong"));
                    ct.setGiaNhap(rs.getLong("gia_nhap"));
                    ct.setThanhTien(rs.getLong("thanh_tien"));
                    list.add(ct);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}
