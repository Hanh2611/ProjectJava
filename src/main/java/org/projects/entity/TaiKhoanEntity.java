package org.projects.entity;

public class TaiKhoanEntity {
    private String tenDangNhap;
    private String matKhau;
    private String trangThai;
    private int maNguoiDung;
    private int quyenNguoiDung;
    private boolean is_delete;
    public TaiKhoanEntity() {
        this.trangThai = "hoat_dong";
        this.is_delete = false;
    }
    public TaiKhoanEntity(String tenDangNhap, String matKhau) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        trangThai = "hoat_dong";
        maNguoiDung = 0;
        quyenNguoiDung = 0;
        this.is_delete = false;
    }
    public TaiKhoanEntity(String tenDangNhap, String matKhau, String trangThai, int maNguoiDung) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.maNguoiDung = maNguoiDung;
        this.is_delete = false;
    }
    public TaiKhoanEntity(String tenDangNhap, String matKhau, String trangThai, int maNguoiDung, int quyenNguoiDung) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.maNguoiDung = maNguoiDung;
        this.quyenNguoiDung = quyenNguoiDung;
        this.is_delete = false;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public int getQuyenNguoiDung() {
        return quyenNguoiDung;
    }

    public void setQuyenNguoiDung(int quyenNguoiDung) {
        this.quyenNguoiDung = quyenNguoiDung;
    }

    public boolean isIs_delete() {
        return is_delete;
    }
    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
}
