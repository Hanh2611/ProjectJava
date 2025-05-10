package org.projects.DAO;

import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.config.DatabasesConfig;
import org.projects.entity.NhaCungCapEntity;
import org.projects.entity.NhanVienEntity;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDao implements ChucNangDAO<NhanVienEntity> {

    @Override
    public List<NhanVienEntity> showlist() {
        List<NhanVienEntity> list = new ArrayList<>();
        String query = "SELECT * FROM nhan_vien WHERE is_delete = 0";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                NhanVienEntity nve = new NhanVienEntity(
                        rs.getInt("ma_nhan_vien"),
                        rs.getString("ten_nhan_vien"),
                        rs.getString("email"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("chuc_vu"),
                        rs.getInt("luong"),
                        rs.getBoolean("gioi_tinh")
                        );
                list.add(nve);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int them(NhanVienEntity add) {
        String query = "insert into nhan_vien(ma_nhan_vien , ten_nhan_vien , email, so_dien_thoai , chuc_vu, luong , gioi_tinh , avatar , is_delete) values(?,?,?,?,?,?,?,?,?);";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);)
        {
           ps.setInt(1 , add.getMaNhanVien());
           ps.setString(2 , add.getTenNhanVien());
           ps.setString(3 , add.getEmailNhanVien());
           ps.setString(4 , add.getSdtNhanVien());
           ps.setString(5 , add.getChucvu());
           ps.setInt(6, add.getLuong());
           ps.setBoolean(7,add.getGioitinh());
           ps.setString(8, add.getAvatar());
           ps.setBoolean(9 , false);
           return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int sua(NhanVienEntity fix) {
        String query = "update nhan_vien set ten_nhan_vien = ?,email = ?,so_dien_thoai = ?,chuc_vu = ? , luong = ? , gioi_tinh = ? , avatar = ? where ma_nhan_vien= ?";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query))  {
            ps.setString(1,fix.getTenNhanVien());
            ps.setString(2,fix.getEmailNhanVien());
            ps.setString(3,fix.getSdtNhanVien());
            ps.setString(4,fix.getChucvu());
            ps.setInt(5,fix.getLuong());
            ps.setBoolean(6,fix.getGioitinh());
            ps.setString(7,fix.getAvatar());
            ps.setInt(8,fix.getMaNhanVien());
            return ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int xoa(NhanVienEntity delete) {
        return 0;
    }

    @Override
    public NhanVienEntity search(int id) {
        String query = "select * from nhan_vien where ma_nhan_vien= ? and is_delete = 0";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);) {
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new NhanVienEntity(rs.getInt("ma_nhan_vien"),rs.getString("ten_nhan_vien"),rs.getString("email"),rs.getString("so_dien_thoai"),rs.getString("chuc_vu"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static boolean updateThemMaNguoiDungChoNhanVienSauKhiTaoTaiKhoan(int manguoidung,int manv) {
        String query = "update nhan_vien\n" +
                "set ma_nguoi_dung = ?\n" +
                "where ma_nhan_vien = ?";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);) {
            prs.setInt(1,manguoidung);
            prs.setInt(2,manv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hidden_delete(NhanVienEntity delete) {
        String queryNhanVien = "UPDATE nhan_vien SET is_delete = 1 WHERE ma_nhan_vien = ?";
        String queryNguoiDung = "UPDATE nguoi_dung SET is_delete = 1 WHERE ma_nguoi_dung = ?";
        String queryTaiKhoan = "UPDATE tai_khoan SET is_delete = 1 WHERE ma_nguoi_dung = ?";
        String queryXoaCung = "DELETE FROM nhan_vien WHERE ma_nhan_vien = ?";
        try (Connection c = DatabasesConfig.getConnection()) {
            c.setAutoCommit(false);

            try {
                // Lấy ma_nguoi_dung từ nhan_vien
                String queryGetMaNguoiDung = "SELECT ma_nguoi_dung FROM nhan_vien WHERE ma_nhan_vien = ?";
                int maNguoiDung;

                try (PreparedStatement ps = c.prepareStatement(queryGetMaNguoiDung)) {
                    ps.setInt(1, delete.getMaNhanVien());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        maNguoiDung = rs.getInt("ma_nguoi_dung");
                        if (maNguoiDung == 0) {
                            //throw new Exception("Nhân viên chưa có tài khoản");
                            try(Connection connection = DatabasesConfig.getConnection();
                                PreparedStatement pre = connection.prepareStatement(queryXoaCung);) {
                                pre.setInt(1,delete.getMaNhanVien());
                                int res = pre.executeUpdate();
                                if(res > 0){
                                    c.commit();
                                    return true;
                                }else{
                                    c.rollback();
                                    return false;
                                }
                            }catch (Exception e) {
                                e.printStackTrace();
                                return false;
                            }
                        }
                    } else {
                        throw new Exception("Không tìm thấy nhân viên");
                    }
                }

                // Thực hiện xóa
                try (PreparedStatement psNhanVien = c.prepareStatement(queryNhanVien);
                     PreparedStatement psNguoiDung = c.prepareStatement(queryNguoiDung);
                     PreparedStatement psTaiKhoan = c.prepareStatement(queryTaiKhoan)) {

                    psNhanVien.setInt(1, delete.getMaNhanVien());
                    psNguoiDung.setInt(1, maNguoiDung);
                    psTaiKhoan.setInt(1, maNguoiDung);

                    int resultNhanVien = psNhanVien.executeUpdate();
                    int resultNguoiDung = psNguoiDung.executeUpdate();
                    int resultTaiKhoan = psTaiKhoan.executeUpdate();

                    if (resultNhanVien > 0 && resultNguoiDung > 0 && resultTaiKhoan > 0) {
                        c.commit();
                        return true;
                    } else {
                        c.rollback();
                        return false;
                    }
                }

            } catch (Exception e) {
                c.rollback();
                e.printStackTrace();
                return false;
            } finally {
                c.setAutoCommit(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}