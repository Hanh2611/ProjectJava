package org.projects.entity;

import java.sql.Timestamp;

public class HoaDonEntity {
    private int maHoaDon;
    private int maNV;
    private int maKh;
    private Timestamp ngayTao;
    private double tongGiaTri;
    private String trangThai;
    private String tenNV;
    private String tenKh;
    private boolean isDelete;

    public HoaDonEntity(int maHoaDon, String tenNV,String tenKh, Timestamp ngayTao, double tongGiaTri, String trangThai) {
        this.maHoaDon = maHoaDon;
        this.tenNV = tenNV;
        this.tenKh = tenKh;
        this.ngayTao = ngayTao;
        this.tongGiaTri = tongGiaTri;
        this.trangThai = trangThai;
    }
    public HoaDonEntity() {}
    public int getMaHoaDon() {
        return maHoaDon;
    }
    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    public int getMaNV() {
        return maNV;
    }
    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }
    public int getMaKh() {
        return maKh;
    }
    public void setMaKh(int maKh) {
        this.maKh = maKh;
    }
    public Timestamp getNgayTao() {
        return ngayTao;
    }
    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }
    public double getTongGiaTri() {
        return tongGiaTri;
    }
    public void setTongGiaTri(double tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }
    public String getTrangThai() {
        return trangThai;
    }
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    public String getTenNV() {
        return tenNV;
    }
    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }
    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }
    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

}
