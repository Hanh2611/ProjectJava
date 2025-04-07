package org.projects.entity;

public class CapQuyen {
    private int ma_nhom_quyen, ma_danh_muc_quan_ly;
    private String hanh_dong;
    public CapQuyen() {}

    public CapQuyen(int ma_nhom_quyen, int ma_danh_muc_quan_ly, String hanh_dong) {
        this.ma_nhom_quyen = ma_nhom_quyen;
        this.ma_danh_muc_quan_ly = ma_danh_muc_quan_ly;
        this.hanh_dong = hanh_dong;
    }

    public int getMa_nhom_quyen() {
        return ma_nhom_quyen;
    }

    public void setMa_nhom_quyen(int ma_nhom_quyen) {
        this.ma_nhom_quyen = ma_nhom_quyen;
    }

    public int getMa_danh_muc_quan_ly() {
        return ma_danh_muc_quan_ly;
    }

    public void setMa_danh_muc_quan_ly(int ma_danh_muc_quan_ly) {
        this.ma_danh_muc_quan_ly = ma_danh_muc_quan_ly;
    }

    public String getHanh_dong() {
        return hanh_dong;
    }

    public void setHanh_dong(String hanh_dong) {
        this.hanh_dong = hanh_dong;
    }
}
