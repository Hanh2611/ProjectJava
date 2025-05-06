package org.projects.entity;

public class NguoiDungEntity {
    private int maNguoiDung;
    private String loaiNguoiDung;
    private String tenNguoiDung;

    public NguoiDungEntity() {
        loaiNguoiDung = "khach_hang";
    }

    public NguoiDungEntity(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
        loaiNguoiDung = "khach_hang";
    }

    public NguoiDungEntity(int maNguoiDung, String loaiNguoiDung) {
        this.maNguoiDung = maNguoiDung;
        this.loaiNguoiDung = loaiNguoiDung;
    }

    public NguoiDungEntity(String loaiNguoiDung,String tenNguoiDung) {
        this.loaiNguoiDung = loaiNguoiDung;
        this.tenNguoiDung = tenNguoiDung;
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
}
