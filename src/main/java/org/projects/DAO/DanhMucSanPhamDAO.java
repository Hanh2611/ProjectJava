package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.DanhMucSanPhamEntity;
import org.projects.entity.SanPhamEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DanhMucSanPhamDAO implements ChucNangDAO<DanhMucSanPhamEntity>{
    public DanhMucSanPhamDAO(){
    }

    @Override
    public List<DanhMucSanPhamEntity> showlist() {
        List<DanhMucSanPhamEntity> list = new ArrayList<>();
        String query = """
                SELECT *
                FROM danh_muc_san_pham
            """;
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                DanhMucSanPhamEntity danhMucSanPhamEntity = new DanhMucSanPhamEntity(
                        rs.getInt("ma_danh_muc"),
                        rs.getString("ten_danh_muc")
                );
                list.add(danhMucSanPhamEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int them(DanhMucSanPhamEntity add) {
        return 0;
    }

    @Override
    public int sua(DanhMucSanPhamEntity fix) {
        return 0;
    }

    @Override
    public int xoa(DanhMucSanPhamEntity delete) {
        return 0;
    }

    @Override
    public int chitiet(DanhMucSanPhamEntity detail) {
        return 0;
    }
}
