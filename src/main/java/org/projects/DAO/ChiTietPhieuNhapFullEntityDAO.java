package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.ChiTietPhieuNhapFullEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuNhapFullEntityDAO {

    public List<ChiTietPhieuNhapFullEntity> showlist(int maPN) {
        List<ChiTietPhieuNhapFullEntity> list = new ArrayList<>();
        String query = """
    SELECT ct.ma_phieu_nhap, ct.ma_san_pham, sp.ten_san_pham, sp.don_vi,
           ct.so_luong, ct.gia_nhap, ct.thanh_tien,
           ncc.ten_nha_cung_cap, pn.ma_nha_cung_cap,
           nv.ten_nhan_vien, pn.ma_nhan_vien, pn.ngay_nhap, pn.tong_gia_tri_nhap
    FROM chi_tiet_phieu_nhap ct
    JOIN san_pham sp ON ct.ma_san_pham = sp.ma_san_pham
    JOIN phieu_nhap pn ON ct.ma_phieu_nhap = pn.ma_phieu_nhap
    JOIN nha_cung_cap ncc ON pn.ma_nha_cung_cap = ncc.ma_nha_cung_cap
    JOIN nhan_vien nv ON pn.ma_nhan_vien = nv.ma_nhan_vien
    WHERE ct.ma_phieu_nhap = ?;
""";



        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {

            ps.setInt(1, maPN);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietPhieuNhapFullEntity ct = new ChiTietPhieuNhapFullEntity();
                    ct.setMaPN(rs.getInt("ma_phieu_nhap"));
                    ct.setMasp(rs.getInt("ma_san_pham"));
                    ct.setTenSP(rs.getString("ten_san_pham"));
                    ct.setDonvi(rs.getString("don_vi"));
                    ct.setSoLuong(rs.getInt("so_luong"));
                    ct.setGia(rs.getDouble("gia_nhap"));
                    ct.setTenNCC(rs.getString("ten_nha_cung_cap"));
                    ct.setTenNguoiLap(rs.getString("ten_nhan_vien"));
                    ct.setNgaylap(rs.getTimestamp("ngay_nhap"));
                    ct.setThanhtien(rs.getDouble("thanh_tien"));
                    ct.setMaNCC(rs.getInt("ma_nha_cung_cap"));
                    ct.setMaNguoiLap(rs.getInt("ma_nhan_vien"));
                    ct.setTongGiaTriNhap(rs.getDouble("tong_gia_tri_nhap"));
                    list.add(ct);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
