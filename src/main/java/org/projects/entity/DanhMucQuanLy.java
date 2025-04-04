package org.projects.entity;

public class DanhMucQuanLy {
    private String ten_danh_muc_quan_ly;
    private int ma_danh_muc_quan_ly;
    public DanhMucQuanLy(String ten_danh_muc_quan_ly, int ma_danh_muc_quan_ly) {
        this.ma_danh_muc_quan_ly = ma_danh_muc_quan_ly;
        this.ten_danh_muc_quan_ly = ten_danh_muc_quan_ly;
    }

    public String getTen_danh_muc_quan_ly() {
        return ten_danh_muc_quan_ly;
    }

    public void setTen_danh_muc_quan_ly(String ten_danh_muc_quan_ly) {
        this.ten_danh_muc_quan_ly = ten_danh_muc_quan_ly;
    }

    public int getMa_danh_muc_quan_ly() {
        return ma_danh_muc_quan_ly;
    }

    public void setMa_danh_muc_quan_ly(int ma_danh_muc_quan_ly) {
        this.ma_danh_muc_quan_ly = ma_danh_muc_quan_ly;
    }
}


