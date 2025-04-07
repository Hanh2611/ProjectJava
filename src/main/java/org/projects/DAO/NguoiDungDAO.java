package org.projects.DAO;

import org.projects.entity.NguoiDung;

import java.util.List;

public class NguoiDungDAO implements ChucNangDAO<NguoiDung> {

    @Override
    public List<NguoiDung> showlist() {
        return List.of();
    }

    @Override
    public int them(NguoiDung add) {
        return 0;
    }

    @Override
    public int sua(NguoiDung fix) {
        return 0;
    }

    @Override
    public int xoa(NguoiDung delete) {
        return 0;
    }

    @Override
    public NguoiDung search(int id) {
        return null;
    }

}
