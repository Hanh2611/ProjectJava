package org.projects.entity;

public class SanPham {
    private int id;
    private String tenSanPham;
    private String loaiSanPham;
    private double soLuongTon;
    private double giaBan;
    private String donVi;
    private String quyCach;
    private String hinhAnh;

    public SanPham() {}

    public SanPham(int id, String tenSanPham, String loaiSanPham, double soLuongTon, double giaBan, String donVi, String quyCach, String hinhAnh) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.loaiSanPham = loaiSanPham;
        this.soLuongTon = soLuongTon;
        this.giaBan = giaBan;
        this.donVi = donVi;
        this.quyCach = quyCach;
        this.hinhAnh = hinhAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
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

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getQuyCach() {
        return quyCach;
    }

    public void setQuyCach(String quyCach) {
        this.quyCach = quyCach;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
