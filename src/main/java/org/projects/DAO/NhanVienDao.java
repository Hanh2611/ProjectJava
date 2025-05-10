package org.projects.DAO;

import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.config.DatabasesConfig;
import org.projects.entity.NhaCungCapEntity;
import org.projects.entity.NhanVienEntity;
import org.projects.entity.NhomQuyen;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDao implements ChucNangDAO<NhanVienEntity> {

    @Override
    public List<NhanVienEntity> showlist() {
        List<NhanVienEntity> list = new ArrayList<>();
        String query = "SELECT * FROM nhan_vien;";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                NhanVienEntity nve = new NhanVienEntity(
                        rs.getInt("ma_nhan_vien"),
                        rs.getString("ten_nhan_vien"),
                        rs.getString("email"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("chuc_vu"),
                        rs.getInt("luong"),
                        rs.getBoolean("gioi_tinh")
                        );
                list.add(nve);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int them(NhanVienEntity add) {
        String query = "insert into nhan_vien(ma_nhan_vien , ten_nhan_vien , email, so_dien_thoai , chuc_vu, luong , gioi_tinh) values(?,?,?,?,?,?,?);";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);)
        {
           ps.setInt(1 , add.getMaNhanVien());
           ps.setString(2 , add.getTenNhanVien());
           ps.setString(3 , add.getEmailNhanVien());
           ps.setString(4 , add.getSdtNhanVien());
           ps.setString(5 , add.getChucvu());
           ps.setInt(6, add.getLuong());
           ps.setBoolean(7,add.getGioitinh());
//            System.out.println("Chuẩn bị insert:");
//            System.out.println("Mã NV: " + add.getMaNhanVien());
//            System.out.println("Tên: " + add.getTenNhanVien());
//            System.out.println("Email: " + add.getEmailNhanVien());
//            System.out.println("SĐT: " + add.getSdtNhanVien());
//            System.out.println("Chức vụ: " + add.getChucvu());
//            System.out.println("Lương: " + add.getLuong());
//            System.out.println("Giới tính: " + add.getGioitinh());
           return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int sua(NhanVienEntity fix) {
        String query = "update nhan_vien set ten_nhan_vien = ?,email = ?,so_dien_thoai = ?,chuc_vu = ? , luong = ? , gioi_tinh = ? where ma_nhan_vien= ?";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query))  {
            ps.setString(1,fix.getTenNhanVien());
            ps.setString(2,fix.getEmailNhanVien());
            ps.setString(3,fix.getSdtNhanVien());
            ps.setString(4,fix.getChucvu());
            ps.setInt(5,fix.getLuong());
            ps.setBoolean(6,fix.getGioitinh());
            ps.setInt(7,fix.getMaNhanVien());
            return ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int xoa(NhanVienEntity delete) {
        String query = "delete from nhan_vien where ma_nhan_vien= ?";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);) {
            ps.setInt(1,delete.getMaNhanVien());
            return ps.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public NhanVienEntity search(int id) {
        String query = "select * from nhan_vien where ma_nhan_vien= ?";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);) {
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new NhanVienEntity(rs.getInt("ma_nhan_vien"),rs.getString("ten_nhan_vien"),rs.getString("email"),rs.getString("so_dien_thoai"),rs.getString("chuc_vu"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

//    public static int updateThemMaNguoiDungChoNhanVienSauKhiTaoTaiKhoan(int manguoidung,int manv) {
//        int result = 0;
//        String query = "update nhan_vien set ma_nguoi_dung = ? where ma_nhan_vien = ?";
//        try (Connection connection = DatabasesConfig.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, manguoidung);
//            statement.setInt(2, manv);
//            result = statement.executeUpdate();
//            return result;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
public static int updateThemMaNguoiDungChoNhanVienSauKhiTaoTaiKhoan(int manguoidung, int manv) {
    int result = 0;
    String query = "UPDATE nhan_vien SET ma_nguoi_dung = ? WHERE ma_nhan_vien = ?";

    try (Connection connection = DatabasesConfig.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        System.out.println("Update nhân viên có mã: " + manv + " => mã người dùng: " + manguoidung);

        statement.setInt(1, manguoidung);
        statement.setInt(2, manv);

        result = statement.executeUpdate();
        System.out.println("Số dòng bị ảnh hưởng: " + result);

        return result;
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}

}