package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.NguoiDungEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO implements ChucNangDAO<NguoiDungEntity> {

    @Override
    public List<NguoiDungEntity> showlist() {
        return List.of();
    }

    @Override
    public int them(NguoiDungEntity add) {
        String query = "INSERT INTO nguoi_dung (ten_nguoi_dung, loai_nguoi_dung) VALUES (?, ?)";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement prs = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            prs.setString(1, add.getTenNguoiDung());
            prs.setString(2, add.getLoaiNguoiDung());
            int row = prs.executeUpdate();
            if(row > 0) {
                ResultSet rs = prs.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int sua(NguoiDungEntity fix) {
        return 0;
    }

    @Override
    public int xoa(NguoiDungEntity delete) {
        String query = "delete from nguoi_dung where ma_nguoi_dung = ?";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query)) {
            prs.setInt(1,delete.getMaNguoiDung());
            return prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int suaTheomanguoidungmoi(NguoiDungEntity fix,int manguoidung) {
        String query = "DELETE FROM nguoi_dung WHERE ma_nguoi_dung = ?";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement prs = c.prepareStatement(query)) {
            prs.setInt(1, manguoidung);
            return prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public NguoiDungEntity search(int id) {
        return null;
    }

    public List<String> getdanhsach() {
       List<String> mavaloainguoidung = new ArrayList<>();
        String query = "select DISTINCT nd.loai_nguoi_dung\n" +
                "from nguoi_dung nd";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery()) {
            while (rs.next()) {
                mavaloainguoidung.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mavaloainguoidung;
    }
}
