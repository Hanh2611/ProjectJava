package org.projects.entity;


public class SanPhamEntity {
    private int id;
    private String tenSanPham;
    private String phanLoai;
    private String donVi;
    private double giaBan;
    private double soLuongTon;
    private String quyCach;
    private String hinhAnh;

    public SanPhamEntity(int id, String tenSanPham, String phanLoai, String donVi, double giaBan, double soLuongTon, String quyCach, String hinhAnh) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.phanLoai = phanLoai;
        this.donVi = donVi;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.quyCach = quyCach;
        this.hinhAnh = hinhAnh;
    }

    public int getId() {
        return id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public double getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(double soLuongTon) {
        this.soLuongTon = soLuongTon;
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
