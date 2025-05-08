package org.projects.DAO;

import org.projects.GUI.utils.Helper;
import org.projects.config.DatabasesConfig;
import org.projects.entity.ThongkeDoanhThuEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
        int m = Integer.parseInt(thang);
        int y = Integer.parseInt(nam);
       String startDate = String.format("%04d-%02d-01",y,m);
        int nextMonth = (m == 12) ? 1 : m +1;
        int nextYear = (m == 12) ? y + 1 : y;
        String endDate = String.format("%04d-%02d-01",nextYear,nextMonth);

        String query = "select MONTH(hd.ngay_tao) as thang,YEAR(hd.ngay_tao) as nam,SUM(hd.tong_gia_tri) as tong_hoa_don\n" +
                "from hoa_don hd\n" +
                "where hd.ngay_tao >= ? and hd.ngay_tao < ? and hd.trang_thai = 'da_thanh_toan'\n" +
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
                "where hd.trang_thai = 'da_thanh_toan'\n" +
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

    //lấy ra bảng chứa tháng,năm,tổng doanh thu trong tháng,lợi nhuận thu về
    //lợi nhuận thu về = tổng doanh thu - (chi phí nhập hàng + lương tất cả nhân viên (mặc định cho là tất cả nhân viên đều làm trong tháng)
    public List<ThongkeDoanhThuEntity> laydanhsachtheothangnam(String thang,String nam) {
        List<ThongkeDoanhThuEntity> lst = new ArrayList<>();
        if(thang.equals("Tất cả")) {
            return laydanhsachtatcatheothangnam();
        }
        int m = Integer.parseInt(thang);
        int y = Integer.parseInt(nam);
        String startDate = String.format("%04d-%02d-01",y,m);
        int nextMonth = (m == 12) ? 1 : m +1;
        int nextYear = (m == 12) ? y + 1 : y;
        String endDate = String.format("%04d-%02d-01",nextYear,nextMonth);
        String query = "SELECT MONTH(hd.ngay_tao) AS thang,\n" +
                "       YEAR(hd.ngay_tao) AS nam,\n" +
                "       SUM(hd.tong_gia_tri) AS tong_hoa_don,\n" +
                "       ANY_VALUE(IFNULL(pn.tong_chi_phi_nhap_trong_thang,0)) as tong_chi_phi_nhap_trong_thang,\n" +
                "        (SUM(hd.tong_gia_tri) - ANY_VALUE(IFNULL(pn.tong_chi_phi_nhap_trong_thang,0) +  (select SUM(luong) from nhan_vien) )) as loi_nhuan\n" +
                "from hoa_don hd\n" +
                "left join (\n" +
                "    select MONTH(ngay_nhap) as thang_nhap,\n" +
                "           YEAR(ngay_nhap) as nam_nhap,\n" +
                "           SUM(tong_gia_tri_nhap) as tong_chi_phi_nhap_trong_thang\n" +
                "    from phieu_nhap\n" +
                "    group by thang_nhap,nam_nhap\n" +
                ") pn on MONTH(hd.ngay_tao) = pn.thang_nhap and YEAR(hd.ngay_tao) = pn.nam_nhap\n" +
                "where hd.ngay_tao >= ? and hd.ngay_tao < ? and hd.trang_thai = 'da_thanh_toan'\n" +
                "group by nam,thang\n" +
                "order by nam,thang;\n";
        try (Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);
         ) {
            prs.setString(1, startDate);
            prs.setString(2, endDate);
            ResultSet rs = prs.executeQuery();
            while(rs.next()) {
                lst.add(new ThongkeDoanhThuEntity(rs.getString("thang"),rs.getString("nam"),rs.getDouble("tong_hoa_don"),rs.getDouble("tong_chi_phi_nhap_trong_thang"),rs.getDouble("loi_nhuan")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public List<ThongkeDoanhThuEntity> laydanhsachtatcatheothangnam() {
        List<ThongkeDoanhThuEntity> lst = new ArrayList<>();
        String query = "SELECT MONTH(hd.ngay_tao) AS thang,\n" +
                "       YEAR(hd.ngay_tao) AS nam,\n" +
                "       SUM(hd.tong_gia_tri) AS tong_hoa_don,\n" +
                "       ANY_VALUE(IFNULL(pn.tong_chi_phi_nhap_trong_thang,0)) as tong_chi_phi_nhap_trong_thang,\n" +
                "        (SUM(hd.tong_gia_tri) - ANY_VALUE(IFNULL(pn.tong_chi_phi_nhap_trong_thang,0) +  (select SUM(luong) from nhan_vien where chuc_vu = 'Giám Đốc'))) as loi_nhuan\n" +
                "from hoa_don hd\n" +
                "left join (\n" +
                "    select MONTH(ngay_nhap) as thang_nhap,\n" +
                "           YEAR(ngay_nhap) as nam_nhap,\n" +
                "           SUM(tong_gia_tri_nhap) as tong_chi_phi_nhap_trong_thang\n" +
                "    from phieu_nhap\n" +
                "    group by thang_nhap,nam_nhap\n" +
                ") pn on MONTH(hd.ngay_tao) = pn.thang_nhap and YEAR(hd.ngay_tao) = pn.nam_nhap\n" +
                "where hd.trang_thai = 'da_thanh_toan'\n" +
                "group by nam,thang\n" +
                "order by nam,thang;\n";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery();) {
            while(rs.next()) {
                lst.add(new ThongkeDoanhThuEntity(rs.getString("thang"),rs.getString("nam"),rs.getDouble("tong_hoa_don"),rs.getDouble("tong_chi_phi_nhap_trong_thang"),rs.getDouble("loi_nhuan")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    //lấy ra tổng hóa đơn theo năm
    public HashMap<String,Double> laytonghoadontheonam(String nam) {
        HashMap<String,Double> hm = new HashMap<>();
        if(nam.equals("Tất cả")) return laytonghoadontatcacacnam();
        int y = Integer.parseInt(nam);
        String startDate = String.format("%04d-01-01",y);
        String endDate = String.format("%04d-01-01",y + 1);
        String query = "select YEAR(hd.ngay_tao) as nam,SUM(hd.tong_gia_tri) as tong_hoa_don\n" +
                "from hoa_don hd \n" +
                "where hd.ngay_tao >= ? and hd.ngay_tao < ? and hd.trang_thai = 'da_thanh_toan'\n" +
                "group by YEAR(hd.ngay_tao)\n" +
                "order by nam;";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);
        ) {
            prs.setString(1, startDate);
            prs.setString(2, endDate);
            ResultSet rs = prs.executeQuery();
            while(rs.next()) {
                hm.put(rs.getString("nam"),rs.getDouble("tong_hoa_don"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    public HashMap<String,Double> laytonghoadontatcacacnam() {
        HashMap<String,Double> hm = new HashMap<>();
        String query = "select YEAR(hd.ngay_tao) as nam,SUM(hd.tong_gia_tri) as tong_hoa_don\n" +
                "from hoa_don hd \n" +
                "where hd.trang_thai = 'da_thanh_toan'\n" +
                "group by YEAR(hd.ngay_tao)\n" +
                "order by nam;";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
        ResultSet rs = prs.executeQuery();) {
            while(rs.next()) {
                hm.put(rs.getString("nam"),rs.getDouble("tong_hoa_don"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    public List<ThongkeDoanhThuEntity> laydanhsachtheonam(String nam) {
        List<ThongkeDoanhThuEntity> lst = new ArrayList<>();
        if(nam.equals("Tất cả")) {
            return laydanhsachtatcatheonam();
        }
        int y = Integer.parseInt(nam);
        String startDate = String.format("%04d-01-01",y);
        String endDate = String.format("%04d-12-31",y);
        String query = "select YEAR(hd.ngay_tao) as nam,\n" +
                "       SUM(hd.tong_gia_tri) as tong_hoa_don,\n" +
                "       ANY_VALUE(IFNULL(pn.tong_chi_phi_nhap_trong_nam,0)) as tong_chi_phi_nhap_trong_nam,\n" +
                "       (SUM(hd.tong_gia_tri) - ANY_VALUE(IFNULL(pn.tong_chi_phi_nhap_trong_nam,0) + (SELECT SUM(luong) from nhan_vien) )) as loi_nhuan\n" +
                "from hoa_don hd\n" +
                "left join (\n" +
                "    select YEAR(ngay_nhap) as nam_nhap,\n" +
                "           SUM(tong_gia_tri_nhap) as tong_chi_phi_nhap_trong_nam\n" +
                "    from phieu_nhap\n" +
                "    group by nam_nhap\n" +
                ") pn on YEAR(hd.ngay_tao) = nam_nhap\n" +
                "where hd.ngay_tao >= ? and hd.ngay_tao <= ? and hd.trang_thai = 'da_thanh_toan'\n" +
                "group by nam\n" +
                "order by nam";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);
        ) {
            prs.setString(1,startDate);
            prs.setString(2,endDate);
            ResultSet rs = prs.executeQuery();
            while(rs.next()) {
                lst.add(new ThongkeDoanhThuEntity(rs.getString("nam"),rs.getDouble("tong_hoa_don"),rs.getDouble("tong_chi_phi_nhap_trong_nam"),rs.getDouble("loi_nhuan")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public List<ThongkeDoanhThuEntity> laydanhsachtatcatheonam() {
        List<ThongkeDoanhThuEntity> lst = new ArrayList<>();
        String query = "select YEAR(hd.ngay_tao) as nam,\n" +
                "       SUM(hd.tong_gia_tri) as tong_hoa_don,\n" +
                "       ANY_VALUE(IFNULL(pn.tong_chi_phi_nhap_trong_nam,0)) as tong_chi_phi_nhap_trong_nam,\n" +
                "       (SUM(hd.tong_gia_tri) - ANY_VALUE(IFNULL(pn.tong_chi_phi_nhap_trong_nam,0) + (SELECT SUM(luong) from nhan_vien) )) as loi_nhuan\n" +
                "from hoa_don hd\n" +
                "left join (\n" +
                "    select YEAR(ngay_nhap) as nam_nhap,\n" +
                "           SUM(tong_gia_tri_nhap) as tong_chi_phi_nhap_trong_nam\n" +
                "    from phieu_nhap\n" +
                "    group by nam_nhap\n" +
                ") pn on YEAR(hd.ngay_tao) = nam_nhap\n" +
                "where hd.trang_thai = 'da_thanh_toan'\n" +
                "group by nam\n" +
                "order by nam";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
        ResultSet rs = prs.executeQuery();) {
            while(rs.next()) {
                lst.add(new ThongkeDoanhThuEntity(rs.getString("nam"),rs.getDouble("tong_hoa_don"),rs.getDouble("tong_chi_phi_nhap_trong_nam"),rs.getDouble("loi_nhuan")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

}
