package org.projects.DAO;

import org.projects.entity.NhanVienEntity;
import org.projects.entity.TaiKhoanEntity;
import org.projects.config.DatabasesConfig;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaiKhoanDAO implements ChucNangDAO<TaiKhoanEntity>{

    @Override
    public List<TaiKhoanEntity> showlist() {
        List<TaiKhoanEntity> result = new ArrayList<TaiKhoanEntity>();
        String query = "select * from tai_khoan where is_delete = 0";
        try (Connection connection = DatabasesConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TaiKhoanEntity tk = new TaiKhoanEntity(resultSet.getString("ten_dang_nhap"), resultSet.getString("mat_khau"), resultSet.getString("trang_thai"), resultSet.getInt("ma_nguoi_dung"));
                result.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

        @Override
        public int them(TaiKhoanEntity add) {
            String query = "insert into tai_khoan (ten_dang_nhap,ma_nguoi_dung,mat_khau,trang_thai,is_delete) values(?,?,?,?,?)";
            try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement prs = c.prepareStatement(query);) {
                prs.setString(1,add.getTenDangNhap());
                prs.setInt(2,add.getMaNguoiDung());
                prs.setString(3,add.getMatKhau());
//                System.out.println(add.getQuyenNguoiDung());
//                prs.setInt(4,add.getQuyenNguoiDung());
                prs.setString(4,add.getTrangThai());
                prs.setBoolean(5 , false);
                return prs.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
    }

    public int getManhomquyentheotennhomquyen(String tenquyen) {
        String query = "select ma_nhom_quyen\n" +
                "from nhom_quyen\n" +
                "where ten_nhom_quyen = ?";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);
      ) {
           prs.setString(1,tenquyen);
           ResultSet rs = prs.executeQuery();
           if(rs.next()) {
               return rs.getInt("ma_nhom_quyen");
           }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return -1;
    }

    @Override
    public int sua(TaiKhoanEntity fix) {
        String query = "UPDATE tai_khoan SET mat_khau = ?, ma_nguoi_dung = ?, trang_thai = ? WHERE ten_dang_nhap = ?";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement prs = c.prepareStatement(query)) {
            prs.setString(1, fix.getMatKhau());
            prs.setInt(2, fix.getMaNguoiDung());
//            prs.setInt(3, fix.getQuyenNguoiDung());
            prs.setString(4, fix.getTrangThai());
            prs.setString(5, fix.getTenDangNhap());
            return prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int xoa(TaiKhoanEntity delete) {
        String query = "DELETE FROM tai_khoan WHERE ten_dang_nhap = ?";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement prs = c.prepareStatement(query)) {
            prs.setString(1, delete.getTenDangNhap());
            return prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public TaiKhoanEntity search(int id) {
        return null;
    }

    public String getTenNguoiDung(int maNguoiDung) {
        String queryOnNhanVien = "select * from nhan_vien where ma_nguoi_dung = ?";
        String queryOnKhachHang = "select * from khach_hang where ma_nguoi_dung = ?";
        String result = "";
        try (Connection connection = DatabasesConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(queryOnNhanVien);) {
            preparedStatement.setInt(1, maNguoiDung);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("ten_nhan_vien");
            } else {
                try (Connection connection2 = DatabasesConfig.getConnection();
                PreparedStatement preparedStatement2 = connection2.prepareStatement(queryOnKhachHang);) {
                    preparedStatement2.setInt(1, maNguoiDung);
                    ResultSet resultSet2 = preparedStatement2.executeQuery();
                    if (resultSet2.next()) {
                        result = resultSet2.getString("ten_khach_hang");
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                    return result;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public String getLoaiNguoiDung(int maNguoiDung) {
        String query = "select loai_nguoi_dung from nguoi_dung where ma_nguoi_dung = ?";
        String result = "";
        try (Connection connection = DatabasesConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, maNguoiDung);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("loai_nguoi_dung");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public  List<String> gettrangthai() {
        List<String> result = new ArrayList<>();
        String query = "select DISTINCT tk.trang_thai\n" +
                "from tai_khoan tk";
        try(Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);
        ResultSet rs = prs.executeQuery()) {
            while (rs.next()) {
                result.add(rs.getString("trang_thai"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<NhanVienEntity> layDanhSachNhanVienChuaCoTaiKhoan() {
        List<NhanVienEntity> danhSach = new ArrayList<>();
        String query = "SELECT * FROM nhan_vien WHERE ma_nguoi_dung IS NULL;";

        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                danhSach.add(new NhanVienEntity(rs.getInt("ma_nhan_vien"),rs.getString("ten_nhan_vien"),rs.getString("email"),rs.getString("so_dien_thoai"),rs.getString("chuc_vu"),rs.getInt("luong"),rs.getBoolean("gioi_tinh")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    public  String layTenNhomQuyenTheoMaNguoiDung(int maNguoiDung) {
        String tenNhomQuyen = "";
        String query = "SELECT nq.ten_nhom_quyen FROM quyen_nguoi_dung qnd " +
                "JOIN nhom_quyen nq ON qnd.ma_nhom_quyen = nq.ma_nhom_quyen " +
                "WHERE qnd.ma_nguoi_dung = ?";

        try (Connection conn = DatabasesConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, maNguoiDung);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tenNhomQuyen = rs.getString("ten_nhom_quyen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenNhomQuyen;
    }

    public static boolean capnhatmatkhauvatrangthai(String tendangnhap,String matkhau,String trangthai) {
        String query = "update tai_khoan set mat_khau = ? , trang_thai = ? where ten_dang_nhap = ?";
        try (Connection c = DatabasesConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, matkhau);
            ps.setString(2, trangthai);
            ps.setString(3, tendangnhap);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static TaiKhoanEntity getTaiKhoanByTenDangNhap(String tenDangNhap) {
        String query = "select * from tai_khoan where ten_dang_nhap = ?";
        TaiKhoanEntity taiKhoan = new TaiKhoanEntity();
        try (Connection c = DatabasesConfig.getConnection();
        PreparedStatement prs = c.prepareStatement(query);) {
            prs.setString(1, tenDangNhap);
            taiKhoan.setTenDangNhap(tenDangNhap);
            ResultSet rs = prs.executeQuery();
            if (rs.next()) {
                taiKhoan.setMaNguoiDung(rs.getInt("ma_nguoi_dung"));
                taiKhoan.setMatKhau(rs.getString("mat_khau"));
                taiKhoan.setTrangThai(rs.getString("trang_thai"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taiKhoan;
    }

}
