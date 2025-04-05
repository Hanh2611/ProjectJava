package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.NhomQuyen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class PhanQuyenDAO implements ChucNangDAO<NhomQuyen> {
    @Override
    public List<NhomQuyen> showlist() {
        String query = "select * from nhom_quyen";
        List<NhomQuyen> listNhomQuyen = new ArrayList<>();

        try (Connection connection = DatabasesConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                NhomQuyen nhomQuyen = new NhomQuyen(resultSet.getInt("ma_nhom_quyen"), resultSet.getString("ten_nhom_quyen"));
                listNhomQuyen.add(nhomQuyen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhomQuyen;
    }

    @Override
    public int them(NhomQuyen add) {
        return 0;
    }

    @Override
    public int sua(NhomQuyen fix) {
        return 0;
    }

    @Override
    public int xoa(NhomQuyen delete) {
        return 0;
    }

    @Override
    public int chitiet(NhomQuyen detail) {
        return 0;
    }
}
