package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.DanhMucSanPhamEntity;
import org.projects.entity.SanPhamEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDao implements ChucNangDAO<SanPhamEntity> {

    @Override
    public List<SanPhamEntity> showlist() {
        List<SanPhamEntity> list = new ArrayList<>();
        String query = """
                SELECT *
                FROM san_pham
                JOIN danh_muc_san_pham on san_pham.phan_loai = danh_muc_san_pham.ma_danh_muc
                ORDER BY san_pham.ma_san_pham;""";
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
                                                     rs.getString("quy_cach"),
                                                     rs.getString("img"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int them(SanPhamEntity add) {
        return 0;
    }

    @Override
    public int sua(SanPhamEntity fix) {
        return 0;
    }

    @Override
    public int xoa(SanPhamEntity delete) {
        return 0;
    }

    @Override
    public int chitiet(SanPhamEntity detail) {
        return 0;
    }
}
