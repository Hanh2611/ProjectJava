package org.projects.entity;

public class ThongKePhieuNhapEntity {
    private int manhacungcap;
    private int maphieunhap;
    private String tennhanvien;
    private String tennhacungcap;
    private String ngaynhap;
    private double tonggiatri;
    private int soluong;

    public ThongKePhieuNhapEntity(int manhacungcap, String tennhacungcap, int soluong) {
        this.manhacungcap = manhacungcap;
        this.tennhacungcap = tennhacungcap;
        this.soluong = soluong;
    }

    public ThongKePhieuNhapEntity(String ngaynhap,int soluong, double tonggiatri) {
        this.ngaynhap = ngaynhap;
        this.soluong = soluong;
        this.tonggiatri = tonggiatri;
    }

    public int getManhacungcap() {
        return manhacungcap;
    }

    public void setManhacungcap(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }

    public String getTennhacungcap() {
        return tennhacungcap;
    }

    public void setTennhacungcap(String tennhacungcap) {
        this.tennhacungcap = tennhacungcap;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getMaphieunhap() {
        return maphieunhap;
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public double getTonggiatri() {
        return tonggiatri;
    }

    public void setTonggiatri(double tonggiatri) {
        this.tonggiatri = tonggiatri;
    }
}
