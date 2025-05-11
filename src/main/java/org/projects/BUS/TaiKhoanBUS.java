package org.projects.BUS;

import org.projects.DAO.NguoiDungDAO;
import org.projects.DAO.NhanVienDao;
import org.projects.DAO.NhomQuyenDAO;
import org.projects.DAO.TaiKhoanDAO;
import org.projects.entity.NguoiDungEntity;
import org.projects.entity.NhanVienEntity;
import org.projects.entity.TaiKhoanEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaiKhoanBUS {
    public static List<TaiKhoanEntity> getListTaiKhoan() {
        return new TaiKhoanDAO().showlist();
    }

    public static String getTenNguoiDung(int maNguoiDung) {
        return new TaiKhoanDAO().getTenNguoiDung(maNguoiDung);
    }

    public static String getLoaiNguoiDung(int maNguoiDung) {
        return new TaiKhoanDAO().getLoaiNguoiDung(maNguoiDung);
    }

    public static List<String> getDanhsachLoainguoidung() {
        return new NguoiDungDAO().getdanhsach();
    }

    public static List<String> laytennhomquyen() {
        return new NhomQuyenDAO().getDanhsachtennhomquyen();
    }
    public static List<String> laytrangthai() {
        return new TaiKhoanDAO().gettrangthai();
    }

    public static boolean themtaikhoan(TaiKhoanEntity taikhoan) {
        return new TaiKhoanDAO().them(taikhoan) > 0;
    }

    public static int getmaquyentutenquyen(String tenquyen) {
        return new TaiKhoanDAO().getManhomquyentheotennhomquyen(tenquyen);
    }

    public static List<String> laydanhsachnv() {
        List<String> lst = new ArrayList<String>();
        TaiKhoanDAO dao = new TaiKhoanDAO();
        for(NhanVienEntity nvEntity : dao.layDanhSachNhanVienChuaCoTaiKhoan()) {
            lst.add(String.valueOf(nvEntity.getMaNhanVien()));
        }
        return lst;
    }

    public static HashMap<Integer,String> laymanhanvienvaten() {
        HashMap<Integer,String> hm = new HashMap<>();
        for(NhanVienEntity nvEntity : new TaiKhoanDAO().layDanhSachNhanVienChuaCoTaiKhoan()) {
            hm.put(nvEntity.getMaNhanVien(),nvEntity.getChucvu());
        }
        return hm;
    }

    public static String getTenQuyen(int manguoidung) {
        return new TaiKhoanDAO().layTenNhomQuyenTheoMaNguoiDung(manguoidung);
    }

    public static List<TaiKhoanEntity> search(String key,String word) {
        getListTaiKhoan();
        List<TaiKhoanEntity> lst = new ArrayList<>();
        word = word.toLowerCase();
        switch (key) {
            case "---":
                lst.addAll(getListTaiKhoan());
                break;
            case "tên đăng nhập":
                for(TaiKhoanEntity tk : getListTaiKhoan()) {
                    if(tk.getTenDangNhap().contains(word)) {
                        lst.add(tk);
                    }
                }
                break;
            case "mã người dùng":
                for(TaiKhoanEntity tk : getListTaiKhoan()) {
                    if(tk.getMaNguoiDung() == (Integer.parseInt(word))) {
                        lst.add(tk);
                    }
                }
                break;
            default:
                break;
        }
        return lst;
    }

    public static TaiKhoanEntity getTaiKhoan(String tenDangNhap) {
        return TaiKhoanDAO.getTaiKhoanByTenDangNhap(tenDangNhap);
    }
}
