package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.ThongKePhieuNhapEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
}
