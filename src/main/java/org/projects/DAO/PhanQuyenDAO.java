package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.NhomQuyenEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class PhanQuyenDAO implements ChucNangDAO<NhomQuyenEntity> {
    @Override
    public List<NhomQuyenEntity> showlist() {
        String query = "select * from nhom_quyen";
        List<NhomQuyenEntity> listNhomQuyen = new ArrayList<>();

        try (Connection connection = DatabasesConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                NhomQuyenEntity nhomQuyen = new NhomQuyenEntity(resultSet.getInt("ma_nhom_quyen"), resultSet.getString("ten_nhom_quyen"));
                listNhomQuyen.add(nhomQuyen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhomQuyen;
    }

    @Override
    public int them(NhomQuyenEntity add) {
        return 0;
    }

    @Override
    public int sua(NhomQuyenEntity fix) {
        return 0;
    }

    @Override
    public int xoa(NhomQuyenEntity delete) {
        return 0;
    }

    @Override
    public NhomQuyenEntity search(int id) {
        return null;
    }
}
