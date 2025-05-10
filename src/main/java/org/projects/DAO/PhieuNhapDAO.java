package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.PhieuNhapEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAO implements ChucNangDAO<PhieuNhapEntity> {

        @Override
        public List<PhieuNhapEntity> showlist() {
            List<PhieuNhapEntity> list = new ArrayList<>();
            String query = "SELECT pn.ma_phieu_nhap, nv.ten_nhan_vien, ncc.ten_nha_cung_cap,\n" +
                    "       pn.ngay_nhap, pn.tong_gia_tri_nhap\n" +
                    "FROM phieu_nhap pn\n" +
                    "JOIN nhan_vien nv ON pn.ma_nhan_vien = nv.ma_nhan_vien\n" +
                    "JOIN nha_cung_cap ncc ON pn.ma_nha_cung_cap = ncc.ma_nha_cung_cap;";
            try (Connection c = DatabasesConfig.getConnection();
                 PreparedStatement ps = c.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    PhieuNhapEntity pn = new PhieuNhapEntity();
                    pn.setMaPN(rs.getInt("ma_phieu_nhap"));
                    pn.setTenNV(rs.getString("ten_nhan_vien"));
                    pn.setTenNCC(rs.getString("ten_nha_cung_cap"));
                    pn.setNgayNhap(rs.getTimestamp("ngay_nhap"));
                    pn.setTongGiaTri(rs.getDouble("tong_gia_tri_nhap"));
                    list.add(pn);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

    @Override
    public int them(PhieuNhapEntity pn) {
        String query = "INSERT INTO phieu_nhap (ma_phieu_nhap, ma_nhan_vien, ma_nha_cung_cap, tong_gia_tri_nhap) VALUES (?, ?, ?, ?)";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {

            ps.setInt(1, pn.getMaPN());
            ps.setInt(2, pn.getMaNV());
            ps.setInt(3, pn.getMaNCC());
            ps.setDouble(4, pn.getTongGiaTri());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public int sua(PhieuNhapEntity pn) {
        String query = "UPDATE phieu_nhap SET ma_nhan_vien = ?, ma_nha_cung_cap = ?, tong_gia_tri_nhap = ? WHERE ma_phieu_nhap = ?";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {

            ps.setInt(1, pn.getMaNV());
            ps.setInt(2, pn.getMaNCC());
            ps.setDouble(3, pn.getTongGiaTri());
            ps.setInt(4, pn.getMaPN());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int xoa(PhieuNhapEntity pn) {
        String deleteChiTiet = "DELETE FROM chi_tiet_phieu_nhap WHERE ma_phieu_nhap = ?;";
        String deletePhieu = "DELETE FROM phieu_nhap WHERE ma_phieu_nhap = ?;";

        try (Connection c = DatabasesConfig.getConnection()) {
            c.setAutoCommit(false); // Bắt đầu transaction

            // Xóa chi tiết trước
            try (PreparedStatement psChiTiet = c.prepareStatement(deleteChiTiet)) {
                psChiTiet.setInt(1, pn.getMaPN());
                psChiTiet.executeUpdate();
            }

            // Xóa phiếu nhập
            try (PreparedStatement psPhieu = c.prepareStatement(deletePhieu)) {
                psPhieu.setInt(1, pn.getMaPN());
                int rowsAffected = psPhieu.executeUpdate();

                c.commit(); // Hoàn tất transaction
                return rowsAffected;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public PhieuNhapEntity search(int id) {
        return null;
    }
    public List<PhieuNhapEntity> search(String type, String keyword) {
        List<PhieuNhapEntity> list = new ArrayList<>();
        String query = "SELECT pn.ma_phieu_nhap, nv.ten_nhan_vien, ncc.ten_nha_cung_cap, " +
                "pn.ngay_nhap, pn.tong_gia_tri_nhap " +
                "FROM phieu_nhap pn " +
                "JOIN nhan_vien nv ON pn.ma_nhan_vien = nv.ma_nhan_vien " +
                "JOIN nha_cung_cap ncc ON pn.ma_nha_cung_cap = ncc.ma_nha_cung_cap ";

        switch (type.trim().toLowerCase()) {
            case "mã":
                query += "WHERE pn.ma_phieu_nhap LIKE ?";
                keyword = "%" + keyword + "%";
                break;
            case "tên nhân viên":
                query += "WHERE nv.ten_nhan_vien LIKE ?";
                keyword = "%" + keyword + "%";
                break;
            case "tên ncc":
                query += "WHERE ncc.ten_nha_cung_cap LIKE ?";
                keyword = "%" + keyword + "%";
                break;
            default:
                return showlist();
        }

        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, keyword);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PhieuNhapEntity pn = new PhieuNhapEntity();
                    pn.setMaPN(rs.getInt("ma_phieu_nhap"));
                    pn.setTenNV(rs.getString("ten_nhan_vien"));
                    pn.settenNCC(rs.getString("ten_nha_cung_cap"));
                    pn.setNgayNhap(rs.getTimestamp("ngay_nhap"));
                    pn.setTongGiaTri(rs.getDouble("tong_gia_tri_nhap"));
                    list.add(pn);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean isExistedInPhieuNhap(int spId){
            String sql = """
                    SELECT 1
                    FROM chi_tiet_phieu_nhap ctpn
                    WHERE ctpn.ma_san_pham = ?
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
