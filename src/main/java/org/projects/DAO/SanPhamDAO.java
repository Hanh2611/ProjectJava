package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.DanhMucSanPhamEntity;
import org.projects.entity.Enum.QuyCach;
import org.projects.entity.SanPhamEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO implements ChucNangDAO<SanPhamEntity> {

    @Override
    public List<SanPhamEntity> showlist() {
        List<SanPhamEntity> list = new ArrayList<>();
        String query = """
                SELECT *
                FROM san_pham
                JOIN danh_muc_san_pham on san_pham.phan_loai = danh_muc_san_pham.ma_danh_muc
                ORDER BY san_pham.ma_san_pham
                """;
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                SanPhamEntity sp = new SanPhamEntity(rs.getInt("ma_san_pham"),
                                                     rs.getString("ten_san_pham"),
                                                     new DanhMucSanPhamEntity(rs.getInt("ma_danh_muc"), rs.getString("ten_danh_muc")),
                                                     rs.getString("don_vi"),
                                                     rs.getDouble("gia_ban"),
                                                     rs.getDouble("so_luong_ton"),
                                                     QuyCach.fromValue(rs.getString("quy_cach")),
                                                     rs.getString("img"),
                                                     rs.getBoolean("trang_thai"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public SanPhamEntity findById(int id) {
        String query = """
        SELECT * FROM san_pham
         JOIN danh_muc_san_pham on san_pham.phan_loai = danh_muc_san_pham.ma_danh_muc
         WHERE ma_san_pham = ?
        """;
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SanPhamEntity(rs.getInt("ma_san_pham"),
                                         rs.getString("ten_san_pham"),
                                         new DanhMucSanPhamEntity(rs.getInt("phan_loai"), rs.getString("ten_danh_muc")),
                                         rs.getString("don_vi"),
                                         rs.getDouble("gia_ban"),
                                         rs.getDouble("so_luong_ton"),
                                         QuyCach.fromValue(rs.getString("quy_cach")),
                                         rs.getString("img"),
                                         rs.getBoolean("trang_thai"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SanPhamEntity> findBy(String keyword, String value) {
        List<SanPhamEntity> list = new ArrayList<>();
        String query = """
                SELECT *
                FROM san_pham
                JOIN danh_muc_san_pham on san_pham.phan_loai = danh_muc_san_pham.ma_danh_muc
                WHERE ? LIKE ?
                ORDER BY san_pham.ma_san_pham
                """;
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            switch (keyword) {
                case "Tên":
                    ps.setString(1, "ten_san_pham");
                    break;
                default:
                    return list;
            }
            ps.setString(2, "%" + value + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamEntity sp = new SanPhamEntity(rs.getInt("ma_san_pham"),
                                                     rs.getString("ten_san_pham"),
                                                     new DanhMucSanPhamEntity(rs.getInt("ma_danh_muc"), rs.getString("ten_danh_muc")),
                                                     rs.getString("don_vi"),
                                                     rs.getDouble("gia_ban"),
                                                     rs.getDouble("so_luong_ton"),
                                                     QuyCach.fromValue(rs.getString("quy_cach")),
                                                     rs.getString("img"),
                                                     rs.getBoolean("trang_thai"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



    @Override
    public int them(SanPhamEntity sanPhamEntity) {
        String query = """
                INSERT INTO san_pham (ten_san_pham, phan_loai, don_vi, gia_ban, quy_cach, img)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, sanPhamEntity.getTenSanPham());
            ps.setInt(2, sanPhamEntity.getPhanLoai().getId());
            ps.setString(3, sanPhamEntity.getDonVi());
            ps.setDouble(4, sanPhamEntity.getGiaBan());
            ps.setString(5, sanPhamEntity.getQuyCach().toString());
            ps.setString(6, sanPhamEntity.getHinhAnh());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int sua(SanPhamEntity sanPhamEntity) {
        String query = """
            UPDATE san_pham
            SET ten_san_pham = ?, phan_loai = ?, don_vi = ?, gia_ban = ?, so_luong_ton = ?, quy_cach = ?, img = ?, trang_thai = ?
            WHERE ma_san_pham = ?
        """;
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, sanPhamEntity.getTenSanPham());
            ps.setInt(2, sanPhamEntity.getPhanLoai().getId());
            ps.setString(3, sanPhamEntity.getDonVi());
            ps.setDouble(4, sanPhamEntity.getGiaBan());
            ps.setDouble(5, sanPhamEntity.getSoLuongTon());
            ps.setString(6, sanPhamEntity.getQuyCach().toString());
            ps.setString(7, sanPhamEntity.getHinhAnh());
            ps.setBoolean(8, sanPhamEntity.isTrangThai());
            ps.setInt(9, sanPhamEntity.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int xoa(SanPhamEntity sanPhamEntity) {
        String query = """
            DELETE FROM san_pham
            WHERE ma_san_pham = ?
        """;
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setInt(1, sanPhamEntity.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public SanPhamEntity search(int id) {
        return null;
    }

    public List<SanPhamEntity> getSanPhamByDanhMuc(int id) {
        List<SanPhamEntity> list = new ArrayList<>();
        String query = """
                SELECT *
                FROM san_pham
                JOIN danh_muc_san_pham on san_pham.phan_loai = danh_muc_san_pham.ma_danh_muc
                WHERE phan_loai = ?
                ORDER BY san_pham.ma_san_pham
                """;
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamEntity sp = new SanPhamEntity(rs.getInt("ma_san_pham"),
                                                     rs.getString("ten_san_pham"),
                                                     new DanhMucSanPhamEntity(rs.getInt("ma_danh_muc"), rs.getString("ten_danh_muc")),
                                                     rs.getString("don_vi"),
                                                     rs.getDouble("gia_ban"),
                                                     rs.getDouble("so_luong_ton"),
                                                     QuyCach.fromValue(rs.getString("quy_cach")),
                                                     rs.getString("img"),
                                                     rs.getBoolean("trang_thai"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
