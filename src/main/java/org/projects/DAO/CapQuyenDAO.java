package org.projects.DAO;

import com.mysql.cj.jdbc.CallableStatement;
import org.projects.config.DatabasesConfig;
import org.projects.entity.CapQuyen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CapQuyenDAO implements ChucNangDAO<CapQuyen> {

    @Override
    public List<CapQuyen> showlist() {
        return List.of();
    }

    @Override
    public int them(CapQuyen capQuyen) {
        int result = 0;
        String query = "insert into cap_quyen(ma_nhom_quyen, ma_danh_muc_quan_ly, hanh_dong) values(?,?,?)";
        try (Connection connection = DatabasesConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, capQuyen.getMa_nhom_quyen());
            statement.setInt(2, capQuyen.getMa_danh_muc_quan_ly());
            statement.setString(3, capQuyen.getHanh_dong());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int sua(CapQuyen fix) {
        return 0;
    }

    @Override
    public int xoa(CapQuyen capQuyen) {
        int result = 0;
        String query = "delete from cap_quyen where ma_nhom_quyen=?";
        try (Connection connection = DatabasesConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setInt(1, capQuyen.getMa_nhom_quyen());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public CapQuyen search(int id) {
        return null;
    }


    public boolean check(CapQuyen capQuyen) {
        String query1 = "select * from cap_quyen where ma_nhom_quyen=? and ma_danh_muc_quan_ly=? and hanh_dong = ?";
        try (Connection connection = DatabasesConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(query1);) {
            statement.setInt(1, capQuyen.getMa_nhom_quyen());
            statement.setInt(2, capQuyen.getMa_danh_muc_quan_ly());
            statement.setString(3, capQuyen.getHanh_dong());
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
