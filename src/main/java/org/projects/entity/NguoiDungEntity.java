package org.projects.entity;

public class NguoiDungEntity {
    private int maNguoiDung;
    private String loaiNguoiDung;
    private String tenNguoiDung;
    private boolean is_delete;
    public NguoiDungEntity() {
        loaiNguoiDung = "khach_hang";
    }

    public NguoiDungEntity(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
        loaiNguoiDung = "khach_hang";
    }

    public NguoiDungEntity(int maNguoiDung, String loaiNguoiDung ) {
        this.maNguoiDung = maNguoiDung;
        this.loaiNguoiDung = loaiNguoiDung;
        this.is_delete = false;
    }


    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getLoaiNguoiDung() {
        return loaiNguoiDung;
    }

    public void setLoaiNguoiDung(String loaiNguoiDung) {
        this.loaiNguoiDung = loaiNguoiDung;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
    public boolean isIs_delete() {
        return is_delete;
    }
}
