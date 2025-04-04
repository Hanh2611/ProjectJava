package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.DanhMucQuanLy;
import org.projects.entity.NhomQuyen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DanhMucQuanLyDAO implements ChucNangDAO<DanhMucQuanLy> {

    @Override
    public List<DanhMucQuanLy> showlist() {
        String query = "select * from danh_muc_quan_ly";
        List<DanhMucQuanLy> listDanhMucQuanLy = new ArrayList<DanhMucQuanLy>();
        try (Connection connection = DatabasesConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                DanhMucQuanLy danhMucQuanLy = new DanhMucQuanLy(resultSet.getString("ten_danh_muc_quan_ly"), resultSet.getInt("ma_danh_muc_quan_ly"));
                listDanhMucQuanLy.add(danhMucQuanLy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDanhMucQuanLy;
    }

    @Override
    public int them(DanhMucQuanLy add) {
        return 0;
    }

    @Override
    public int sua(DanhMucQuanLy fix) {
        return 0;
    }

    @Override
    public int xoa(DanhMucQuanLy delete) {
        return 0;
    }

    @Override
    public int chitiet(DanhMucQuanLy detail) {
        return 0;
    }
}
