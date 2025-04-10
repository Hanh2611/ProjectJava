package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.HoaDonEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO implements ChucNangDAO<HoaDonEntity> {

    @Override
    public List<HoaDonEntity> showlist() {
        List<HoaDonEntity> list = new ArrayList<>();
        String query = "select * from hoa_don";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonEntity hd = new HoaDonEntity(
                        rs.getInt("ma_hoa_don"),
                        rs.getInt("ma_nhan_vien"),
                        rs.getInt("ma_khach_hang"),
                        rs.getTimestamp("ngay_tao"),
                        rs.getDouble("tong_gia_tri"),
                        "" // placeholder cho tenNCC nếu muốn join thêm
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
        return 0;
    }

    @Override
    public int sua(HoaDonEntity fix) {
        return 0;
    }

    @Override
    public int xoa(HoaDonEntity delete) {
        return 0;
    }

    @Override
    public HoaDonEntity search(int id) {
        return null;
    }
}
