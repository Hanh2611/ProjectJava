package org.projects.DAO;

import org.projects.config.DatabasesConfig;
import org.projects.entity.NhaCungCapEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO implements ChucNangDAO<NhaCungCapEntity> {

    @Override
    public List<NhaCungCapEntity> showlist() {
        List<NhaCungCapEntity> list = new ArrayList<>();
        String query = "select * from nha_cung_cap;";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                NhaCungCapEntity ncc = new NhaCungCapEntity(rs.getInt("ma_nha_cung_cap"),rs.getString("ten_nha_cung_cap"),rs.getString("so_dien_thoai"),rs.getString("email"),rs.getString("dia_chi_nha_cung_cap"));
                list.add(ncc);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int them(NhaCungCapEntity add) {
        String query = "insert into nha_cung_cap(ten_nha_cung_cap,so_dien_thoai,email,dia_chi_nha_cung_cap) values(?,?,?,?);";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);)
            {
            ps.setString(1,add.getTenNCC());
            ps.setString(2,add.getSoDienThoaiNCC());
            ps.setString(3,add.getEmailNCC());
            ps.setString(4,add.getDiaCHiNCC());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int sua(NhaCungCapEntity fix) {
        String query = "update nha_cung_cap set ten_nha_cung_cap = ?,so_dien_thoai = ?,email = ?,dia_chi_nha_cung_cap = ? where ma_nha_cung_cap= ?";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement ps = c.prepareStatement(query);)  {
            ps.setString(1,fix.getTenNCC());
            ps.setString(2,fix.getSoDienThoaiNCC());
            ps.setString(3,fix.getEmailNCC());
            ps.setString(4,fix.getDiaCHiNCC());
            ps.setInt(5,fix.getMaNCC());
            return ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int xoa(NhaCungCapEntity delete) {
        String query = "delete from nha_cung_cap where ma_nha_cung_cap= ?";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement ps = c.prepareStatement(query);) {
            ps.setInt(1,delete.getMaNCC());
            return ps.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public NhaCungCapEntity search(int id) {
        String query = "select * from nha_cung_cap where ma_nha_cung_cap= ?";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);) {
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new NhaCungCapEntity(rs.getInt("ma_nha_cung_cap"),rs.getString("ten_nha_cung_cap"),rs.getString("so_dien_thoai"),rs.getString("email"),rs.getString("dia_chi_nha_cung_cap"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public NhaCungCapEntity layNhaCungCapByMaPhieuNhap(int maphieunhap) {
        String query = "select ncc.ten_nha_cung_cap,ncc.so_dien_thoai,ncc.dia_chi_nha_cung_cap,ncc.email\n" +
                "from phieu_nhap pn  join  nha_cung_cap ncc on pn.ma_nha_cung_cap = ncc.ma_nha_cung_cap\n" +
                "where pn.ma_phieu_nhap = ?";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query)) {
            prs.setInt(1,maphieunhap);
            ResultSet rs = prs.executeQuery();
            if(rs.next()) {
                return new NhaCungCapEntity(rs.getString("ten_nha_cung_cap"),rs.getString("so_dien_thoai"),rs.getString("email"),rs.getString("dia_chi_nha_cung_cap"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
