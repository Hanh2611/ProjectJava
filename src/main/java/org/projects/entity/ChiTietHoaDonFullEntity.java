package org.projects.entity;

import java.sql.Timestamp;

public class ChiTietHoaDonFullEntity {
    private Timestamp ngayTao;
    private String tenNguoiLap;
    private int maNguoiLap;
    private int maHD;
    private int maKH;
    private String tenKH;
    private int maSP;
    private int soLuong;
    private String tenSP;
    private double giaBan;
    private double thanhTien;
    private double tongGiaTri;
    private String trangThai;
    public ChiTietHoaDonFullEntity(Timestamp ngayTao, String tenNguoiLap,int maNguoiLap
    , int maHD, int maKH, String tenKH, int maSP, int soLuong, String tenSP,double giaBan, double thanhTien) {}

    public ChiTietHoaDonFullEntity() {}

    public Timestamp getNgayTao() {
        return ngayTao;
    }
    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }
    public String getTenNguoiLap() {
        return tenNguoiLap;
    }
    public void setTenNguoiLap(String tenNguoiLap) {
        this.tenNguoiLap = tenNguoiLap;
    }
    public int getMaNguoiLap() {
        return maNguoiLap;
    }
    public void setMaNguoiLap(int maNguoiLap) {
        this.maNguoiLap = maNguoiLap;
    }
    public int getMaHD() {
        return maHD;
    }
    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }
    public int getMaKH() {
        return maKH;
    }   
    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }
    public String getTenKH() {
        return tenKH;
    }
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }
    public int getMaSP() {
        return maSP;
    }
    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }
    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public String getTenSP() {
        return tenSP;
    }
    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    public double getGiaBan() {
        return giaBan;
    }
    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
    public double getThanhTien() {
        return thanhTien;
    }
    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
    public double getTongGiaTri() {
        return tongGiaTri;
    }
    public void setTongGiaTri(double tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }
    public void setTrangThai(String trangThai) {this.trangThai = trangThai;}
    public String getTrangThai() {return trangThai;}
}
