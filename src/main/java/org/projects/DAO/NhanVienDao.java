package org.projects.DAO;

import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.config.DatabasesConfig;
import org.projects.entity.NhaCungCapEntity;
import org.projects.entity.NhanVienEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDao implements ChucNangDAO<NhanVienEntity> {

    @Override
    public List<NhanVienEntity> showlist() {
        List<NhanVienEntity> list = new ArrayList<>();
        String query = "select * from nhan_vien;";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                NhanVienEntity nve = new NhanVienEntity(rs.getInt("ma_nhan_vien"),rs.getString("ten_nhan_vien"),
                        rs.getString("email"),rs.getString("so_dien_thoai"),rs.getString("chuc_vu"));
                list.add(nve);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int them(NhanVienEntity add) {
        return 0;
    }

    @Override
    public int sua(NhanVienEntity fix) {
        return 0;
    }

    @Override
    public int xoa(NhanVienEntity delete) {
        return 0;
    }

    @Override
    public int chitiet(NhanVienEntity detail) {
        return 0;
    }
}