package org.projects.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.projects.config.DatabasesConfig;

public class UserDao {
    public boolean DangKy(String tendangnhap,String matkhau,String sdt) {
        String user_sql = "INSERT INTO users(username,password,phone) Value(?,?,?)";
        try(Connection connection = DatabasesConfig.getConnection();
            // thuc hien cau lenh sql
            PreparedStatement pst = connection.prepareStatement(user_sql)) {
                    pst.setString(1, tendangnhap);//ten dang nhap la primary key
                    pst.setString(2, matkhau);
                    pst.setString(3, sdt);
                    int row = pst.executeUpdate(); // thuc thi cau lenh sql + return ve so dong duoc them vao
                    return row > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    } 
}
