package org.projects.entity;


import org.projects.entity.Enum.QuyCach;

public class SanPhamEntity {
    private int id;
    private String tenSanPham;
    private DanhMucSanPhamEntity phanLoai;
    private String donVi;
    private double giaBan;
    private double soLuongTon;
    private QuyCach quyCach;
    private String hinhAnh;
    private boolean trangThai;

    public SanPhamEntity() {
    }

    public SanPhamEntity(int id, String tenSanPham, DanhMucSanPhamEntity phanLoai, String donVi, double giaBan, double soLuongTon, QuyCach quyCach, String hinhAnh) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.phanLoai = phanLoai;
        this.donVi = donVi;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.quyCach = quyCach;
        this.hinhAnh = hinhAnh;
        this.trangThai = true;
    }

    public SanPhamEntity(String tenSanPham, DanhMucSanPhamEntity phanLoai, String donVi, double giaBan, double soLuongTon, QuyCach quyCach, String hinhAnh, boolean trangThai) {
        this.tenSanPham = tenSanPham;
        this.phanLoai = phanLoai;
        this.donVi = donVi;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.quyCach = quyCach;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }

    public SanPhamEntity(int id, String tenSanPham, DanhMucSanPhamEntity phanLoai, String donVi, double giaBan, double soLuongTon, QuyCach quyCach, String hinhAnh, boolean trangThai) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.phanLoai = phanLoai;
        this.donVi = donVi;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.quyCach = quyCach;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
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

    public DanhMucSanPhamEntity getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(DanhMucSanPhamEntity phanLoai) {
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

    public QuyCach getQuyCach() {
        return quyCach;
    }

    public void setQuyCach(QuyCach quyCach) {
        this.quyCach = quyCach;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public boolean isTrangThai() { return trangThai; }

    public void setTrangThai(boolean trangThai) { this.trangThai = trangThai; }
}
