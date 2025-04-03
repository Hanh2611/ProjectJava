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
        return 0;
    }

    @Override
    public int sua(NhaCungCapEntity fix) {
        return 0;
    }

    @Override
    public int xoa(NhaCungCapEntity delete) {
        return 0;
    }

    @Override
    public int chitiet(NhaCungCapEntity detail) {
        return 0;
    }
}
