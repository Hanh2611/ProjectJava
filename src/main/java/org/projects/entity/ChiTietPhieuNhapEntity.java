package org.projects.entity;

public class ChiTietPhieuNhapEntity {
    private int maPN;
    private int maSP;
    private int soLuong;
    private double giaNhap;
    private double thanhTien;

    public ChiTietPhieuNhapEntity(int maPN,int maSP, int soLuong, double giaNhap, double thanhTien) {
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.thanhTien = thanhTien;
    }

    public ChiTietPhieuNhapEntity(){
    }

    public int getMaPN() {
        return maPN;
    }
    public void setMaPN(int maPN) {
        this.maPN = maPN;
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
    public double getGiaNhap() {
        return giaNhap;
    }
    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }
    public double getThanhTien() {
        return thanhTien;
    }
    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }



}
