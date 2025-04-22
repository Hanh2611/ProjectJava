package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.ThongkeHoaDonEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThongKeHoaDonDAO {
    public HashMap<String,Integer> getALLsoluong() {
        HashMap<String,Integer> soluong = new HashMap<>();
        String query = "select DATE(hd.ngay_tao) as ngay,COUNT(*) as so_hoa_don\n" +
                "from hoa_don hd\n" +
                "group by DATE(hd.ngay_tao);";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);
        ResultSet rs = prs.executeQuery()) {
            while (rs.next()) {
                soluong.put(rs.getString(1), rs.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soluong;
    }
    public HashMap<String,Integer> getSoluongtheongay(String from, String to,String trangthai) {
        HashMap<String,Integer> soluongtheongay = new HashMap<>();
        String query = "SELECT DATE(hd.ngay_tao) AS ngay, COUNT(*) AS so_hoa_don\n" +
                "FROM hoa_don hd\n" +
                "WHERE hd.ngay_tao BETWEEN ? AND ?\n" +
                "  AND (? = 'Tất cả' OR hd.trang_thai = ?)\n" +
                "GROUP BY DATE(hd.ngay_tao);\n";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);) {
           prs.setString(1, from);
           prs.setString(2, to);
           prs.setString(3, trangthai);
           prs.setString(4, trangthai);
           ResultSet rs = prs.executeQuery();
           while (rs.next()) {
               soluongtheongay.put(rs.getString(1), rs.getInt(2));
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soluongtheongay;
    }

    public List<ThongkeHoaDonEntity> showtable() {
        List<ThongkeHoaDonEntity> lst = new ArrayList<>();
        String query = "SELECT DATE(hd.ngay_tao) AS ngay, COUNT(*) AS so_luong_hoa_don, hd.trang_thai\n" +
                "FROM hoa_don hd\n" +
                "GROUP BY DATE(hd.ngay_tao), hd.trang_thai";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery()) {
            while (rs.next()) {
                lst.add(new ThongkeHoaDonEntity(rs.getString("ngay"),rs.getInt("so_luong_hoa_don"),rs.getString("trang_thai")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }


}
