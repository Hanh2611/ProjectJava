package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.ChiTietHoaDonFullEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ChiTietHoaDonFullDAO {

    public List<ChiTietHoaDonFullEntity> showList(int maHD) {
        List<ChiTietHoaDonFullEntity> list = new ArrayList<>();
        String sql = "SELECT hd.ma_hoa_don, hd.ngay_tao,hd.tong_gia_tri," +
                "nv.ma_nhan_vien, nv.ten_nhan_vien, " +
                "kh.ma_khach_hang, kh.ten_khach_hang, " +
                "ct.ma_san_pham, sp.ten_san_pham, ct.gia_ban, ct.so_luong, ct.thanh_tien " +
                "FROM hoa_don hd " +
                "JOIN nhan_vien nv ON hd.ma_nhan_vien = nv.ma_nhan_vien " +
                "JOIN khach_hang kh ON hd.ma_khach_hang = kh.ma_khach_hang " +
                "JOIN chi_tiet_hoa_don ct ON hd.ma_hoa_don = ct.ma_hoa_don " +
                "JOIN san_pham sp ON ct.ma_san_pham = sp.ma_san_pham " +
                "WHERE hd.ma_hoa_don = ?";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietHoaDonFullEntity hd = new ChiTietHoaDonFullEntity();
                hd.setMaHD(rs.getInt("ma_hoa_don"));
                hd.setNgayTao(rs.getTimestamp("ngay_tao")); // Không gọi .toLocalDateTime()
                hd.setMaNguoiLap(rs.getInt("ma_nhan_vien"));
                hd.setTenNguoiLap(rs.getString("ten_nhan_vien"));
                hd.setMaKH(rs.getInt("ma_khach_hang"));
                hd.setTenKH(rs.getString("ten_khach_hang"));
                hd.setMaSP(rs.getInt("ma_san_pham"));
                hd.setTenSP(rs.getString("ten_san_pham"));
                hd.setGiaBan(rs.getDouble("gia_ban"));
                hd.setSoLuong(rs.getInt("so_luong"));
                hd.setThanhTien(rs.getDouble("thanh_tien"));
                hd.setTongGiaTri(rs.getDouble("tong_gia_tri"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<ChiTietHoaDonFullEntity> showListByKhachHang(int maKH) {
        List<ChiTietHoaDonFullEntity> list = new ArrayList<>();
        String sql = "SELECT DISTINCT hd.ma_hoa_don, hd.ngay_tao, hd.tong_gia_tri, hd.trang_thai, " +
                "nv.ma_nhan_vien, nv.ten_nhan_vien, " +
                "kh.ma_khach_hang, kh.ten_khach_hang, " +
                "ct.ma_san_pham, sp.ten_san_pham, ct.gia_ban, ct.so_luong, ct.thanh_tien " +
                "FROM hoa_don hd " +
                " JOIN nhan_vien nv ON hd.ma_nhan_vien = nv.ma_nhan_vien " +
                " JOIN khach_hang kh ON hd.ma_khach_hang = kh.ma_khach_hang " +
                " JOIN chi_tiet_hoa_don ct ON hd.ma_hoa_don = ct.ma_hoa_don " +
                " JOIN san_pham sp ON ct.ma_san_pham = sp.ma_san_pham " +
                "WHERE kh.ma_khach_hang = ?";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, maKH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietHoaDonFullEntity e = new ChiTietHoaDonFullEntity();
                e.setMaHD(rs.getInt("ma_hoa_don"));
                e.setNgayTao(rs.getTimestamp("ngay_tao"));
                e.setTongGiaTri(rs.getDouble("tong_gia_tri"));
                e.setTrangThai(rs.getString("trang_thai"));
                e.setMaNguoiLap(rs.getInt("ma_nhan_vien"));
                e.setTenNguoiLap(rs.getString("ten_nhan_vien"));
                e.setMaKH(rs.getInt("ma_khach_hang"));
                e.setTenKH(rs.getString("ten_khach_hang"));
                e.setMaSP(rs.getInt("ma_san_pham"));
                e.setTenSP(rs.getString("ten_san_pham"));
                e.setGiaBan(rs.getDouble("gia_ban"));
                e.setSoLuong(rs.getInt("so_luong"));
                e.setThanhTien(rs.getDouble("thanh_tien"));
                list.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
