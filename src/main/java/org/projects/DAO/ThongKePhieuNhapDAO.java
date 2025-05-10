package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.ThongKePhieuNhapEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ThongKePhieuNhapDAO {
    //bieu do tron the hien nha cung cap nao cung cấp nhiều nhất
    public HashMap<String,Integer> getnhacungcapvasoluong() {
        HashMap<String,Integer> hm = new HashMap<>();
        String query = "select pn.ma_nha_cung_cap,ncc.ten_nha_cung_cap,ctpn.so_luong\n" +
                "from nha_cung_cap ncc join phieu_nhap pn on ncc.ma_nha_cung_cap = pn.ma_nha_cung_cap join chi_tiet_phieu_nhap ctpn on pn.ma_phieu_nhap = ctpn.ma_phieu_nhap";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery();) {
            while(rs.next()) {
                ThongKePhieuNhapEntity tkpnE = new ThongKePhieuNhapEntity(rs.getInt("ma_nha_cung_cap"),
                                                                            rs.getString("ten_nha_cung_cap"),
                                                                            rs.getInt("so_luong"));
                hm.put(tkpnE.getTennhacungcap(),tkpnE.getSoluong());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    //bieu do cot so sánh tổng giá trị phiếu nhập theo ngày
    public HashMap<String,Double> gettonggiatriphieunhap() {
            HashMap<String,Double> hm = new HashMap<>();
        String query = "SELECT pn.ngay_nhap, SUM(pn.tong_gia_tri_nhap) AS tong_gia_tri_nhap " +
                "FROM phieu_nhap pn " +
                "GROUP BY pn.ngay_nhap " +
                "ORDER BY pn.ngay_nhap";
            try(Connection c = DatabasesConfig.getConnection();
                PreparedStatement prs = c.prepareStatement(query);
                ResultSet rs = prs.executeQuery();) {
                while(rs.next()) {
                    hm.put(rs.getString("ngay_nhap"),rs.getDouble("tong_gia_tri_nhap"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return hm;
    }

    public List<ThongKePhieuNhapEntity> getlist() {
        List<ThongKePhieuNhapEntity> lst = new ArrayList<>();
        String query = "SELECT DATE(ngay_nhap) as ngay, COUNT(*) AS so_phieu, SUM(tong_gia_tri_nhap) AS tong_gia_tri " +
                "FROM phieu_nhap " +
                "GROUP BY DATE(ngay_nhap) " +
                "ORDER BY ngay;";

        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);
        ResultSet rs = prs.executeQuery()) {
            while(rs.next()) {
                lst.add(new ThongKePhieuNhapEntity(rs.getString("ngay"),rs.getInt("so_phieu"),rs.getDouble("tong_gia_tri")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    //lọc theo các tiêu chí
    public HashMap<String,Double> loctheonhieutieuchi(String from,String to,String tenncc,String tennv) {
        HashMap<String,Double> hm = new HashMap<>();
        String query = "SELECT ncc.ten_nha_cung_cap, SUM(pn.tong_gia_tri_nhap) AS tong_gia_tri\n" +
                "FROM phieu_nhap pn\n" +
                "JOIN nha_cung_cap ncc ON pn.ma_nha_cung_cap = ncc.ma_nha_cung_cap\n" +
                "JOIN nhan_vien nv ON pn.ma_nhan_vien = nv.ma_nhan_vien\n" +
                "WHERE (? IS NULL OR pn.ngay_nhap >= ?)\n" +
                "  AND (? IS NULL OR pn.ngay_nhap <= ?)\n" +
                "  AND (? IS NULL OR ncc.ten_nha_cung_cap = ?)\n" +
                "  AND (? IS NULL OR nv.ten_nhan_vien = ?)\n" +
                "GROUP BY ncc.ten_nha_cung_cap\n";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);
        ) {
            prs.setString(1, from);
            prs.setString(2, from);
            prs.setString(3, to);
            prs.setString(4, to);
            prs.setString(5, tenncc);
            prs.setString(6, tenncc);
            prs.setString(7, tennv);
            prs.setString(8, tennv);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                String tenNCC = rs.getString("ten_nha_cung_cap");
                double tongGiaTri = rs.getDouble("tong_gia_tri");
                hm.put(tenNCC,tongGiaTri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    //Pie
    public HashMap<String, Integer> getNCCVaSoLuongTheoTieuChi(String from, String to, String tenncc, String tennv) {
        HashMap<String, Integer> hm = new HashMap<>();
        String query = "SELECT ncc.ten_nha_cung_cap, SUM(ctpn.so_luong) AS so_luong " +
                "FROM phieu_nhap pn " +
                "JOIN nha_cung_cap ncc ON pn.ma_nha_cung_cap = ncc.ma_nha_cung_cap " +
                "JOIN nhan_vien nv ON pn.ma_nhan_vien = nv.ma_nhan_vien " +
                "JOIN chi_tiet_phieu_nhap ctpn ON pn.ma_phieu_nhap = ctpn.ma_phieu_nhap " +
                "WHERE (? IS NULL OR pn.ngay_nhap >= ?) " +
                "AND (? IS NULL OR pn.ngay_nhap <= ?) " +
                "AND (? IS NULL OR ncc.ten_nha_cung_cap = ?) " +
                "AND (? IS NULL OR nv.ten_nhan_vien = ?) " +
                "GROUP BY ncc.ten_nha_cung_cap";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement prs = c.prepareStatement(query)) {

            prs.setString(1, from); prs.setString(2, from);
            prs.setString(3, to); prs.setString(4, to);
            prs.setString(5, tenncc); prs.setString(6, tenncc);
            prs.setString(7, tennv); prs.setString(8, tennv);

            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                hm.put(rs.getString("ten_nha_cung_cap"), rs.getInt("so_luong"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }


    public List<ThongKePhieuNhapEntity> getListTheoTieuChi(String from, String to, String tenncc, String tennv) {
        List<ThongKePhieuNhapEntity> lst = new ArrayList<>();
        String query = "SELECT DATE(pn.ngay_nhap) as ngay, COUNT(*) AS so_phieu_nhap, SUM(pn.tong_gia_tri_nhap) AS tong_gia_tri " +
                "FROM phieu_nhap pn " +
                "JOIN nha_cung_cap ncc ON pn.ma_nha_cung_cap = ncc.ma_nha_cung_cap " +
                "JOIN nhan_vien nv ON pn.ma_nhan_vien = nv.ma_nhan_vien " +
                "WHERE (? IS NULL OR pn.ngay_nhap >= ?) " +
                "AND (? IS NULL OR pn.ngay_nhap <= ?) " +
                "AND (? IS NULL OR ncc.ten_nha_cung_cap = ?) " +
                "AND (? IS NULL OR nv.ten_nhan_vien = ?) " +
                "GROUP BY DATE(pn.ngay_nhap) " +
                "ORDER BY ngay";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement prs = c.prepareStatement(query)) {

            prs.setString(1, from); prs.setString(2, from);
            prs.setString(3, to); prs.setString(4, to);
            prs.setString(5, tenncc); prs.setString(6, tenncc);
            prs.setString(7, tennv); prs.setString(8, tennv);

            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                lst.add(new ThongKePhieuNhapEntity(
                        rs.getString("ngay"),
                        rs.getInt("so_phieu_nhap"),
                        rs.getDouble("tong_gia_tri")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    public static Date layngaynhapnhonhat() {
        String query = "select min(pn.ngay_nhap) as ngay\n" +
                "from phieu_nhap pn";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);
        ResultSet rs = prs.executeQuery()) {
            if (rs.next()) {
                return rs.getDate("ngay");
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date layngayhientai() {
        String query = "select date(now()) as ngayhientai";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);
        ResultSet rs = prs.executeQuery()) {
            if (rs.next()) {
                return rs.getDate("ngayhientai");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
