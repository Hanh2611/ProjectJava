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
        String query = "SELECT * FROM phieu_nhap;";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PhieuNhapEntity pn = new PhieuNhapEntity(
                        rs.getInt("ma_phieu_nhap"),
                        rs.getInt("ma_nhan_vien"),
                        rs.getInt("ma_nha_cung_cap"),
                        rs.getDouble("tong_gia_tri_nhap"),
                        "" // placeholder cho tenNCC nếu muốn join thêm
                );
                pn.setNgayNhap(rs.getTimestamp("ngay_nhap"));
                list.add(pn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int them(PhieuNhapEntity pn) {
        String query = "INSERT INTO phieu_nhap (ma_phieu_nhap, ma_nhan_vien, ma_nha_cung_cap, tong_gia_tri_nhap) VALUES (?, ?, ?, ?);";
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
        String query = "UPDATE phieu_nhap SET ma_nhan_vien = ?, ma_nha_cung_cap = ?, tong_gia_tri_nhap = ? WHERE ma_phieu_nhap = ?;";
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
        String query = "DELETE FROM phieu_nhap WHERE ma_phieu_nhap = ?;";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {

            ps.setInt(1, pn.getMaPN());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public PhieuNhapEntity search(int id) {
        String query = "SELECT * FROM phieu_nhap WHERE ma_phieu_nhap = ?;";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PhieuNhapEntity pn = new PhieuNhapEntity(
                            rs.getInt("ma_phieu_nhap"),
                            rs.getInt("ma_nhan_vien"),
                            rs.getInt("ma_nha_cung_cap"),
                            rs.getDouble("tong_gia_tri_nhap"),
                            ""
                    );
                    pn.setNgayNhap(rs.getTimestamp("ngay_nhap"));
                    return pn;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
