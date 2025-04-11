package org.projects.entity;

import java.sql.Timestamp;

public class ChiTietPhieuNhapFullEntity {
    private Timestamp ngaylap;
    private int maNguoiLap;
    private String tenNguoiLap;
    private int maPN;
    private int maNCC;
    private String tenNCC;
    private int masp;
    private String tenSP;
    private double gia;
    private String donvi;
    private double thanhtien;
    private String quyCach;
    private int soLuong;
    public ChiTietPhieuNhapFullEntity(Timestamp ngaylap,int maNguoiLap,String tenNguoiLap,int maPN,int maNCC,String tenNCC,
                                  int masp, String tenSP, double gia, String donvi, double thanhtien, String quyCach,int soLuong){
        this.ngaylap = ngaylap;
        this.maNguoiLap = maNguoiLap;
        this.tenNguoiLap = tenNguoiLap;
        this.maPN = maPN;
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.masp = masp;
        this.tenSP = tenSP;
        this.gia = gia;
        this.donvi = donvi;
        this.thanhtien = thanhtien;
        this.quyCach = quyCach;
        this.soLuong = soLuong;
    }
    public ChiTietPhieuNhapFullEntity(){

    }
    public Timestamp getNgaylap() {
        return ngaylap;

    }
    public void setNgaylap(Timestamp ngaylap) {
        this.ngaylap = ngaylap;
    }
    public int getMaNguoiLap() {
        return maNguoiLap;
    }
    public void setMaNguoiLap(int maNguoiLap) {
        this.maNguoiLap = maNguoiLap;
    }
    public String getTenNguoiLap() {
        return tenNguoiLap;
    }
    public void setTenNguoiLap(String tenNguoiLap) {
        this.tenNguoiLap = tenNguoiLap;
    }
    public int getMaPN() {
        return maPN;
    }
    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }
    public int getMaNCC() {
        return maNCC;
    }
    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }
    public String getTenNCC() {
        return tenNCC;
    }
    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }
    public int getMasp() {
        return masp;
    }
    public void setMasp(int masp) {
        this.masp = masp;
    }
    public String getTenSP() {
        return tenSP;
    }
    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    public double getGia() {
        return gia;
    }
    public void setGia(double gia) {
        this.gia = gia;
    }
    public String getDonvi() {
        return donvi;
    }
    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }
    public double getThanhtien() {
        return thanhtien;
    }
    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }
    public String getQuyCach() {
        return quyCach;
    }
    public void setQuyCach(String quyCach) {
        this.quyCach = quyCach;
    }
    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }


}
