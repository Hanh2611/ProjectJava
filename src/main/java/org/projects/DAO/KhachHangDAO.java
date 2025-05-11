package org.projects.DAO;
import org.projects.config.DatabasesConfig;
import org.projects.entity.KhachHangEntity;
import org.projects.entity.NhanVienEntity;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO implements ChucNangDAO<KhachHangEntity> {
    @Override
    public List<KhachHangEntity> showlist() {
        List<KhachHangEntity> list = new ArrayList<>();
        String query = "SELECT * FROM khach_hang where is_delete = 0;";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                KhachHangEntity khe = new KhachHangEntity(rs.getInt("ma_khach_hang"), rs.getString("ten_khach_hang"), rs.getString("so_dien_thoai"), rs.getString("dia_chi") , rs.getString("avatar"));
                list.add(khe);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int them(KhachHangEntity add) {
        String query = "insert into khach_hang values(?,?,?,?,?,?,?);";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);){
            int newMa = getMaxKhachHang() + 1;
            ps.setInt(1 , newMa);
            ps.setInt(2 , 10);
            ps.setString(3 , add.getTen());
            ps.setString(4 , add.getSdt());
            ps.setString(5 , add.getDiaChi());
            ps.setString(6 , add.getAvatar());
            ps.setBoolean(7 , false);
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int sua(KhachHangEntity fix) {
        String query = "update khach_hang set ten_khach_hang = ? , so_dien_thoai = ? , dia_chi = ? , avatar = ? where ma_khach_hang = ?";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);){
            ps.setString(1 , fix.getTen());
            ps.setString(2 , fix.getSdt());
            ps.setString(3 , fix.getDiaChi());
            ps.setString(4 , fix.getAvatar());
            ps.setInt(5 , fix.getMa());
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int xoa(KhachHangEntity delete) {
        String query = "delete from khach_hang where ma_khach_hang = ?";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);) {
            ps.setInt(1,delete.getMa());
            return ps.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public KhachHangEntity search(int id) {
        String query = "select * from khach_hang where ma_khach_hang= ?";
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(query);) {
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new KhachHangEntity(rs.getInt("ma_khach_hang"),rs.getString("ten_khach_hang"),rs.getString("so_dien_thoai"),rs.getString("dia_chi"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public boolean delete(KhachHangEntity delete) {
        String queryKhachHang = "UPDATE khach_hang SET is_delete = 1 WHERE ma_khach_hang = ?";
        String queryNguoiDung = "UPDATE nguoi_dung SET is_delete = 1 WHERE ma_nguoi_dung = ?";
        String queryTaiKhoan = "UPDATE tai_khoan SET is_delete = 1 WHERE ma_nguoi_dung = ?";
        String queryXoaCung = "DELETE FROM khach_hang WHERE ma_khach_hang = ?";
        try (Connection c = DatabasesConfig.getConnection()) {
            c.setAutoCommit(false);

            try {
                // Lấy ma_nguoi_dung từ nhan_vien
                String queryGetMaNguoiDung = "SELECT ma_nguoi_dung FROM khach_hang WHERE ma_khach_hang = ?";
                int maNguoiDung;

                try (PreparedStatement ps = c.prepareStatement(queryGetMaNguoiDung)) {
                    ps.setInt(1, delete.getMa());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        maNguoiDung = rs.getInt("ma_nguoi_dung");
                        if (maNguoiDung == 0) {
                            //throw new Exception("Nhân viên chưa có tài khoản");
                            try(Connection connection = DatabasesConfig.getConnection();
                                PreparedStatement pre = connection.prepareStatement(queryXoaCung);) {
                                pre.setInt(1,delete.getMa());
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
                        throw new Exception("Không tìm thấy khách hàng");
                    }
                }

                // Thực hiện xóa
                try (PreparedStatement psKhachHang = c.prepareStatement(queryKhachHang);
                     PreparedStatement psNguoiDung = c.prepareStatement(queryNguoiDung);
                     PreparedStatement psTaiKhoan = c.prepareStatement(queryTaiKhoan)) {

                    psKhachHang.setInt(1, delete.getMa());
                    psNguoiDung.setInt(1, maNguoiDung);
                    psTaiKhoan.setInt(1, maNguoiDung);

                    int resultKhachHang = psKhachHang.executeUpdate();
                    int resultNguoiDung = psNguoiDung.executeUpdate();
                    int resultTaiKhoan = psTaiKhoan.executeUpdate();

                    if (resultKhachHang > 0 && resultNguoiDung > 0 && resultTaiKhoan > 0) {
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
    public int getMaxKhachHang() {
        String query = "SELECT MAX(ma_khach_hang) FROM khach_hang";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}