package org.projects.entity;

public class NguoiDung {
    private int maNguoiDung;
    private String loaiNguoiDung;

    public NguoiDung() {
        loaiNguoiDung = "khach_hang";
    }

    public NguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
        loaiNguoiDung = "khach_hang";
    }

    public NguoiDung(int maNguoiDung, String loaiNguoiDung) {
        this.maNguoiDung = maNguoiDung;
        this.loaiNguoiDung = loaiNguoiDung;
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
}
