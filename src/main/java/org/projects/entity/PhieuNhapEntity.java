package org.projects.entity;

import java.sql.Timestamp;

public class PhieuNhapEntity {
    private int maPN;
    private int maNV;
    private int maNCC;
    private double tongGiaTri;
    private String tenNCC;
    private Timestamp ngayNhap;

    public PhieuNhapEntity( int maPN, int maNV, int maNCC, double tongGiaTri, String tenNCC) {
        this.maPN = maPN;
        this.maNV = maNV;
        this.maNCC = maNCC;
        this.tongGiaTri = tongGiaTri;
        this.tenNCC = tenNCC;
    }
    public int getMaPN() {return maPN;}
    public int getMaNV() {return maNV;}
    public int getMaNCC() {return maNCC;}
    public double getTongGiaTri() {return tongGiaTri;}
    public String getTenNCC() {return tenNCC;}
    public Timestamp getNgayNhap() {return ngayNhap;}
    public void setMaPN(int maPN) {this.maPN = maPN;}
    public void setMaNV(int maNV) {this.maNV = maNV;}
    public void setMaNCC(int maNCC) {this.maNCC = maNCC;}
    public void setTongGiaTri(double tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }
    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }
    public void setNgayNhap(Timestamp ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

}

