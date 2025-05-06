package org.projects.DAO;
import org.projects.config.DatabasesConfig;
import org.projects.entity.KhachHangEntity;
import org.projects.entity.NhanVienEntity;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO implements ChucNangDAO<KhachHangEntity> {

    @Override
    public List<KhachHangEntity> showlist() {
        List<KhachHangEntity> list = new ArrayList<>();
        String query = "SELECT * FROM khach_hang;";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                KhachHangEntity khe = new KhachHangEntity(rs.getInt("ma_khach_hang"), rs.getString("ten_khach_hang"), rs.getString("so_dien_thoai"), rs.getString("dia_chi"));
                list.add(khe);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int them(KhachHangEntity add) {
        String query = "insert into khach_hang values(?,?,?,?,?);";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);){
            ps.setInt(1 , add.getMa());
            ps.setInt(2 , 10);
            ps.setString(3 , add.getTen());
            ps.setString(4 , add.getSdt());
            ps.setString(5 , add.getDiaChi());
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int sua(KhachHangEntity fix) {
        String query = "update khach_hang set ten_khach_hang = ? , so_dien_thoai = ? , dia_chi = ? where ma_khach_hang = ?";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);){
            ps.setString(1 , fix.getTen());
            ps.setString(2 , fix.getSdt());
            ps.setString(3 , fix.getDiaChi());
            ps.setInt(4 , fix.getMa());
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int xoa(KhachHangEntity delete) {
        String query = "delete from khach_hang where ma_khach_hang = ?";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);) {
            ps.setInt(1,delete.getMa());
            return ps.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public KhachHangEntity search(int id) {
        String query = "select * from khach_hang where ma_khach_hang= ?";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);) {
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new KhachHangEntity(rs.getInt("ma_khach_hang"),rs.getString("ten_khach_hang"),rs.getString("so_dien_thoai"),rs.getString("dia_chi"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}