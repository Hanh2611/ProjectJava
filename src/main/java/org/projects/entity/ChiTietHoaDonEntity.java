package org.projects.entity;

public class ChiTietHoaDonEntity {
    private int maSP;
    private int maHD;
    private int soLuong;
    private double giaBan;
    private double thanhTien;
    public ChiTietHoaDonEntity(int maSP, int maHD, int soLuong, double giaBan, double thanhTien) {
        this.maSP = maSP;
        this.maHD = maHD;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = thanhTien;
    }
    public ChiTietHoaDonEntity(){}
    public int getMaSP() {
        return maSP;

    }
    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }
    public int getMaHD() {
        return maHD;
    }
    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }
    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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

}
