package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.HoaDonEntity;
import org.projects.entity.SanPhamEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKeTongQuanDAO {
    //lay tong so luong ton trong table san pham
    public double getSoluongtonsanpham() {
        double tongsoluongton = 0;
        String query = "select sum(so_luong_ton) from san_pham";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            if(rs.next()) {
                tongsoluongton = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongsoluongton;
    }

    //lay tong gia tri trong hoa don
    public double getTonggiatri() {
        double tonggiatri = 0;
        String query = "select sum(tong_gia_tri) from hoa_don";
        try(Connection c  = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            if(rs.next()) {
                tonggiatri = rs.getDouble(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return tonggiatri;
    }

    public int getSoluonghoadon() {
        int soluonghoadon = 0;
        String query = "select count(*) from hoa_don";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            if(rs.next()) {
                soluonghoadon = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soluonghoadon;
    }

    public int getSoluongkhachhang() {
        int soluongkhachhang = 0;
        String query = "select count(*) from khach_hang";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            if(rs.next()) {
                soluongkhachhang = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soluongkhachhang;
    }
}
