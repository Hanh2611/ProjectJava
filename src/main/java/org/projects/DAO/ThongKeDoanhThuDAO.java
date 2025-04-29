package org.projects.DAO;

import org.projects.GUI.utils.Helper;
import org.projects.config.DatabasesConfig;
import org.projects.entity.ThongkeDoanhThuEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThongKeDoanhThuDAO {
    //lấy ra tổng tiền của hóa đơn theo ngày
    public HashMap<String,Double> laytongtiencuahoadontheongay(String from,String to) {
        HashMap<String,Double> hm = new HashMap<>();
        if(from == null || from.trim().isEmpty() || to == null || to.trim().isEmpty()) return laytatcatongtiencuahoadontheongay();
        String query = "select DATE(hd.ngay_tao) as ngay,sum(hd.tong_gia_tri) as tong_hoa_don\n" +
                "from hoa_don hd\n" +
                "where (hd.ngay_tao between ? and ?) and hd.trang_thai = 'da_thanh_toan'\n" +
                "group by DATE(hd.ngay_tao)\n" +
                "order by DATE(hd.ngay_tao)";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
           ) {
            prs.setString(1, from);
            prs.setString(2, to);
            ResultSet rs = prs.executeQuery();
            while(rs.next()) {
                hm.put(rs.getString("ngay"),rs.getDouble("tong_hoa_don"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    public HashMap<String,Double> laytatcatongtiencuahoadontheongay() {
        HashMap<String,Double> hm = new HashMap<>();
        String query = "select DATE(hd.ngay_tao) as ngay,sum(hd.tong_gia_tri) as tong_hoa_don\n" +
                "from hoa_don hd\n" +
                "where hd.trang_thai = 'da_thanh_toan'\n" +
                "group by DATE(hd.ngay_tao)\n" +
                "order by DATE(hd.ngay_tao)";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
        ResultSet rs = prs.executeQuery();) {
            while(rs.next()) {
                hm.put(rs.getString("ngay"),rs.getDouble("tong_hoa_don"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    public List<ThongkeDoanhThuEntity> laydanhsachtheongay(String from,String to) {
        List<ThongkeDoanhThuEntity> lst = new ArrayList<>();
        if(from == null || from.trim().isEmpty() || to == null || to.trim().isEmpty()) return laydanhsachtatca();
        String query = "select hd.ma_hoa_don,kh.ten_khach_hang,DATE (hd.ngay_tao) as ngay,SUM(hd.tong_gia_tri) as tong_hoa_don\n" +
                "from hoa_don hd join khach_hang kh on hd.ma_khach_hang = kh.ma_khach_hang\n" +
                "where (hd.ngay_tao between ? and ?) and hd.trang_thai = 'da_thanh_toan'\n" +
                "group by DATE (hd.ngay_tao), kh.ten_khach_hang, hd.ma_hoa_don\n" +
                "order by DATE (hd.ngay_tao)\n";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);) {
            prs.setString(1, from);
            prs.setString(2, to);
            ResultSet rs = prs.executeQuery();
            while(rs.next()) {
                lst.add(new ThongkeDoanhThuEntity(rs.getInt("ma_hoa_don"),rs.getString("ten_khach_hang"),rs.getString("ngay"),rs.getDouble("tong_hoa_don")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public List<ThongkeDoanhThuEntity> laydanhsachtatca() {
        List<ThongkeDoanhThuEntity> lst = new ArrayList<>();
        String query = "select hd.ma_hoa_don,kh.ten_khach_hang,DATE (hd.ngay_tao) as ngay,SUM(hd.tong_gia_tri) as tong_hoa_don\n" +
                "from hoa_don hd join khach_hang kh on hd.ma_khach_hang = kh.ma_khach_hang\n" +
                "where hd.trang_thai = 'da_thanh_toan'\n" +
                "group by DATE (hd.ngay_tao), kh.ten_khach_hang, hd.ma_hoa_don\n" +
                "order by DATE (hd.ngay_tao)";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);
        ResultSet rs = prs.executeQuery();) {
            while (rs.next()) {
                lst.add(new ThongkeDoanhThuEntity(rs.getInt("ma_hoa_don"),rs.getString("ten_khach_hang"),rs.getString("ngay"),rs.getDouble("tong_hoa_don")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    //lấy ra tổng hóa đơn theo tháng
    public HashMap<String,Double> laytongtienhoadontheothang(String thang,String nam) {
        HashMap<String,Double> hm = new HashMap<>();
        if(thang.equals("Tất cả")) {
            return laytatcatongtienhoadontheothang();
        }
        String thangFormat = String.format("%02d", Integer.parseInt(thang));
        int songaytrongthang = Helper.layNgaytrongthang(Integer.parseInt(thang),Integer.parseInt(nam));
        String startDate = nam + "-" + thangFormat + "-01";
        String endDate = nam + "-" + thangFormat + "-" + String.valueOf(songaytrongthang);
        String query = "select MONTH(hd.ngay_tao) as thang,YEAR(hd.ngay_tao) as nam,SUM(hd.tong_gia_tri) as tong_hoa_don\n" +
                "from hoa_don hd\n" +
                "where hd.ngay_tao >= ? and hd.ngay_tao <= ?\n" +
                "group by MONTH(hd.ngay_tao), YEAR(hd.ngay_tao)\n" +
                "order by nam,thang";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);) {
            prs.setString(1, startDate);
            prs.setString(2, endDate);
            ResultSet rs = prs.executeQuery();
            while(rs.next()) {
                String thangnam = rs.getString("thang") + "-" + rs.getString("nam");
                hm.put(thangnam,rs.getDouble("tong_hoa_don"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    public HashMap<String,Double> laytatcatongtienhoadontheothang() {
        HashMap<String,Double> hm = new HashMap<>();
        String query = "select MONTH(hd.ngay_tao) as thang,YEAR(hd.ngay_tao) as nam,SUM(hd.tong_gia_tri) as tong_hoa_don\n" +
                "from hoa_don hd\n" +
                "group by MONTH(hd.ngay_tao), YEAR(hd.ngay_tao)\n" +
                "order by nam,thang";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery()) {
            while(rs.next()) {
                String thangnam = rs.getString("thang") + "-" + rs.getString("nam");
                hm.put(thangnam,rs.getDouble("tong_hoa_don"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }
}
