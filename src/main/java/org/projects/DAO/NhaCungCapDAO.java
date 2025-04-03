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
        String query = "update nha_cung_cap set ten_nha_cung_cap = ? so_dien_thoai = ? email = ? dia_chi_nha_cung_cap = ? where ma_nha_cung_cap= ?";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement ps = c.prepareStatement(query);)  {
            ps.setString(1,fix.getTenNCC());
            ps.setString(2,fix.getSoDienThoaiNCC());
            ps.setString(3,fix.getEmailNCC());
            ps.setString(4,fix.getDiaCHiNCC());
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
            ps.setString(1,delete.getTenNCC());
            return ps.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int chitiet(NhaCungCapEntity detail) {
        return 0;
    }
}
