package org.projects.entity;

public class ThongKeSanPhamEntity {
    private int maSanPham;
    private String tenSanPham;
    private String tenDanhMuc;
    private double soLuongTon;
    private double giaBan;
    private String trangThai;

    public ThongKeSanPhamEntity(int maSanPham, String tenSanPham, String tenDanhMuc, double soLuongTon, double giaBan, String trangThai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tenDanhMuc = tenDanhMuc;
        this.soLuongTon = soLuongTon;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public double getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(double soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
