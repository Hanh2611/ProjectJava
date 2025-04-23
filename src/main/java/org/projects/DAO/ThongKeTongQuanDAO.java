package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.HoaDonEntity;
import org.projects.entity.SanPhamEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
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

    //lay ra tháng-năm -> tổng doanh thu tháng đó
    public HashMap<String,Double> getDoanhthutheothang() {
        HashMap<String,Double> doanhthutheothang = new HashMap<>();
        String query = "SELECT DATE_FORMAT(ngay_tao, '%Y-%m') AS thang, SUM(tong_gia_tri) AS doanh_thu\n" +
                "FROM hoa_don\n" +
                "GROUP BY thang\n" +
                "ORDER BY thang;";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery()) {
            while (rs.next()) {
                String namthang = rs.getString("thang");
                double tonggiatri = rs.getDouble("doanh_thu");
                doanhthutheothang.put(namthang,tonggiatri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhthutheothang;
    }

    public HashMap<String,Double> getnhacungcapvatonggiatrinhap() {
        HashMap<String,Double> nhacungcapvatonggiatrinhap = new HashMap<>();
        String query = "select ncc.ten_nha_cung_cap,pn.tong_gia_tri_nhap\n" +
                "from nha_cung_cap ncc join phieu_nhap pn on ncc.ma_nha_cung_cap = pn.ma_nha_cung_cap\n";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery();) {
            while(rs.next()) {
                String ten_nha_cung_cap = rs.getString("ten_nha_cung_cap");
                double tonggiatri = rs.getDouble("tong_gia_tri_nhap");
                nhacungcapvatonggiatrinhap.put(ten_nha_cung_cap,tonggiatri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhacungcapvatonggiatrinhap;
    }

    public HashMap<Integer,String> gethoadontrangthai() {
        HashMap<Integer,String> hoadontrangthai = new HashMap<>();
        String query = "select hd.ma_hoa_don,hd.trang_thai\n" +
                "from hoa_don hd;";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery()) {
            while(rs.next()) {
                int mahoadon = rs.getInt("ma_hoa_don");
                String trangthai = rs.getString("trang_thai");
                hoadontrangthai.put(mahoadon,trangthai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoadontrangthai;
    }

    public HashMap<Integer,String> gettaikhoantrangthai() {
        HashMap<Integer,String> taikhoantrangthai = new HashMap<>();
        String query = "select tk.ma_nguoi_dung,tk.trang_thai\n" +
                "from tai_khoan tk;";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery()) {
            while(rs.next()) {
                int manguoidung = rs.getInt("ma_nguoi_dung");
                String trangthai = rs.getString("trang_thai");
                taikhoantrangthai.put(manguoidung,trangthai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taikhoantrangthai;
    }

    public HashMap<Integer,String> getsanphamtrangthai() {
        HashMap<Integer,String> sanphamtrangthai = new HashMap<>();
        String query = "\n" +
                "select sp.ma_san_pham,sp.trang_thai\n" +
                "from san_pham sp;";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery();) {
            while (rs.next()) {
                int masanpham = rs.getInt("ma_san_pham");
                String trangthai = rs.getString("trang_thai");

                String tt = "Đang kinh doanh";
                if(trangthai.equals("0")) tt = "Ngừng kinh doanh";
                sanphamtrangthai.put(masanpham,tt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanphamtrangthai;
    }

}
