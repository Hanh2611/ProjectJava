package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.ThongKeSanPhamEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThongKeSanPhamDAO {
        public HashMap<String,Integer> getSoluongsanphamtheodanhmuc() {
            HashMap<String,Integer> soluongsanphamtheodanhmuc = new HashMap<>();
            String query = "SELECT dmsp.ten_danh_muc, COUNT(sp.ma_san_pham) AS so_luong_san_pham\n" +
                    "FROM danh_muc_san_pham dmsp\n" +
                    "         JOIN san_pham sp ON dmsp.ma_danh_muc = sp.phan_loai\n" +
                    "GROUP BY dmsp.ten_danh_muc;\n";
            try(Connection c = DatabasesConfig.getConnection();
                PreparedStatement prs = c.prepareStatement(query);
                ResultSet rs = prs.executeQuery();) {
                while(rs.next()) {
                    String tendanhmuc = rs.getString("ten_danh_muc");
                    int soLuong = rs.getInt("so_luong_san_pham");
                    soluongsanphamtheodanhmuc.put(tendanhmuc, soLuong);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return soluongsanphamtheodanhmuc;
        }

        public double getSoluongton() {
            double total = 0.0;
            String query = "select sum(sp.so_luong_ton)\n" +
                    "from san_pham sp";
            try(Connection c = DatabasesConfig.getConnection();
                PreparedStatement ps = c.prepareStatement(query);
                ResultSet rs = ps.executeQuery();) {
                if(rs.next()) {
                    total = rs.getDouble(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return total;
        }

        public List<ThongKeSanPhamEntity> listTable() {
            List<ThongKeSanPhamEntity> lst = new ArrayList<ThongKeSanPhamEntity>();
            String query = "SELECT\n" +
                    "    sp.ma_san_pham,\n" +
                    "    sp.ten_san_pham,\n" +
                    "    dm.ten_danh_muc,\n" +
                    "    sp.so_luong_ton,\n" +
                    "    sp.gia_ban,\n" +
                    "    sp.trang_thai\n" +
                    "FROM\n" +
                    "    san_pham sp\n" +
                    "        JOIN\n" +
                    "    danh_muc_san_pham dm ON sp.phan_loai = dm.ma_danh_muc;\n";
            try(Connection c = DatabasesConfig.getConnection();
                PreparedStatement prs = c.prepareStatement(query);
                ResultSet rs = prs.executeQuery()) {
                while(rs.next()) {
                    String tt = "Đang kinh doanh";
                    if(rs.getString("trang_thai").equals("0")) tt = "Ngừng kinh doanh";
                    lst.add(new ThongKeSanPhamEntity(rs.getInt("ma_san_pham"),
                                                    rs.getString("ten_san_pham"),
                                                    rs.getString("ten_danh_muc"),
                                                    rs.getDouble("so_luong_ton"),
                                                    rs.getDouble("gia_ban"),
                                                    tt));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return lst;
        }

        public List<String> getDanhmucSanpham() {
            List<String> list = new ArrayList<>();
            String query = "select * from danh_muc_san_pham;";
            try(Connection c = DatabasesConfig.getConnection();
                PreparedStatement prs = c.prepareStatement(query);
                ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("ten_danh_muc"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
}
