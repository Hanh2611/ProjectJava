package org.projects.entity;

public class ThongkeDoanhThuEntity {
    private int mahoadon;
    private String tenkhachhang;
    private String ngaytaodon;
    private double tongtienhoadon;
    public ThongkeDoanhThuEntity(int mahoadon,String tenkhachhang,String ngaytaodon,double tongtienhoadon) {
        this.mahoadon = mahoadon;
        this.tenkhachhang = tenkhachhang;
        this.ngaytaodon = ngaytaodon;
        this.tongtienhoadon = tongtienhoadon;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getNgaytaodon() {
        return ngaytaodon;
    }

    public void setNgaytaodon(String ngaytaodon) {
        this.ngaytaodon = ngaytaodon;
    }

    public double getTongtienhoadon() {
        return tongtienhoadon;
    }

    public void setTongtienhoadon(double tongtienhoadon) {
        this.tongtienhoadon = tongtienhoadon;
    }
}
